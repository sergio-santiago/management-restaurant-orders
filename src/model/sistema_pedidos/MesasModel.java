package model.sistema_pedidos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.DatabaseConnectionService;

public class MesasModel {
	public String[] getNombreMesasDisponibles() {
		ArrayList<String> nombreMesasDisponibles = new ArrayList<String>();
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			String query = "select * from " + DatabaseConnectionService.getDatabase() + ".MESA minus select m.* from " + DatabaseConnectionService.getDatabase() + ".MESA m, " + DatabaseConnectionService.getDatabase() + ".PEDIDO p where m.\"ID\" = p.ID_MESA and p.EN_CURSO = 'true'";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				nombreMesasDisponibles.add(resultSet.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		String[] nombreMesasArray = nombreMesasDisponibles.toArray(new String[nombreMesasDisponibles.size()]);
		return nombreMesasArray;
	}
	
	public String[] getNombreMesasOcupadas() {
		ArrayList<String> nombreMesasOcupadas = new ArrayList<String>();
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			String query = "select m.* from " + DatabaseConnectionService.getDatabase() + ".MESA m, " + DatabaseConnectionService.getDatabase() + ".PEDIDO p where m.\"ID\" = p.ID_MESA and p.EN_CURSO = 'true'";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				nombreMesasOcupadas.add(resultSet.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
		    try { if (statement != null) statement.close(); } catch (Exception e) {};
		    try { if (DatabaseConnectionService.getConnection() != null) DatabaseConnectionService.closeConnection(); } catch (Exception e) {};
		}
		String[] nombreMesasArray = nombreMesasOcupadas.toArray(new String[nombreMesasOcupadas.size()]);
		return nombreMesasArray;
	}
	
	public int findIdPedidoActivoByNombreMesa(String nombreMesa) {
		int id = -1;
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();			
			String query = "select p.id from " + DatabaseConnectionService.getDatabase() + ".pedido p, " + DatabaseConnectionService.getDatabase() + ".mesa m where p.id_mesa = m.id and p.en_curso = 'true' and m.nombre = '" + nombreMesa + "'";
			resultSet = statement.executeQuery(query);
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
	
	public int searchMesaIdByNombre(String nombreMesa) {
		int id = -1;
		//Sacamos de BBDD
		DatabaseConnectionService.openConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Hacemos consulta
			statement = DatabaseConnectionService.getConnection().createStatement();
			String query = "select id from " + DatabaseConnectionService.getDatabase() + ".mesa where nombre = '" + nombreMesa + "'";
			resultSet = statement.executeQuery(query);
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
	
}
