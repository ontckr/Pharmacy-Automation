package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import model.*;

public class ClientConnection {
	
	
	public static ArrayList<DrugStock> getPharmacyDrugs(String username){

		ArrayList<DrugStock> drugStocks = new ArrayList<DrugStock>();
		
		
		try (Socket socket = new Socket("127.0.0.1", 35000)) {
			
			ClientController.sendMessage("GETDRUGS",socket);
			
			ClientController.sendMessage(username, socket);
		
			
			drugStocks = (ArrayList<DrugStock>)ClientController.receiveData(socket);
			
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
