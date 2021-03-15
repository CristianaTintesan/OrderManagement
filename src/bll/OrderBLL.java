package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Model.Client;
import Model.Orders;
import Model.Product;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;

/**
 * Clasa pentru a apela medotele din clesele DAO
 * 
 * @author Tintesan Cristiana
 *
 */
public class OrderBLL {

	/**
	 * orderDAO pentru a putea apele metodele definite in clasa OrderDAO
	 */
	private OrderDAO orderDAO;

	/**
	 * Constructorul clasei OrderBLL
	 */
	public OrderBLL() {
		orderDAO = new OrderDAO();
	}
	/*
	 * Pentru a insera o noua comanda in tabel este nevoie sa determinam prima data
	 * id-ul clientului care a efectuat comanda si id-ul prosului dorit de client.In
	 * tabelul Orders din MySQL idc idp sunt chei straine care fac legatura cu
	 * tabelul Client si Product
	 */

	/**
	 * Aceasta metoda este folosita pentru a putea insera o noua comanda in
	 * tabel.Pentru a putea face inserarea trebuie sa determinam daca nume
	 * clientului si al produsului sunt corect, daca aceasta au fost deja inserate
	 * in tabelul client si product.
	 * 
	 * @param id          reprezinta id-ul comenzii pe care dorim sa o inseram.Nu
	 *                    pot exista doua comenzi cu acelasi id
	 * @param nameClient  reprezinta numele clientului care a solicitat comanda
	 * @param nameProduct reprezinta numele produsului care a fost comandat
	 * @param quantity    reprezinta cantitatea care a fost comandata
	 * @return returneaza Orders ord in cazul in care comanda a fosrt inserata,
	 *         mesaj in caz contrar.
	 */
	public Orders newOrder(int id, String nameClient, String nameProduct, int quantity) {
		Client c = new Client();
		ClientDAO cl = new ClientDAO();
		c = cl.findByName(nameClient);// gasim clientul
		Product p = new Product();
		ProductDAO pr = new ProductDAO();
		p = pr.findByName(nameProduct);// gasim produsul
		int idc = c.getId();
		int idp = p.getId();
		Orders ord = orderDAO.insert(id, idc, idp, quantity);// adugam comanda

		if (ord == null) {
			throw new NoSuchElementException("The order with id " + id + " was not insert!");
		}
		return ord;
	}

	/**
	 * Aceasta metoda este folosita pentru a afisa toate comenzile din tabel.
	 * 
	 * @return returneaza lista comenzilor din tabel, sau mesaj in cazul in care
	 *         lista este goala.
	 */
	public List<Orders> selectAll() {// pentru a afisa tabelul Orders
		List<Orders> or = new ArrayList<Orders>();
		or = orderDAO.selectAll();
		if (or == null) {
			throw new NoSuchElementException(" empty!");
		}
		return or;
	}

}
