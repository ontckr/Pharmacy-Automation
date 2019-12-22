package socket;


import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerController {
	
	
    public static void sendMessage(String message, Socket socket) throws IOException {
       
    	DataOutputStream output = new DataOutputStream(socket.getOutputStream());
       
    	output.writeUTF(message);
       
    	output.flush();
    }
    
    public static String receiveMessage(Socket socket) throws IOException {
       
    	DataInputStream input = new DataInputStream(socket.getInputStream());

        String message = input.readUTF();
       
        return message;
    }
    
    public static void sendData(Object data, Socket socket) throws IOException {

        ObjectOutputStream dOut = new ObjectOutputStream(socket.getOutputStream());

        dOut.writeObject(data);           
        
        dOut.close();
    }

    public static Object receiveData(Socket socket) throws IOException, ClassNotFoundException {
    
    	ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      
    	Object o = in.readObject();

        return o;
    }
    
}
