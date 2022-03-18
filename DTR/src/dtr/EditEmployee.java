package dtr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class EditEmployee {

	private JFrame frame;
	private JTextField fn1;
	private JTextField ln1;
	private JTextField occup;
	private JTextField age;
	private JTextField emaill1;
	private Dbcon db = new Dbcon();
	private ResultSet res;
	private JComboBox<String> rest;
	private JComboBox<String> gender;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployee window = new EditEmployee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String Fcapitalize(String str)
	{
	    if(str == null) return str;
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	/**
	 * Create the application.
	 */
	
	public void Clearall() {
		
			fn1.setText("");
			ln1.setText("");
			occup.setText("");
			age.setText("");
			emaill1.setText("");
			rest.setSelectedItem("");
			gender.setSelectedItem("");
	
	}
	
	public void InsertEmp() {
		
		String fn,ln,ag,gend,mil,job,restr,uid;
		int idnum ;
		
		fn     =   Fcapitalize(fn1.getText());
		ln     =   Fcapitalize(ln1.getText());
		ag     =   age.getText();
		mil    =   emaill1.getText();
		job    =   Fcapitalize(occup.getText());
		restr  =   rest.getSelectedItem().toString();
		gend   =   gender.getSelectedItem().toString();
		
		
			try {
				db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl ORDER BY id DESC");
				res = db.preState.executeQuery();
				
				if(res.next() == true) {
					
					idnum = res.getInt("id") + 1;
					
					String idid = idnum + "";
					String zeroes;
					if(idid.length() == 1) {
						zeroes = "00";
						
						
					}else {
					
						zeroes = "0";
					}
					
					uid = fn.charAt(0) + "" + ln.charAt(0) + zeroes + idnum;
					
					db.preState = db.con.
							  prepareStatement("INSERT INTO employee_tbl(fname,lname,age,gender,emailadd,occupation,restriction,unique_id,stats) "
							  		+ "VALUES('"+fn+"','"+ln+"','"+ag+"','"+gend+"','"+mil+"','"+job+"','"+restr+"','"+uid+"', 'Active')"
							  );
					db.preState.executeUpdate();
					JOptionPane.showMessageDialog(null, "You added " + fn + " " + ln + " in the database");
					 Clearall();
				}else {
					
					idnum = 1;
					uid = fn.charAt(0) + "" + ln.charAt(0) + "00" + idnum;
					
					db.preState = db.con.
							  prepareStatement("INSERT INTO employee_tbl(fname,lname,age,gender,emailadd,occupation,restriction,unique_id,stats) "
							  		+ "VALUES('"+fn+"','"+ln+"','"+ag+"','"+gend+"','"+mil+"','"+job+"','"+restr+"','"+uid+"', 'Active')"
							  );
					db.preState.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "You added " + fn + " " + ln + " in the database");
					 Clearall();
				}
				
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
	
		
		
		/*
		 * JOptionPane.showMessageDialog(null, fn); JOptionPane.showMessageDialog(null,
		 * ln); JOptionPane.showMessageDialog(null, ag);
		 * JOptionPane.showMessageDialog(null, mil); JOptionPane.showMessageDialog(null,
		 * job); JOptionPane.showMessageDialog(null, restr);
		 * JOptionPane.showMessageDialog(null, gend);
		 */
		
	
		
		
	}
	
	public EditEmployee() {
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
		
		JLabel lblNewLabel = new JLabel("Add Employee");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(117, 11, 500, 88);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		fn1 = new JTextField();
		fn1.setFont(new Font("Tahoma", Font.BOLD, 15));
		fn1.setBounds(35, 120, 216, 48);
		frame.getContentPane().add(fn1);
		fn1.setColumns(10);
		
		ln1 = new JTextField();
		ln1.setFont(new Font("Tahoma", Font.BOLD, 15));
		ln1.setBounds(261, 120, 216, 48);
		frame.getContentPane().add(ln1);
		ln1.setColumns(10);
		
		occup = new JTextField();
		occup.setFont(new Font("Tahoma", Font.BOLD, 15));
		occup.setBounds(355, 206, 188, 48);
		frame.getContentPane().add(occup);
		occup.setColumns(10);
		
		ArrayList<String> resList = new ArrayList<>(Arrays.asList("", "Admin", "Employee")); 
		rest = new JComboBox<>();
		rest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rest.setModel(new DefaultComboBoxModel<String>(resList.toArray(new String[0])));
		rest.setBounds(567, 206, 92, 36);
		frame.getContentPane().add(rest);
		
		gender = new JComboBox<>();
		gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gender.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Male", "Female"}));
		gender.setBounds(587, 120, 72, 36);
		frame.getContentPane().add(gender);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(35, 95, 72, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(261, 95, 92, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(587, 95, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		age = new JTextField();
		age.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				
			}
		});
		age.setFont(new Font("Tahoma", Font.BOLD, 15));
		age.setBounds(487, 120, 86, 49);
		frame.getContentPane().add(age);
		age.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setBounds(487, 95, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		emaill1 = new JTextField();
		emaill1.setFont(new Font("Tahoma", Font.BOLD, 15));
		emaill1.setBounds(35, 206, 308, 48);
		frame.getContentPane().add(emaill1);
		emaill1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(35, 181, 46, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Occupation");
		lblNewLabel_6.setBounds(355, 179, 85, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Restriction");
		lblNewLabel_7.setBounds(567, 181, 92, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * String fn,ln,ag,gend,mil,job,restr;
				 * 
				 * 
				 * fn = fn1.getText(); ln = ln1.getText(); ag = age.getText(); mil =
				 * emaill1.getText(); job = occup.getText(); restr = "Employee"; gend = "Male";
				 */
				InsertEmp();
				
				
				
				
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnSubmit.setBounds(275, 328, 179, 60);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnBack = new JButton("<< Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage.main(null);
				frame.dispose();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnBack);
	}
}
