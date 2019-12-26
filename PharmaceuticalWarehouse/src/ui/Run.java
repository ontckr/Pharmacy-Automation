package ui;

import java.io.IOException;
import model.SoldDrug;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import database.*;
import model.DrugStock;
import model.Pharmacy;
import socket.ServerController;

public class Run {
	public static void main(String [] args) {
		
		DatabaseController.setupInitialData();
		
	
		AdminMainPage adminMainPage = null;
		

		try {
			adminMainPage = new AdminMainPage();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
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

            try {
            	
            	System.out.println("Connected: " + socket);
            	
            	message = ServerController.receiveMessage(socket);
                
                if (message.equals("LOGIN")) {
                	
					String username = ServerController.receiveMessage(socket);
					String password = ServerController.receiveMessage(socket);
					
					int count = DatabaseController.LoginCheck(username, password);
					
					if (count == 0) {
						
						ServerController.sendMessage("LOGINFAILED", socket);
						
					}else if (count == 2) {
						
						ServerController.sendMessage("OK", socket);
																		
					}
				}  
             
                else if (message.contentEquals("USERINFO")) {
                	
					String username = ServerController.receiveMessage(socket);
					
					ArrayList<Pharmacy> pharmacy = DatabaseController.getUserInfo(username);
					
					ServerController.sendData(pharmacy, socket);
					
				}
				else if (message.contentEquals("GETDRUGS")) {
					
					String username = ServerController.receiveMessage(socket);
					
					ArrayList<DrugStock> drugStocks = DatabaseController.getPharmacyStock(username);
					
					ServerController.sendData(drugStocks, socket);
					
				}
				else if(message.contentEquals("DECREASESTOCK")) {
					String username = ServerController.receiveMessage(socket);
					SoldDrug soldDrug=(SoldDrug) ServerController.receiveData(socket);
					int id=soldDrug.getID();
					int value=soldDrug.getValue();
					String username1=soldDrug.getUsername();
					DatabaseController.decreaseStock(id, value, username1);
					System.out.println("Db username geldi: "+username1);
					System.out.println("Db username geldi: "+id);
					System.out.println("Db username geldi: "+value);
					
				}

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
            }
        }
    }
}