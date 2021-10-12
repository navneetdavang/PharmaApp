package system.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.beans.Customer;
import system.jdbc.ConnectionManager;

public class RegistrationServices {
	public static Customer customerRegService(String first_name, String last_name, String contact_no, String email_id, String password) {
		Customer customer = null;
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("INSERT INTO Customer (first_name,last_name,contact_no,email_id,password) VALUES (?,?,?,?,?);");
			stmt.setString(1, first_name);
            stmt.setString(2, last_name);
            stmt.setString(3, contact_no);
            stmt.setString(4, email_id);
            stmt.setString(5, password);
            
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
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
}
