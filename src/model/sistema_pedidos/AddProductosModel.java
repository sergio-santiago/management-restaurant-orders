package model.sistema_pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.DatabaseConnectionService;

public class AddProductosModel {
	
	/**
	 * Retorna un array con el nombre de todas las categorias de productos
	 * @return
	 */
	public String[] getNombreCategorias() {
		ArrayList<String> categorias = new ArrayList<String>();
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery("select nombre from " + DatabaseConnectionService.getDatabase() + ".categoria order by id asc");
			while(resultSet.next()) {
			categorias.add(resultSet.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		return categorias.toArray(new String[categorias.size()]);
	}
	
	/**
	 * Crea y retorna vacio el modelo de tabla usado en la vista AddProductosView
	 * @return
	 */
	public DefaultTableModel getEmptyTableModel() {
		//Creamos el modelo vacio
		String[] columnasModel = {"Producto", "Precio", "Unidades", "Subtotal"};
		DefaultTableModel model = new DefaultTableModel(null, columnasModel) {//Al crear el modelo sin datos modificamos algunos metodos de la clase
			private static final long serialVersionUID = 1L;
				Class<?>[] columnTypes = new Class[] {
					String.class, String.class, Integer.class, String.class
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
		return model;
	}
	
	/**
	 * Retorna un array con el nombre de los productos pertenecientes a la categoria pasada como parametro
	 * @param nombreCategoria
	 * @return
	 */
	public String[] getNombreProductosFromCategoria(String nombreCategoria) {
		ArrayList<String> nombreProductos = new ArrayList<String>();
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			String sql = "select p.nombre from " + DatabaseConnectionService.getDatabase() + ".producto p, " + DatabaseConnectionService.getDatabase() + ".categoria c where p.id_categoria = c.id and c.nombre = '" + nombreCategoria + "' order by p.id asc";
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				nombreProductos.add(resultSet.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		return nombreProductos.toArray(new String[nombreProductos.size()]);
	}
	
	/**
	 * Retorna el precio de un producto pasandole como parametro el nombre de este
	 * @param nombreProducto
	 * @return
	 */
	public double getPrecioProductoFromNombre(String nombreProducto) {
		double precio = 0;
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			String sql = "select precio from " + DatabaseConnectionService.getDatabase() + ".producto where nombre = '" + nombreProducto + "'";
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			precio = resultSet.getDouble("precio");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		return precio;
	}
	
	/**
	 * Retorna el ID de un producto pasandole como parametro el nombre de este
	 * @param nombreProducto
	 * @return
	 */
	public int getIdProductoFromNombre(String nombreProducto) {
		int id = -1;
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			String sql = "select id from " + DatabaseConnectionService.getDatabase() + ".producto where nombre = '" + nombreProducto + "'";
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			id = resultSet.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		return id;
	}
	
	public double getPrecioPedidoFromId(int id) {
		double precio = 0;
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			String sql = "select precio from " + DatabaseConnectionService.getDatabase() + ".pedido where id = " + id;
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			precio = resultSet.getDouble("precio");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		return precio;
	}
	
	public int getLastIdFrom(String tableName) {
		int id = 0;
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			String sql = "select MAX(id) as \"id\" from " + DatabaseConnectionService.getDatabase() + "." + tableName;
			statement = DatabaseConnectionService.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			id = resultSet.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		return id;
	}
	
	public void insertNewPedido(int id, double precio, String fecha, int idMesa) {
		String sql = "insert into " + DatabaseConnectionService.getDatabase() + ".pedido values (" + id + ", " + precio + ", 'true', TO_DATE('" + fecha + "', 'mm-dd-yyyy'), " + idMesa + ")";
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
	
	public void insertNewComanda(int id, int idPedido) {
		String sql = "insert into " + DatabaseConnectionService.getDatabase() + ".comanda values (" + id + ", " + idPedido + ")";
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
	
	public void inserNewRelacionComandaProducto(int idComanda, int idProducto, int cantidad) {
		String sql = "insert into " + DatabaseConnectionService.getDatabase() + ".comanda_producto values (" + idComanda + ", " + idProducto + ", " + cantidad +")";
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
	
	public void updatePrecioTotalPedido(int idPedido, double precio) {
		String sql = "update " + DatabaseConnectionService.getDatabase() + ".pedido set precio = " + precio + " where id = " + idPedido;
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
