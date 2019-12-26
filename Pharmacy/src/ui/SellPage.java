package ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import model.DrugStock;
import socket.ClientConnection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SellPage extends JFrame {

	private JPanel contentPane;

	public SellPage(String username) {

		ArrayList<DrugStock> drugSell = ClientConnection.getPharmacyDrugs(username);

		setBounds(100, 100, 396, 105);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.getSelectedItem();
				System.out.println(comboBox.getSelectedItem());
				SellDrugPage sellDrug = null;
				try {
					int id = comboBox.getSelectedIndex() + 1;
					sellDrug = new SellDrugPage(comboBox.getSelectedItem().toString(), id, username,
							drugSell.get(comboBox.getSelectedIndex()).getStock());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				sellDrug.setVisible(true);

			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { drugSell.get(0).getName(),
				drugSell.get(1).getName(), drugSell.get(2).getName(), drugSell.get(3).getName(),
				drugSell.get(4).getName(), drugSell.get(5).getName(), drugSell.get(6).getName(),
				drugSell.get(7).getName(), drugSell.get(8).getName(), drugSell.get(9).getName() }));
		comboBox.setBounds(45, 21, 316, 35);
		contentPane.add(comboBox);
	}
}
