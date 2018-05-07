package controller.administracion;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import view.administracion.CambiarPassAdministracionView;

public class CambiarPassAdministracionController {
	private CambiarPassAdministracionView view = null;
	
	public CambiarPassAdministracionController() {
		generarVentana();
	}
	
	public void generarVentana() {
		this.view = new CambiarPassAdministracionView();
		//Eventos de la ventana		
		view.getBtnAceptar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Comprobamos campos llenos
				if(new String(view.getPassActualField().getPassword()).equals("") || new String(view.getNuevaPassField().getPassword()).equals("") || new String(view.getRepetirNuevaPassField().getPassword()).equals("")) {
					view.getLblMensajeError().setText("Atencion no puede haber campos vacios");
					view.getLblMensajeError().setVisible(true);
				} else {
					//Validamos pass anterior
					if(!checkCurrentPassword(new String(view.getPassActualField().getPassword()))) {
						view.getLblMensajeError().setText("Atencion la contrase\u00F1a actual no es correcta");
						view.getLblMensajeError().setVisible(true);
					} else {
						//Comprobamos nuevas pass
						if(!Arrays.equals(view.getNuevaPassField().getPassword(), view.getRepetirNuevaPassField().getPassword())) {
							view.getLblMensajeError().setText("Atencion las nuevas contrase\u00F1as no coinciden");
							view.getLblMensajeError().setVisible(true);
						} else {
							//Guardamos nueva pass en archivo
							updatePassword(new String(view.getNuevaPassField().getPassword()));
							destruirVentana();
						}	
					}
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
	private boolean checkCurrentPassword(String currentPassword) {
		boolean validPass = false;
		File archivo = null;
	    FileReader fr = null;
	    BufferedReader br = null;
		try {
			//Generamos MD5 con la cadena del formulario
	    	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    	byte[] array = messageDigest.digest(currentPassword.getBytes("UTF-8"));
	    	StringBuilder passwordStringMD5 = new StringBuilder();
	    	for(byte b : array) {
	    		passwordStringMD5.append(String.format("%02x", b));
	    	}
	    	//Sacamos hash del archivo
	    	String path = System.getProperty("user.dir") + "/src/controller/administracion/pass.txt";
	    	archivo = new File(path);
	        fr = new FileReader(archivo);
	        br = new BufferedReader(fr);
	        String truePassMD5 = br.readLine();
	    	//Comparamos
	    	if (passwordStringMD5.toString().equals(truePassMD5)) validPass = true;
	    } catch(Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (null != fr) fr.close();
	        } catch (Exception e2) {
	        	e2.printStackTrace();
	        }
	    }
		return validPass;
	}
	
	/**
	 * Convertimos y guardamos nueva pass
	 * @param passwordString
	 * @return
	 */
	private void updatePassword(String newPassword) {
		FileWriter fichero = null;
        PrintWriter pw = null;
		try {
			//Generamos MD5
	    	MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    	byte[] array = messageDigest.digest(newPassword.getBytes("UTF-8"));
	    	StringBuilder newPasswordMD5 = new StringBuilder();
	    	for(byte b : array) {
	    		newPasswordMD5.append(String.format("%02x", b));
	    	}
	    	//Guardamos en archivo
	    	String path = System.getProperty("user.dir") + "/src/controller/administracion/pass.txt";
	    	fichero = new FileWriter(path);
	    	pw = new PrintWriter(fichero);
	    	pw.print(newPasswordMD5);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    } finally {
	    	try {
	    		if (null != fichero) fichero.close();
	    	} catch (Exception e2) {
	    		e2.printStackTrace();
	    	}
	    }
	}
	
	public void destruirVentana() {
		view.dispose();
	}
	
}