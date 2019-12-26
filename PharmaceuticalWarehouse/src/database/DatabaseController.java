package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.*;

public class DatabaseController {

	static String url = "jdbc:sqlite:mypharmacy.db";
	private static Object exception;

	public static void setupInitialData() {

		String pharmacySql = "CREATE TABLE IF NOT EXISTS pharmacy (\n" + "id integer PRIMARY KEY,\n"
				+ "name text NOT NULL,\n" + "address char(100),\n" + "email char(50),\n" + "username char(50),\n"
				+ "district text, \n" + "password char(50),\n" + "phone text \n" + ");";

		String drugsSql = " CREATE TABLE IF NOT EXISTS drugs (\n" + "id integer PRIMARY KEY,\n"
				+ "name text NOT NULL, \n" + "box_size text NOT NULL \n" + ");";

		String pharmacyDrugsSql = "CREATE TABLE IF NOT EXISTS pharmacydrugs (\n" + "id integer PRIMARY KEY,\n"
				+ "pharmacy_username TEXT NOT NULL,\n" + "drug_id integer NOT NULL,\n" + "stock integer \n"
				+ ");";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {

			stmt.execute(pharmacySql);
			stmt.execute(drugsSql);
			stmt.execute(pharmacyDrugsSql);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		boolean firstLoad = true;

		try (Connection conn = DriverManager.getConnection(url);) {
			String queryString = "SELECT * FROM drugs ;";

			Statement pStatement = conn.createStatement();

			ResultSet resultSet = pStatement.executeQuery(queryString);

			while (resultSet.next()) {
				firstLoad = false;
			}
			pStatement.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}

		if (!firstLoad) {
			
			return;
		}

		String addDrugs = "INSERT INTO drugs (name,box_size) VALUES (\"Talcid 500 mg\" ,\"10\"), (\"Ultravist Flakon 300\",\"10\"), (\"Visanne Tablet 2 mg\",\"10\"), (\"Minoset 500 mg\",\"10\"), (\"Aspirin 500 mg\",\"10\"), (\"Baclan 75 mg\",\"10\"), (\"Ciproxin 500 mg\",\"10\"), (\"Glucobay Tablet 100 mg\",\"10\"), (\"Luminal 100 mg Tablet\",\"10\"), (\"Xofigo\",\"10\");";

		try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) {
			stmt.execute(addDrugs);

			stmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void newPharmacy(String name, String address, String email, String username, String password, String district, String phone) {
		try (Connection conn = DriverManager.getConnection(url);) {
			String queryString = "INSERT INTO pharmacy (name, address, email, username, password, district, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(queryString);

			pStatement.setString(1, name);
			pStatement.setString(2, address);
			pStatement.setString(3, email);
			pStatement.setString(4, username);
			pStatement.setString(5, password);
			pStatement.setString(6, district);
			pStatement.setString(7, phone);

			pStatement.executeUpdate();

			pStatement.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}
	}

	public static ArrayList<DrugStock> getPharmacyStock(String username) {

		ArrayList<DrugStock> drugs = new ArrayList<DrugStock>();

		try (Connection conn = DriverManager.getConnection(url);) {
			String queryString = "SELECT * FROM pharmacydrugs INNER JOIN drugs "
					+ "ON pharmacydrugs.drug_id = drugs.id  WHERE pharmacy_username = '" + username + "'";
			System.out.println(queryString);

			Statement pStatement = conn.createStatement();

			ResultSet resultSet = pStatement.executeQuery(queryString);

			while (resultSet.next()) {
				int stock = resultSet.getInt("stock");
				String name = resultSet.getString("name");

				DrugStock drug = new DrugStock(stock, name);
				drugs.add(drug);
			}

			pStatement.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}
		for (DrugStock drugStock : drugs) {
			System.out.println(drugStock.getName());
			System.out.println(drugStock.getStock());
		}
		return drugs;

	}

	public static ArrayList<BoxSize> getDrugInfo() {

		ArrayList<BoxSize> boxSizes = new ArrayList<BoxSize>();

		try (Connection conn = DriverManager.getConnection(url);) {
			String queryString = "SELECT name,box_size FROM drugs";
			System.out.println(queryString);

			Statement pStatement = conn.createStatement();

			ResultSet resultSet = pStatement.executeQuery(queryString);

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String boxSize = resultSet.getString("box_size");
				BoxSize boxSize2 = new BoxSize(name, boxSize);
				boxSizes.add(boxSize2);
			}

			pStatement.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}

		return boxSizes;

	}

	public static int LoginCheck(String username, String password) {
		try (Connection conn = DriverManager.getConnection(url);) {
			String queString = "SELECT * FROM pharmacy WHERE username=? AND password=?";
			PreparedStatement pStatement = conn.prepareStatement(queString);
			pStatement.setString(1, username);
			pStatement.setString(2, password);

			ResultSet resultSet = pStatement.executeQuery();

			int count = 0;
			while (resultSet.next()) {
				count = count + 1;
				System.out.println(resultSet);
			}

			resultSet.close();
			pStatement.close();

			if (count == 0) {
				return 0;
			}
			if (username.equals("admin")) {
				return 1;
			} else {
				return 2;
			}

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}
		return 0;
	}

	public static Pharmacy getUser(int pharmacyId) {
		ArrayList<Pharmacy> userList = new ArrayList<Pharmacy>();
		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "SELECT id, name, address, email, username, district, phone FROM pharmacy WHERE id= "+ pharmacyId + ";";
			Statement pStatement = conn.createStatement();

			ResultSet resultSet = pStatement.executeQuery(queryString);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				String username = resultSet.getString("username");
				String district = resultSet.getString("district");
				String phone = resultSet.getString("phone");
				Pharmacy pharmacy = new Pharmacy(id, name, address, email, username, district, phone);
				userList.add(pharmacy);
			}
			pStatement.close();
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}

		return userList.get(0);
	}

	public static ArrayList<Pharmacy> getUsers() {
		ArrayList<Pharmacy> userList = new ArrayList<Pharmacy>();
		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "SELECT id, name, address, email, username, district, phone FROM pharmacy;";
			Statement pStatement = conn.createStatement();

			ResultSet resultSet = pStatement.executeQuery(queryString);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				String username = resultSet.getString("username");
				String district = resultSet.getString("district");
				String phone = resultSet.getString("phone");
				Pharmacy pharmacy = new Pharmacy(id, name, address, email, username, district, phone);
				userList.add(pharmacy);
			}
			pStatement.close();
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}

		return userList;
	}

	public static ArrayList<Pharmacy> getUserInfo(String user_name) {

		ArrayList<Pharmacy> userList = new ArrayList<Pharmacy>();

		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "SELECT id,name, address, email, username, district, phone FROM pharmacy WHERE username = '"
					+ user_name + "';";

			Statement pStatement = conn.createStatement();

			ResultSet resultSet = pStatement.executeQuery(queryString);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				String username = resultSet.getString("username");
				String district = resultSet.getString("district");
				String phone = resultSet.getString("phone");
				Pharmacy pharmacy = new Pharmacy(id, name, address, email, username, district, phone);
				userList.add(pharmacy);
			}
			pStatement.close();
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}

		return userList;
	}

	public static void updatePharmacyInfo(String username,String address,String email,String district,String phone) {
		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "UPDATE pharmacy SET address='"+address+"',email='"+email+"',district='"+district+"',phone='"+phone+"'WHERE username='"+username+"'";

			PreparedStatement pStatement = conn.prepareStatement(queryString);

			pStatement.executeUpdate();
			pStatement.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, exception);
		}
	}
	
	public static void updateStock(int id, int value, String username) {

		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "UPDATE pharmacydrugs SET stock=stock+'" + value + "' WHERE drug_id= '" + id
					+ "' AND pharmacy_username='" + username + "'";

			PreparedStatement pStatement = conn.prepareStatement(queryString);

			pStatement.executeUpdate();
			pStatement.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, exception);
		}
	}

	public static void decreaseStock(int id, int value, String username) {

		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "UPDATE pharmacydrugs SET stock=stock-'" + value
					+ "' WHERE drug_id =  ? AND pharmacy_username =  ? ";

			PreparedStatement pStatement = conn.prepareStatement(queryString);
			pStatement.setInt(1, id);
			pStatement.setString(2, username);
			pStatement.executeUpdate();
			pStatement.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, exception);
		}
	}

	public static void newPharmacyStock(String username, int drug_id, int stock) {

		try (Connection conn = DriverManager.getConnection(url);) {

			String queryString = "INSERT INTO pharmacydrugs (pharmacy_username,drug_id,stock) VALUES (?, ?, ?)";
			PreparedStatement pStatement = conn.prepareStatement(queryString);

			pStatement.setString(1, username);
			pStatement.setInt(2, drug_id);
			pStatement.setInt(3, stock);

			pStatement.executeUpdate();

			pStatement.close();

		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null, exception);
		}

	}

	public static void deletePharmacy(int id, String username) {

		try (Connection conn = DriverManager.getConnection(url);) {

			String removePharmacy = "DELETE FROM pharmacy WHERE id = ?";
			String removePharmacyDrugString = "DELETE FROM pharmacydrugs WHERE pharmacy_username =  ? ";

			PreparedStatement pStatement = conn.prepareStatement(removePharmacy);
			PreparedStatement pStatement2 = conn.prepareStatement(removePharmacyDrugString);

			pStatement.setInt(1, id);
			pStatement.executeUpdate();

			pStatement2.setString(1, username);
			pStatement2.executeUpdate();

			pStatement.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Pharmacy delete exception");
			System.out.println(e);
		}
	}
}
