package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import database.DatabaseController;
import model.BoxSize;
import model.DrugStock;
import model.Pharmacy;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class SupplyStockPage extends JFrame {

	private JPanel contentPane;

	public SupplyStockPage(AdminMainPage adminMainPage, Pharmacy selectedUser) {
		ArrayList<BoxSize> boxSizes = DatabaseController.getDrugInfo();
		setResizable(false);

		String username = selectedUser.getUsername();
		ArrayList<DrugStock> drugs = DatabaseController.getPharmacyStock(username);
		for (DrugStock drugStock : drugs) {
			System.out.println(drugStock.getName());
		}
		setBounds(100, 100, 685, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTalcidMg = new JLabel(drugs.get(0).getName());
		lblTalcidMg.setBounds(40, 180, 200, 20);
		lblTalcidMg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblTalcidMg);

		JLabel lblUltravistFlakon = new JLabel(drugs.get(1).getName());
		lblUltravistFlakon.setBounds(40, 220, 200, 20);
		lblUltravistFlakon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblUltravistFlakon);

		JLabel lblVisanneTablet = new JLabel(drugs.get(2).getName());
		lblVisanneTablet.setBounds(40, 260, 200, 20);
		lblVisanneTablet.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblVisanneTablet);

		JLabel lblMinoset = new JLabel(drugs.get(3).getName());
		lblMinoset.setBounds(40, 300, 200, 20);
		lblMinoset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblMinoset);

		JLabel lblAspirin = new JLabel(drugs.get(4).getName());
		lblAspirin.setBounds(40, 340, 200, 20);
		lblAspirin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblAspirin);

		JLabel lblBaclan = new JLabel(drugs.get(5).getName());
		lblBaclan.setBounds(40, 380, 200, 20);
		lblBaclan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblBaclan);

		JLabel lblCiproxin = new JLabel(drugs.get(6).getName());
		lblCiproxin.setBounds(40, 420, 200, 20);
		lblCiproxin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblCiproxin);

		JLabel lblGlucobay = new JLabel(drugs.get(7).getName());
		lblGlucobay.setBounds(40, 460, 200, 20);
		lblGlucobay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblGlucobay);

		JLabel lblLuminal = new JLabel(drugs.get(8).getName());
		lblLuminal.setBounds(40, 500, 200, 20);
		lblLuminal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblLuminal);

		JLabel lblXofigo = new JLabel(drugs.get(9).getName());
		lblXofigo.setBounds(40, 540, 200, 20);
		lblXofigo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblXofigo);

		JLabel lblBoxSize = new JLabel("Box Size");
		lblBoxSize.setBounds(274, 125, 74, 30);
		lblBoxSize.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblBoxSize);

		JLabel label = new JLabel(boxSizes.get(0).getBoxSize());
		label.setBounds(300, 180, 48, 14);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label);

		JLabel label_1 = new JLabel(boxSizes.get(1).getBoxSize());
		label_1.setBounds(300, 220, 48, 14);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_1);

		JLabel label_2 = new JLabel(boxSizes.get(2).getBoxSize());
		label_2.setBounds(300, 260, 48, 14);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_2);

		JLabel label_3 = new JLabel(boxSizes.get(3).getBoxSize());
		label_3.setBounds(300, 300, 48, 14);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_3);

		JLabel label_4 = new JLabel(boxSizes.get(4).getBoxSize());
		label_4.setBounds(300, 340, 48, 14);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_4);

		JLabel label_5 = new JLabel(boxSizes.get(5).getBoxSize());
		label_5.setBounds(300, 380, 48, 14);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_5);

		JLabel label_6 = new JLabel(boxSizes.get(6).getBoxSize());
		label_6.setBounds(300, 420, 48, 14);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_6);

		JLabel label_7 = new JLabel(boxSizes.get(7).getBoxSize());
		label_7.setBounds(300, 460, 48, 14);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_7);

		JLabel label_8 = new JLabel(boxSizes.get(8).getBoxSize());
		label_8.setBounds(300, 500, 48, 14);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_8);

		JLabel label_9 = new JLabel(boxSizes.get(9).getBoxSize());
		label_9.setBounds(300, 540, 48, 14);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label_9);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setBounds(425, 125, 74, 30);
		lblStock.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblStock);

		JLabel label1 = new JLabel();
		label1.setBounds(435, 180, 48, 14);
		label1.setText(String.valueOf(drugs.get(0).getStock()));
		label1.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label1);

		JLabel label2 = new JLabel();
		label2.setBounds(435, 220, 48, 14);
		label2.setText(String.valueOf(drugs.get(1).getStock()));
		label2.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label2);

		JLabel label3 = new JLabel();
		label3.setBounds(435, 260, 48, 14);
		label3.setText(String.valueOf(drugs.get(2).getStock()));
		label3.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label3);

		JLabel label4 = new JLabel();
		label4.setBounds(435, 300, 48, 14);
		label4.setText(String.valueOf(drugs.get(3).getStock()));
		label4.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label4);

		JLabel label5 = new JLabel(String.valueOf(drugs.get(4).getStock()));
		label5.setBounds(435, 340, 48, 14);
		label5.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label5);

		JLabel label6 = new JLabel(String.valueOf(drugs.get(5).getStock()));
		label6.setBounds(435, 380, 48, 14);
		label6.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label6);

		JLabel label7 = new JLabel(String.valueOf(drugs.get(6).getStock()));
		label7.setBounds(435, 420, 48, 14);
		label7.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label7);

		JLabel label8 = new JLabel(String.valueOf(drugs.get(7).getStock()));
		label8.setBounds(435, 460, 48, 14);
		label8.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label8);

		JLabel label9 = new JLabel(String.valueOf(drugs.get(8).getStock()));
		label9.setBounds(435, 500, 48, 14);
		label9.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label9);

		JLabel label10 = new JLabel(String.valueOf(drugs.get(9).getStock()));
		label10.setBounds(435, 540, 48, 14);
		label10.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(label10);

		JLabel lblStockPlus = new JLabel("Stock++");
		lblStockPlus.setBounds(555, 125, 74, 30);
		lblStockPlus.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 16));
		contentPane.add(lblStockPlus);

		JSpinner spinner1 = new JSpinner();
		spinner1.setBounds(560, 180, 70, 25);
		spinner1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner1.setModel(new SpinnerNumberModel(0, 0, null, new Integer(1)));
		contentPane.add(spinner1);

		JSpinner spinner2 = new JSpinner();
		spinner2.setBounds(560, 215, 70, 25);
		spinner2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner2.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner2);

		JSpinner spinner3 = new JSpinner();
		spinner3.setBounds(560, 255, 70, 25);
		spinner3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner3.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner3);

		JSpinner spinner4 = new JSpinner();
		spinner4.setBounds(560, 295, 70, 25);
		spinner4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner4.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner4);

		JSpinner spinner5 = new JSpinner();
		spinner5.setBounds(560, 335, 70, 25);
		spinner5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner5);

		JSpinner spinner6 = new JSpinner();
		spinner6.setBounds(560, 375, 70, 25);
		spinner6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner6.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner6);

		JSpinner spinner7 = new JSpinner();
		spinner7.setBounds(560, 415, 70, 25);
		spinner7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner7.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner7);

		JSpinner spinner8 = new JSpinner();
		spinner8.setBounds(560, 455, 70, 25);
		spinner8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner8.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner8);

		JSpinner spinner9 = new JSpinner();
		spinner9.setBounds(560, 495, 70, 25);
		spinner9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner9.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner9);

		JSpinner spinner10 = new JSpinner();
		spinner10.setBounds(560, 535, 70, 25);
		spinner10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinner10.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		contentPane.add(spinner10);

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(560, 600, 90, 30);
		btnEdit.setForeground(Color.BLACK);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int value1 = (int) spinner1.getValue();
				{
					DatabaseController.updateStock(1, value1, username);
				}
				int value2 = (int) spinner2.getValue();
				{
					DatabaseController.updateStock(2, value2, username);
				}

				int value3 = (int) spinner3.getValue();
				{
					DatabaseController.updateStock(3, value3, username);
				}
				int value4 = (int) spinner4.getValue();
				{
					DatabaseController.updateStock(4, value4, username);
				}
				int value5 = (int) spinner5.getValue();
				{
					DatabaseController.updateStock(5, value5, username);
				}
				int value6 = (int) spinner6.getValue();
				{
					DatabaseController.updateStock(6, value6, username);
				}
				int value7 = (int) spinner7.getValue();
				{
					DatabaseController.updateStock(7, value7, username);
				}
				int value8 = (int) spinner8.getValue();
				{
					DatabaseController.updateStock(8, value8, username);
				}
				int value9 = (int) spinner9.getValue();
				{
					DatabaseController.updateStock(9, value9, username);
				}
				int value10 = (int) spinner10.getValue();
				{
					DatabaseController.updateStock(10, value10, username);
				}
				try {
					setVisible(false);
					
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}

		});
		contentPane.add(btnEdit);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 673, 645);
		panel.setBackground(new Color(229, 232, 238));
		contentPane.add(panel);
		panel.setLayout(null);
		
				JLabel lblDrugs = new JLabel("Drugs");
				lblDrugs.setBounds(55, 124, 74, 30);
				panel.add(lblDrugs);
				lblDrugs.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 16));
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(229,232,238));
				panel_1.setBorder(new LineBorder(new Color(135, 206, 250), 3, true));
				panel_1.setBounds(161, 21, 400, 50);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel name = new JLabel(selectedUser.getName());
				name.setBounds(0, 0, 400, 50);
				panel_1.add(name);
				name.setForeground(new Color(128, 0, 128));
				name.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 18));
				name.setHorizontalAlignment(SwingConstants.CENTER);
		

	}
}
