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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import chat.Server;
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
import javax.swing.JList;
import javax.swing.JTextField;

public class AdminMainPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private AdminMainPage adminMainPage;
	private JTextField messageTextfield;
    private DefaultListModel<String> listModel;
    private JTextPane textPane;
    
	public AdminMainPage() throws IOException {
        listModel = new DefaultListModel<String>();

       
		adminMainPage= this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1075, 551);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Pharmaceutical Warehouse");
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setBounds(20, 25, 274, 30);
		contentPane.add(lblAdmin);
		
		JButton btnNewPharmacy = new JButton("New Pharmacy");
		btnNewPharmacy.setFont(new Font("Dialog", Font.BOLD, 15));
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
		btnNewPharmacy.setBounds(341, 24, 160, 34);
		contentPane.add(btnNewPharmacy);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Arial", Font.PLAIN, 15));
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);
		textPane.setBounds(529, 25, 325, 431);
		contentPane.add(textPane);

		
		table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "District", "Phone"
			}
		));
		table.setForeground(Color.BLACK);
		
		
		table.setBackground(Color.WHITE);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		table.setBounds(20, 72, 390, 361);
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		ArrayList<Pharmacy> pharmacies = DatabaseController.getUsers();
		
		for (Pharmacy pharmacy : pharmacies) {
			model.addRow(new Object[]{pharmacy.getId(),pharmacy.getName(), pharmacy.getDistrict(), pharmacy.getPhone()});
			

		};
			
		JScrollPane scrollPane = new JScrollPane();
		
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMinWidth(0);
	    table.getColumnModel().getColumn(0).setMaxWidth(0);
	    table.getColumnModel().getColumn(0).setWidth(0);
	    
	    scrollPane.setBounds(20,72,481,384);
        scrollPane.setViewportView(table);
		contentPane.add(scrollPane);

		
		JButton editPharmacy = new JButton("Edit");
		editPharmacy.setFont(new Font("Dialog", Font.BOLD, 15));
		editPharmacy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				EditPharmacyPage editPharmacyPage=null;
				
				int selectedRow = table.getSelectedRow();
				int id = (int) table.getValueAt(selectedRow, 0);				
				
				
				int selectedIndex = table.getSelectedRow();
				
				System.out.println("Selected index"+selectedIndex);
				
				if (selectedIndex < 0) {
					JOptionPane.showMessageDialog(null, "Select a pharmacy to edit.");
				}else {
					Pharmacy selectedUser = DatabaseController.getUser(id);
					System.out.println(selectedIndex);
					System.out.println("Selected user: "+ selectedUser.getName());
				
					editPharmacyPage = new EditPharmacyPage(adminMainPage,selectedUser);
					editPharmacyPage.setVisible(true);
				}
				
			}
		});
		
		editPharmacy.setBounds(214, 468, 108, 34);
		contentPane.add(editPharmacy);
		
		JList userList = new JList(listModel);
		userList.setFont(new Font("Arial", Font.PLAIN, 13));
		
		userList.setBounds(866, 25, 181, 431);
		contentPane.add(userList);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				
				int id = (int) table.getValueAt(selectedIndex, 0);	

				System.out.println("silmek icin sectigin id = " + id);
				if (selectedIndex < 0) {
					JOptionPane.showMessageDialog(null, "Select a pharmacy to delete.");
				}else {
					Pharmacy selectedUser = DatabaseController.getUser(id);

					System.out.println("Selected user: "+ selectedUser.getUsername());
					
					String username = selectedUser.getUsername();
					
					DatabaseController.deletePharmacy(id, username);
					refreshTable();
					
				}
			}
		});
		btnDelete.setBounds(20, 469, 117, 34);
		contentPane.add(btnDelete);
		
		messageTextfield = new JTextField();
		messageTextfield.setBounds(527, 468, 327, 34);
		contentPane.add(messageTextfield);
		messageTextfield.setColumns(10);

        textPane.setContentType( "text/html" );
		Server server = new Server(12345, listModel, textPane,messageTextfield);
		
		Thread one = new Thread() {
		    public void run() {
		        try {
		           server.run();
		        }catch(Exception e) {
		        	
		        }
		    }  
		};

		one.start();
		

		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				  if (userList.getSelectedValue() != null) {
	                    String message = messageTextfield.getText().trim();
	                    if (message.equals("")) {
	                        return;
	                    }
	                    server.sendMessageToUser(
	                            message, userList.getSelectedValue().toString(), true
	                    );
	                    server.clearAndAddToPane();

	                }
			}
		});
		btnSend.setBounds(866, 468, 181, 34);
		contentPane.add(btnSend);
		
		JButton btnSupply = new JButton("Supply");
		btnSupply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplyStockPage supplyStockPage=null;
				
				int selectedRow = table.getSelectedRow();
				int id = (int) table.getValueAt(selectedRow, 0);				
				
				
				int selectedIndex = table.getSelectedRow();
				
				System.out.println("Selected index"+selectedIndex);
				
				if (selectedIndex < 0) {
					JOptionPane.showMessageDialog(null, "Select a pharmacy to supply.");
				}else {
					Pharmacy selectedUser = DatabaseController.getUser(id);
					System.out.println(selectedIndex);
					System.out.println("Selected user: "+ selectedUser.getName());
				
					supplyStockPage = new SupplyStockPage(adminMainPage,selectedUser);
					supplyStockPage.setVisible(true);
				}
			}
		});
		btnSupply.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSupply.setBounds(384, 468, 117, 34);
		contentPane.add(btnSupply);
		setVisible(true);
		
	}
	
	
	
	public void refreshTable() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ArrayList<Pharmacy> pharmacies = DatabaseController.getUsers();
		for (Pharmacy pharmacy : pharmacies) {
			model.addRow(new Object[]{pharmacy.getId(),pharmacy.getName(), pharmacy.getDistrict(), pharmacy.getPhone()});

		}
	}
}
