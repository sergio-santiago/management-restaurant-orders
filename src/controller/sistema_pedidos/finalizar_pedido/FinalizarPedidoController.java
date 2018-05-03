package controller.sistema_pedidos.finalizar_pedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import model.sistema_pedidos.FinalizarPedidoModel;
import view.sistema_pedidos.FinalizarPedidoView;

public class FinalizarPedidoController {
	private FinalizarPedidoModel model = null;
	private FinalizarPedidoView view = null;
	
	FinalizarPedidoController(int idPedido) {
		model = new FinalizarPedidoModel();
		DefaultTableModel detallesPedido = model.getDetallesPedidoModel(idPedido);
		Object[] mesaAndPrecio = model.getMesaAndPrecioPedido(idPedido);	
		boolean lblAtencionVisible = ((double) mesaAndPrecio[1] == calcularSumaSubtotales(detallesPedido)) ? false : true;//Si la suma de subtotales no coincide con precio mostramos mensaje
		generarVentana(idPedido, lblAtencionVisible, (String) mesaAndPrecio[0], (double) mesaAndPrecio[1], detallesPedido);
	}
	
	public void generarVentana(int idPedido, boolean lblAtencionVisible, String mesa, double precio, TableModel tableDetallesPedidoModel) {
		//Creamos la vista
		view = new FinalizarPedidoView(lblAtencionVisible, mesa, precio, tableDetallesPedidoModel);
		//Eventos de la vista
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MesasFinalizarPedidoController();
			}
		});
		view.getBtnFinalizarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.finalizarPedido(idPedido);
				destruirVentana();
				new MenuSistemaPedidosController();
			}
		});
	}
	
	public void destruirVentana() {
		this.view.dispose();
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
