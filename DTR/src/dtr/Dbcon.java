package dtr;
// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dbcon {
	
	Connection con;
	PreparedStatement preState;
	PreparedStatement preState2;
	PreparedStatement preState3;
	
	
	public void dbCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb", "root", "");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
