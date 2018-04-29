package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.administracion.PassAdministracionController;
import view.InicioView;

public class InicioController {
	private static InicioView view = null;
	public static void main(String[] args) {
		generarVentana();
	}
	
	public static void generarVentana() {
		view = new InicioView();
		//Eventos de la ventana
		view.getBtnInicioDelSistema().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Inicio del sistema...");
			}
		});
		view.getBtnAdministracionDelSistema().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PassAdministracionController();
			}
		});
	}
	
	public static void destruirVentana() {
		view.dispose();
	}
	
}