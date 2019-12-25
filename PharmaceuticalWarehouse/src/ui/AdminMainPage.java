package ui;

import java.awt.BorderLayout;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseController;
import model.Pharmacy;

import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import java.awt.SystemColor;

public class AdminMainPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private AdminMainPage adminMainPage;

	public AdminMainPage() throws IOException {
		adminMainPage= this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 828, 551);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Pharmaceutical Warehouse");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdmin.setBounds(20, 25, 194, 30);
		contentPane.add(lblAdmin);
		
		JButton btnNewPharmacy = new JButton("New Pharmacy");
		btnNewPharmacy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewPharmacy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewPharmacy newPharmacyPage = null;
			
				try {
					newPharmacyPage = new NewPharmacy(adminMainPage);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				newPharmacyPage.setVisible(true);
			}
		});
		btnNewPharmacy.setBounds(250, 25, 160, 30);
		contentPane.add(btnNewPharmacy);
		
		JTextPane txtpnAsdasd = new JTextPane();
		txtpnAsdasd.setBackground(SystemColor.textHighlight);
		txtpnAsdasd.setBounds(443, 31, 339, 402);
		contentPane.add(txtpnAsdasd);

		
		table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "District", "Phone"
			}
		));
		
		
		table.setBackground(SystemColor.info);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(153, 180, 209), new Color(153, 180, 209), new Color(153, 180, 209), new Color(153, 180, 209)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		table.setBounds(20, 72, 390, 361);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		ArrayList<Pharmacy> pharmacies = DatabaseController.getUsers();
		
		for (Pharmacy pharmacy : pharmacies) {
			model.addRow(new Object[]{pharmacy.getName(), pharmacy.getDistrict(), pharmacy.getPhone()});
			

		};
		
		
		table.setFillsViewportHeight(true);
		contentPane.add(table);

		
		JButton editPharmacy = new JButton("Edit");
		editPharmacy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		editPharmacy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				EditPharmacyPage editPharmacyPage=null;
				int selectedIndex = table.getSelectedRow();
				System.out.println("Selected ind"+selectedIndex);
				if (selectedIndex < 0) {
					JOptionPane.showMessageDialog(null, "Select a pharmacy to edit.");
				}else {
					Pharmacy selectedUser = pharmacies.get(selectedIndex);
					System.out.println(selectedIndex);
					System.out.println("Selected user: "+ selectedUser.getName());
				
					editPharmacyPage = new EditPharmacyPage(adminMainPage,selectedUser);
					editPharmacyPage.setVisible(true);
				}
				
			}
		});
		editPharmacy.setBounds(168, 456, 89, 30);
		contentPane.add(editPharmacy);
		
	}
	
	public void refreshTable() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ArrayList<Pharmacy> pharmacies = DatabaseController.getUsers();
		for (Pharmacy pharmacy : pharmacies) {
			model.addRow(new Object[]{pharmacy.getName(), pharmacy.getDistrict(), pharmacy.getPhone()});

		}
	}
}
