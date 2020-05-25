/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evently;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Essam Hisham
 */
public class EventManagerClass {
    private String eventTitle,date,roomID,startTime , endTime;
    private Socket clientSocket;
    private PrintWriter dataOut;
    private BufferedReader dataIn;
    
    public EventManagerClass() throws IOException {        
      setUpConnection();
    }

    private void setUpConnection(){
        try {
        clientSocket=new Socket("localhost",12345);    
        dataOut = new PrintWriter(this.clientSocket.getOutputStream(), true);
        dataIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));          
        System.out.println("Connection established");  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
     private void insertEvent() throws IOException{
        String str = "";
        str += "bookevent?name=" + eventTitle; str += "&roomID=" + roomID; str += "&startingTime=" + startTime; str += "&endingTime=" + endTime;
        dataOut.println(str); 
        if("err".equals(dataIn.readLine())){
           System.out.println("Event wasn't added ");
        }
        else{
           System.out.println("Event has been added ");
        }
     }


    public void createEvent(String eventTitle,
     String date,String roomID,String startTime,String endTime) throws IOException
    {
        this.eventTitle = eventTitle; this.startTime = startTime; this.endTime = endTime; this.date = date ; this.roomID = roomID;
        insertEvent();
    }
 }   
   