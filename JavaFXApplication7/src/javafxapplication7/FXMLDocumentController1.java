/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chand
 */
public class FXMLDocumentController1 implements Initializable {
@FXML 
  Stage stage;
private PreparedStatement pst=null;
private Connection con=null;
private ResultSet rs=null;
 @FXML private FXMLDocumentController fd;
    @FXML
    private TextField Firstnametextfield;
    @FXML
    private TextField Lastnametext;
    @FXML
    private TextField Phonenumbertextfield;
    @FXML
    private TextField Citytextfield;
    @FXML
    private TextField Postcodetextfield;
      public ComboBox<Integer> comboboxid;
    @FXML
    private Button saveid;
    @FXML
    private Button closebutton;
    
  
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        con= dba.Dbconnection.pmtconnection();
      //  fd= new FXMLDocumentController();
        
        
    }  
    public void setController(FXMLDocumentController cont){
        this.fd=cont;
    }

    @FXML
    private void newbuttonAction(ActionEvent event) throws SQLException {
        System.out.println("working");
        
           //  mainwindow();
           
            if(event.getSource()==saveid){
                
            if(Citytextfield.getText().equals("")||Firstnametextfield.getText().equals("")||Lastnametext.getText().equals("")||Phonenumbertextfield.getText().equals("")||Postcodetextfield.getText().equals("")){
               
                Alert alert= new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Erro message");
                alert.setContentText("Please fill all fields");
                alert.showAndWait();
            }
            else{
           String sql="insert into book(Firstname,Lastname,Phonenumber,City,Postcode) Values(?,?,?,?,?)";
           String First= Firstnametextfield.getText();
           String Last= Lastnametext.getText();
           String Phone= Phonenumbertextfield.getText();
           String city=Citytextfield.getText();
           String post=Postcodetextfield.getText();
           try {
               pst=con.prepareStatement(sql);
               pst.setString(1, First);
                pst.setString(2, Last);
                 pst.setString(3, Phone);
                  pst.setString(4, city);
                   pst.setString(5, post);
                   int i=pst.executeUpdate();
                   if(i==1){
                       Alert alert= new Alert(AlertType.CONFIRMATION);
             alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation");
                alert.setContentText("Thank you");
                  alert.showAndWait();
                  //     setCellTable();
                  //   loadDatafromDatabase();
                   }
                  // stage.close(); 
           } catch (SQLException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
              Alert alert= new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
                alert.setHeaderText("Server Not Found");
                alert.setContentText("Please try again");
                  alert.showAndWait();
                  
           }
           
           finally{
               pst.close();
           }
            Clearall(); 
           
            //fd.setCellTable();
            //fd.loadDatafromDatabase();
          }
            }
         if(event.getSource()==closebutton){
            stage=(Stage) closebutton.getScene().getWindow();
           stage.close();   
             // fd.refrshtable();
           }
        
       
    }
            
            
      //  stage=(Stage) closebutton.getScene().getWindow();
        //   stage.close(); 
    /*
     public void mainwindow(){
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(JavaFXApplication7.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    
 public void Clearall(){
         Firstnametextfield.clear();
         Lastnametext.clear();
         Phonenumbertextfield.clear();
         Citytextfield.clear();
         Postcodetextfield.clear();
         
     }
 
    
 
}
