package controlador.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Book;

public class Database {

	private static Database instance = null;
	private final String URL_BASEDATOS = "jdbc:mysql://localhost:3306/libreria";
	private final String user = "root";
	private final String password = "123456aA!";
	private Connection connection;

	private Database() {
		try {
			connection = DriverManager.getConnection(URL_BASEDATOS, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}, "Shutdown-thread"));
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}
}
