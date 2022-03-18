package dtr;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class SearchDate {

	private JFrame frame;
	JComboBox<String> comboList;
	Dbcon db = new Dbcon();
	List<String> empList = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDate window = new SearchDate();
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
	public void popcomb() {
	
		
		
		try {
			
			db.preState = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE stats = 'Active' ORDER BY fname ASC");
			ResultSet rs = db.preState.executeQuery();
			while(rs.next()) {
				empList.add(rs.getString("fname") +" "+rs.getString("lname"));
			}
			empList.add("Print All");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SearchDate() {
		db.dbCon();
		popcomb();
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		comboList = new JComboBox<>();
		comboList.setModel(new DefaultComboBoxModel<String>(empList.toArray(new String[0])));
		comboList.setBounds(10, 114, 141, 22);
		frame.getContentPane().add(comboList);

		 
		
		JDateChooser toDate = new JDateChooser();
		toDate.setDateFormatString("yyyy-MM-dd");		
		toDate.setBounds(299, 116, 103, 20);
		
		frame.getContentPane().add(toDate);
		JDateChooser fromDate = new JDateChooser();
		fromDate.setDateFormatString("yyyy-MM-dd");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat sdfc = new SimpleDateFormat("yyyyMMdd"); 
	
		
		
		fromDate.setBounds(173, 116, 103, 20);
		frame.getContentPane().add(fromDate);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String fnames = comboList.getSelectedItem().toString();
					String fdate = sdf.format(fromDate.getDate());
					String tdate = sdf.format(toDate.getDate());
					String cfdate = sdfc.format(fromDate.getDate());
					String ctdate = sdfc.format(toDate.getDate());
					if(Integer.parseInt(cfdate) <= Integer.parseInt(ctdate)) {
					
						if(fnames == "Print All") {
							
							empList.remove(empList.size() - 1);
							
							for(int i=0;i<empList.size();i++) {
								SinglePrint.main(new String[]{empList.get(i),fdate,tdate});
							}
						}else {
						SinglePrint.main(new String[]{fnames,fdate,tdate});
						}
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Date Format", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
//					System.out.println("fullname : " + fnames);
//					System.out.println("from date : " + fdate);
//					System.out.println("to date : " + tdate);
					
					
				} catch(NullPointerException e1) {
					 JOptionPane.showMessageDialog(null, "Error Date", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(151, 175, 141, 75);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Employee Name.");
		lblNewLabel.setBounds(10, 89, 109, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("From:");
		lblNewLabel_1.setBounds(173, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("To:");
		lblNewLabel_2.setBounds(313, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Print Record");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_3.setBounds(146, 11, 278, 43);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
