package Model;

/**
 * Clasa pentru a modela un client
 * 
 * @author Tintesan Cristiana
 *
 */
public class Client {

	/**
	 * variabile int care reprezinta id-ul clientului
	 */
	private int id;
	/**
	 * Variabile String care reprezinta numele clientului
	 */
	private String nume;
	/**
	 * Variabila String care reprezinta orasul clientului
	 */
	private String city;

	/**
	 * Constructorul clasei fara parametrii
	 */
	public Client() {

	}

	/**
	 * Constructor pentru id, nume, oras
	 * 
	 * @param id   id-ul clientului
	 * @param nume numele clientului
	 * @param city orasul clientului
	 */
	public Client(int id, String nume, String city) {
		super();
		this.id = id;
		this.nume = nume;
		this.city = city;
	}

	/**
	 * Constructor pentru nume si oras
	 * 
	 * @param nume
	 * @param city
	 */
	public Client(String nume, String city) {
		super();
		this.nume = nume;
		this.city = city;
	}

	/**
	 * Metoda pentru a obtine id-ul unui client
	 * 
	 * @return retunreaza id-ul
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metoda pentru a obtine numele unui client
	 * 
	 * @return returneaza numele clientului
	 */
	public String getNume() {
		return nume;
	}

	/**
	 * Metoda pentru a obtine orasul unui client
	 * 
	 * @return returneaza orasul
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Metoda pentru a seta id-ul unui client
	 * 
	 * @param id reprezinta id-ul cu care dorim sa il setam
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metoda pentru setarea numelui unui clinet
	 * 
	 * @param nume numele cu care dorim sa il setam
	 */
	public void setNume(String nume) {
		this.nume = nume;
	}

	/**
	 * Metoda pentru o seta numele orasului
	 * 
	 * @param city reprezinta orasul cu care dorim sa il setem
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Metoda care returneazaz un string prin care se afiseaza un client
	 */
	public String toString() {
		return "Client [id=" + id + ", Name= " + nume + ", city=" + city + "]";
	}

}
