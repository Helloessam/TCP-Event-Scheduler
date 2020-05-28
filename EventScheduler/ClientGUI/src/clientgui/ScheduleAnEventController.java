
package clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Essam Hisham
 *
 *
 **/

public class ScheduleAnEventController implements Initializable {
    
    @FXML
    public TextField eventTitletxt;

    @FXML
    public ComboBox timeSlotCbox;

    @FXML
    public ComboBox roomIdCbox;

    @FXML
    public DatePicker datePickerCbox;

    @FXML
    public Button addEventBtn;

    @FXML
    public Button btnViewEvent;

    @FXML
    public Label label1 ;

    public void addEventBtn(ActionEvent event) throws IOException {
        String transfarevalue = "";
        String EventTitle, Timeperiod, RoomId, date;
        EventTitle  = eventTitletxt.getText();
        Timeperiod = timeSlotCbox.getValue().toString();
        RoomId = roomIdCbox.getValue().toString();
        LocalDate localDate = datePickerCbox.getValue();
        if (timeSlotCbox.getValue().toString().equals("08:00 am to 10:00 am ")){
            transfarevalue = "P1";
        }
        if (timeSlotCbox.getValue().toString().equals("10:00 am to 12:00 pm ")){
            transfarevalue = "P2";
        }
        if (timeSlotCbox.getValue().toString().equals("12:00 pm to 02:00 pm ")){
            transfarevalue = "P3";
        }
        if (timeSlotCbox.getValue().toString().equals("02:00 pm to 04:00 pm")){
            transfarevalue = "P4";
        }

        if(timeSlotCbox.getValue() == null || roomIdCbox.getValue() == null || eventTitletxt.getText().isEmpty() || datePickerCbox.getValue()== null )
            new Alert(Alert.AlertType.WARNING, "HEY , Some fields were left empty ! ").showAndWait();
        else{
            EventManagerClass newEventManager;
            newEventManager = new EventManagerClass();
          //  newEventManager.createEvent(EventTitle,localDate.toString(),RoomId,transfarevalue);
        }

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roomIdCbox.getItems().clear();

        roomIdCbox.getItems().addAll("Room1", "Room2", "Room3" , "Room4", "Room5");

        roomIdCbox.getSelectionModel().select("Select Class id ");

        timeSlotCbox.getItems().clear();

        timeSlotCbox.getItems().addAll("08:00 am to 10:00 am ", "10:00 am to 12:00 pm ", "12:00 pm to 02:00 pm " , "02:00 pm to 04:00 pm");

        timeSlotCbox.getSelectionModel().select("Select time slot ");
        // TODO
    }    
    
}

