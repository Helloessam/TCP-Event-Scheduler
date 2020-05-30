
package clientgui;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Essam Hisham
 */
public class EventManagerClass {
    private String eventTitle,date,roomID,TimeSlot;
    private Socket clientSocket;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;

    public EventManagerClass() throws IOException {
        setUpConnection();
    }

    private void setUpConnection(){
        try {
            clientSocket=new Socket("localhost",8000);
            dataIn = new DataInputStream(clientSocket.getInputStream());
            dataOut = new DataOutputStream(clientSocket.getOutputStream());
            System.out.println("Connection established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertEvent() throws IOException{
        String str = "";
        str += "3" + " " + roomID + " " + TimeSlot + " " + date + " " + eventTitle +" "+ LogInSceneController.username ;
        System.out.println(str);
        dataOut.writeUTF(str);
        if("error".equals(dataIn.readUTF())){
            System.out.println("Event wasn't added ");
        }
        else{
            System.out.println("Event has been added ");
        }
    }


    public void createEvent(String eventTitle, String date,String roomID,String TimeSlot) throws IOException
    {
        this.eventTitle = eventTitle; this.TimeSlot = TimeSlot; this.date = date ; this.roomID = roomID;
        insertEvent();
    }
}
   
