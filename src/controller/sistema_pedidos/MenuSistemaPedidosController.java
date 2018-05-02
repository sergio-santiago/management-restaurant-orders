package controller.sistema_pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.InicioController;
import controller.sistema_pedidos.add_comanda.MesasAddComandaController;
import controller.sistema_pedidos.finalizar_pedido.MesasFinalizarPedidoController;
import controller.sistema_pedidos.nuevo_pedido.MesasNuevoPedidoController;
import view.sistema_pedidos.MenuSistemaPedidosView;

public class MenuSistemaPedidosController {
	private MenuSistemaPedidosView view = null;
	
	public MenuSistemaPedidosController() {
		generarVentana();
	}
	
	public void generarVentana() {
		this.view = new MenuSistemaPedidosView();
		//Eventos de la ventana
		view.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				InicioController.main(null);
			}
		});
		view.getBtnNuevoPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MesasNuevoPedidoController();
			}
		});
		view.getBtnAddComanda().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MesasAddComandaController();	
			}
		});
		view.getBtnFinalizarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MesasFinalizarPedidoController();
			}
		});
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}
