package dtr;
// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AdminPage {
	
	private Dbcon db = new Dbcon();
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage window = new AdminPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		JOptionPane.showMessageDialog(null, args);
		
	}

	
	
	
	
	
	/**
	 * Create the application.
	 */
	public AdminPage() {
		initialize();
		db.dbCon();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME ADMIN");
		lblNewLabel.setFont(new Font("Myanmar Text", Font.BOLD, 32));
		lblNewLabel.setBounds(156, 22, 407, 133);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.MAGENTA, 4, true));
		panel.setBounds(201, 149, 314, 268);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("EMPLOYEES");
		btnNewButton.setBounds(30, 26, 257, 97);
		panel.add(btnNewButton);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				EmployeePage.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 33));
		btnNewButton.setForeground(Color.ORANGE);
		
		JButton btnNewButton_1 = new JButton("PRINT DTR");
		btnNewButton_1.setBounds(28, 147, 259, 97);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SearchDate.main(null);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 34));
		btnNewButton_1.setForeground(Color.ORANGE);
		
		JButton btnNewButton_2 = new JButton("-Logout-");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dtr.main(null);
				frame.dispose();
				
			}
		});
		btnNewButton_2.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
	}
}
