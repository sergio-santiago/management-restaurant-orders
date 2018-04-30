package model.administracion.historial_pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.swing.table.DefaultTableModel;

import model.DatabaseConnectionService;

public class DetallesPedidoModel {
	
	/**
	 * Retornamos el modelo de la tabla con los productos del pedido, la cantidad...etc
	 * @param idPedido
	 * @return
	 */
	public DefaultTableModel getDetallesPedidoModel(int idPedido) {
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
				
				Object[] rowData = new Object[columnasModel.length];//Creamos un array de cualquier tipo de objeto para meter los diferentes tipos de datos que contiene un registro
				
				//Sacamos de BBDD
				DatabaseConnectionService.openConnection();
				Statement statement = null;
				ResultSet resultSet = null;
				try {
					//Hacemos consulta
					statement = DatabaseConnectionService.getConnection().createStatement();
					String query = "select p.NOMBRE as \"producto\", p.PRECIO as \"precio\", sum (cp.CANTIDAD) as \"unidades\" from " + DatabaseConnectionService.getDatabase() + ".COMANDA c, " + DatabaseConnectionService.getDatabase() + ".PRODUCTO p, " + DatabaseConnectionService.getDatabase() + ".COMANDA_PRODUCTO cp where c.\"ID\" = cp.ID_COMANDA and p.\"ID\" = cp.ID_PRODUCTO and c.ID_PEDIDO = " + idPedido + " group by p.\"ID\", p.NOMBRE, p.PRECIO";
					resultSet = statement.executeQuery(query);
					
					//Iteramos la los datos metiendolos en el modelo
					while(resultSet.next()) {
						rowData[0] = resultSet.getString("producto");
						rowData[1] = NumberFormat.getCurrencyInstance().format(resultSet.getDouble("precio"));
						rowData[2] = resultSet.getInt("unidades");
						rowData[3] = NumberFormat.getCurrencyInstance().format(resultSet.getDouble("precio") * resultSet.getInt("unidades"));	
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

	/**
	 * Retornamos un array con la informacion del pedido: id, mesa, fecha...etc
	 * @param idPedido
	 * @return
	 */
	public Object[] getPropiedadesPedido(int idPedido) {
		Object[] propiedades = new Object[5];
		
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			String query = "select p.\"ID\", p.PRECIO, p.EN_CURSO, p.FECHA, m.NOMBRE from " + DatabaseConnectionService.getDatabase() + ".PEDIDO p, " + DatabaseConnectionService.getDatabase() + ".MESA m where p.ID_MESA = m.\"ID\" and p.\"ID\" = " + idPedido;
			resultSet = statement.executeQuery(query);
			resultSet.next();
			//Metemos en array
			propiedades[0] = resultSet.getInt("id");
			propiedades[1] = resultSet.getDouble("precio");
			propiedades[2] = (resultSet.getString("en_curso").equals("true")) ? true : false;
			propiedades[3] = resultSet.getString("fecha").substring(0, resultSet.getString("fecha").indexOf(" "));
			propiedades[4] = resultSet.getString("nombre");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		
		return propiedades;
	}

}
