package apo_polyroll.controller;

import apo_polyroll.APO_Polyroll;
import apo_polyroll.model.HumanPlayer;
import apo_polyroll.model.IAPlayer;
import apo_polyroll.model.Plateau;
import apo_polyroll.model.Plateau.Jeton;
import apo_polyroll.model.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static AnchorPane rootLayout;  
    
    @FXML GridPane grdPothellier;
    
    ImageView[][] imgPlateau;
    
    static {
        EMPTY_PICTURE = new Image("ressources/empty.png");
        WHITE_PICTURE = new Image("ressources/pion_blanc.png");
        BLACK_PICTURE = new Image("ressources/pion_noir.png");
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
            
            Jeton newToken = physicOthellier.getToken(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
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
        }
    }
    
    
    /* -------------------------------------------------------------------------
    *                         GAME MANAGEMENT 
    * -------------------------------------------------------------------------- */
    
    private Plateau physicOthellier;
    private boolean endOfGame;
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
        
        endOfGame = false;
        physicOthellier = new Plateau();
        player = new HumanPlayer();
        computer = new IAPlayer();
        
        updateOthellier();
    }
    
}
