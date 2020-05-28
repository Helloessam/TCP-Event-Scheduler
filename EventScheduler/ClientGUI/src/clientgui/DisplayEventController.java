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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    @FXML private TableView<Table> table;
    @FXML private TableColumn<Table,String> period;
    @FXML private TableColumn<Table,String> status;
    
    ObservableList<String> list = FXCollections.observableArrayList("Room1","Room2", "Room3");
    
    
    
    public void display(ActionEvent event) throws IOException{

         String roomNo = (String) roomSelector.getValue();
         String daten = datePick.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
         String p = "2 " + roomNo + " " + daten;
         System.out.print(p);
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
        
         
        
        ObservableList<Table> info = FXCollections.observableArrayList();
        info.add(new Table("1","Available"));
            // Period
            period.setCellValueFactory(new PropertyValueFactory<>("period"));
    
            // Status
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
            
        table.setItems(info);
       // table.getColumns().addAll(periodColumn,statusColumn);
    }    
    
} 
