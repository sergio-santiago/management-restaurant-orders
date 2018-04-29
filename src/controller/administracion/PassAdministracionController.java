package controller.administracion;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import controller.InicioController;
import view.administracion.PassAdministracionView;

public class PassAdministracionController {
	private String pass = "1234";
	private PassAdministracionView view = null;
	
	public PassAdministracionController() {
		generarVentana();
	}
	
	public void generarVentana() {
		this.view = new PassAdministracionView();
		//Eventos de la ventana		
		view.getBtnEntrar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if(Arrays.equals(view.getPasswordField().getPassword(), pass.toCharArray())) {
					InicioController.destruirVentana();
					destruirVentana();
					new MenuAdministracionController();
				} else {
					view.getLblPassIncorrecta().setVisible(true);
					view.getPasswordField().setText("");
					view.getPasswordField().requestFocusInWindow();
				}
			}
		});
		//Lo hacemos modal
		view.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		view.setVisible(true);
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}