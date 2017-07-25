/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication7;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author chand
 */
public class FXMLDocumentController implements Initializable {
    
   
 private PreparedStatement pst=null;
private Connection con=null;
private ResultSet rs=null;
private TextField Firstnametextfield;

 private TextField Lastnametext;
 private TextField Phonenumbertextfield;
private TextField Citytextfield;
private TextField Postcodetextfield;
private FXMLDocumentController1 cont1;

    
     @FXML  private Button newbutton;
     private Boolean okcliked = false;
     
    private Button saveid;
    private Button closebutton;
   Stage Secondstage = new Stage();
   Stage stage;
  Parent root;
   Scene scene;
   Person person;
    
   
   @FXML  private TableView<Person> tableview;
   // @FXML private GridPane grid;
    @FXML
    private TableColumn<Person, String> Firstnamecoloumn;
    @FXML
    private TableColumn<Person, String> Lastnamecoloumn;
    @FXML
    private Label firstnamelabel;
    @FXML
    private Label lastnamelabel;
    @FXML
    private Label phonelabel;
    @FXML
    private Label citylabel;
    @FXML
    private Label postcodelabel;
    @FXML
    private AnchorPane parentroot;
    @FXML
    private GridPane grid;
    @FXML
    private Button editbutton;
    @FXML
    private Button delete;

     @FXML private TableColumn<Person, String> Pid;
    @FXML
    public ComboBox<Integer> comboboxid;
   private ObservableList<Person> data = FXCollections.observableArrayList();
 private ObservableList<Integer> combodata= FXCollections.observableArrayList();
    @FXML
    private TextField firstnamecombofield;
    @FXML
    private TextField LastnameCombofield;
    @FXML
    private TextField Phonecombofieldd;
    @FXML
    private TextField Citycombofield;
    @FXML
    private TextField Postcodecombofield;
    @FXML
    private TextField id;
    @FXML
    private Button Refreshbutton;

 
    @FXML
     public void newbuttonAction(ActionEvent event) throws SQLException, IOException   {
      //   AnchorPane pane = FXMLLoader.load(getClass().getResource("Secondwindow.fxml"));
       //  parentroot.getChildren().setAll(pane);
     
       if(event.getSource()==newbutton){
           
           stage=(Stage)newbutton.getScene().getWindow();
           try {
             root = FXMLLoader.load(getClass().getResource("Secondwindow.fxml"));
           } catch (IOException ex) {
               Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("new button not is working");
           }
           
             scene = new Scene(root);
          
            Secondstage.setScene(scene);
            Secondstage.show();
             System.out.println("new button is working");
                 }
       
       
     
     }
     
      @FXML
      public void deltebuttonAction(ActionEvent event){
            System.out.println("Delte button is working");
             if(Citycombofield.getText().equals("")|| firstnamecombofield.getText().equals("")||LastnameCombofield.getText().equals("")|| Phonecombofieldd.getText().equals("")||Postcodecombofield.getText().equals("")){
               
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Erro message");
                alert.setContentText("You did not select serial number");
                alert.showAndWait();
            }
         else{
           
     try {
         String query= "Delete from book where Pid = ?";
         pst= con.prepareStatement(query);
        pst.setString(1, id.getText());
         pst.executeUpdate();
         pst.close();
       
     } catch (SQLException ex) {
          
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("Delte button is not working");
     }
         
       Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                
                alert.setHeaderText("Delete");
                alert.setContentText("Deleted from Phonebook");
                alert.showAndWait();
          
               refrshtable();
           
       }
      }
     
     
     
    
     
     
  
   
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      con= dba.Dbconnection.pmtconnection();
     cont1= new FXMLDocumentController1();
      cont1.setController(this);
     
      setCellTable();
    loadDatafromDatabase();
     fillcombobox();
    comboboxid.setItems(combodata);
    //  refrshtable();
     // change();
    } 
   
    
   
    
    public void setCellTable(){
        Firstnamecoloumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		Lastnamecoloumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
                Pid.setCellValueFactory(new PropertyValueFactory<Person, String>("Id"));
              
		tableview.getSelectionModel().selectedItemProperty().addListener(
				 (observable, oldValue,newValue)->showalldetails(newValue)
				 );
                           
            System.out.println("Working very well setcelltable function");
    }
    public void showalldetails(Person person)
	{
		
		firstnamelabel.setText(person.getFirstname());
		lastnamelabel.setText(person.getLastname());
		phonelabel.setText(person.getPhonenumber());
		citylabel.setText(person.getCity());
		postcodelabel.setText(person.getPostcode());
               
		 
	}
    
    public void loadDatafromDatabase(){
       try {
           pst=con.prepareStatement("Select * from book");
           rs=pst.executeQuery();
           while(rs.next()){
               data.add(new Person(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
           }
       } catch (SQLException ex) {
           Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Data not loading");
       }
         tableview.setItems(data);
         
}
    
     public void fillcombobox(){
         
       
     try {
         
         pst=con.prepareStatement("Select Pid from book");
         rs=pst.executeQuery();
         while(rs.next()){
               combodata.add(rs.getInt(1));
           }
        pst.close();
         rs.close();
     } catch (SQLException ex) {
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
         
    }


   @FXML  private void ComboAction(ActionEvent event) { 
     try {
         String query= "Select * from book where Pid=?";
         pst=con.prepareStatement(query);
         pst.setInt(1,comboboxid.getSelectionModel().getSelectedItem());
         rs=pst.executeQuery();
          while(rs.next()){
             id.setText(rs.getInt(1)+"");
              firstnamecombofield.setText(rs.getString(2));
              LastnameCombofield.setText(rs.getString(3));
             Phonecombofieldd.setText(rs.getString(4));
               Citycombofield.setText(rs.getString(5));
               Postcodecombofield.setText(rs.getString(6));
               
           }
          pst.close();
          rs.close();
     } catch (SQLException ex) {
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    public void Clearall1(){
          firstnamecombofield.clear();
              LastnameCombofield.clear();
            Phonecombofieldd.clear();
               Citycombofield.clear();
               Postcodecombofield.clear();
              id.clear();

         
     }
    public void refrshtable(){
        
        data.clear();
        combodata.clear();
       loadDatafromDatabase();
       fillcombobox();
       Clearall1();
       //  setCellTable();
  
        
    }

    @FXML
    private void RefreshbuttonAction(ActionEvent event) {
        refrshtable();
          Alert alert= new Alert(Alert.AlertType.INFORMATION);
                
                alert.setHeaderText("Refresh");
                alert.setContentText("Your table has been refreshed");
                alert.showAndWait();
           
    }

    @FXML
    private void editbutton(ActionEvent event) {
         if(Citycombofield.getText().equals("")|| firstnamecombofield.getText().equals("")||LastnameCombofield.getText().equals("")|| Phonecombofieldd.getText().equals("")||Postcodecombofield.getText().equals("")){
               
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Erro message");
                alert.setContentText("You did not select any serial number");
                alert.showAndWait();
            }
         else{
           try {
               
               String query = "Update book set Firstname=?,Lastname=?,Phonenumber=?,City=?,Postcode=? where Pid='"+id.getText()+"' ";
           String First=  firstnamecombofield.getText();
           String Last= LastnameCombofield.getText();
           String Phone= Phonecombofieldd.getText();
           String city=Citycombofield.getText();
           String post=Postcodecombofield.getText();
              
         pst= con.prepareStatement(query);
             //pst.setString(1, id.getText());
               pst.setString(1, First);
                pst.setString(2, Last);
                 pst.setString(3, Phone);
                  pst.setString(4, city);
                   pst.setString(5, post);
                   pst.execute();
                 //  pst.close();
                       Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation");
                alert.setHeaderText("Phonebook has been updated");
                alert.setContentText("Thank you");
                alert.showAndWait();
      
                  
     } catch (SQLException ex) {
          
         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("Edit button is not working");
     }
         
           refrshtable();
    }
    }
     
     
     
    }





    
    
    
    
  
