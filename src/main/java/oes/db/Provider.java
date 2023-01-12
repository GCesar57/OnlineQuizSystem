package oes.db;
import java.sql.*;
public class Provider {
	public static Connection getMysqlConnection() {
		Connection con = null;
		try {
			//
			Class.forName("com.mysql.jdbc.Driver");  
		    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineQuizSystem","root","");
			 System.out.println("Connection successful!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}//
}
