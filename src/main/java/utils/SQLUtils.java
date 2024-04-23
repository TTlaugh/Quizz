package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SQLUtils {

	public static DataSource getDataSource() {
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setURL(Constant.MySQLProperties.URL);
		mysqlDS.setUser(Constant.MySQLProperties.USERNAME);
		mysqlDS.setPassword(Constant.MySQLProperties.PASSWORD);
		mysqlDS.setDatabaseName("QuizzServer");
		return mysqlDS;
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = getDataSource().getConnection();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		System.out.println("Releasing all open resources ...");
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException)e).getSQLState());
				System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
				System.err.println("Message: " + e.getMessage()); 
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		testDataSource();
	}
	private static void testDataSource() {
<<<<<<< HEAD:src/utils/SQLUtils.java
		Connection connection = SQLUtils.getConnection();
		Person person = new Person("123", "Nguyen Van", "hehe", "a@mail", "0123");
		if (connection != null) {
			System.out.println("Connected to the database!");
		}
		try {
			PreparedStatement pStatement = connection.prepareStatement(
					"INSERT INTO Person VALUES (?,?,?,?,?)");
			pStatement.setString(1, person.getPersonID());
			pStatement.setString(2, person.getFirstName());
			pStatement.setString(3, person.getLastName());
			pStatement.setString(4, person.getEmail());
			pStatement.setString(5, person.getPhone());
			pStatement.executeUpdate();
			SQLUtils.closeConnection(connection);
		} catch (SQLException e) {
			SQLUtils.printSQLException(e);
		}
=======
//		Connection connection = SQLUtils.getConnection();
//		Person person = new Person("123", "Nguyen Van", "hehe", "a@mail", "0123");
//		try {
//			PreparedStatement pStatement = connection.prepareStatement(
//					"INSERT INTO Person VALUES (?,?,?,?,?)");
//			pStatement.setString(1, person.getPersonID());
//			pStatement.setString(2, person.getFirstName());
//			pStatement.setString(3, person.getLastName());
//			pStatement.setString(4, person.getEmail());
//			pStatement.setString(5, person.getPhone());
//			pStatement.executeUpdate();
//			SQLUtils.closeConnection(connection);
//		} catch (SQLException e) {
//			SQLUtils.printSQLException(e);
//		}
>>>>>>> 5e8e4ffabfdbebdf84185c65e8014c172afd3dda:src/main/java/utils/SQLUtils.java
	}
}
