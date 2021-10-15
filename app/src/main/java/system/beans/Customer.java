package system.beans;

public class Customer {
	private int id;
	private String first_name;
	private String last_name;
	private String contact_no;
	private String email_id;
	private String password;
	
	public Customer() {
		this.first_name = "";
		this.last_name = "";
		this.contact_no = "";
		this.email_id = "";
		this.password = "";
	}
	
	public Customer(int id, String first_name, String last_name, String contact_no, String email_id, String password) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact_no = contact_no;
		this.email_id = email_id;
		this.password = password;
	}
	
	public Customer(String first_name, String last_name, String contact_no, String email_id, String password){
		this.first_name = first_name;
		this.last_name = last_name;
		this.contact_no = contact_no;
		this.email_id = email_id;
		this.password = password;
	}
	
	// added Copy constructor
	public Customer(Customer customer) {
		this.id = customer.getId();
		this.first_name = customer.getFirstName();
		this.last_name = customer.getLastName();
		this.contact_no = customer.getContactNo();
		this.email_id = customer.getEmailId();
		this.password = customer.getPassword();
	}
	



	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getFirstName() {
		return first_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	public String getLastName() {
		return last_name;
	}
	
	public void setContactNo(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getContactNo() {
		return contact_no;
	}
	
	public void setEmailId(String email_id) {
		this.email_id = email_id;
	}
	public String getEmailId() {
		return email_id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	// overriding the toString method
	@Override
	public String toString() {
		return this.email_id;
		
	}
	
}
