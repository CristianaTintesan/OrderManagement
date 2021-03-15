package dao;

import java.util.ArrayList;
import Model.Bill;

/**
 * Clasa care implemenaza opariile pentru facturi
 * 
 * @author Tintesan Cristiana
 *
 */
public class BillDAO extends AbstractDAO<Bill> {

	/**
	 * Metoda pentru a adauga o noua factura
	 * 
	 * @param id         este id-ul facturii
	 * @param numeClient este un Strinf care indica numele clientului pentru care se
	 *                   face factura
	 * @param numeProdus este un String care indica numele prosului comandat
	 * @param quantity   este un int care indica cantitatea de produs comandata
	 * @param pretTotal  este un float care indica pretul total pe care clientul
	 *                   trebuie sa il achite
	 * @return returneaza lista facturilor
	 */
	public Bill insert(int id, String numeClient, String numeProdus, int quantity, float pretTotal) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(id);
		list.add(numeClient);
		list.add(numeProdus);
		list.add(quantity);
		list.add(pretTotal);
		return super.insert(list);
	}

}
