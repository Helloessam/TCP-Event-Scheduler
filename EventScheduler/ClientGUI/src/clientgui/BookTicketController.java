/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage ;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


/**
 *
 * @author Essam Hisham
 */
public class BookTicketController implements Initializable {
    
     @FXML
    public ComboBox eventChooserCbox;

    @FXML
    public Button bookbtn;
    @FXML
    public Button searchbtn ;
    @FXML
    public TextField eventTitleSearchtxt;

    String FoundedEvent = "";
    
    
    private static void PrintTicket(String info) {
        FileChooser directoryPicker = new FileChooser();
        directoryPicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        File file = directoryPicker.showSaveDialog(null);
        if(file != null){
            try {
                Label tag = new Label(info);
                tag.setStyle("-fx-background-color: white; -fx-text-fill:black;");
                tag.setWrapText(true);
                tag.setMinSize(200, 200);
                tag.setMaxSize(450, 250);
                tag.setPrefSize(400, 150);
                Scene scene = new Scene(new Group(tag));
                WritableImage img = new WritableImage(500, 300) ;
                scene.snapshot(img);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(img, null);
                //Write the snapshot to the chosen file
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) { ex.printStackTrace(); }
        }

    }
    public void searchbtn (ActionEvent event) throws IOException {
         if( eventTitleSearchtxt.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Search field was left empty ! ").showAndWait();
         }
         else {
        String str = "";
        //Message Format for Searching An Event : "23 Networks"
        str += "23" + " " + eventTitleSearchtxt.getText() ;
        System.out.println(str);
        ClientGUI.out.writeUTF(str);
        // if found : Reserver Send "Event1"
        if("Event1".equals(ClientGUI.in.readUTF())){
          System.out.println("Event found ");
          FoundedEvent = eventTitleSearchtxt.getText();
        }
        // if found : Reserver Send "Event0"
        else if ("Event0".equals(ClientGUI.in.readUTF())){
          System.out.println("Event not found ");
            
        }
         }
    }

    public void bookbtn(ActionEvent event) throws IOException {
        String transfarevalue = "";
        String EventName = "";
        Random random = new Random();
        DateFormat tdate = new SimpleDateFormat("yyyyMMdd");
        String ticketnum = tdate.format(new Date()) + String.valueOf(random.nextInt(999999999));
        // check if search field was left empty to make sure not to send empty message to server
        if( eventTitleSearchtxt.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Please Search For Event Name First ").showAndWait();
         }
        else {
        if (eventChooserCbox.getValue().toString().equals("08:00 am to 10:00 am ")){
            transfarevalue = "P1";
        }
         else if (eventChooserCbox.getValue().toString().equals("10:00 am to 12:00 pm ")){
            transfarevalue = "P2";
        }
        else if (eventChooserCbox.getValue().toString().equals("12:00 pm to 02:00 pm ")){
            transfarevalue = "P3";
        }
        else {
            transfarevalue = "P4";
        }
        
        
        if(transfarevalue == "P1" ) {
            bookbtn.setDisable(true);
        }
        else{
            String info = "";
            info = eventChooserCbox.getValue().toString();
            EventName = eventTitleSearchtxt.getText();
                PrintTicket("Hello Your ticket number is : " + ticketnum + " and can  successfully attend class : " + " " + EventName + "@" + info);
         }
        
        ///// Communicating With the Server ///////////////////////////////////////
        String str = "";
        
        // //Message Format for Booking An Event : "25 P1 Networks"
        str += "25" + " " + transfarevalue + " " + FoundedEvent  ;
        System.out.println(str);
       ClientGUI.out.writeUTF(str);
        
        // response Message "error" if the EVENT CAN'T be booked at choosen time slot
        if("error".equals(ClientGUI.in.readUTF())){
            System.out.println("Event wasn't Book ");
             new Alert(Alert.AlertType.WARNING, " Event wasn't Book Choose another time slot").showAndWait();
        }
        
        // response Message "Success" if the EVENT CAN be booked at choosen time slot
         if ("Success".equals(ClientGUI.in.readUTF())){
            System.out.println("Event has been Booked ");
             new Alert(Alert.AlertType.INFORMATION, " Event was Booked").showAndWait();
             String info = "";
              info = eventChooserCbox.getValue().toString();
            EventName = eventTitleSearchtxt.getText();
                PrintTicket("Hello Your ticket number is : " + ticketnum + " and can  successfully attend class : " + " " + EventName + "@" + info);
        }
       }
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        eventChooserCbox.getItems().clear();
        eventChooserCbox.getItems().addAll("08:00 am to 10:00 am ", "10:00 am to 12:00 pm ", "12:00 pm to 02:00 pm " , "02:00 pm to 04:00 pm");
        eventChooserCbox.getSelectionModel().select("Select an event to book ");
    }    
    
}
