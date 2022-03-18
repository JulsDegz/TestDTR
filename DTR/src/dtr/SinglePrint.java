package dtr;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SinglePrint {

	private JFrame frame;
	private JTable dis_tbl;
	Dbcon db = new Dbcon();
	private JLabel lblName,lblUid,lblFrom,lblTo;
	private String fllname;
	private String uids;
	private String fdate,ffdate;
	private String tdate,ttdate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SinglePrint window = new SinglePrint();
							window.frame.setVisible(true);
							try {
							window.uids = window.getUid(args[0]);
							window.fllname = args[0];
							window.fdate = args[1];
							window.tdate = args[2];
							window.setval();
							}catch(ArrayIndexOutOfBoundsException e1) {
								
								window.uids = "";
								window.fllname = "";
								window.fdate = "";
								window.tdate = "";
								
							}
							
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public void setval() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
//			Date date1 = sdf.parse(fdate);
//			Date date2 = sdf.parse(tdate);
			lblName.setText("Name : "+fllname);
			lblUid.setText("Unique Id : "+uids);
			lblFrom.setText("Pay Period Start : " + fdate);
			lblTo.setText("Pay Period End : "+ tdate);
			
			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");

			try {

			     ffdate = myFormat.format(fromUser.parse(fdate));
			     ttdate = myFormat.format(fromUser.parse(tdate));
			     DisTime();
//			    System.out.println(fdate);
//				System.out.println(tdate);
//			    System.out.println(ffdate);
//			    System.out.println(ttdate);
			} catch (ParseException e) {
			    e.printStackTrace();
			}
		
		
	
		
		
		
		
		
	}
	
	public String getUid(String fn) {
		
		String uid = null;
		
		try {
			db.preState = db.con.prepareStatement("SELECT * FROM employee_time WHERE fullname = '"+fn+"'");
			ResultSet rss = db.preState.executeQuery();
			if(rss.next()) {
				String idd = rss.getString("id");
				db.preState2 = db.con.prepareStatement("SELECT * FROM employee_tbl WHERE id = '"+idd+"'");
				ResultSet rs2 = db.preState2.executeQuery();
				if(rs2.next()) {
					 uid = rs2.getString("unique_id");
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uid;
		
	}
	
	public void DisTime() {

		String[] heder = {"","Date","Time In","Time Out","Daily Hours"};
		DefaultTableModel tableModel = (DefaultTableModel)dis_tbl.getModel();
		
		 try {
			 db.preState = db.con.prepareStatement("SELECT * FROM employee_time WHERE fullname = '"+fllname+"'");
			 ResultSet res1 = db.preState.executeQuery();
			
			 long diff = 0;
			 
			 
			 for(int i= 0; i<heder.length;i++) {
				 tableModel.setColumnIdentifiers(heder);
				 
				 while(res1.next()) {
					 
					 int comppp = Integer.parseInt(res1.getString("inDate"));
					 int comppp2 = Integer.parseInt(res1.getString("outDate"));
					 
					if(comppp2 > 0 && comppp >= Integer.parseInt(ffdate) && comppp <= Integer.parseInt(ttdate)) {
					 
						
					 String timeId = res1.getString("time_id");
					 String ddisplay = res1.getString("dateDisplay");
					 String inn = res1.getString("inTime");
					 String outt = res1.getString("outTime");
					 String inDate = res1.getString("inDate");
					 String outDate = res1.getString("outDate");
					 TimeSolve ts = new TimeSolve(inn,outt,inDate,outDate);
					    long timee = ts.TotalTime();
					 	long diffSeconds = timee / 1000 % 60;
						long diffMinutes = timee / (60 * 1000) % 60;
						long diffHours = timee / (60 * 60 * 1000);
						diff = diff + timee;
						String sec = String.format("%02d", diffSeconds);
						String min = String.format("%02d", diffMinutes);
						String hrs = String.format("%02d", diffHours);
					    String timedis = hrs+":"+min+":"+sec;
					 String[] row = {timeId,ddisplay,inn,outt,timedis};
					 tableModel.addRow(row);
					}
				 }
			 }
			 
			 
			 String totaltime = LongToTime(diff);
			 String[] tots = {"","","Total Hours :","        --------------------- ",totaltime};
			 tableModel.addRow(tots);
			 
		 }catch(SQLException | ParseException e) {
			 
			 
		 }
		 
	}
	
	public String LongToTime(long timee) {
		
		
	 	long diffSeconds = timee / 1000 % 60;
		long diffMinutes = timee / (60 * 1000) % 60;
		long diffHours = timee / (60 * 60 * 1000);		
		
		String sec = String.format("%02d", diffSeconds);
		String min = String.format("%02d", diffMinutes);
		String hrs = String.format("%02d", diffHours);
	    String timedis = hrs+":"+min+":"+sec;
		return timedis;
	}
	
	void tbldis() {
		PrintCell pcss = new PrintCell();
		dis_tbl.setShowGrid(false);
		dis_tbl.setIntercellSpacing(new Dimension(0, 0));
		dis_tbl.getTableHeader().setReorderingAllowed(false);
		dis_tbl.getTableHeader().setResizingAllowed(false);
		dis_tbl.setDefaultRenderer(Object.class, pcss);
		
		
		
	}
	/**
	 * Create the application.
	 */
	public SinglePrint() {
		db.dbCon();
		initialize();
		tbldis();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 738, 528);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE TIME SHEET");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(205, 50, 311, 38);
		frame.getContentPane().add(lblNewLabel);
		
		 lblName = new JLabel("Name :");
		lblName.setBounds(136, 86, 249, 14);
		frame.getContentPane().add(lblName);
		
		 lblUid = new JLabel("Unique Id :");
		lblUid.setBounds(136, 104, 249, 14);
		frame.getContentPane().add(lblUid);
		
		 lblFrom = new JLabel("Pay Period Starting :");
		lblFrom.setBounds(395, 86, 249, 14);
		frame.getContentPane().add(lblFrom);
		
		 lblTo = new JLabel("Pay Period Ending :");
		lblTo.setBounds(395, 104, 249, 14);
		frame.getContentPane().add(lblTo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 129, 545, 349);
		frame.getContentPane().add(scrollPane);
		
		dis_tbl = new JTable();
		dis_tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tableModel = (DefaultTableModel)dis_tbl.getModel();
				int rownum = dis_tbl.getSelectedRow();
				int colnum = dis_tbl.getSelectedColumn();
			
				if(colnum == 2 || colnum ==3) {
					
				String timeIds = tableModel.getValueAt(rownum, 0).toString();
				String timevals = tableModel.getValueAt(rownum, colnum).toString();
				
				
				}
			}
		});
		scrollPane.setViewportView(dis_tbl);
		
		JButton btnNewButton = new JButton("Print");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = "Julmar Degojas";
				String address = "JD001";
				String date1 = "Mar/01/2022";
				String date2 = "Mar/13/2022";
				
				try {
					PrinterJob job = PrinterJob.getPrinterJob();

		            MessageFormat[] header = new MessageFormat[9];

		            header[0] = new MessageFormat("");
		            header[1] = new MessageFormat("");
		            header[2] = new MessageFormat("");
		            header[3] = new MessageFormat("                                          ----------------------------EMPLOYEE TIME SHEET----------------------------");
		            header[4] = new MessageFormat("");
		            header[5] = new MessageFormat("           Name :               " + name);
		            header[6] = new MessageFormat("           Employee Id :   " + address);
		            header[7] = new MessageFormat("           From :               " + date1);
		            header[8] = new MessageFormat("           To :                    " + date2);

		            MessageFormat[] footer = new MessageFormat[1];
		            footer[0] = new MessageFormat("");
		            job.setPrintable(new MyTablePrintable(dis_tbl, JTable.PrintMode.NORMAL, header, footer));
		            job.printDialog();
		            job.print();
					
					
					
					
					
					//dis_tbl.print(JTable.PrintMode.FIT_WIDTH,headers,footers);
					
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(623, 426, 89, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add New");
		btnNewButton_1.setBounds(601, 156, 111, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Time");
		btnNewButton_2.setBounds(601, 205, 111, 38);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete Time");
		btnNewButton_3.setBounds(601, 259, 111, 38);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("<<<Exit");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_4.setBounds(10, 11, 89, 38);
		frame.getContentPane().add(btnNewButton_4);
	}
}
