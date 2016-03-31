package apo_polyroll.controller;

import apo_polyroll.APO_Polyroll;
import apo_polyroll.model.Plateau;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Darkos
 */
public class OthelloFXMLController implements Initializable {

    /* -------------------------------------------------------------------------
    *                             VIEW MANAGEMENT  
    * -------------------------------------------------------------------------- */
    
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
        
        imgPlateau = new ImageView [8][8];
        
        for(int i = 0 ; i< 8; i++){
            
            for(int j = 0 ; j < 8; j++){
                
                ImageView image = new ImageView();
                image.setFitHeight(69);
                image.setFitWidth(69);
                image.setImage(EMPTY_PICTURE);
                
                imgPlateau[i][j] = image;
                grdPothellier.add(image,i,j);
                
                GridPane.setValignment(image, VPos.CENTER);
                GridPane.setHalignment(image, HPos.CENTER);
            }
        }
        run();
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
    
    
    
    /* -------------------------------------------------------------------------
    *                         GAME MANAGEMENT 
    * -------------------------------------------------------------------------- */
    
    private Plateau physicOthellier;
    
    /* Run the game <3 */
    public void run() {
        
        initializeGame();
        
        //todo : run the game
        
    }
    
    private void initializeGame() {
        physicOthellier = new Plateau();
        //todo : new players 
    }
    
}
