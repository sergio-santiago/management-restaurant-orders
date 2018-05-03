package controller.administracion.gestion;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.administracion.gestion.ConfirmacionBorradoView;

public class ConfirmacionBorradoController {
	private ConfirmacionBorradoView view = null;
	private boolean status;
	
	public ConfirmacionBorradoController() {
		generarVentana();
	}
	
	public void generarVentana() {
		this.view = new ConfirmacionBorradoView();
		//Eventos de la ventana		
		view.getBtnSi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				status = true;
				destruirVentana();
			}
		});
		view.getBtnNo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				status = false;
				destruirVentana();
			}
		});
		//Lo hacemos modal
		view.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		view.setVisible(true);
	}
	
	public void destruirVentana() {
		view.dispose();
	}

	public boolean isStatus() {
		return status;
	}
	
}