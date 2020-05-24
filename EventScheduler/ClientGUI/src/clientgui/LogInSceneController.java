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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nour
 */
public class LogInSceneController implements Initializable {
   
    
    @FXML private TextField usernameText;
    @FXML private TextField passwordText;
    
    String password, username;

    public void logInPressed (ActionEvent event) throws IOException{
        
        username = usernameText.getText().toLowerCase();
        password= passwordText.getText();
        
        if(username.isEmpty() || password.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "PLEASE INPUT ALL CREDENTIALS").showAndWait();
        }
        else{
            ClientGUI.out.writeUTF("0 " + username + " " + password);
            if(ClientGUI.in.readUTF().equals("decline access")){
                new Alert(Alert.AlertType.ERROR, "USERNAME OR PASSWORD IS INCORRECT").showAndWait();
                usernameText.clear(); 
                passwordText.clear();
            }
            else{
                Parent root= FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                Scene receivingScene = new Scene(root);
                Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(receivingScene);
                window.show();     
            } 
        }
    }
    
    public void showHomePage(ActionEvent event) throws IOException{
        Parent root= FXMLLoader.load(getClass().getResource("EntryScene.fxml"));
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
