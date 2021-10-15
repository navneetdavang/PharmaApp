package system.beans;

public class Medicine {

	protected int id;
	protected String name;
	protected int stock;
	protected int price;
	
	public Medicine() {  	// default constructor
		this.id = -1;
		this.name = "";
		this.stock = -1;
		this.price = -1;
	}
	
	// constructors for fetching the values for db
	public Medicine(int id, String name, int stock, int price) {	
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	
	
	// contructor for inserting the values into db
	public Medicine(String name, int stock, int price) {
		this.id = -1;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	
	
	public Medicine(int id, Medicine med) {
		this.id = -1;
		this.name = med.getName();
		this.stock = med.getStock();
		this.price = med.getPrice();
	
	}
	
	public Medicine(Medicine med) {
		this.id = med.getId();
		this.name = med.getName();
		this.stock = med.getStock();
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Med : " + this.name + " " + this.stock + " " + this.price;
	}

	
}
