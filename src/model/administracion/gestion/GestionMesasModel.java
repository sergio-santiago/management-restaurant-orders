package model.administracion.gestion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import model.DatabaseConnectionService;

public class GestionMesasModel {
	public DefaultTableModel getModelMesas() {
		//Creamos el modelo vacio
		String[] columnasModel = {"ID", "Nombre"};
		DefaultTableModel model = new DefaultTableModel(null, columnasModel) {//Al crear el modelo sin datos modificamos algunos metodos de la clase
			private static final long serialVersionUID = 1L;
				Class<?>[] columnTypes = new Class[] {
						Integer.class, String.class
				};
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}//Restrincion de tipo de datos en cada columna
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}//Columnas no editables
		};
		
		Object[] rowData = new Object[columnasModel.length];//Creamos un array de cualquier tipo de objeto para meter los diferentes tipos de datos que contiene un registro
		
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from " + DatabaseConnectionService.getDatabase() + ".mesa order by id asc");
			
			//Iteramos la los datos metiendolos en el modelo
			while(resultSet.next()) {
				rowData[0] = resultSet.getInt("id");
				rowData[1] = resultSet.getString("nombre");
				model.addRow(rowData);//metemos el array de datos como un nuevo registro
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		
		return model;
	}
	
	public void insertNewMesa(int id, String nombre) {
		String sql = "insert into " + DatabaseConnectionService.getDatabase() + ".mesa values (" + id + ", '" + nombre + "')";
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		try {
			statement = DatabaseConnectionService.getConnection().createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
	}
	
	public void updateMesa(int id, String nombre) {
		String sql = "update " + DatabaseConnectionService.getDatabase() + ".mesa set nombre = '" + nombre + "' where id = " + id;
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		try {
			statement = DatabaseConnectionService.getConnection().createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
	}
	
	public void deleteMesa(int id) {
		String sql = "delete from " + DatabaseConnectionService.getDatabase() + ".mesa where id = " + id;
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		try {
			statement = DatabaseConnectionService.getConnection().createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
	}
	
}