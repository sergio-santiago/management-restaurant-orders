package controller.administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.InicioController;
import controller.administracion.historial_pedidos.HistorialPedidosController;
import view.administracion.MenuAdministracionView;

public class MenuAdministracionController {
	private MenuAdministracionView view = null;
	
	public MenuAdministracionController() {
		generarVentana();
	}
	
	public void generarVentana() {
		this.view = new MenuAdministracionView();
		//Eventos de la ventana
		view.getBtnSalir().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				InicioController.main(null);
			}
		});
		view.getBtnHistorial().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new HistorialPedidosController();
			}
		});
		view.getBtnGestionCategorias().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				System.out.println("Vamos a la gestion de CATEGORIAS");//-----------------------------------------------------------------TODO
			}
		});
		view.getBtnGestionProductos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				System.out.println("Vamos a la gestion de PRODUCTOS");//-----------------------------------------------------------------TODO
			}
		});
		view.getBtnGestionMesas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				System.out.println("Vamos a la gestion de MESAS");//-----------------------------------------------------------------TODO
			}
		});
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}
