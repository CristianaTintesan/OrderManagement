package dao;

import java.util.ArrayList;
import java.util.List;
import Model.Product;

/**
 * Clasa care impplementeaza operatiile pentru produs
 * 
 * @author Tintesan Cristiana
 *
 */
public class ProductDAO extends AbstractDAO<Product> {

	/**
	 * Metoda pentru a insera un produs.Toti parametrii sunt obligatorii pentru a
	 * realiza inserarea
	 * 
	 * @param id       este id-ul produsului
	 * @param name     este nume produsului
	 * @param quantity este cantitatea produsului
	 * @param price    este pretul produsului
	 * @return returneaza lista produselor
	 */
	public Product insert(int id, String name, int quantity, float price) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(id);
		list.add(name);
		list.add(quantity);
		list.add(price);
		return super.insert(list);
	}

	/**
	 * Metoda pentru a reliza stergerea unui produs
	 * 
	 * @param name indica numele produsului pe care dorim sa in stergm
	 * @return returneaza lista produselor dupa stergere
	 */
	public Product delete(String name) {
		ArrayList<Object> list = new ArrayList<Object>();
		ArrayList<String> names = new ArrayList<String>();
		if (name != "") {
			list.add(name);
			names.add("nume");
		}
		return super.delete(list, names);
	}

	/**
	 * Metoda pentru a actualiza produsele.Pentru a decrementa stocul sau pentru a
	 * mari stocul atunci cand se insereaza doua produse cu acelasi nume si pret
	 * 
	 * @param id
	 * @param nume
	 * @param quantity
	 * @param price
	 * @return returneaza lista produselor dupa actualizare
	 */
	public Product update(int id, String nume, int quantity, float price) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(id);
		list.add(nume);
		list.add(quantity);
		list.add(price);
		ArrayList<String> names = new ArrayList<String>();
		names.add("id");
		names.add("nume");
		names.add("quantity");
		names.add("price");
		return super.update(list, names, id, "id");
	}

	/**
	 * Metoda pentru a afisa toate produsele
	 */
	public List<Product> selectAll() {
		return super.selectAll();
	}
}
