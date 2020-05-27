/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author mohamedelkhawaga
 */
public class CreatEventController implements Initializable {

    
    @FXML private DatePicker datePick;
    @FXML private ComboBox roomSelector;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
} 
