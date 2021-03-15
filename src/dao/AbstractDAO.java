package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 * Clasa care creaza operatiile de insert, delete, update si select all pentru
 * tabele.
 * 
 * @author Tintesan Cristiana;unele portiuni de cod au fost preluate din
 *         bibliografie
 *
 * @param <T>
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * Metoda pentru a selecta un anumit camp dintr-un tabel
	 * 
	 * @param field coloana asupra careia sa pune conditia
	 * @return
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * Metoda pentru o afisa tabelul in intregime
	 * 
	 * @return
	 */
	public String createSelectAllStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	/**
	 * Metoda pentru a insera intr-un tabel
	 * 
	 * @param n
	 * @return
	 */
	public String createInsertStatement(int n) {
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO ");
		sb.append(type.getSimpleName());
		sb.append(" VALUES (");
		for (int i = 0; i < n - 1; i++)
			sb.append("? ,");
		sb.append("?) ");
		return sb.toString();
	}

	/**
	 * Metoda pentru a sterge un element dintr-un tabel
	 * 
	 * @param names
	 * @return
	 */
	public String createDeleteStatement(ArrayList<String> names) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE ");
		for (String it : names) {
			sb.append(it + "=? ");
			if (it != names.get(names.size() - 1))
				sb.append("and ");
		}
		return sb.toString();
	}

	/**
	 * Metoda pentru a face update.Este folosit doar pentru tabelul Product
	 * 
	 * @param names
	 * @param field
	 * @return
	 */
	public String createUpdateStatement(ArrayList<String> names, String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for (String it : names) {
			sb.append(it);
			sb.append("=? ");
			if (it != names.get(names.size() - 1))
				sb.append(",");
		}
		sb.append(" WHERE ");
		sb.append(field);
		sb.append("= ?");
		return sb.toString();
	}

	/**
	 * Metoda folosita pentru gasi un client sau un produs dupa nume
	 * 
	 * @param name
	 * @return
	 */
	public T findByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("nume");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, name);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "findBynameStatement " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Metota pentru a insera intr-un tabel
	 * 
	 * @param elements
	 * @return
	 */
	public T insert(ArrayList<Object> elements) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createInsertStatement(elements.size());
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			int i = 1;
			for (Object it : elements) {
				if (it instanceof Integer)
					statement.setInt(i, (int) it);
				else if (it instanceof String)
					statement.setString(i, (String) it);
				else if (it instanceof Float)
					statement.setFloat(i, (Float) it);
				i++;
			}
			statement.execute();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "InsertStatement " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;

	}

	/**
	 * Metoda utilizata pentru a sterge dintr-un tabel
	 * 
	 * @param list
	 * @param names
	 * @return
	 */
	public T delete(ArrayList<Object> list, ArrayList<String> names) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteStatement(names);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i = 1;
			for (Object it : list) {
				if (it instanceof Integer)
					statement.setInt(i, (int) it);
				else
					statement.setString(i, (String) it);
				i++;
			}
			statement.execute();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "DeleteStatement " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Pentru a face update la tabelul Product
	 * 
	 * @param list
	 * @param names
	 * @param id
	 * @param field
	 * @return
	 */
	public T update(ArrayList<Object> list, ArrayList<String> names, int id, String field) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createUpdateStatement(names, field);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int i = 1;
			for (Object it : list) {
				if (it instanceof Integer)
					statement.setInt(i, (int) it);
				else if (it instanceof String)
					statement.setString(i, (String) it);
				else
					statement.setFloat(i, (Float) it);
				i++;
			}
			statement.setInt(i, id);
			statement.execute();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Pentru a afisa toate elementele dintr-un tabel
	 * 
	 * @return
	 */
	public List<T> selectAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllStatement();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:selectAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

}
