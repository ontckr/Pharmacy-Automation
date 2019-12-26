package socket;

import java.io.IOException;
import ui.SellDrugPage;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.*;

public class ClientConnection {

	public static int login(String username, String password) {
		
		int result = 0 ;
		
		try (Socket socket = new Socket("127.0.0.1", 35000)) {

			ClientController.sendMessage("LOGIN", socket);

			ClientController.sendMessage(username, socket);
			ClientController.sendMessage(password, socket);
			
			String messageString = ClientController.receiveMessage(socket);
			
			if (messageString.equals("OK")) {
				
				result = 1;
				
				System.out.print("Login Succesful...");

			} else if (messageString.equals("LOGINFAILED")) {
				
				result = 0;
				
				System.out.print("Username and password incorrect");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Pharmacy> getUserInfo(String username) {
		
		ArrayList<Pharmacy> pharmacy = new ArrayList<Pharmacy>();
		
		try (Socket socket = new Socket("127.0.0.1", 35000)) {

			ClientController.sendMessage("USERINFO", socket);
			ClientController.sendMessage(username, socket);

			pharmacy = (ArrayList<Pharmacy>) ClientController.receiveData(socket);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return pharmacy;
	}

	public static ArrayList<DrugStock> getPharmacyDrugs(String username) {

		ArrayList<DrugStock> drugStocks = new ArrayList<DrugStock>();

		try (Socket socket = new Socket("127.0.0.1", 35000)) {

			ClientController.sendMessage("GETDRUGS", socket);
			ClientController.sendMessage(username, socket);

			drugStocks = (ArrayList<DrugStock>) ClientController.receiveData(socket);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return drugStocks;
	}

	public static void sendSoldDrug(int id,int value,String username) {
		try (Socket socket = new Socket("127.0.0.1", 35000)) {
			SellDrugPage sellDrugPage=null;
			ClientController.sendMessage("DECREASESTOCK", socket);
			ClientController.sendMessage(username, socket);
			SoldDrug soldDrug=new SoldDrug(id,value,username);
			ClientController.sendData(soldDrug, socket);
			
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
