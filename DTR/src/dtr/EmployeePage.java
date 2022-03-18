package dtr;
// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeePage {

	private JFrame frame;
	private JTable empTable;
	private JScrollPane scrollPane;
	private Dbcon db = new Dbcon();
	private ResultSet res;
	private DefaultTableModel tbl_mdl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage window = new EmployeePage();
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
	public void EmployeeDisplay(){
		 
		
			try {

				db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl");
				res = db.preState.executeQuery();
				
				 // table.setModel(DbUtils.resultSetToTableModel(res)); //use this to display all info in the table 
				
				
				
				tbl_mdl = (DefaultTableModel) empTable.getModel();
				String fullname, uid, stats,restr,statuss;
				String[] heder = { "Unique Id Number", "Full name", "Position","Restriction", "Status"};
				
				//int numa = 1;
				
				for (int i = 0; i < heder.length; i++) {
				
					tbl_mdl.setColumnIdentifiers(heder);// header
					// colNames[i] = rsmd.getColumnName(i + 1);
					// tbl_mdl.setColumnIdentifiers(colNames);

					while (res.next()) {
						fullname = res.getString("fname") + " " + res.getString("lname");
						restr = res.getString("restriction");
						uid = res.getString("unique_id");
						stats = res.getString("occupation");
						statuss = res.getString("stats");
						String[] roww = {uid, fullname, stats, restr, statuss};
						tbl_mdl.addRow(roww);
					}

				}

			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public void miceClicked() {
		
		int rownum = empTable.getSelectedRow();
		//int colnum = empTable.getSelectedColumn();
		
		
		tbl_mdl = (DefaultTableModel) empTable.getModel();
		String uid_pass =  tbl_mdl.getValueAt(rownum, 0).toString();
		
		
		
		EUP_emp.main(uid_pass);
		frame.dispose();
		//tf_emp.setText(tbl_mdl.getValueAt(rownum, 0).toString());
	}
	public EmployeePage() {
		initialize();
		db.dbCon();
		EmployeeDisplay();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 528);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("<< Back");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPage.main(null);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(20, 15, 89, 39);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 65, 675, 397);
		frame.getContentPane().add(scrollPane);
		
		empTable = new JTable();
		empTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				miceClicked() ;			
				
			}
		});
		empTable.setForeground(Color.WHITE);
		empTable.setBackground(Color.BLACK);
		empTable.setFont(new Font("Tahoma", Font.BOLD, 11));
		empTable.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
		empTable.getTableHeader().setBackground(Color.WHITE);
		empTable.getTableHeader().setResizingAllowed(false);
		empTable.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(empTable);
		
		JLabel lblNewLabel = new JLabel("Employee List");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(187, 15, 352, 39);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Add New");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditEmployee.main(null);
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(592, 11, 103, 43);
		frame.getContentPane().add(btnNewButton_1);
	}
}
