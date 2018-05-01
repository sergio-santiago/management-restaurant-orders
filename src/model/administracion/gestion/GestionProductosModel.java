package model.administracion.gestion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.DatabaseConnectionService;

public class GestionProductosModel {
	public DefaultTableModel getModelProductos() {
		//Creamos el modelo vacio
		String[] columnasModel = {"ID", "Nombre", "Precio", "Categoria"};
		DefaultTableModel model = new DefaultTableModel(null, columnasModel) {//Al crear el modelo sin datos modificamos algunos metodos de la clase
			private static final long serialVersionUID = 1L;
				Class<?>[] columnTypes = new Class[] {
						Integer.class, String.class, String.class, String.class
				};
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}//Restrincion de tipo de datos en cada columna
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
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
			resultSet = statement.executeQuery("select p.id, p.nombre, p.precio, c.nombre as \"categoria\" from " + DatabaseConnectionService.getDatabase() + ".producto p, " + DatabaseConnectionService.getDatabase() + ".categoria c where p.id_categoria = c.id order by id asc");
			//Iteramos la los datos metiendolos en el modelo
			while(resultSet.next()) {
				rowData[0] = resultSet.getInt("id");
				rowData[1] = resultSet.getString("nombre");
				rowData[2] = NumberFormat.getCurrencyInstance().format(resultSet.getDouble("precio"));
				rowData[3] = resultSet.getString("categoria");
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
	
	public DefaultComboBoxModel<String> getComboBoxModelCategorias() {
		//Creamos el modelo vacio
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from " + DatabaseConnectionService.getDatabase() + ".categoria order by id asc");
			//Iteramos la los datos metiendolos en el modelo
			while(resultSet.next()) {
				StringBuilder categoriaString = new StringBuilder();
				categoriaString.append(resultSet.getInt("id"));
				categoriaString.append("-");
				categoriaString.append(resultSet.getString("nombre"));
				model.addElement(categoriaString.toString());
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
	
	public void insertNewProducto(int id, String nombre, double precio, int idCategoria) {
		String sql = "insert into " + DatabaseConnectionService.getDatabase() + ".producto values (" + id + ", '" + nombre + "', " + precio + ", " + idCategoria + ")";
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
	
	public void updateProducto(int id, String nombre, double precio, int idCategoria) {
		String sql = "update " + DatabaseConnectionService.getDatabase() + ".producto set nombre = '" + nombre + "', precio = " + precio + ", id_categoria = " + idCategoria + " where id = " + id;		
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
	
	public void deleteProducto(int id) {
		String sql = "delete from " + DatabaseConnectionService.getDatabase() + ".producto where id = " + id;
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