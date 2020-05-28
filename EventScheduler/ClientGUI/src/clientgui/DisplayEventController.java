/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mohamedelkhawaga
 */
public class DisplayEventController implements Initializable {

    
    @FXML private DatePicker datePick;
    @FXML private ComboBox roomSelector;
    @FXML 
    private Button dispBtn;
    @FXML private Button backbtn;
    @FXML private Label nodata;
    @FXML private TableView<Table> table;
    @FXML private TableColumn<Table,String> period;
    @FXML private TableColumn<Table,String> status;
    String splitWhole[], splitLine[]; 
   
    ObservableList<String> list = FXCollections.observableArrayList("Room1","Room2", "Room3");
    
    
    
    public void display(ActionEvent event) throws IOException, RuntimeException{
         int indicator[]={0,0,0,0,0};     
         
         String daten = datePick.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
         String roomNo = (String) roomSelector.getValue();
         String p = "2 " + roomNo + " " + daten;
         System.out.print(p);
         // Sending Date and Room to server to query on DB
         ClientGUI.out.writeUTF(p);           
         // Receiveing response from server
             String   response = ClientGUI.in.readUTF();
                System.out.print(response);
                // Start of Viewing items on TableView
                ObservableList<Table> info = FXCollections.observableArrayList();
                ///////// If condition to check if response is empty/////////
                if ("".equals(response))
                {
                    int i=0;
                    while (true)
                     {
                         if (indicator[i]==0 || "".equals(response))
                             info.add(new Table("P"+(i+1),"Available"));
                         
                         i++;
                         if (i==5)
                             break;
                     }
                }
                /////// Else if response is not empty///////
                else {
         // splitting the response array 
         splitWhole = response.split("\n");
         // assigning the number of available events
         int l = splitWhole.length;        
         
         // While loop to setup an array with all available and non available  periods
         int i=0;
         while (l!=0){
             splitLine = splitWhole[i].split(" ");
            switch (splitLine[1])
             {
                 case "P1": indicator[0] = 1; break;
                 case "P2": indicator[1] = 1; break;
                 case "P3": indicator[2] = 1; break;
                 case "P4": indicator[3] = 1; break;
                 case "P5": indicator[4] = 1; break;              
             }      
        l--; i++;          
         }
         
        // while loop to create new objects and add to table based on database
         i=0; int j=0; l = splitWhole.length;
         while (true)
         {
             if (indicator[i]==0 )
                  info.add(new Table("P"+(i+1),"Available"));
             else{
                  splitLine = splitWhole[j].split(" ");
                  info.add(new Table(splitLine[1] + ": " + splitLine[0],"Not Available"));
                  j++;    
             }
             
             
             i++;
             if (i==5 || j>l)
                 break;
             
         }
                }
        
            // setting Period from Table class
            period.setCellValueFactory(new PropertyValueFactory<>("period"));
    
            // setting Status value from Table Class
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
      
        table.setItems(info);
        
    }
    
    public void back(ActionEvent event) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene receivingScene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(receivingScene);
        window.show();  
    }
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roomSelector.setItems(list);
        
         
        
        
       
    }    
    
} 
