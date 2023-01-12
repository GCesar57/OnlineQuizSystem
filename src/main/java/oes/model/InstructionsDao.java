package oes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oes.db.Instructions;
import oes.db.Provider;

public class InstructionsDao {
	public static boolean insertInstruction(Instructions ist) {
		boolean status = false;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "INSERT INTO instructiontable VALUES(?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ist.getInstruction());
			int val = pst.executeUpdate();
			if(val >0) {
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
	public static ArrayList<Instructions>getAllRecords(){
		//
		ArrayList<Instructions>samp = new ArrayList<Instructions>();
		try {
			//
			samp.clear(); //Removes all of the elements from this list. 
			Connection con = Provider.getMysqlConnection();
			String sql = "SELECT * FROM instructiontable";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) {
				Instructions i = new Instructions();
				i.setInstruction(rs.getString(1));
				samp.add(i);
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return samp;
	}//
	//
	public static int deleteRecord(Instructions ist) {
		int val = 0;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "DELETE FROM instructiontable WHERE instruction=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ist.getInstruction());
			val = pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			val = -1;
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return val;
	}//
	//
	public static int doUpdateNowRecord(String instaoriginal,String instamodified) {
		//
		int status = 0;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "UPDATE instructiontable SET instruction=? WHERE instruction=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, instamodified);
			pst.setString(2, instaoriginal);
			int val = pst.executeUpdate();
			if(val > 0) {
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

