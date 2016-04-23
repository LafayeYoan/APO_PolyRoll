package apo_polyroll.controller;

import apo_polyroll.APO_Polyroll;
import apo_polyroll.model.BasicIAPlayer;
import apo_polyroll.model.HumanPlayer;
import apo_polyroll.model.IAPlayer;
import apo_polyroll.model.MinMaxIAPlayer;
import apo_polyroll.model.Plateau;
import apo_polyroll.model.Plateau.Jeton;
import static apo_polyroll.model.Plateau.PLATEAU_SIZE;
import apo_polyroll.utils.Position;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

    private static final Image EMPTY_PICTURE;
    private static final Image WHITE_PICTURE;
    private static final Image BLACK_PICTURE;
    private static Image PLAYABLE_PICTURE;
    private static AnchorPane rootLayout;  
    
    @FXML GridPane grdPothellier;
    @FXML Label lblTokenPlayer;
    @FXML Label lblNbBlack;
    @FXML Label lblNbWhite;
    @FXML TextArea txtHistory;
    
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
        
        imgPlateau = new ImageView [PLATEAU_SIZE][PLATEAU_SIZE];
        
        for(int i = 0 ; i < PLATEAU_SIZE; i++){
            
            for(int j = 0 ; j < PLATEAU_SIZE; j++){
                
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
                        
                        //Tour du joueur
                        int x = GridPane.getColumnIndex(spot);
                        int y = GridPane.getRowIndex(spot);
                        Position p = new Position(x,y);
                        
                        if(!playableSpot.contains(p)){
                            txtHistory.setText(txtHistory.getText() + "[Player] Vous ne pouvez pas jouer ici !\n");
                            return;
                        }
                        
                        txtHistory.setText(txtHistory.getText() + "[Player] Le jeton x: "+ (p.x + 1) +" y:"+ (p.y + 1) +" a été joué.\n");
                        physicOthellier.addAndReverse(p, player.getToken());
                        updateOthellier();
                        
                        //Tour de l'ordinateur
                        Position jeuIA = computer.getChoice(physicOthellier);
                        
                        //si l'IA ne peut pas jouer : fin du jeu
                        if(jeuIA == null) {
                            
                            txtHistory.setText(txtHistory.getText() + "IA BLOQUEE : FIN DU JEU !");                            
                        } else {
                        
                            txtHistory.setText(txtHistory.getText() + "[Ordinateur] Le jeton x:"+ (jeuIA.x + 1) +" y:"+ (jeuIA.y + 1) +" a été joué.\n");
                            physicOthellier.addAndReverse(jeuIA, computer.getToken());
                            playableSpot = player.getPlayableSpots(physicOthellier);
                            updateOthellier();
                        }
                        
                        //vérifie si le jeu est fini
                        if(physicOthellier.isFull()) {
                           txtHistory.setText(txtHistory.getText() + "JEU TERMINE ! MERCI D'AVOIR JOUE ! :) \n");
                           //todo : rajouter des boutons pour rejouer
                        }
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
                    ((ImageView)node).setImage(EMPTY_PICTURE);
            }
            
            Position p = new Position (x,y);
            
            if(playableSpot.contains(p)){
                ((ImageView)node).setImage(PLAYABLE_PICTURE);
            }
        }
        
        lblTokenPlayer.setText(player.getToken().toString());
        lblNbBlack.setText(physicOthellier.getNumberOfToken(Jeton.BLACK) + "");
        lblNbWhite.setText(physicOthellier.getNumberOfToken(Jeton.WHITE) + "");
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
    private HumanPlayer player;
    private IAPlayer computer;

    /***
     * Run the Game : 
     * Initialize, update and manage the game
     * (Manage the imgPlateau with the physicOthellier)
     */
    public void runTheGame() {
        
        physicOthellier = new Plateau();
        player = new HumanPlayer();
        
        //computer = new BasicIAPlayer();
        computer = new MinMaxIAPlayer();
        
        playableSpot = player.getPlayableSpots(physicOthellier);
        
        updateOthellier();        
    }
    
}
