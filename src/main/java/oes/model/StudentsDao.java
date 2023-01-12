package oes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oes.db.Provider;
import oes.db.Students;
public class StudentsDao {
	public static boolean doValidate(Students sd) {
		boolean status = false;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "SELECT * FROM studenttable where userid=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, sd.getUsername());
			pst.setString(2, sd.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				sd.setName(rs.getString("name"));
				status = true;
			}else {
				status = false;
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}//
	//
	public static boolean insertStudent(Students st) {
		boolean status = false;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "INSERT INTO studenttable VALUES(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, st.getUsername());
			pst.setString(2, st.getPassword());
			pst.setString(3, st.getName());
			int val = pst.executeUpdate();
			if(val>0) {
				status = true;
			}else {
				status = false;
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}//
	//
	public static ArrayList<Students>getAllRecords(){
		ArrayList<Students> samp = new ArrayList<Students>();
		try {
			//
			samp.clear();// Removes all of the elements from this list. 
			//The list will be empty after this call returns.
			Connection con = Provider.getMysqlConnection();
			String sql = "SELECT * FROM studenttable";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				Students s = new Students();
				s.setUsername(rs.getString(1));
				s.setPassword(rs.getString(2));
				s.setName(rs.getString(3));
				samp.add(s);
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return samp;
	}//
	//
	public static int deleteRecord(Students st) {
		int status = 0;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "DELETE FROM studenttable WHERE userid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, st.getUsername());
			int val = pst.executeUpdate();
			if(val>0) {
				status = 1;
			}else {
				status = 0;
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}//
	//
	public static String getStudentName(String username) {
		String name = null;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "SELECT name FROM studenttable WHERE userid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				name = rs.getString(1);
			}else {
				name = "DB ERROR";
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return name;
	}//
	//
	public static int doUpdateNowRecord(String originalusername,String newuserid,String newpassword,String newname) {
		//
		int status = 0;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "UPDATE studenttable SET userid=?,password=?,name=? WHERE userid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newuserid);
			pst.setString(2, newpassword);
			pst.setString(3, newname);
			pst.setString(4, originalusername);
			int val = pst.executeUpdate();
			if(val>0) {
				status = 1;
			}else {
				status = -1;
			}//
		} catch (Exception e) {
			// TODO: handle exception
			status = 2;
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return status;
	}//
}


