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
	
	
	public Medicine(int id, Medicine med) {
		this.id = -1;
		this.name = med.getName();
		this.quantity = med.getQuantity();
		this.price = med.getPrice();
	
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Med : " + this.name + " " + this.quantity + " " + this.price;
	}
	
	
}
