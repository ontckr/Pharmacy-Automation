package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.*;

public class ClientConnection {

	public static boolean login(String username, String password) {
		boolean result = false;
		try (Socket socket = new Socket("127.0.0.1", 35000)) {

			ClientController.sendMessage("LOGIN", socket);

			ClientController.sendMessage(username, socket);
			ClientController.sendMessage(password, socket);
			String messageString = ClientController.receiveMessage(socket);
			if (messageString.equals("OK")) {
				result = true;
				System.out.print("Login Succesful...");

			} else if (messageString.equals("LOGINUNSUCCESFUL")) {
				result = false;
				System.out.print("Username and password incorrect");
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;

	}

	/*
	 * Chat Sell Get Pharmacy Drug Info Logout
	 */

	public static ArrayList<Pharmacy> getUserInfo(String username) {
		ArrayList<Pharmacy> pharmacy = new ArrayList<Pharmacy>();
		try (Socket socket = new Socket("127.0.0.1", 35000)) {

			ClientController.sendMessage("USERINFO", socket);

			

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

			

			drugStocks = (ArrayList<DrugStock>) ClientController.receiveData(socket);

			System.out.print("ilaclar geldi");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return drugStocks;
	}

}
