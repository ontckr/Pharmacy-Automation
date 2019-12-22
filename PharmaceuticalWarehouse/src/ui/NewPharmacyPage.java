package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import model.*;
import database.*;
import helper.*;

public class NewPharmacyPage extends JFrame {

	private JPanel contentPane;
	private JTextField nameText;
	private JTextField addressText;
	private JTextField emailText;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;

	private AdminMainPage adminMainPage;
	
	Connection connection = null;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_0;

	public NewPharmacyPage(AdminMainPage adminMainPage) throws ParseException {
		this.adminMainPage = adminMainPage;
		ArrayList<BoxSize> boxSizes = DatabaseController.getBoxSizes();
		setResizable(false);
		
		setTitle("Add New Pharmacy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 793, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblName.setBounds(28, 45, 68, 14);
		contentPane.add(lblName);
		
		JTextField nameText = new JTextField();
		nameText.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		nameText.setBounds(106, 45, 176, 22);
		contentPane.add(nameText);
		nameText.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblAddress.setBounds(28, 295, 68, 14);
		contentPane.add(lblAddress);
		
		JTextArea addressText = new JTextArea(5,10);
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		addressText.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		addressText.setWrapStyleWord(true);
		addressText.setLineWrap(true);
		addressText.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		addressText.setBounds(106, 295, 176, 106);
		contentPane.add(addressText);
		addressText.setColumns(10);
		
		JTextField emailText = new JTextField();
		emailText.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		emailText.setBounds(106, 95, 176, 22);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblEmail.setBounds(28, 95, 68, 14);
		contentPane.add(lblEmail);
		
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblPassword.setBounds(28, 145, 89, 14);
		contentPane.add(lblPassword);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("New pharmacy page closed.");
				setVisible(false);
			}
		});
		
		btnCancel.setBounds(28, 455, 89, 23);
		contentPane.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		passwordField.setBounds(106, 145, 176, 22);
		contentPane.add(passwordField);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblPhone.setBounds(28, 195, 68, 14);
		contentPane.add(lblPhone);
		
		MaskFormatter maskFormatter = new MaskFormatter("(0###) ### ## ##");
		
		JFormattedTextField phoneText = new JFormattedTextField(maskFormatter);
		phoneText.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		phoneText.setBounds(106, 195, 176, 25);
		contentPane.add(phoneText);
		
		
		JLabel lblDistrict = new JLabel("District:");
		lblDistrict.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblDistrict.setBounds(28, 245, 68, 14);
		contentPane.add(lblDistrict);
		
		JComboBox<String> DistrictComboBox = new JComboBox<String>();
		DistrictComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Alia\u011Fa", "Bal\u00E7ova", "Bornova", "Konak"}));
		DistrictComboBox.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		DistrictComboBox.setBounds(106, 245, 176, 26);
		contentPane.add(DistrictComboBox);
		JLabel lblTalcidMg = new JLabel(boxSizes.get(0).getName());
		lblTalcidMg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTalcidMg.setBounds(319, 46, 200, 20);
		contentPane.add(lblTalcidMg);

		JLabel lblUltravistFlakon = new JLabel(boxSizes.get(1).getName());
		lblUltravistFlakon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUltravistFlakon.setBounds(319, 86, 200, 20);
		contentPane.add(lblUltravistFlakon);

		JLabel lblVisanneTablet = new JLabel(boxSizes.get(2).getName());
		lblVisanneTablet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblVisanneTablet.setBounds(319, 126, 200, 20);
		contentPane.add(lblVisanneTablet);

		JLabel lblMinoset = new JLabel(boxSizes.get(3).getName());
		lblMinoset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMinoset.setBounds(319, 166, 200, 20);
		contentPane.add(lblMinoset);

		JLabel lblAspirin = new JLabel(boxSizes.get(4).getName());
		lblAspirin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAspirin.setBounds(319, 206, 200, 20);
		contentPane.add(lblAspirin);

		JLabel lblBaclan = new JLabel(boxSizes.get(5).getName());
		lblBaclan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBaclan.setBounds(319, 246, 200, 20);
		contentPane.add(lblBaclan);

		JLabel lblCiproxin = new JLabel(boxSizes.get(6).getName());
		lblCiproxin.setBackground(new Color(128, 0, 0));
		lblCiproxin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCiproxin.setBounds(319, 286, 200, 20);
		contentPane.add(lblCiproxin);

		JLabel lblGlucobay = new JLabel(boxSizes.get(7).getName());
		lblGlucobay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGlucobay.setBounds(319, 326, 200, 20);
		contentPane.add(lblGlucobay);

		JLabel lblLuminal = new JLabel(boxSizes.get(8).getName());
		lblLuminal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLuminal.setBounds(319, 366, 200, 20);
		contentPane.add(lblLuminal);

		JLabel lblXofigo = new JLabel(boxSizes.get(9).getName());
		lblXofigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblXofigo.setBounds(319, 406, 200, 20);
		contentPane.add(lblXofigo);
		
		JLabel lblBoxSize = new JLabel("Box Size");
		lblBoxSize.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblBoxSize.setBounds(495, 10, 74, 20);
		contentPane.add(lblBoxSize);
		
		JTextField textField_1 = new JTextField();
		textField_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		
		textField_1.setBounds(694, 86, 48, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		textField_2.setBounds(694, 126, 48, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		textField_3.setBounds(694, 166, 48, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		textField_4.setBounds(694, 206, 48, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblOrder = new JLabel("Order");
		lblOrder.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblOrder.setBounds(694, 10, 48, 20);
		contentPane.add(lblOrder);
		
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UserNameConverter uConverter = new UserNameConverter();
				
				String username = uConverter.Converter(nameText.getText());
				
				DatabaseController.newPharmacy(
						nameText.getText(), 
						addressText.getText(),
						emailText.getText(), 
						username,
						passwordField.getText(), 
						DistrictComboBox.getSelectedItem().toString(), 
						phoneText.getText());
				adminMainPage.refreshTable();
				
				/*
				String [] a = new String[10];
				
				for(int i = 0 ; i<=9 ;i++ ) {
					
					a[i] = "textField_" + i + ".getText()";
					
				}
				*/
				
				DatabaseController.newPharmacyStock(username, 1 ,Integer.parseInt(textField_0.getText()));
				DatabaseController.newPharmacyStock(username, 2 ,Integer.parseInt(textField_1.getText()));
				DatabaseController.newPharmacyStock(username, 3 ,Integer.parseInt(textField_2.getText()));
				DatabaseController.newPharmacyStock(username, 4 ,Integer.parseInt(textField_3.getText()));
				DatabaseController.newPharmacyStock(username, 5 ,Integer.parseInt(textField_4.getText()));
				DatabaseController.newPharmacyStock(username, 6 ,Integer.parseInt(textField_5.getText()));
				DatabaseController.newPharmacyStock(username, 7 ,Integer.parseInt(textField_6.getText()));
				DatabaseController.newPharmacyStock(username, 8 ,Integer.parseInt(textField_7.getText()));
				DatabaseController.newPharmacyStock(username, 9 ,Integer.parseInt(textField_8.getText()));
				DatabaseController.newPharmacyStock(username, 10 ,Integer.parseInt(textField_9.getText()));

				
				
				setVisible(false);
			}
		});
	
		btnCreate.setBounds(495, 455, 89, 23);
		contentPane.add(btnCreate);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(694, 246, 48, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(694, 286, 48, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_7.setColumns(10);
		textField_7.setBounds(694, 326, 48, 20);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_8.setColumns(10);
		textField_8.setBounds(694, 366, 48, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_9.setColumns(10);
		textField_9.setBounds(694, 406, 48, 20);
		contentPane.add(textField_9);
		
		textField_0 = new JTextField();
		textField_0.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		textField_0.setColumns(10);
		textField_0.setBounds(694, 46, 48, 20);
		contentPane.add(textField_0);
		
		JLabel boxSize1 = new JLabel(boxSizes.get(0).getBoxSize());
		boxSize1.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize1.setBounds(520, 46, 48, 14);
		contentPane.add(boxSize1);
		
		JLabel boxSize2 = new JLabel(boxSizes.get(1).getBoxSize());
		boxSize2.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize2.setBounds(520, 86, 48, 14);
		contentPane.add(boxSize2);
		
		JLabel boxSize3 = new JLabel(boxSizes.get(2).getBoxSize());
		boxSize3.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize3.setBounds(520, 126, 48, 14);
		contentPane.add(boxSize3);
		
		JLabel boxSize4 = new JLabel(boxSizes.get(3).getBoxSize());
		boxSize4.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize4.setBounds(520, 166, 48, 14);
		contentPane.add(boxSize4);
		
		JLabel boxSize5 = new JLabel(boxSizes.get(4).getBoxSize());
		boxSize5.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize5.setBounds(520, 206, 48, 14);
		contentPane.add(boxSize5);
		
		JLabel boxSize6 = new JLabel(boxSizes.get(5).getBoxSize());
		boxSize6.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize6.setBounds(520, 246, 48, 14);
		contentPane.add(boxSize6);
		
		JLabel boxSize7 = new JLabel(boxSizes.get(6).getBoxSize());
		boxSize7.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize7.setBounds(520, 286, 48, 14);
		contentPane.add(boxSize7);
		
		JLabel boxSize8 = new JLabel(boxSizes.get(7).getBoxSize());
		boxSize8.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize8.setBounds(520, 326, 48, 14);
		contentPane.add(boxSize8);
		
		JLabel boxSize9 = new JLabel(boxSizes.get(8).getBoxSize());
		boxSize9.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize9.setBounds(520, 366, 48, 14);
		contentPane.add(boxSize9);
		
		JLabel boxSize10 = new JLabel(boxSizes.get(9).getBoxSize());
		boxSize10.setFont(new Font("Tahoma", Font.BOLD, 15));
		boxSize10.setBounds(520, 406, 48, 14);
		contentPane.add(boxSize10);
		
		JLabel lblDrugs = new JLabel("Drugs");
		lblDrugs.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		lblDrugs.setBounds(343, 10, 74, 20);
		contentPane.add(lblDrugs);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(292, 11, 10, 490);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBounds(10, 10, 278, 490);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 224, 230));
		panel_1.setBounds(296, 10, 466, 490);
		contentPane.add(panel_1);
	}
}
