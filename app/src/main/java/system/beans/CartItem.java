package system.beans;

public class CartItem{

	private int id;
	private String name;
	private int stock;
	private int price;
	private int quantity;
	private int total;
	
	// constructor
	public CartItem(Medicine med, int quantity) {
		
		this.id = med.getId();
		this.name = med.getName();
		this.stock = med.getPrice() - quantity;
		this.price = med.getPrice();
		this.quantity = quantity;
		this.total = this.price * quantity;
	}
	
	// gettters and setters

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.total = this.price * quantity;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	

	
}
