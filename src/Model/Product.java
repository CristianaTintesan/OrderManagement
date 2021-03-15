package Model;

/**
 * Clasa pentru a modela un produs
 * 
 * @author Tintesan Cristiana
 *
 */

public class Product {
	/**
	 * variabila int pentru a repezenta id-ul produsului
	 */
	private int id;
	/**
	 * variabila String pentru a reprezenta numele produsului
	 */
	private String nume;
	/**
	 * variabila int pentru a reprezenta cantitatea produsului inserat
	 */
	private int quantity;
	/**
	 * variabila float pentru a reprezenta pretul unui produs
	 */
	private float price;

	/**
	 * Constructor fara parametrii
	 */
	public Product() {

	}

	/**
	 * Constructor pentru id, name, quantity si price
	 * 
	 * @param id
	 * @param name
	 * @param quantity
	 * @param price
	 */
	public Product(int id, String name, int quantity, float price) {
		super();
		this.id = id;
		this.nume = name;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Constructor pentru name, quantity si pricce
	 * 
	 * @param name
	 * @param quantity
	 * @param price
	 */
	public Product(String name, int quantity, int price) {
		super();
		this.nume = name;
		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * Metoda pentru a returna id-ul produsului
	 * 
	 * @return returneaza id-ul
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metoda pentru a obtine numele produsului
	 * 
	 * @return returneaza numele produsului
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * Metoda pentru a obtine cantitatea de produs
	 * 
	 * @return returneaza cantitatea
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Metoda pentru a obtine pretul produsului
	 * 
	 * @return returneaza pretul
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Metoda pentru a seta id-ul produsului
	 * 
	 * @param id reprezinta id-ul cu care dorim sa il setam
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metoda pentru a seta nume prosului
	 * 
	 * @param name reprezinta numele cu care dorim sa il setam
	 */
	public void setNume(String name) {
		this.nume = name;
	}

	/**
	 * Metoda pentru a seta cantitatea
	 * 
	 * @param quantity reprezinta cantitatea cu care dorim sa o setam
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Metoda pentru a seta pretul
	 * 
	 * @param price reprezinta pretul cu care dorim sa setam
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Metoda pentru a afisa un produs.Returneaza un String
	 */
	public String toString() {
		return "Product [id=" + id + ", Name=" + nume + ", Quantity=" + quantity + ", price=" + price + "]";
	}

}
