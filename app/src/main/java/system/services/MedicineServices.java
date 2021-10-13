package system.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import system.beans.Chemist;
import system.beans.Medicine;
import system.jdbc.ConnectionManager;

public class MedicineServices {
	
	
	// method to add medicine in the db;
	public static boolean addMedicine(Medicine med) {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("INSERT INTO Medicine (medicine_name, quantity, price) "
					+ "VALUES(?,?,?)");
			stmt.setString(1, med.getName());
			stmt.setInt(2, med.getQuantity());
			stmt.setInt(3, med.getPrice());
			
			int row_affected = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			//  TODO Auto-generated catch block
			//	e.printStackTrace();
			System.out.println("Error : Unable to add medicine in db");
			return false;
		}
		
		return true;
	}
	
	
	// method to delete the medicine in the db
	public static boolean deleteMedicine(Medicine med) {
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			stmt = con.prepareStatement("DELETE FROM Medicine WHERE medicine_name = ?;");
			stmt.setString(1, med.getName());
			
			int row_affected = stmt.executeUpdate();
			stmt.close();
			
		} catch (SQLException e) {
			//  TODO Auto-generated catch block
			//	e.printStackTrace();
			System.out.println("Error : Unable to delete medicine in db");
			return false;
		}
		
		return true;
		
		
	}
	
	
	
	
}
