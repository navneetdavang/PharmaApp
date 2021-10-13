package system.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.beans.Medicine;
import system.jdbc.ConnectionManager;

public class MedicineDao {
	

	public static ObservableList<Medicine> getAllMedicine(){
		ObservableList<Medicine> medList = FXCollections.observableArrayList();
		
		Connection con = ConnectionManager.getConnection();
		PreparedStatement stmt;
		
		try {
			
			stmt = con.prepareStatement("SELECT * FROM Medicine");
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				medList.add(new Medicine(
						rs.getInt("medicine_id"),
						rs.getString("medicine_name"), 
						rs.getInt("quantity"), 
						rs.getInt("price")));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Unable to fetch the medicine list from db");
			return null;
		}
		
		
		return medList;
	}

}
