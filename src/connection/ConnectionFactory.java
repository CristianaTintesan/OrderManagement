package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa pentru a realiza conexiunea cu baza de date
 * 
 * @author Cod preluat din bibliografie
 *
 */
public class ConnectionFactory {

	/**
	 * Pentru a afisa mesajele in cazul in care operatia nu este executa cu succes.
	 */
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	/**
	 * Pentru a aceesa baza de date
	 */
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	/**
	 * databese1 este numele schemei din MySql
	 */
	private static final String DBURL = "jdbc:mysql://localhost:3306/database1";
	/**
	 * user pt baza de date din MySql
	 */
	private static final String USER = "root";
	/**
	 * parola pentru baza de date din MySql
	 */
	private static final String PASS = "Cristiana16";

	/**
	 * instantierea unui variabiled de tipul ConnectioFactory
	 */
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Metoda pentru a realiza conexiunea
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Aceasta metoda realizeaza conexiunea cu baza de date
	 * 
	 * @return retunreaza un mesaj de eroare in cazul in care conexiunea a esuat sau
	 *         conexiunea
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Metoda folosita pentru a obtine conexiunea
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * Aceasta metoda este folosita pentru a opri conexiunea cu baza de date
	 * 
	 * @param connection reprezinta conexiunea care trebuie inchisa
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	/**
	 * Metota folosita pentru a inchide statement-ul
	 * 
	 * @param statement
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	/**
	 * Metoda folosita pentru a inchide resulSet
	 * 
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}