package bll;

import java.util.NoSuchElementException;
import Model.Bill;
import Model.Client;
import Model.Product;
import dao.BillDAO;
import dao.ClientDAO;
import dao.ProductDAO;

/**
 * Clasa pentru a apela medotele din clesele DAO
 * 
 * @author Tintesan Cristiana
 *
 */
public class BillBLL {

	/**
	 * billDAO pentru a apele metodele definita in clasa BillDAO
	 */
	private BillDAO billDAO;

	/**
	 * Constructorul clasei BillBLL
	 */
	public BillBLL() {
		billDAO = new BillDAO();
	}

	/**
	 * Aceasta metoda este folosita pentru a adauga o factura in tabel.
	 * 
	 * @param id         reprezinta id-ul facturii.Nu pot exista doua facturi cu
	 *                   acelasi id
	 * @param numeClient reprezinta numele clientului pentru care se face factura
	 * @param numeProdus reprezinta numele produsului care a fost comandat
	 * @param quantity   reprezinta cantitatea produsului care a fost comandat
	 * @return returneaza Bill bl in cazul in face factura a fost inserata in tabel
	 *         sau mesaj in caz contrar.
	 */
	public Bill newBill(int id, String numeClient, String numeProdus, int quantity) {
		Client c = new Client();
		ClientDAO cl = new ClientDAO();
		c = cl.findByName(numeClient);// gasim clientul
		Product p = new Product();
		ProductDAO pr = new ProductDAO();
		p = pr.findByName(numeProdus);// gasim produsul
		Bill bl = billDAO.insert(id, c.getNume(), p.getNume(), quantity, p.getPrice() * quantity);// adugam factura
		if (bl == null) {
			throw new NoSuchElementException("The bill with id " + id + " was not insert!");
		}
		return bl;
	}

}
