package system.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.beans.Chemist;
import system.beans.Customer;
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
	
	
	
	public static Customer customerLoginService(String first_name, String last_name, String contact_no, String email_id, String password) {
		Customer customer = null;
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Customer WHERE email_id = ? AND password = ?;");
			stmt.setString(1, email_id);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if(rs != null) {
				customer = new Customer(
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("contact_no"),
						rs.getString("email_id"),
						rs.getString("password"));
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			//  TODO Auto-generated catch block
			//	e.printStackTrace();
			System.out.println("Error : Chemist Loign Services");
		}
		
		return customer;
	}
}
