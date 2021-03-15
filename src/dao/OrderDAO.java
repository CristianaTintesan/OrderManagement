package dao;

import java.util.ArrayList;
import java.util.List;
import Model.Orders;

/**
 * Clasa care implementeaza operatiile pentru comenzi
 * 
 * @author Tintesan Cristiana
 *
 */
public class OrderDAO extends AbstractDAO<Orders> {

	/**
	 * Metoda pentru a insera o comanda
	 * 
	 * @param id       este id-ul comenzii
	 * @param idc      este id-ul clientului
	 * @param idp      este id-ul produsului
	 * @param quantity este cantitatea comandata
	 * @return
	 */
	public Orders insert(int id, int idc, int idp, int quantity) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(id);
		list.add(idc);
		list.add(idp);
		list.add(quantity);
		return super.insert(list);
	}

	/**
	 * Metoda pentru afisarea comenzilor
	 */
	public List<Orders> selectAll() {
		return super.selectAll();
	}

}
