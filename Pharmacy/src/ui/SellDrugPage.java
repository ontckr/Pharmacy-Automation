package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import socket.ClientConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SellDrugPage extends JFrame {
	
	private String drug;
	private int id;
	private JPanel contentPane;
	private JFrame frame;
	private String username;
	public SellDrugPage(String drug,int id, String username,int stock) {
		this.username=username;
		this.drug=drug;
		this.id=id;
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(drug);
		lblNewLabel.setBounds(10, 45, 161, 35);
		contentPane.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(181, 45, 93, 35);
		contentPane.add(spinner);
		
		
		JButton btnNewButton = new JButton("OK");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = (int) spinner.getValue();
				if(stock!=0 && value<stock) {
					
					ClientConnection.sendSoldDrug(id, value, username);
					System.out.println(drug);
					System.out.println(id);
					System.out.println(username);
				}else {
					JOptionPane.showMessageDialog(null, drug+" is not available in your stock. You can't sell "+drug);
					setVisible(false);
				}
				setVisible(false);
				
			}
			
		});
		
		btnNewButton.setBounds(185, 127, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
