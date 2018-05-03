package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnectionService {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String driverType = "thin";
	private static String host = "localhost";
	private static int port = 1521;
	private static String sid = "XE";
	private static String user = "SYSTEM";
	private static String pass = "root";
	private static String database = "default_database";
	private static String url = null;
	private static Connection connection = null;
	
	private DatabaseConnectionService() {}
	
	public static void openConnection() {
		try {
			Class.forName(DatabaseConnectionService.driver);
			DatabaseConnectionService.url = "jdbc:oracle:" + DatabaseConnectionService.driverType + ":@" + DatabaseConnectionService.host + ":" + DatabaseConnectionService.port + ":" + DatabaseConnectionService.sid;
			DatabaseConnectionService.connection = DriverManager.getConnection(DatabaseConnectionService.url, DatabaseConnectionService.user, DatabaseConnectionService.pass);
			DatabaseConnectionService.checkConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection() {
		try {
			DatabaseConnectionService.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void checkConnection() {
		try {
			if (DatabaseConnectionService.connection.isClosed()) {
				System.out.println("ATENCION: la conexion con la BBDD se encuentra cerrada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return DatabaseConnectionService.connection;
	}
	
	public static String getDatabase() {
		return DatabaseConnectionService.database;
	}
	
}
