
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


public class RegistrationSceneController implements Initializable {
    
    
    
    @FXML private TextField usernameText;
    @FXML private TextField passwordText;
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField emailText;
    @FXML private TextField phoneText;
    @FXML private TextField jobText;
    
    String username, password, firstName, lastName, email, phone, job;

    public void registerBtn(ActionEvent event) throws IOException{
        
        username = usernameText.getText().toLowerCase();
        password = passwordText.getText();
        firstName = firstNameText.getText();
        lastName = lastNameText.getText();
        email = emailText.getText();
        phone = phoneText.getText();
        job = jobText.getText();
        
        if(username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || job.isEmpty())
              new Alert(Alert.AlertType.ERROR, "PLEASE INPUT ALL DATA!").showAndWait();
        else{
            ClientGUI.out.writeUTF("1 "+username + " " +password + " " + firstName + " " + lastName + " " + email+ " " + phone + " " +job);
        
            if(ClientGUI.in.readUTF().equals("Username already exists")){
                new Alert(Alert.AlertType.ERROR, "USERNAME ALREADY EXISTS!").showAndWait();
                usernameText.clear();
            }
            else{
                showHomePage(event);
            }
        }
        
    }
    
    public void cancelBtn(ActionEvent event) throws IOException{
        showHomePage(event);  
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
