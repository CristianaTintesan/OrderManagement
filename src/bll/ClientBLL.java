package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import dao.ClientDAO;
import Model.Client;

/**
 * Clasa pentru a apela medotele din clesele DAO
 * 
 * @author Tintesan Cristiana
 *
 */
public class ClientBLL {
	/**
	 * clientDAO pentru a apela metodele din clasa ClientDAO
	 */
	private ClientDAO clientDAO;

	/**
	 * Costructorul clasei ClientBLL
	 */
	public ClientBLL() {
		clientDAO = new ClientDAO();
	}

	/**
	 * Aceasta metoda este folosita pentru cautarea unui client.Se apeleaza metoda
	 * fintByName definita in clasa ClientDAO
	 * 
	 * @param name reprezinta clientul pe care dorim sa il gasim
	 * @return Client cl daca s-a gasit clientul cautat.In cazul in care nu am gasit
	 *         clientul, returnam un mesaj
	 */
	public Client findClientByName(String name) { // fuctie de cautare a unui client in tabela dupa nume
		Client cl = clientDAO.findByName(name);
		if (cl == null) {
			throw new NoSuchElementException("The client with name " + name + " was not found!");
		}
		return cl;
	}

	/**
	 * Aceasta metoda insereaza un nou client.Se apeleaza metoda insert definata in
	 * clasa ClientDAO
	 * 
	 * @param id   reprezinta id-ul clientului inserat
	 * @param name reprezinta numele clientului inserat
	 * @param city reprezinta orasul clientului inserat
	 * @return Clientul cl daca inserarea a avut loc cu succes, mesaj de eroare in
	 *         caz contrar.
	 */
	public Client insert(int id, String name, String city) {// pentru a insera un client in tabel
		Client cl = clientDAO.insert(id, name, city);
		if (cl == null) {
			throw new NoSuchElementException("The client with id " + id + " was not inserted!");
		}
		return cl;
	}

	/**
	 * Aceasta metoda sterge un client.Se apeleaza metoda delete definita in clasa
	 * ClientDAO
	 * 
	 * @param name numele clientului pe care dorim sa in stergem
	 * @return Clientul cl daca stergerea a avut loc cu succes, mesaj de eroare in
	 *         caz contrar
	 */
	public Client delete(String name) { // pentru a sterge un client din tabel
		Client cl = clientDAO.delete(name);
		if (cl == null) {
			throw new NoSuchElementException("The client  was not deleted!");
		}
		return cl;
	}

	/**
	 * Aceasta metoda este folosita pentru a putea afisa clientii inserati.
	 * 
	 * @return o lista de clienti, mesaj in cazul in care lista clientilor este
	 *         goala.
	 */
	public List<Client> selectAll() {// pentru afisare tabelului client
		List<Client> cl = new ArrayList<Client>();
		cl = clientDAO.selectAll();
		if (cl == null) {
			throw new NoSuchElementException("Elements were not found!");
		}
		return cl;
	}

}
