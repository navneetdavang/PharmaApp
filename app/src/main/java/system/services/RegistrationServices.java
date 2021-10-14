package system.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.beans.Customer;
import system.jdbc.ConnectionManager;

public class RegistrationServices {
	public static Customer customerRegService(String first_name, String last_name, String contact_no, String email_id, String password) {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("INSERT INTO Customer (first_name,last_name,contact_no,email_id,password) VALUES (?,?,?,?,?);");
			stmt.setString(1, first_name);
            stmt.setString(2, last_name);
            stmt.setString(3, contact_no);
            stmt.setString(4, email_id);
            stmt.setString(5, password);
            
            int row_affected = stmt.executeUpdate();
			
			stmt.close();
			
		} catch(SQLException e) {
//			e.printStackTrace();
			System.out.println("Error while registering the customer");
			return null;
		}
		
		return new Customer(first_name, last_name, contact_no, email_id, password);
	}
}
