/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apo_polyroll;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Darkos
 */
public class OthelloFXMLController implements Initializable {

    @FXML GridPane othellier;
    
    ImageView[][] plateau;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        plateau = new ImageView [8][8];
        for(int i = 0 ; i< 8; i++){
            for(int j = 0 ; j < 8; j++){
                ImageView image = new ImageView();
                plateau[i][j] = image;
                othellier.add(image,i,j);
            }
        }
    }    
    
}
