package ui;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import database.*;
import model.*;
import database.*;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPharmacyPage extends JFrame {
	private JPanel contentPane;

	private AdminMainPage adminMainPage;

	public EditPharmacyPage(AdminMainPage adminMainPage, Pharmacy selectedUser) throws ParseException {
		setResizable(false);
		
		String username = selectedUser.getUsername();
		this.adminMainPage = adminMainPage;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 377, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblName.setBounds(30, 35, 68, 16);
		contentPane.add(lblName);

		JLabel nameText = new JLabel();
		nameText.setText(selectedUser.getName());

		nameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameText.setBounds(115, 35, 200, 23);
		contentPane.add(nameText);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblAddress.setBounds(30, 235, 75, 16);
		contentPane.add(lblAddress);

		JTextField emailText = new JTextField();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailText.setBounds(115, 135, 200, 23);
		contentPane.add(emailText);
		emailText.setText(selectedUser.getEmail());

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblEmail.setBounds(30, 135, 68, 16);
		contentPane.add(lblEmail);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("New pharmacy page closed.");
				setVisible(false);
			}
		});

		btnCancel.setBounds(30, 395, 90, 30);
		contentPane.add(btnCancel);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblPhone.setBounds(30, 85, 68, 16);
		contentPane.add(lblPhone);

	
		
		

		MaskFormatter maskFormatter = new MaskFormatter("(0###) ### ## ##");
		
		JFormattedTextField phoneText = new JFormattedTextField(maskFormatter);
		phoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneText.setBounds(115, 85, 200, 23);
		phoneText.setText(selectedUser.getPhone());
		contentPane.add(phoneText);
		

		JLabel lblDistrict = new JLabel("District:");
		lblDistrict.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDistrict.setBounds(30, 185, 68, 16);
		contentPane.add(lblDistrict);
		
		JComboBox<String> districtComboBox= new JComboBox<String>();
		districtComboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Alia\u011Fa", "Bal\u00E7ova", "Bornova", "Konak" }));
		districtComboBox.setSelectedItem(selectedUser.getDistrict());
		districtComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		districtComboBox.setBounds(115, 185, 200, 23);
		contentPane.add(districtComboBox);
		
		JTextArea addressTextArea = new JTextArea(5, 10);
		addressTextArea.setText(selectedUser.getAddress());
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		addressTextArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addressTextArea.setWrapStyleWord(true);
		addressTextArea.setLineWrap(true);
		addressTextArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		addressTextArea.setColumns(10);
		addressTextArea.setBounds(115, 235, 200, 106);
		contentPane.add(addressTextArea);
		
		JButton Edit = new JButton("Edit");
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseController.updatePharmacyInfo(username, addressTextArea.getText(), emailText.getText(), districtComboBox.getSelectedItem().toString(), phoneText.getText());
				adminMainPage.refreshTable();
				setVisible(false);
			}
		});
		Edit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Edit.setBounds(252, 395, 90, 30);
		contentPane.add(Edit);
		
		
		

		
		
		
		
		
		
		
		
		
	}
}
