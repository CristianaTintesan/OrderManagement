package dao;

import java.util.ArrayList;
import java.util.List;

import Model.Client;

/**
 * Clasa care implementeaza operatiile pentru client
 * 
 * @author Tintesan Cristiana
 *
 */
public class ClientDAO extends AbstractDAO<Client> {

	/**
	 * Metoda pentru a gasi un client din tabel dupa nume
	 */
	public Client findByName(String nume) {
		return super.findByName(nume);
	}

	/**
	 * Metoda pnetru a insera un client
	 * 
	 * @param id   este id-ul clientului
	 * @param nume este numele clientului
	 * @param city este orasul clientului
	 * @return returneaza lista clientilor dupa inserare
	 */
	public Client insert(int id, String nume, String city) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(id);
		list.add(nume);
		list.add(city);
		return super.insert(list);
	}

	/**
	 * Metoda pentru a sterge un client
	 * 
	 * @param name este nume clientului pe care dorim sa il stergem
	 * @return
	 */
	public Client delete(String name) {
		ArrayList<Object> list = new ArrayList<Object>();
		ArrayList<String> names = new ArrayList<String>();
		if (name != "") {
			list.add(name);
			names.add("nume");
		}
		return super.delete(list, names);
	}

	/**
	 * Metoda pentru a afisa toti clientii
	 */
	public List<Client> selectAll() {
		return super.selectAll();
	}
}
