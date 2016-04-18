package apo_polyroll;

import apo_polyroll.controller.OthelloFXMLController;
import apo_polyroll.controller.OthelloMenuFXMLController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Yoan LAFAYE DE MICHEAUX - Sacha LHOPITAL
 */
public class APO_Polyroll extends Application {
    
    private Stage primaryStage;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PolyRoll");

        OthelloMenuFXMLController.initRootLayout(primaryStage);
    }
    
     

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
