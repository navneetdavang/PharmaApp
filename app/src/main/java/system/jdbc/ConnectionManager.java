package system.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
	
	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/pharmacy_mangement_system";
	
	private static final String USERNAME = "DJMN";
	
	private static final String PASSWORD = "DJMN@1234";
	
	
	public ConnectionManager() {
		// do nothing
	}
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER_CLASS);
			
			con=DriverManager.getConnection(  
					CONNECTION_URL,
					USERNAME,
					PASSWORD);  
			
			System.out.println("Successfully Connected to JDBC");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
