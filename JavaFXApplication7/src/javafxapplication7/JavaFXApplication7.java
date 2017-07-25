/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author chand
 */
public class JavaFXApplication7 extends Application {
    private Stage stage;
    private FXMLDocumentController fx;
    private FXMLDocumentController1 fx1;
 //  private FXMLDocumentController db;
    
    @Override
    public void start(Stage stage){
        this.stage = stage;
       mainwindow();
      // fx1.setController(fx);
    // secondwindow();
    }
    
    
    
    
    public void mainwindow(){
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplication7.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*
    public void secondwindow(){
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Secondwindow.fxml"));
            
            Scene scene = new Scene(root);
            Stage Secondstage = new Stage();
            Secondstage.setScene(scene);
            Secondstage.show();
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplication7.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    */
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
