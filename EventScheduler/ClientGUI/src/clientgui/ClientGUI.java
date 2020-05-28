
package clientgui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ClientGUI extends Application {
   
    static int port = 8000;
    static String host = "localhost";
    static DataInputStream in;
    static DataOutputStream out;
    static Socket socket;
    int status;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DisplayEvent.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Event Scheduling Application");
        stage.show();
        try{
        socket = new Socket(host,port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "UNABLE TO REACH SERVER!").showAndWait();
            System.exit(status);
        }
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
    
}
