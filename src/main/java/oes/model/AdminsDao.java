package oes.model;
import java.sql.*;

import oes.db.Admins;
import oes.db.Provider;
public class AdminsDao {
	public static boolean doValidate(Admins ad) {
		//
		boolean status = false;
		try {
			//
			Connection con = Provider.getMysqlConnection();
			String sql = "SELECT * FROM admintable WHERE userid=? AND password=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, ad.getUsername());
			pst.setString(2, ad.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				status = true;
			}else {
				status = false;
			}//
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
			System.out.println(e);
		}
		return status;
	}//
}
