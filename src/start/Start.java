package start;

import presentation.ReadFile;

/**
 * Clasa ca relueaza programul
 * 
 * @author Tintesan Cristiana
 *
 */
public class Start {
	/**
	 * Metoda statica main.In aceasta metoda sunt apelate metodele openFile si
	 * closeFile din clasa ReadFile pentru a deschide si inchide fisierul text de
	 * intrare.Metoda readFile citeste fiecare linie a fiserului si apeleaza metoda
	 * corespunzatoare din clasa Controller
	 * 
	 * @param args reprezinta cu array de String-uri
	 */
	public static void main(String[] args) {
		ReadFile a = new ReadFile();
		a.openFile();
		a.readFile();
		a.closeFile();
		System.out.println("Operatiile s-au efectuat cu succesc!");
	}

}
