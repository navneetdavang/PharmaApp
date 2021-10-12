package system.beans;

public class Chemist {
	
	private String username;
	private String password;
	
	public Chemist() {
		this.username = "";
		this.password = "";
	}
	
	public Chemist(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
