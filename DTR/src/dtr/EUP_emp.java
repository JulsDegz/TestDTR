package dtr;
// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EUP_emp {

	private JFrame frame;
	private Dbcon db = new Dbcon();
	private ResultSet res;
	JTextField fn1;
	JTextField ln1;
	JTextField occups,emaill1,tfAge;
	JComboBox<String> rest,gender;
	JLabel id_disp;
	JComboBox<String> jcStats;
	
	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EUP_emp window = new EUP_emp();
					window.frame.setVisible(true);
					
					//declaire the class object to aviod using static methods
					
					//just instanciate
					
					//passing value through main then pass again into a method
					
					window.tfValue(args);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});
		
	}


	/**
	 * Create the application.
	 */
	
	public void tfValue(String uid) {
		
		String fname,lname,age,gen,email,occup,restr,ids,stat;
		
		try {
			
			db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE unique_id = '" + uid + "' ");
			res = db.preState.executeQuery();
			
			
			
			if (res.next() == true) {
				
				ids       =     res.getString("id");
				fname     =     res.getString("fname");
				lname     =     res.getString("lname");
				occup     =     res.getString("occupation");
				restr     =     res.getString("restriction");
				age       =     res.getString("age");
				gen       =     res.getString("gender");
				email     =     res.getString("emailadd");
				stat     =     res.getString("stats");
				
				
				fn1.setText(fname);
				ln1.setText(lname);
				occups.setText(occup);
				emaill1.setText(email);
				rest.setSelectedItem(restr);
				gender.setSelectedItem(gen);
				jcStats.setSelectedItem(stat);
				
				tfAge.setText(age);
				id_disp.setText(ids);
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	
		
		
	}
	
	public EUP_emp() {
		db.dbCon();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 528);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Information");
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
		
		occups = new JTextField();
		occups.setFont(new Font("Tahoma", Font.BOLD, 15));
		occups.setBounds(369, 206, 188, 48);
		frame.getContentPane().add(occups);
		occups.setColumns(10);
		
		ArrayList<String> resList = new ArrayList<>(Arrays.asList("Admin", "Employee")); 
		ArrayList<String> gend = new ArrayList<>(Arrays.asList("Male", "Female")); 
		ArrayList<String> stats = new ArrayList<>(Arrays.asList("Active", "Hidden")); 

		rest = new JComboBox<>();
		rest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rest.setModel(new DefaultComboBoxModel<String>(resList.toArray(new String[0])));
		rest.setBounds(567, 206, 92, 36);
		frame.getContentPane().add(rest);
		
		gender = new JComboBox<>();
		gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gender.setModel(new DefaultComboBoxModel<>(gend.toArray(new String[0])));
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
		tfAge = new JTextField();
		tfAge.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfAge.setBounds(487, 120, 86, 49);
		frame.getContentPane().add(tfAge);
		tfAge.setColumns(10);
		
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
		lblNewLabel_6.setBounds(369, 181, 85, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Restriction");
		lblNewLabel_7.setBounds(567, 181, 92, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
						
				
					String upId = id_disp.getText();
					String upFname = fn1.getText();
					String upLname = ln1.getText();
					String upAge = tfAge.getText();
					String upGend = gender.getSelectedItem().toString();
					String upEmail = emaill1.getText();
					String upOccup = occups.getText();
					String upRest = rest.getSelectedItem().toString();
					String upsts = jcStats.getSelectedItem().toString();
							
					try {
						
						db.preState = db.con.prepareStatement("UPDATE employee_tbl SET fname =?, lname =?, age=?, gender=?, emailadd=?, occupation=?, restriction=?, stats=? WHERE id = '"+ upId +"' ");
						db.preState.setString(1, upFname);
						db.preState.setString(2, upLname);
						db.preState.setString(3, upAge);
						db.preState.setString(4, upGend);
						db.preState.setString(5, upEmail);
						db.preState.setString(6, upOccup);
						db.preState.setString(7, upRest);
						db.preState.setString(8, upsts);
						db.preState.executeUpdate();
						
						EmployeePage.main(null);
						frame.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
			
			
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnUpdate.setBounds(203, 340, 179, 60);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("<< Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EmployeePage.main(null);
				frame.dispose();
			
			
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_8 = new JLabel("ID Number :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(35, 340, 92, 23);
		frame.getContentPane().add(lblNewLabel_8);
		
		id_disp = new JLabel("");
		id_disp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		id_disp.setBounds(125, 340, 38, 23);
		frame.getContentPane().add(id_disp);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fullnames = fn1.getText() + " " + ln1.getText();
				String gends;
				if(gender.getSelectedItem().toString() == "Male") {
					gends = "his";
				}else {
					gends = "her";
				}
				
				int dialogButton = JOptionPane.showConfirmDialog (null, "Delete " + fullnames + " ? \r\nAll of "+gends+" data will also be deleted ","WARNING",JOptionPane.YES_NO_OPTION);

				if(dialogButton == JOptionPane.YES_OPTION) 
				{
				
					
				
				}
				if(dialogButton == JOptionPane.NO_OPTION) 
				{
					
					
				}
				
				
			}
		});
		btnDelete.setBounds(410, 340, 188, 60);
		frame.getContentPane().add(btnDelete);
		
		
		
		jcStats = new JComboBox<>();
		jcStats.setModel(new DefaultComboBoxModel<>(stats.toArray(new String[0])));
		jcStats.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jcStats.setBounds(35, 288, 85, 36);
		frame.getContentPane().add(jcStats);
		
		JLabel lblNewLabel_9 = new JLabel("Status");
		lblNewLabel_9.setBounds(35, 265, 46, 14);
		frame.getContentPane().add(lblNewLabel_9);
	}
}