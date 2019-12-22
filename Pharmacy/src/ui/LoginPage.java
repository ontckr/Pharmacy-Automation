package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import model.*;
import socket.ClientConnection;
import socket.ClientController;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameText;
	private JPasswordField passwordField;

	private static LoginPage login_page;
	
	public static void main(String[] args) throws IOException {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_page = new LoginPage();
					login_page.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginPage() {
		
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 288, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(35, 41, 65, 14);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(35, 92, 65, 14);
		contentPane.add(lblPassword);

		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameText.setBounds(126, 38, 96, 20);
		contentPane.add(usernameText);
		usernameText.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(126, 89, 96, 20);
		contentPane.add(passwordField);

		

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		btnLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				boolean result = ClientConnection.login(usernameText.getText(), passwordField.getText());
				System.out.println(result);
						 
		
			}
		});
		btnLogin.setBounds(89, 170, 89, 23);
		contentPane.add(btnLogin);
	}
}

