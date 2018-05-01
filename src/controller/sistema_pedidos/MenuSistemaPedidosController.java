package controller.sistema_pedidos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.InicioController;
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
				System.out.println("NUEVO PEDIDO");
			}
		});
		view.getBtnAddComanda().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				System.out.println("ADD COMANDA A PEDIDO");
			}
		});
		view.getBtnFinalizarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				System.out.println("FINALIZAR PEDIDO");
			}
		});
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}
