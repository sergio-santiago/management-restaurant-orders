package model.administracion.historial_pedidos;

import java.sql.Statement;
import java.text.NumberFormat;
import javax.swing.table.DefaultTableModel;
import model.DatabaseConnectionService;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistorialPedidosModel {
	
	public DefaultTableModel getModelPedidos() {
		//Creamos el modelo vacio
		String[] columnasModel = {"ID", "Precio", "En curso?", "Fecha", "Mesa"};
		DefaultTableModel model = new DefaultTableModel(null, columnasModel) {//Al crear el modelo sin datos modificamos algunos metodos de la clase
			private static final long serialVersionUID = 1L;
				Class<?>[] columnTypes = new Class[] {
					Float.class, Integer.class, Boolean.class, String.class, String.class
				};
				public Class<?> getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}//Restrincion de tipo de datos en cada columna
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
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
			resultSet = statement.executeQuery("select p.\"ID\", p.PRECIO, p.EN_CURSO, p.FECHA, m.NOMBRE from default_database.PEDIDO p, default_database.MESA m where p.ID_MESA = m.\"ID\"");
			
			//Iteramos la los datos metiendolos en el modelo
			while(resultSet.next()) {
				rowData[0] = resultSet.getInt("id");
				rowData[1] = NumberFormat.getCurrencyInstance().format(resultSet.getDouble("precio"));
				rowData[2] = (resultSet.getString("en_curso").equals("true")) ? true : false;
				rowData[3] = resultSet.getString("fecha").substring(0, resultSet.getString("fecha").indexOf(" "));
				rowData[4] = resultSet.getString("nombre");
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
}
	 

