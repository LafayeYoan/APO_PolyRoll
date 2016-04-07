package apo_polyroll.controller;

import apo_polyroll.APO_Polyroll;
import apo_polyroll.model.HumanPlayer;
import apo_polyroll.model.IAPlayer;
import apo_polyroll.model.Plateau;
import apo_polyroll.model.Plateau.Jeton;
import apo_polyroll.model.Player;
import apo_polyroll.utils.Position;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Manage the view JavaFX and the game 
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class OthelloFXMLController implements Initializable {
   
    /* -------------------------------------------------------------------------
    *                             VIEW MANAGEMENT  
    * -------------------------------------------------------------------------- */
    
    public static final int IMG_SIZE = 69;

    private static Image EMPTY_PICTURE;
    private static Image WHITE_PICTURE;
    private static Image BLACK_PICTURE;
    private static Image PLAYABLE_PICTURE;
    private static AnchorPane rootLayout;  
    
    @FXML GridPane grdPothellier;
    
    ImageView[][] imgPlateau;
    
    static {
        EMPTY_PICTURE = new Image("ressources/empty.png");
        WHITE_PICTURE = new Image("ressources/pion_blanc.png");
        BLACK_PICTURE = new Image("ressources/pion_noir.png");
        PLAYABLE_PICTURE = new Image("ressources/pion_jouable.png");
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        imgPlateau = new ImageView [Plateau.PLATEAU_SIZE][Plateau.PLATEAU_SIZE];
        
        for(int i = 0 ; i< 8; i++){
            
            for(int j = 0 ; j < 8; j++){
                
                ImageView image = new ImageView();
                image.setFitHeight(IMG_SIZE);
                image.setFitWidth(IMG_SIZE);
                image.setImage(EMPTY_PICTURE);
                imgPlateau[i][j] = image;
                
                grdPothellier.add(image,i,j);
                
                GridPane.setValignment(image, VPos.CENTER);
                GridPane.setHalignment(image, HPos.CENTER);
                
                //add eventListener
                image.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        Object sourceObject = event.getSource();
                        if(!sourceObject.getClass().equals(ImageView.class)){
                            return;
                        }
                        ImageView spot = (ImageView)sourceObject;
                        //spot position
                        int x = GridPane.getColumnIndex(spot);
                        int y = GridPane.getRowIndex(spot);
                        
                        Position p = new Position(x,y);
                        if(!playableSpot.contains(p)){
                            System.out.println("Le jeton x:"+x+" y:"+y+" a été clické, mais n'est pas jouable");
                            return;
                        }
                        System.out.println("Le jeton x:"+x+" y:"+y+" a été clické, mise a jour du plateau");
                        
                        //Mise a jour du plateau
                        //physicOthellier.addToken(p,player.getToken());
                        
                        
                        //Position jeuIA = computer.getChoice();
                        //physicOthellier.addToken(jeuIA,computer.getToken());
                        playableSpot = player.getPlayableSpots(physicOthellier);
                        updateOthellier();
                        
                        
                    }

                });
            }
        }
        runTheGame(); 
    } 
    
    /**
     * Initializes the root layout.
     */
    public static void initRootLayout(Stage primaryStage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(APO_Polyroll.class.getResource("view/OthelloFXML.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /***
     * Update the view based on the physic othellier.
     * Check every node of the Grid Pane (i.e : every square of the othellier)
     * and replace the token if necessary.
     */
    private void updateOthellier() {
        
        for (Node node : grdPothellier.getChildren()) {
            
            if(GridPane.getColumnIndex(node) == null){
                //If null : the node is not a square
                continue;
            }
            
            int x = GridPane.getColumnIndex(node);
            int y = GridPane.getRowIndex(node);
            Jeton newToken = physicOthellier.getToken(x,y);
            switch (newToken) {
                case BLACK:                        
                    ((ImageView)node).setImage(BLACK_PICTURE);
                    break;

                case WHITE:
                    ((ImageView)node).setImage(WHITE_PICTURE);
                    break;

                default: 
                    //Do nothing, token is already empty by default
                    //((ImageView)node).setImage(EMPTY_PICTURE);
            }
            Position p = new Position (x,y);
            if(playableSpot.contains(p)){
                ((ImageView)node).setImage(PLAYABLE_PICTURE);
            }
        }
    }
    
    /***
     * Uptate the listener
     * 
     */
    private void updateListenner() {
        ArrayList <apo_polyroll.utils.Position> playableSpot = player.getPlayableSpots(physicOthellier);
        for (Node node : grdPothellier.getChildren()) {
            
            if(GridPane.getColumnIndex(node) == null){
                //If null : the node is not a square
                continue;
            }
            
            ImageView spot = (ImageView)node;
            //remove all listenner
            
            //spot.removeEventHandler(EventType.ROOT, spot.get);
            
        }
    }
    
    /* -------------------------------------------------------------------------
    *                         GAME MANAGEMENT 
    * -------------------------------------------------------------------------- */
    
    private Plateau physicOthellier;
    private ArrayList <apo_polyroll.utils.Position> playableSpot;
    private Player player;
    private Player computer;

    /***
     * Run the Game : 
     * Initialize, update and manage the game
     * (Manage the imgPlateau with the physicOthellier)
     */
    public void runTheGame() {
        
        initializeGame();
        
    }
    
    /***
     * Setup the othellier and players for the game
     */
    private void initializeGame() {
        
        physicOthellier = new Plateau();
        player = new HumanPlayer();
        computer = new IAPlayer();
        
        playableSpot = player.getPlayableSpots(physicOthellier);
        
        updateOthellier();
    }
    
}
