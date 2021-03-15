package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Model.Product;
import dao.ProductDAO;

/**
 * Clasa pentru a apela medotele din clesele DAO
 * 
 * @author Tintesan Cristiana
 *
 */
public class ProductBLL {

	/**
	 * productDAO pentru a putea apele metodele din clasa ProductDAO
	 */
	private ProductDAO productDAO;

	/**
	 * Constructorul clasei ProductBLL
	 */
	public ProductBLL() {
		productDAO = new ProductDAO();
	}

	/**
	 * Aceasta metoda cauta un produs dupa numele acestuia.Este apelata metoda
	 * fineProductByName definita in clasa ProductDAO
	 * 
	 * @param name reprezinta numele produsului pe care dorim sa il cautam
	 * @return Product pr in cazul in care am gasit produsul, mesaj in caz contrar.
	 */
	public Product findProductByName(String name) { // fuctie de cautare a unui produs in tabela dupa nume
		Product pr = productDAO.findByName(name);
		if (pr == null) {
			throw new NoSuchElementException("The product with name " + name + " was not found!");
		}
		return pr;
	}

	/**
	 * Aceasta metoda insereaza un produs nou.Este apelata functia insert din clasa
	 * ProductDAO
	 * 
	 * @param id       reprezinta id-ul produsului pe care dorim sa il inseram
	 * @param name     reprezinta numele produsului pe care dorim sa il inseram
	 * @param quantity reprezinta cantitatea produsului pe care dorim sa il inseram
	 * @param price    este de tipul float si reprezinta pretul produsului pe care
	 *                 dorim sa il inseram
	 * @return Product pr-produsul pe care l-am inserat, sau mesaj in caz contrat
	 */
	public Product insert(int id, String name, int quantity, float price) {// Pentru a insera un produs
		Product pr = productDAO.insert(id, name, quantity, price);
		if (pr == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not insert!");
		}
		return pr;

	}

	/**
	 * Aceasta metoda sterge un produs.Este apelata metoda delete din clasa
	 * ProductDAO
	 * 
	 * @param name reprezinta numele produsului pe care dorim sa il stergem.
	 * @return Product pr-produsul sters, sau mesaj in caz contrar
	 */
	public Product delete(String name) {// Pentru a sterge un produs
		Product pr = productDAO.delete(name);
		if (pr == null) {
			throw new NoSuchElementException("The product  was not deleted!");
		}
		return pr;
	}

	/**
	 * Aceasta metoda este folosita pentru a actualiza valorile din tabelul Product.
	 * 
	 * @param id-                 id-ul produsului pe care dorim sa in actualizam
	 * @param name-numele         produsului pe care dorim sa in actualizam
	 * @param quantity-cantitatea produsului pe care dorim sa il actualizam
	 * @param price-pretul        produsului pe care dorim sa il actualizam
	 * @return Product pr-produsul actualizat sau mesaj in cazul in care
	 *         actualizarea nu a avut loc
	 */
	public Product update(int id, String name, int quantity, float price) {
		Product pr = productDAO.update(id, name, quantity, price);// Pt a actualiza tabelul Product atunci cand se
																	// introduce o noua comanda
		if (pr == null) {
			throw new NoSuchElementException("The product with id =" + id + " was not updated!");
		}
		return pr;
	}

	/**
	 * Aceasta metoda este folosita pentru a afisa produsele inserate
	 * 
	 * @return returneaza o lista produselor sau mesaj in cazul in care lista este
	 *         goala
	 */
	public List<Product> selectAll() {// Pentru a afisa tabelul Product
		List<Product> pr = new ArrayList<Product>();
		pr = productDAO.selectAll();
		if (pr == null) {
			throw new NoSuchElementException(" not found!");
		}
		return pr;
	}

}
