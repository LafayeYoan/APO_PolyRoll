package apo_polyroll.controller;

import apo_polyroll.APO_Polyroll;
import apo_polyroll.model.Plateau;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    //Controller
    private Plateau physicOthellier;
    private static AnchorPane rootLayout;
    private OthelloFXMLController othelloFXMLController;
    
    // Vue 
    @FXML GridPane othellier;
    ImageView[][] plateau;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        plateau = new ImageView [8][8];
        for(int i = 0 ; i< 8; i++){
            for(int j = 0 ; j < 8; j++){
                ImageView image = new ImageView();
                plateau[i][j] = image;
                othellier.add(image,i,j);
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
    
    /* Run the game <3 */
    public void run() {
        
        initializeGame();
        
        //todo : run the game
        
    }
    
    private void initializeGame() {
        physicOthellier = new Plateau();
        System.out.println("GAME ON !");
        //todo : new players 
    }
    
}
