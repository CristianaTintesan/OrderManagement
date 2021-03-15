package Model;

/**
 * Clasa pentru a modela o factura
 * 
 * @author Tintesan Cristiana
 *
 */
public class Bill {
	/**
	 * id-ul facturii.Nu pot exista 2 facturii cu acelasi id
	 */
	private int id;
	/**
	 * Numele clientului pentru care se realizeaza factura
	 */
	private String numeClient;
	/**
	 * Numele produsului comandat
	 */
	private String numeProdus;
	/**
	 * Cantitatea de produs comandata
	 */
	private int cantitate;
	/**
	 * Pretul total pe care clientul trebuie sa il achite
	 */
	private float pretTotal;

	/**
	 * Constructor fara parametrii
	 */
	public Bill() {

	}

	/**
	 * Constructorul care primeste ca parametru toate campurile
	 * 
	 * @param id
	 * @param numeClient
	 * @param numeProdus
	 * @param cantitate
	 * @param pretTotal
	 */
	public Bill(int id, String numeClient, String numeProdus, int cantitate, float pretTotal) {
		this.id = id;
		this.numeClient = numeClient;
		this.numeProdus = numeProdus;
		this.cantitate = cantitate;
		this.pretTotal = pretTotal;
	}

	/**
	 * Constructor care primeste ca parametru id, numeClient, numeProdus, cantitate,
	 * pretTotal
	 * 
	 * @param numeClient
	 * @param numeProdus
	 * @param cantitate
	 * @param pretTotal
	 */
	public Bill(String numeClient, String numeProdus, int cantitate, float pretTotal) {
		this.numeClient = numeClient;
		this.numeProdus = numeProdus;
		this.cantitate = cantitate;
		this.pretTotal = pretTotal;
	}

	/**
	 * Metoda care returneaza un string prin care este afisata o factura
	 */
	public String toString() {
		return "Bill: id=" + id + " nume client= " + numeClient + " nume Produs= " + numeProdus + "cantitate="
				+ cantitate + "pretTotal=" + pretTotal;
	}
}
