package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DrugStock;
import socket.ClientConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Font;

public class SellDrugPage extends JFrame {

	private String drug;
	private int id;
	private JPanel contentPane;
	private JFrame frame;
	private String username;

	public SellDrugPage(String username) {
		this.username = username;
		this.drug = drug;
		this.id = id;
		setBounds(100, 100, 468, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ArrayList<DrugStock> drugSell = ClientConnection.getPharmacyDrugs(username);
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.getSelectedItem();
				System.out.println(comboBox.getSelectedItem());
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { drugSell.get(0).getName(),
				drugSell.get(1).getName(), drugSell.get(2).getName(), drugSell.get(3).getName(),
				drugSell.get(4).getName(), drugSell.get(5).getName(), drugSell.get(6).getName(),
				drugSell.get(7).getName(), drugSell.get(8).getName(), drugSell.get(9).getName() }));
		comboBox.setBounds(10, 21, 316, 35);
		contentPane.add(comboBox);

		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		spinner.setBounds(337, 20, 93, 35);
		contentPane.add(spinner);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int value = (int) spinner.getValue();
				int stock = drugSell.get(comboBox.getSelectedIndex()).getStock();
				int id = comboBox.getSelectedIndex() + 1;
				
				String drug = comboBox.getSelectedItem().toString();
				if (stock != 0 && value <= stock) {

					ClientConnection.sendSoldDrug(id, value, username);
					System.out.println(drug);
					System.out.println(id);
					System.out.println(username);
					setVisible(false);


				} else if (value > stock) {
					JOptionPane.showMessageDialog(null, "Your stock is less than " + value);
					
				} else if (stock == 0) {
					JOptionPane.showMessageDialog(null,drug + " is not available in your stock. You can't sell " + drug);
				}
			}

		});

		btnNewButton.setBounds(183, 94, 100, 34);
		contentPane.add(btnNewButton);

	}
}
