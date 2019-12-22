package ui;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import database.*;
import model.DrugStock;
import socket.ServerController;

public class Run {
	public static void main(String [] args) {
		
		DatabaseController.setupInitialData();
		
		
		try (ServerSocket listener = new ServerSocket(35000)) {
            System.out.println("Server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(4);
            while (true) {
                pool.execute(new URLMaster(listener.accept()));
            }
        } catch (IOException e) {
        	e.printStackTrace();
		}
	}
	
	
	private static class URLMaster implements Runnable {
        private Socket socket;
        String message;

        URLMaster(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            System.out.println("Connected: " + socket);

            try {
                
            	message = ServerController.receiveMessage(socket);
                
                if (message.equals("GETDRUGS")) {
                	
                	System.out.print("ilaclar alindi");
                	
                	String username = ServerController.receiveMessage(socket);
                	
                	ArrayList<DrugStock> drugStocks = DatabaseController.getPharmacyStock(username);
                	
                	ServerController.sendData(drugStocks, socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
