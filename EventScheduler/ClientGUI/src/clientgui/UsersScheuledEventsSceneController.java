
package clientgui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class UsersScheuledEventsSceneController implements Initializable {
    
    @FXML private ListView<String> eventsList;
     
    
    public void listViewClicked(){
        String eventDetails= eventsList.getSelectionModel().getSelectedItem();
        
    }
    
    public void goBackToHomepage (ActionEvent event) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        Scene receivingScene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(receivingScene);
        window.show();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ClientGUI.out.writeUTF("5 " + LogInSceneController.username);
            String responseArray[]= ClientGUI.in.readUTF().split("\n");
            for(String event: responseArray)
                eventsList.getItems().add(event);
        } catch (IOException e) {
            System.out.println(e);
        }
    }    
    
}
