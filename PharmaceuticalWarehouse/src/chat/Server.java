package chat;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.User;



public class Server {

    private int port;
    private List<User> clients;
    private ServerSocket server;
    private  DefaultListModel<String> listModel;
    public  JTextPane jtextFilDiscu;
    private  JTextField jtextInputChat;
    
    public Server(int port, DefaultListModel listModel, JTextPane jtextFilDiscu, JTextField jtextInputChat) {
		this.listModel = listModel;
		this.jtextFilDiscu = jtextFilDiscu;
		this.jtextInputChat = jtextInputChat;
		this.port = port;
	    this.clients = new ArrayList<>();
	   
	}

    public void run() throws IOException {
        server = new ServerSocket(port) {
            protected void finalize() throws IOException {
                this.close();
            }
        };
        System.out.println("Chat server is running...");

        while (true) {
            Socket client = server.accept();

            String nickname = (new Scanner(client.getInputStream())).nextLine();
            nickname = nickname.replace(",", ""); 
            nickname = nickname.replace(" ", "_");
            
            System.out.println("New Client: " + nickname );

            User newUser = new User(client, nickname);

            this.clients.add(newUser);
            listModel.removeAllElements();
            this.clients.forEach((User u) -> {
                listModel.addElement(u.getNickname());
            });
            new Thread(new UserHandler(this, newUser)).start();
        }
    }

    
    
    public void removeUser(User user) {
    	
        this.clients.remove(user);
        
        listModel.removeAllElements();
        
        System.out.println(user + "leave the chat");

        this.clients.forEach((User u) -> {
            listModel.addElement(u.getNickname());
        });
    }

    
    
    public void sendMessageToUser(String message, String user, boolean isAdmin) {
        for (User client : this.clients) {
            if (client.getNickname().equals(user)) {
                if (isAdmin) {
                    client.getOutStream().println("Pharmaceutical Warehouse" + "<span>: " + message + "</span>");
                } else {
                    client.getOutStream().println(user + "<span>: " + message + "</span>");
                }
            }
        }
    }
    
    
    
    public  void clearAndAddToPane() {
        try {
            String message = jtextInputChat.getText().trim();
            if (message.equals("")) {
                return;
            }

            appendToPane(jtextFilDiscu, " Pharmaceutical Warehouse : " + message);
            jtextInputChat.requestFocus();
            jtextInputChat.setText(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        }
    }

    
    
    public  void appendToPane(JTextPane textPane, String message) {
        HTMLDocument doc = (HTMLDocument) textPane.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) textPane.getEditorKit();
        try {
            editorKit.insertHTML(doc, doc.getLength(), message, 0, 0, null);
            textPane.setCaretPosition(doc.getLength());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



class UserHandler implements Runnable {

    private Server server;
    private User user;

    public UserHandler(Server server, User user) {
        this.server = server;
        this.user = user;
    }

    public void run() {
        String message;

        Scanner scanner = new Scanner(this.user.getInputStream());
        
        while (scanner.hasNextLine()) {
        	
            message = scanner.nextLine();

            server.appendToPane(server.jtextFilDiscu, user.getNickname() + ": " + message);

            server.sendMessageToUser(message, user.getNickname(), false);
        }

        server.removeUser(user);
        
        scanner.close();
    }
}


