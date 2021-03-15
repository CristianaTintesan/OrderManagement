package presentation;

import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import bll.BillBLL;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ProductDAO;
import start.Start;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Model.Client;
import Model.Orders;
import Model.Product;

/**
 * Clasa care implementeaza operatiile de inserare, sterge, actualizare si
 * crearea fisierul pdf.
 * 
 * @author Tintesan Cristiana
 *
 */
public class Controller {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
	/**
	 * p1 reprezinta lista produselor.Aici stocam produsele inserate.
	 */
	private List<Product> p1 = new ArrayList<Product>();// pentru a memora produsele din tabel
	/**
	 * c1 reprezinta lista clientilor.Aici stocam clienti inserato
	 */
	private List<Client> c1 = new ArrayList<Client>();// pentru a memora clientii din tabel
	/**
	 * o1 reprezinta lista comenzilor.Aici stocam comenzile inserate.
	 */
	private List<Orders> o1 = new ArrayList<Orders>();// pentru a memora comenzile

	/**
	 * Metoda insert client insereaza clientul in tabelul client
	 * 
	 * @param str str reprezinta stringul care contine informatiile despre clientulu
	 *            care urmeaza sa fie inserat in tabel
	 * @param idc idc este id-ul clientului.Acesta este incrementat la fiecare
	 *            inserare astel incat nu pot exista doi clienti cu acelasi id in
	 *            tabel.
	 */
	public void insertClient(String str, int idc) {
		String[] strArray, strArray2;
		strArray = str.split(":");
		strArray2 = strArray[1].split(",");
		ClientBLL clientBLL = new ClientBLL();
		try {
			clientBLL.insert(idc++, strArray2[0], strArray2[1]);// inseram un nou client
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		c1 = clientBLL.selectAll();
	}

	/**
	 * Metoda deleteClient este utilizata pentru a sterge un client din tabel.
	 * 
	 * @param str parametru str este un string care contine informatii despre
	 *            clientul care urmeaza sa fie sters din tabel
	 */
	public void deleteClient(String str) {
		String[] strArray, strArray2;
		strArray = str.split(":");
		strArray2 = strArray[1].split(",");
		ClientBLL clientBLL = new ClientBLL();
		try {
			clientBLL.delete(strArray2[0]);// stergem clientul cu numele transmis ca si parametru
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		c1 = clientBLL.selectAll();
	}

	/**
	 * Metoda insertProduct este pentru a insera un produs in tabel
	 * 
	 * @param str parametrul str este de tipul String si contine informatiile
	 *            necesare pentru produsul care trebuie inserat
	 * @param idp idp este de tipul int si este id-ul prodului.Acesta este
	 *            incrementat la fiecare comanda de insert product citita din fisier
	 */
	public void insertProduct(String str, int idp) {
		String[] strArray, strArray2;
		strArray = str.split(":");
		int ok = 1;
		strArray2 = strArray[1].split(",");
		ProductBLL productBLL = new ProductBLL();
		int quantity = Integer.parseInt(strArray2[1]);
		float price = Float.parseFloat(strArray2[2]);
		try {
			for (Product produs : p1)
				if (produs.getNume().equals(strArray2[0])) {// verifcam daca nu avem un produs un acelasi nume pe care
															// dorim sa il inseram
					ok = 0;
					productBLL.update(produs.getId(), produs.getNume(), quantity + produs.getQuantity(),
							produs.getPrice());// daca avem facem update, nu insert la tabel
				}
			if (ok == 1)
				productBLL.insert(idp++, strArray2[0], quantity, price);// daca nu, il inseram
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		p1 = productBLL.selectAll();
	}

	/**
	 * Aceasta metoda este apelata pentru a sterge un client din tabel
	 * 
	 * @param str str este de tipul String si contine informatiile necerare pentru a
	 *            sterge produsul
	 */
	public void deleteProduct(String str) {
		String[] strArray;
		strArray = str.split(":");
		ProductBLL productBLL = new ProductBLL();
		try {// stergem produsul cu numele transmis ca si parametru
			productBLL.delete(strArray[1]);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		p1 = productBLL.selectAll();
	}

	/**
	 * Metoda apelata pentru a insera o comanda in tabelul orders si pentru a
	 * actualiza stocul produsului pentru care se executa comanda
	 * 
	 * @param str str este de tipul String si contine informatiile necesare pentru a
	 *            aduaga o noua comanda si a actualiza stocul produsului
	 * @param idO idO este de tipul int si indica id-ul comenzii.Aceasta valoare
	 *            este incrementata la fiecare comanda order citita din fiser
	 * @param y   y este de tipul int si este utilizat pentru a crea un nume unic
	 *            pentru fiecare fiser pdf in care se genereaza un mesaj de eroare
	 *            in cazul in care nu avem sufiecient stoc pentru a executa comanda
	 * @param y2  y2 este de tipul int si este folosit pentru a genera un nume unic
	 *            pentru fiecare fisier pdf in care se afiseaza cate o facuta pentru
	 *            o comanda
	 * @param idB idB este de tipul int si indica id-ul facutrii.
	 */
	public void insertOrderUpdateProduct(String str, int idO, int y, int y2, int idB) {
		String[] strArray, strArray2;
		float price = 0;
		strArray = str.split(":");
		strArray2 = strArray[1].split(",");
		int ok = 0, quantity = Integer.parseInt(strArray2[2]);
		OrderBLL orderBLL = new OrderBLL();
		ProductBLL productBLL = new ProductBLL();
		ProductDAO productDAO = new ProductDAO();
		Product p = new Product();
		try {
			p = productDAO.findByName(strArray2[1]);
			price = p.getPrice();
			if (p.getQuantity() < quantity) {// verificam daca avem stoc suficient pt a indeplini comanda
				createInsufiectStocMesaj(y++);// daca nu, afisam mesaj
			} else {
				ok = 1;
				generateBill(y2++, strArray2[0], strArray2[1], quantity, price * quantity);
				productBLL.update(p.getId(), p.getNume(), p.getQuantity() - quantity, p.getPrice());
				// daca avem stoc, scadem din stoc cantitatea necesara pt a indeplini comanda
			}
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
		p1 = productBLL.selectAll();
		if (ok == 1) {
			try {
				quantity = Integer.parseInt(strArray2[2]);// inseram o noua comanda
				insertBill(str, idB++);
				orderBLL.newOrder(idO++, strArray2[0], strArray2[1], quantity);

			} catch (Exception ex) {
				LOGGER.log(Level.INFO, ex.getMessage());
			}
		}
		o1 = orderBLL.selectAll();
	}

	/**
	 * Metoda insertBill este apelata pentru o insera o noua facura in tabelul Bill
	 * 
	 * @param str str este de tipul String si contine informatiile necesare pentru a
	 *            aduga o noua facutra in tabel
	 * @param idB idB este de tipul int si indica id-ul facuturii .
	 */
	public void insertBill(String str, int idB) {
		String[] strArray, strArray2;
		strArray = str.split(":");
		strArray2 = strArray[1].split(",");
		BillBLL billBLL = new BillBLL();
		try {
			int quantity = Integer.parseInt(strArray2[2]);
			billBLL.newBill(idB++, strArray2[0], strArray2[1], quantity);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}
	}

	/**
	 * Metoda este utilizata pentru a genera un fisier pdf in care afisam un mesaj
	 * in cazul in care nu avem suficient stoc pentru o executa o comanda
	 * 
	 * @param x parametrul x este de tipul int si este folosit pentru a crea un nume
	 *          unic pentru fiecare fiser pdf
	 */
	public void createInsufiectStocMesaj(int x) {
		try {
			String s = Integer.toString(x);
			String path = "C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment3\\presentation_StocInsuficent" + s
					+ ".pdf";
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();
			doc.add(new Paragraph("Nu se poate genera comanda dorita deoarece stocul de produs nu este suficient"));
			doc.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Aceasta metoda afiseaza cate o factura pentru fiecare comanda ceruta
	 * 
	 * @param x          parametrul x este de tipul int si este folosit pentru a
	 *                   crea cate un nume unic pentru fiecare fisier pdf generat
	 *                   pentru facturi
	 * @param numeClient este de tipul String si indica numele clientului care a
	 *                   cerut comanda
	 * @param numeProdus numeProdus este de tipul String si indica numele produsului
	 *                   cerut in comanda
	 * @param cant       cant este de tipul int si indica cantitatea ceruta
	 * @param price      price reprezinta pretul total pe care clientul trebuie sa
	 *                   il achite
	 */
	public void generateBill(int x, String numeClient, String numeProdus, int cant, float price) {
		try {
			String s = Integer.toString(x);
			String path = "C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment3\\presentation_Bill" + s + ".pdf";
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();
			doc.add(new Paragraph("Clientul " + numeClient + " a comandat: " + numeProdus + " in cantitate de " + cant
					+ " pret total= " + price));
			doc.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Metota createClientPDF este utilizata pentru a crea fisierul pdf in care este
	 * afisat tabelul clientilor.
	 * 
	 * @param x parametrul x este folosit penru a putea crea un nume diferit al
	 *          fiserului pdf de fiecare data cand se citeste comanda report client
	 *          din fiserul de intrare
	 */
	public void createClientPDF(int x) {
		try {
			String s = Integer.toString(x);
			String path = "C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment3\\presentation_clientRaport" + s
					+ ".pdf";
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();
			PdfPTable table = new PdfPTable(3);
			addTableClientHeader(table);
			addRowsClientTable(table, c1);
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			System.out.println("eroare la generarea pdf");
		}
	}

	/**
	 * Metoda folosita pentru a crea header-ul tabelului Client.Cele trei coloane
	 * din tabel sunt: idClient,NumeClient si Oras
	 * 
	 * @param table table reprezinta tabelul clientilor
	 */
	public void addTableClientHeader(PdfPTable table) {
		Stream.of("idClient", "NumeClient", "Oras").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	/**
	 * Metota utilizata pentru a insera un nou rand in tabelul Client
	 * 
	 * @param table table reprezinta tabelul clientilor
	 * @param c12   c12 este lista clientilor din tabel
	 */
	private void addRowsClientTable(PdfPTable table, List<Client> c12) {
		for (Client c : c12) {
			int x = c.getId();
			table.addCell(Integer.toString(x));
			table.addCell(c.getNume());
			table.addCell(c.getCity());
		}
	}

	/**
	 * Metoda utilizata pentru a insera un nou rand in tabelul produselor
	 * 
	 * @param table table este tabelul produselor
	 * @param p12   p12 este lista produselor din tabel
	 */
	private void addRowsProductTable(PdfPTable table, List<Product> p12) {
		for (Product p : p12) {
			int x = p.getId();
			table.addCell(Integer.toString(x));
			table.addCell(p.getNume());
			x = p.getQuantity();
			table.addCell(Integer.toString(x));
			float y = p.getPrice();
			table.addCell(Float.toString(y));
		}
	}

	/**
	 * Metoda createOrderPDF este utilizata pentru a crea fiserul pdf in care se
	 * afiseaza tabelul Orders.
	 * 
	 * @param x parametrul x este pentru a crea un nume nou pentru fiecare fiser pdf
	 *          generat la executarea comenzii order citita din fiserul de intrare
	 */
	public void createOrderPDF(int x) {
		try {
			String s = Integer.toString(x);
			String path = "C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment3\\presentation_orderRaport";
			path = path + s + ".pdf";
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();
			PdfPTable table = new PdfPTable(4);
			addTableOrderHeader(table);
			addRowsOrderTable(table, o1);
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			System.out.println("eroare la generarea pdf");
		}
	}

	/**
	 * metoda este utilizata pentru a insera un nou rand in tabelul orders
	 * 
	 * @param table table este tabelul comenzilor
	 * @param o12   o12 este lista comenzilor din tabel
	 */
	private void addRowsOrderTable(PdfPTable table, List<Orders> o12) {
		for (Orders o : o12) {
			int x = o.getId();
			table.addCell(Integer.toString(x));
			x = o.getIdc();
			table.addCell(Integer.toString(x));
			x = o.getIdp();
			table.addCell(Integer.toString(x));
			x = o.getQuantity();
			table.addCell(Integer.toString(x));
		}
	}

	/**
	 * Aceasta metoda este apelata pentru crearea header-ului din tabelul orders.In
	 * acest tabel cele patru coloane sunt: idOrder, idClient, idProdus si Quantity
	 * 
	 * @param table
	 */
	public void addTableOrderHeader(PdfPTable table) {
		Stream.of("idOrder", "idClient", "idProdus", "Quantity").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	/**
	 * Metoda createProductPDF este utilizata pentru a crea fiserul Pdf in care
	 * afisam tabelul produselor
	 * 
	 * @param x parametrul x este folosit pentru a putea crea un nume nou pentru
	 *          fiecare fiser pdf atunci cand se citeste comanda report product din
	 *          tabelul text de intrare
	 */
	public void createProductPDF(int x) {
		try {
			String s = Integer.toString(x);
			String path = "C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment3\\presentation_productRaport";
			path = path + s + ".pdf";
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();
			PdfPTable table = new PdfPTable(4);
			addTableProductHeader(table);
			addRowsProductTable(table, p1);
			doc.add(table);
			doc.close();
		} catch (Exception e) {
			System.out.println("eroare la generarea pdf");
		}
	}

	/**
	 * Metoda utlizata pentru a crea header-ul tabelului product.Cele patru coloane
	 * din tabel sunt: idProduct, NameProduct, Quantity si Price
	 * 
	 * @param table parametru table este tabelul produselor
	 */
	public void addTableProductHeader(PdfPTable table) {
		Stream.of("idProduct", "NameProduct", "Quantity", "Price").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

}
