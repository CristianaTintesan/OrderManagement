package Model;

/**
 * Clasa pentru a modela o comanda.
 * 
 * @author Tintesan Cristiana
 *
 */
public class Orders {
	/**
	 * variabila int care reprezinta id-ul comenzii
	 */
	private int id;
	/**
	 * variabila int care reprezinta id-ul clientului
	 */
	private int idc;
	/**
	 * variabila int care reprezinta id-ul produsului
	 */
	private int idp;

	/**
	 * variabila int care reprezinta cantitatea de produs comandata
	 */
	private int quantity;

	/**
	 * Constructorul fara parametrii ai clasei
	 */
	public Orders() {

	}

	/**
	 * Constructorl pentru id, idc, idp si quantity
	 * 
	 * @param id
	 * @param idc
	 * @param idp
	 * @param quantity
	 */
	public Orders(int id, int idc, int idp, int quantity) {
		super();
		this.id = id;
		this.idc = idc;
		this.idp = idp;
		this.quantity = quantity;
	}

	/**
	 * Constructotul pentru idp, idc si quantity
	 * 
	 * @param idp
	 * @param idc
	 * @param quantity
	 */
	public Orders(int idp, int idc, int quantity) {
		super();
		this.idc = idc;
		this.idp = idp;
		this.quantity = quantity;
	}

	/**
	 * Metoda pentru a obtine id-ul comenzii
	 * 
	 * @return returneaza un int care indica id-ul
	 */

	public int getId() {
		return id;
	}

	/**
	 * Metoda folosita pentru a obtine id-ul clientului
	 * 
	 * @return ret un ind care este id-ul clientului
	 */
	public int getIdc() {
		return idc;
	}

	/**
	 * metoda folosita pnetru a obtine id-ul prodului
	 * 
	 * @return ret un int care este id-ul produsului
	 */
	public int getIdp() {
		return idp;
	}

	/**
	 * metoda folosita pentru a obtine cantitatea
	 * 
	 * @return returneaza un int care este cantitatea comandata
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Metoda folosita pentru a seta id-ul comenzii
	 * 
	 * @param id reprezinta id-ul cu care dorim sa il setam
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metoda folosita pentru a seta id-ul clientului
	 * 
	 * @param idc reprezinta id-ul cu care dorim sa il setam
	 */
	public void setIdc(int idc) {
		this.idc = idc;
	}

	/**
	 * Metoda folosita pentru a seta id-ul produsului
	 * 
	 * @param idp reprezinta id-ul cu care dorim sa il setam
	 */
	public void setIdp(int idp) {
		this.idp = idp;
	}

	/**
	 * Matoda folosita pentru a seta cantitatea
	 * 
	 * @param quantity reprezinta cantitatea cu care dorim sa setam vechea cantitate
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Metoda folosita pentru a afisa comanda.Returneaza un String
	 */
	public String toString() {
		return "Order [order=" + id + ", Id client=" + idc + ", Id produs=" + idp + ", Quantity=" + quantity + "]";
	}

}
