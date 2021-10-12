package system.beans;

public class Medicine {

	private int id;
	private String name;
	private int quantity;
	private int price;
	
	public Medicine() {  	// default constructor
		this.id = -1;
		this.name = "";
		this.quantity = -1;
		this.price = -1;
	}
	
	// constructors for fetching the values for db
	public Medicine(int id, String name, int quantity, int price) {	
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	// contructor for inserting the values into db
	public Medicine(String name, int quantity, int price) {
		this.id = -1;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	// getters
	
	public int getMedicineId() {
		return this.id;
	}
	
	public String getMedicineName() {
		return this.name;
	}
	
	public int getMedicineQuantity() {
		return this.quantity;
	}
	
	public int getMediciePrice() {
		return this.price;
	}
	
	
	//setters
	
	public void setMedicineId(int id) {
		this.id = id;
	}
	
	public void setMedicineName(String name) {
		this.name = name;
	}
	
	public void setMedicineQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setMedicinePrice(int price) {
		this.price = price;
	}
	
}
