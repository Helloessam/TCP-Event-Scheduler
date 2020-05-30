
package serverlogin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HandleClient implements Runnable{
   
    private final Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static String request;
    static String requestArray[];
    
    
    
    public HandleClient(Socket socket) throws SQLException{
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        while(true){
            request = in.readUTF();
            requestArray = request.split(" ");
            switch(requestArray[0]){
                case "0": logInCheck();break;
                case "1": registerNewUser();break;
                case "2": displayAvailableSlots();break;
                case "3": scheduleAnEvent();break;
                case "4": bookATicket();break;
                case "5": displayUsersScheduledEvents();break;
            }
        }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    private void logInCheck() throws IOException{
        try{
            PreparedStatement stmt = ServerLogIn.con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,requestArray[1]) ;
            stmt.setString(2, requestArray[2]);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                out.writeUTF(rs.getString("first_name")+ " " + rs.getString("last_name"));
            }
            else{
                out.writeUTF("decline access");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    private void registerNewUser() throws IOException{
         try{
            PreparedStatement stmt = ServerLogIn.con.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, requestArray[1]) ;
            stmt.setString(2, requestArray[2]);
            stmt.setString(3, requestArray[5]);
            stmt.setString(4, requestArray[6]);
            stmt.setString(5, requestArray[7]);
            stmt.setString(6, requestArray[3]);
            stmt.setString(7, requestArray[4]);
            stmt.execute();
            out.writeUTF("Registered");
        }
        catch(SQLException e){
            out.writeUTF("Username already exists");
            System.out.println(e);
        }
    }
    private void displayAvailableSlots() throws IOException{
        String response = "";
        try{
            PreparedStatement stmt = ServerLogIn.con.prepareStatement("SELECT * FROM reservations WHERE room_id=? AND Event_date=? ORDER BY period ASC",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, requestArray[1]) ;
            stmt.setString(2, requestArray[2]);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                response = response + rs.getString("Event_name")+ " " +rs.getString("Period") +"\n";
            out.writeUTF(response);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    private void scheduleAnEvent() throws IOException {
        System.out.println("Entered");
        try{
            PreparedStatement stmt = ServerLogIn.con.prepareStatement("INSERT INTO reservations VALUES (?, ?, ?, ?, ?, 50)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, requestArray[1]) ;
            stmt.setString(2, requestArray[3]);
            stmt.setString(3, requestArray[2]);
            stmt.setString(4, requestArray[4]);
            stmt.setString(5, requestArray[5]);
            stmt.execute();
            out.writeUTF("scheduled");
        }
        catch(SQLException e){
            out.writeUTF("error");
            System.out.println(e);
        }
    }

    private void bookATicket() {
        //Method should handle ticket booking
    }

    private void displayUsersScheduledEvents() throws IOException {
        String response = "";
        try{
            PreparedStatement stmt = ServerLogIn.con.prepareStatement("SELECT * FROM reservations WHERE username=? ORDER BY event_date ASC, period ASC",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, requestArray[1]);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                response = response + rs.getString("Event_name")+ " " +rs.getString("Event_date") + " " +rs.getString("Period") + " " + rs.getString("Room_id")+"\n";
            out.writeUTF(response);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
}
