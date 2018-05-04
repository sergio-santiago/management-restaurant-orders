package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DatabaseConnectionService {
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String driverType = "thin";
	private static String host;
	private static int port = 1521;
	private static String sid = "XE";
	private static String user;
	private static String pass;
	private static String database;
	private static String url = null;
	private static Connection connection = null;
	
	private DatabaseConnectionService() {}
	
	public static void openConnection() {
		extractFileConnectionData();
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
	
	/**
	 * Sacamos del fichero de configuracion los datos para establecer conexion con la BBDD
	 */
	private static void extractFileConnectionData() {
	    Properties properties = new Properties();
	    InputStream fileInputStream = null;
	    try {
	    	fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/model/database_config.properties");
	        //cargamos los datos del archivo
	    	properties.load(fileInputStream);
	        DatabaseConnectionService.host = properties.getProperty("dbhost");
	        DatabaseConnectionService.user = properties.getProperty("dbuser");
	        DatabaseConnectionService.pass = properties.getProperty("dbpassword");
	        DatabaseConnectionService.database = properties.getProperty("dbname");
	    } catch (IOException ex) {
	    	System.out.println("ERROR EN LECTURA -> database_config.properties");
	        ex.printStackTrace();
	    } finally {
	        if (fileInputStream != null) {
	            try {
	            	fileInputStream.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

	public static Connection getConnection() {
		return DatabaseConnectionService.connection;
	}
	
	public static String getDatabase() {
		return DatabaseConnectionService.database;
	}
	
}
