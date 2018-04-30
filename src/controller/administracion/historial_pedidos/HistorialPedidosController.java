package controller.administracion.historial_pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import controller.administracion.MenuAdministracionController;
import model.administracion.historial_pedidos.HistorialPedidosModel;
import view.administracion.historial_pedidos.HistorialPedidosView;

public class HistorialPedidosController {

	private HistorialPedidosModel model = null;
	private HistorialPedidosView view = null;
	
	public HistorialPedidosController() {
		model = new HistorialPedidosModel();
		generarVentana(model.getModelPedidos());
	}
	
	public void generarVentana(DefaultTableModel pedidosTableModel) {
		//Creamos la vista(pasamos el modelo de la tabla)
		view = new HistorialPedidosView(pedidosTableModel);

		//Eventos de la vista
		view.getBtnDetalles().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTablePedidos().getSelectedRow() != -1) {
					destruirVentana();
					new DetallesPedidoController((int) view.getTablePedidos().getModel().getValueAt(view.getTablePedidos().getSelectedRow(), 0));
				} else {
					view.getLblNoDetalles().setVisible(true);
				}
			}
		});
		
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MenuAdministracionController();
			}
		});
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
}
