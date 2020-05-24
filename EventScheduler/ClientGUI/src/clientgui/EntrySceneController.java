/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class EntrySceneController implements Initializable {

    public void logInBtn(ActionEvent event) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("LogInScene.fxml"));
        Scene receivingScene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(receivingScene);
        window.show();        
    }
    public void registerBtn(ActionEvent event) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("RegistrationScene.fxml"));
        Scene receivingScene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(receivingScene);
        window.show();        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
