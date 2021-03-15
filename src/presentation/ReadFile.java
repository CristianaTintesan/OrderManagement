package presentation;

import java.util.Scanner;
import java.io.File;
import presentation.ReadFile;
import presentation.Controller;

/**
 * Clasa pentru citirea si inchiderea fiserului.
 * 
 * @author Tintesan Cristiana
 *
 */
public class ReadFile {
	/**
	 * Variabila de tipul Scanner folosita la citirea din fisier
	 */
	Scanner scan;

	/**
	 * pentru a putea apela metodele din clasa Controller
	 */
	Controller c = new Controller();

	/**
	 * Metoda openFile() are rolul de a deschide fisierul text de intrare.In caz de
	 * esec, este aruncata o exceptie cu mesaj "could not open the file"
	 */
	public void openFile() {
		try {// deschidem fisierul text din care citim comenzile
			File file = new File("C:\\Users\\tinte\\OneDrive\\Desktop\\TP\\Assignment3\\src\\commands.txt");
			scan = new Scanner(file);
		} catch (Exception e) {
			System.out.println("could not open de file");
		}
	}

	/**
	 * 
	 * Metoda readFile() citeste fiecare linie din fiser ca un String memorat in
	 * str.In functie de continutul lui str sunt apelate metodele din clasa
	 * Controller pentru realizarea operatiilor cerute
	 */
	public void readFile() {
		String str;
		String[] strArray;
		int x1 = 1, idc = 1, idp = 1, x2 = 1, idO = 1, y = 1, x3 = 1, y2 = 1, x4 = 1;
		while (scan.hasNext()) {// parcurgem pana la final fisierul
			str = scan.nextLine();
			strArray = str.split(":");// determinam ce instructiune avem de facut
			if (strArray[0].equals("Insert client")) {
				c.insertClient(str, idc++);
			} else if (strArray[0].equals("Delete client")) {
				c.deleteClient(str);
			} else if (str.equals("Report client")) {
				c.createClientPDF(x1++);
			} else if (strArray[0].equals("Insert product")) {
				c.insertProduct(str, idp++);
			} else if (strArray[0].equals("Delete Product")) {
				c.deleteProduct(str);
			} else if (str.equals("Report product")) {
				c.createProductPDF(x2++);
			} else if (strArray[0].equals("Order")) {
				c.insertOrderUpdateProduct(str, idO++, y++, y2++, x4++);
			} else if (str.equals("Report order"))
				c.createOrderPDF(x3++);
		}
	}

	/**
	 * Metoda closeFile() inchide fiserul deschis de openFile
	 */
	public void closeFile() {
		scan.close();
	}

}
