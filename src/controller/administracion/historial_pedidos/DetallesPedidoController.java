package controller.administracion.historial_pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.administracion.historial_pedidos.DetallesPedidoModel;
import view.administracion.historial_pedidos.DetallesPedidoView;

public class DetallesPedidoController {
	private DetallesPedidoModel model = null;
	private DetallesPedidoView view = null;
	
	DetallesPedidoController(int idPedido) {	
		model = new DetallesPedidoModel();
		DefaultTableModel detallesPedido = model.getDetallesPedidoModel(idPedido);
		Object[] propiedadesPedido = model.getPropiedadesPedido(idPedido);
		insertDataGenerarVentana(propiedadesPedido, detallesPedido);
	}
	
	public void generarVentana(boolean lblAtencionVisible, int id, boolean enCurso, String fecha, String mesa, double precio, TableModel tableDetallesPedidoModel) {
		//Creamos la vista
		view = new DetallesPedidoView(lblAtencionVisible, id, enCurso, fecha, mesa, precio, tableDetallesPedidoModel);
		//Eventos de la vista
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new HistorialPedidosController();
			}
		});
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
	/**
	 * Le metemos a la vista todos los datos que necesita y que hemos sacado a traves del modelo
	 * @param propiedadesPedido
	 * @param detallesPedido
	 */
	private void insertDataGenerarVentana(Object[] propiedadesPedido, DefaultTableModel detallesPedido) {
		//Sacamos los datos del array con las propiedades del pedido
		int id = (int) propiedadesPedido[0];
		double precio = (double) propiedadesPedido[1];
		boolean enCurso = (boolean) propiedadesPedido[2];
		String fecha = (String) propiedadesPedido[3];
		String mesa = (String) propiedadesPedido[4];		
		
		//Si la suma de subtotales no coincide con precio mostramos mensaje
		boolean lblAtencionVisible = (precio == calcularSumaSubtotales(detallesPedido)) ? false : true;
		
		generarVentana(lblAtencionVisible, id, enCurso, fecha, mesa, precio, detallesPedido);
	}
	
	/**
	 * Recorremos el modelo de la tabla para calcular la suma de los subtotales (estan formateados asi que hay que convertirlos manualmente)
	 * @param detallesPedido
	 * @return
	 */
	private double calcularSumaSubtotales(DefaultTableModel detallesPedido) {
		double sumaSubtotales = 0;
		for(int i = 0; i < detallesPedido.getRowCount(); i++) {
			String subtotalFormateado = (String) detallesPedido.getValueAt(i, 3);
			int finalNumero = subtotalFormateado.length() - 2;
			String subtotalSinDivisa = subtotalFormateado.substring(0, finalNumero);
			Double subtotal = Double.valueOf(subtotalSinDivisa.replace(",", "*").replace(".", "").replace("*", "."));
			sumaSubtotales += subtotal;
		}
		return sumaSubtotales;
	}

}
