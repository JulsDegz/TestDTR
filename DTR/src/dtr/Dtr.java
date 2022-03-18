package dtr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

//import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Dtr{

	private JFrame frame;
	private JLabel clckLabel;
	public JTable table;
	private Dbcon db = new Dbcon();
	private CellColour celcol = new CellColour();
	private String unamee, passs;
	private ResultSet res,res2;
	private DefaultTableModel tbl_mdl;
	private JButton btnin;
	private JButton btnout;
	private JPanel panel_1;
	private JTextField uname;
	private JTextField passw;
	private JPanel adminPanel, panel_2;
	private int clck;
	private JSplitPane splitPane_1;
	private JTextField tf_emp;
	private JPanel panel_3;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dtr window = new Dtr();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	

	
	public void hides() {
		tf_emp.setText("");
		splitPane_1.setVisible(false);
	}
	
	
	void myTimeFunc() {
		Thread threadName = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar calen = new GregorianCalendar();
						int seco = calen.get(Calendar.SECOND);
						int mins = calen.get(Calendar.MINUTE);
						int hrs = calen.get(Calendar.HOUR);
						
						clckLabel.setText(String.format("%02d", hrs) + " : " + String.format("%02d", mins) + " : "
								+ String.format("%02d", seco));
						sleep(1000);
					}

				} catch (Exception m) {
					JOptionPane.showMessageDialog(null, m);
				}

			}

		};
		threadName.start();

	}



	
	void repaintss() {
		  adminPanel.setVisible(false);
		  table.setDefaultRenderer(Object.class, celcol);
		  table.repaint();
	}
	
	void ref_tbl() {
		
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		
	}
	void tbl_dis() {
		
		 
		
		try {

			db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE stats = 'Active'");
			res = db.preState.executeQuery();
			
			 // table.setModel(DbUtils.resultSetToTableModel(res)); //use this to display all info in the table 
			
			
			
			DefaultTableModel tbl_mdl = (DefaultTableModel) table.getModel();
			String fullname, uid,actmethod;
			ArrayList<String> heder2 = new ArrayList<>(Arrays.asList( "", "Full Name", "Action"));
			
			String[] heder = { "Unique_id", "Fullname", "Status" };
			
			
		
			
			for (int i = 0; i < heder.length ; i++) {
				
				tbl_mdl.setColumnIdentifiers(heder2.toArray());// header //should b array no need to loop index
				
				// colNames[i] = rsmd.getColumnName(i + 1);
				// tbl_mdl.setColumnIdentifiers(colNames);

				while (res.next()) {
					
					fullname = res.getString("fname") + " " + res.getString("lname");
					
					uid = res.getString("unique_id");
				
					//stats = res.getString("position");
					
						actmethod = res.getString("id").toString();
						String ans = retval(actmethod);
				
					String[] roww = {uid, fullname, ans};
					
					tbl_mdl.addRow(roww);
					
				}
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

	}
	
	String retval(String id) {
		splitPane_1.setVisible(false);
		String ans = null;
		try {
			db.preState = db.con.prepareStatement("SELECT * FROM employee_time WHERE id = '" + id + "' and outDate = '0' ");
			res2 = db.preState.executeQuery();
			if(res2.next() == true) {
				ans =  "OUT";
			}else {
				ans = "IN";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ans = null;
		}
		
		return ans;
	}
	
	void TimeIns() {
		
			Date dates = new Date();
		    SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
		    SimpleDateFormat sdf_date = new SimpleDateFormat("yyyyMMdd");
		    SimpleDateFormat sdf_date2 = new SimpleDateFormat("MMM/dd/yyyy");
		    
		    
		    String tempTime = "00:00:00";
		    String tempDate = "0";
		    String inTime = sdf_time.format(dates);
		    String inDate = sdf_date.format(dates);
		    String disDate = sdf_date2.format(dates);
			String empId = tf_emp.getText();
			String fullname,ids;
			
		try {
			
			db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE unique_id = ?");
			db.preState.setString(1, empId); 
			
			res = db.preState.executeQuery();
			if(res.next() == true) {
					
				fullname = res.getString("fname") + " " + res.getString("lname");
				ids = res.getString("id");
				
				db.preState = db.con.prepareStatement
						("INSERT INTO employee_time(id, fullname, inDate, outDate, inTime, outTime, dateDisplay, dayId) VALUES(?,?,?,?,?,?,?,?)");
				
				db.preState.setString(1, ids);
				db.preState.setString(2, fullname);
				db.preState.setString(3, inDate);
				db.preState.setString(4, tempDate);
				db.preState.setString(5, inTime);
				db.preState.setString(6, tempTime);
				db.preState.setString(7, disDate);
				db.preState.setString(8, ids + inDate);
				db.preState.executeUpdate();
				tf_emp.setEditable(true);
				hides();
				JOptionPane.showMessageDialog(null,"Employee "+fullname+", Timed In at "+inTime+" on "+disDate);
				
			}else {
				
				hides();
				JOptionPane.showMessageDialog(null, empId + " Employee Id not valid");
				
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 ref_tbl();
		 tbl_dis();
		
		
		
	}
	
	void TfSearch(String tfVal) {
		
		try {
			ResultSet rset,rset2;
			String idid;
			db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE unique_id = '"+tfVal+"'");
			rset = db.preState.executeQuery();
			
			
			if(rset.next() == true) {
				idid = rset.getString("id");
				
				db.preState3 = db.con.prepareStatement("SELECT * FROM employee_time WHERE id = '" + idid + "' and outDate = '0' ");
				rset2 = db.preState3.executeQuery();
				if(rset2.next()) {
					TimeOuts();
				}else {
					TimeIns();
				}
								
			} else {
				
				JOptionPane.showMessageDialog(null,"NO VALUE");
			
			}
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	public String Disdate() {
		Date dates = new Date();
	    SimpleDateFormat sdf_date2 = new SimpleDateFormat("MMM/dd/yyyy");
	    String disDate = sdf_date2.format(dates);
		return disDate;
	}
	void TimeOuts() {
		Date dates = new Date();
	    SimpleDateFormat sdf_time = new SimpleDateFormat("hh:mm:ss");
	    SimpleDateFormat sdf_date = new SimpleDateFormat("yyyyMMdd");
	    
	   
	    String fllname;
	    String tempDate = "0";
	    String outTime = sdf_time.format(dates);
	    String outDate = sdf_date.format(dates);
		String empId = tf_emp.getText();
		String ids;
		
		
		try {
			db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE unique_id = ?");
			db.preState.setString(1, empId); 
			res = db.preState.executeQuery();
			
			if(res.next() == true) {
				
				
				ids = res.getString("id");
				
				db.preState2 = db.con.prepareStatement("SELECT * FROM employee_time WHERE id = ? and outDate = ?");
				db.preState2.setString(1, ids);
				db.preState2.setString(2, tempDate);
				res2 = db.preState2.executeQuery();
				if(res2.next() == true) {
					String disdat = res2.getString("dateDisplay");
					fllname = res2.getString("fullname");
					String timeId = res2.getString("time_id");
					db.preState3 = db.con.prepareStatement("UPDATE  employee_time SET outDate =?, outTime =? WHERE time_id = ?");
					db.preState3.setString(1, outDate);
					db.preState3.setString(2, outTime);
					db.preState3.setString(3, timeId);
					db.preState3.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Employee "+fllname+", Timed Out at "+outTime+" on "+disdat);
					tf_emp.setEditable(true);
					hides();
					
					
				}else {
					hides();
					JOptionPane.showMessageDialog(null, "TIMED IN FIRST");
				}
				
				
				
			}else {
				
				
				JOptionPane.showMessageDialog(null, empId + " Employee Id not valid");
				
				
			}
			
			
			 ref_tbl();
			 tbl_dis();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	 
	public Dtr() {
		// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 
		//WHICH GOES 1ST AND LAST
		db.dbCon(); //1st db connect 
		//popCombo(); //2nd create a method of arraylist to populate in combobox 
		
		initialize(); //initialize the interface
		//its ok to set text or value after initialize but not replace
		tbl_dis(); //if not executed 1st the combobox would be empty
		myTimeFunc();
		repaintss();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 738, 528);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "HELLO USER", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(6, 11, 694, 72);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		clck = 2;
		
		adminPanel = new JPanel();
		adminPanel.setBackground(Color.LIGHT_GRAY);
		adminPanel.setBounds(149, 11, 535, 50);
		panel_1.add(adminPanel);
		adminPanel.setLayout(null);
		
		uname = new JTextField();
		uname.setBounds(10, 19, 167, 20);
		adminPanel.add(uname);
		uname.setColumns(10);
		
		passw = new JTextField();
		passw.setBounds(187, 19, 179, 20);
		adminPanel.add(passw);
		passw.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		
				unamee = uname.getText();
				passs = passw.getText();
			//	String adm = "admin";
				

				try {
					
					db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE fname = '" + unamee + "' AND unique_id = '"+ passs +"' AND restriction = 'Admin'");
					res = db.preState.executeQuery();
					if(res.next()) {
						
				
						AdminPage.main(null);
						frame.dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Information");
					}
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
		});
		btnNewButton_1.setBounds(376, 18, 83, 23);
		adminPanel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setBounds(10, 0, 65, 14);
		adminPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(187, 0, 54, 14);
		adminPanel.add(lblNewLabel_2);
		
		panel_2 = new JPanel();
		panel_2.setBounds(215, 11, 469, 50);
		panel_1.add(panel_2);
		
		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(clck == 1) {
					adminPanel.setVisible(false);	
					btnNewButton.setText("ADMIN");
					
				}else if(clck == 2){
					adminPanel.setVisible(true);	
					btnNewButton.setText("CLOSE");
					clck = 0;
				}
				clck++;
				
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(10, 23, 90, 38);
		panel_1.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), Disdate() , TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(376, 121, 318, 270);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.RED);
		panel_3.setForeground(Color.MAGENTA);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		panel_3.setBounds(10, 21, 298, 62);
		panel.add(panel_3);
		panel_3.setLayout(null);

		clckLabel = new JLabel("");
		clckLabel.setBounds(0, 0, 298, 62);
		panel_3.add(clckLabel);
		clckLabel.setOpaque(true);
		clckLabel.setForeground(Color.BLACK);
		clckLabel.setBackground(new Color(173, 255, 47));
		clckLabel.setHorizontalAlignment(JLabel.CENTER);
		clckLabel.setFont(new Font("Arial Black", Font.BOLD, 23));
		
		splitPane_1 = new JSplitPane();
		splitPane_1.setBounds(77, 181, 179, 62);
		panel.add(splitPane_1);
		
		btnin = new JButton("TIME IN");
		btnin.setBackground(new Color(255, 255, 0));
		btnin.setForeground(new Color(0, 0, 0));
		btnin.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
			btnin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					TimeIns();
					btnout.setVisible(false);
				}
			});
		splitPane_1.setLeftComponent(btnin);
		
		btnout = new JButton("TIME OUT");
		btnout.setBackground(new Color(255, 51, 0));
		btnout.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
			btnout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					TimeOuts();
					btnin.setVisible(false);
				}
			});
		splitPane_1.setRightComponent(btnout);
		
		tf_emp = new JTextField();
		tf_emp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				int tfCount = tf_emp.getText().length();
				String tfVal =  tf_emp.getText();
				
				
				
				if(tfCount == 5) {
				
					TfSearch(tfVal);
					
				}
				
				
			}
		});
		/*
		 * tf_emp.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyReleased(KeyEvent e) {
		 * 
		 * textFieldSearch("" + e.getKeyChar());
		 * 
		 * } });
		 */
	
		
			
		
		
		
		tf_emp.setFont(new Font("SansSerif", Font.BOLD, 21));
		tf_emp.setBounds(77, 98, 179, 43);
		panel.add(tf_emp);
		tf_emp.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 94, 293, 341);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		
//		table.getTableHeader().setDefaultRenderer(new CellColour());
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tbl_mdl = (DefaultTableModel) table.getModel();
				int rownum = table.getSelectedRow();
				//int colnum = table.getSelectedColumn();
				splitPane_1.setVisible(true);
				
				String statss = tbl_mdl.getValueAt(rownum, 2).toString();
				//JOptionPane.showMessageDialog(null, rownum );
				tf_emp.setText(tbl_mdl.getValueAt(rownum, 0).toString());
				tf_emp.setEditable(false);
				
				if(statss == "IN") {
					btnout.setVisible(false);
					btnin.setVisible(true);
				}else {
					btnin.setVisible(false);
					btnout.setVisible(true);
				}
				
			}
		});
		table.setForeground(Color.RED);
		scrollPane.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);
		

	}
}




