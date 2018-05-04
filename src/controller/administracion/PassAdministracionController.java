package controller.administracion;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import controller.InicioController;
import view.administracion.PassAdministracionView;

public class PassAdministracionController {
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
				if(checkPassword(new String(view.getPasswordField().getPassword()))) {
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
	
	/**
	 * Convertimos y comparamos con el hash del archivo
	 * @param passwordString
	 * @return
	 */
	private boolean checkPassword(String passwordString) {
		boolean validPass = false;
		try {
			//Generamos MD5 con la cadena del formulario
	    	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    	byte[] array = messageDigest.digest(passwordString.getBytes("UTF-8"));
	    	StringBuilder passwordStringMD5 = new StringBuilder();
	    	for(byte b : array) {
	    		passwordStringMD5.append(String.format("%02x", b));
	    	}
	    	//Sacamos hash del archivo
	    	String path = System.getProperty("user.dir") + "/src/controller/administracion/pass.txt";
	        FileReader f = new FileReader(path);
	        BufferedReader b = new BufferedReader(f);
	        String truePassMD5 = b.readLine();
	        b.close();
	    	//Comparamos
	    	if (passwordStringMD5.toString().equals(truePassMD5)) validPass = true;
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
		return validPass;
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}