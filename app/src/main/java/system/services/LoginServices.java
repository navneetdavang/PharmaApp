package system.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.beans.Chemist;
import system.jdbc.ConnectionManager;

public class LoginServices {

	public static Chemist chemistLoginService(String username, String password) {
		Chemist chemist = null;
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Chemist WHERE username = ? AND password = ?;");
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if(rs != null) {
				chemist = new Chemist(
						rs.getString("username"),
						rs.getString("password"));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			//  TODO Auto-generated catch block
			//	e.printStackTrace();
			System.out.println("Error : Chemist Loign Services");
		}
		
		return chemist;
	}
}
