package system.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.beans.Chemist;
import system.jdbc.ConnectionManager;

public class BillServices {

	public static boolean addBill(int customer_id, int total_amount) {
		System.out.println(customer_id);
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("INSERT INTO Bills (cust_id, amount) "
					+ "VALUES(?,?)");
			stmt.setInt(1, customer_id);
			stmt.setInt(2, total_amount);
			
			int row_affected = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			//  TODO Auto-generated catch block
				e.printStackTrace();
			System.out.println("Error : Unable to add bill in db");
			return false;
		}
		
		return true;
		
		
	}
	
}
