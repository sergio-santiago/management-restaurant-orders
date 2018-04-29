package controller.administracion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.InicioController;
import view.administracion.MenuAdministracionView;

public class MenuAdministracionController {
	private MenuAdministracionView view = null;
	
	MenuAdministracionController() {
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
				System.out.println("Vamos al historial");
			}
		});
		view.getBtnGestionCategorias().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				System.out.println("Vamos a la gestion de CATEGORIAS");
			}
		});
		view.getBtnGestionProductos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				System.out.println("Vamos a la gestion de PRODUCTOS");
			}
		});
		view.getBtnGestionMesas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				destruirVentana();
				System.out.println("Vamos a la gestion de MESAS");
			}
		});
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}
