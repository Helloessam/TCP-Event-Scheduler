
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
    
    private static final String username = "admintest";
    private static final String password = "admintest";
    private static final String conn = "jdbc:mysql://localhost/EventScheduler";
    Connection con = null;
    
    public HandleClient(Socket socket) throws SQLException{
        this.socket = socket;
        con = DriverManager.getConnection(conn,username,password);
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
            }
        }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    private void logInCheck() throws IOException{
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,requestArray[1]) ;
            stmt.setString(2, requestArray[2]);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                out.writeUTF("grant access");
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
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?)",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
    
}
