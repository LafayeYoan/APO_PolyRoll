/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apo_polyroll.controller;

import apo_polyroll.APO_Polyroll;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Darkos
 */
public class OthelloMenuFXMLController implements Initializable{
    @FXML MenuButton cbLevelIA;
    private static AnchorPane rootLayout;
    public static Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        
        
    }
    
    /**
     * Initializes the root layout.
     */
    public static void initRootLayout(Stage primaryStage) {
        try {
            OthelloMenuFXMLController.primaryStage = primaryStage;
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(APO_Polyroll.class.getResource("view/OthelloMenuFXML.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void handleBtnPlayClick(){
        OthelloFXMLController.initRootLayout(primaryStage);
    }
}
