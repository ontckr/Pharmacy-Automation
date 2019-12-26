package ui;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
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

public class EditPharmacyPage extends JFrame {
	private JPanel contentPane;

	private AdminMainPage adminMainPage;

	public EditPharmacyPage(AdminMainPage adminMainPage, Pharmacy selectedUser) {
		setResizable(false);
		

		this.adminMainPage = adminMainPage;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 699, 724);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblName.setBounds(30, 33, 68, 14);
		contentPane.add(lblName);

		JLabel nameText = new JLabel();
		nameText.setText(selectedUser.getName());

		nameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameText.setBounds(100, 30, 149, 20);
		contentPane.add(nameText);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblAddress.setBounds(100, 72, 75, 14);
		contentPane.add(lblAddress);

		JLabel addressText = new JLabel();
		addressText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addressText.setBounds(185, 70, 149, 20);
		contentPane.add(addressText);
		addressText.setText(selectedUser.getAddress());

		JLabel emailText = new JLabel();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailText.setBounds(502, 31, 149, 20);
		contentPane.add(emailText);
		emailText.setText(selectedUser.getEmail());

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblEmail.setBounds(440, 33, 68, 14);
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

		btnCancel.setBounds(44, 640, 90, 30);
		contentPane.add(btnCancel);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblPhone.setBounds(230, 33, 68, 14);
		contentPane.add(lblPhone);

		try {
			MaskFormatter maskFormatter = new MaskFormatter("(0###) ### ## ##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel phoneText = new JLabel();
		phoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneText.setBounds(300, 30, 149, 20);
		contentPane.add(phoneText);
		phoneText.setText(selectedUser.getPhone());

		JLabel lblDistrict = new JLabel("District:");
		lblDistrict.setFont(new Font("Microsoft JhengHei", Font.BOLD, 17));
		lblDistrict.setBounds(387, 72, 68, 14);
		contentPane.add(lblDistrict);

		JLabel districtText = new JLabel();
		districtText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		districtText.setBounds(465, 69, 149, 22);
		districtText.setText(selectedUser.getDistrict());
		contentPane.add(districtText);

		
		
		
		
		
		
		
		
		
	}
}
