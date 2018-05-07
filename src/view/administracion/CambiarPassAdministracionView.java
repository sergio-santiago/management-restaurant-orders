package view.administracion;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Color;

public class CambiarPassAdministracionView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPassActual;
	private JPasswordField passActualField;
	private JLabel lblNuevaPass;
	private JPasswordField nuevaPassField;
	private JLabel lblRepetirNuevaPass;
	private JPasswordField repetirNuevaPassField;
	private JLabel lblMensajeError;	
	private JButton btnAceptar;
	

	/**
	 * Create the dialog.
	 */
	public CambiarPassAdministracionView() {
		//jDialog
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 275);

		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lblPassActual
		lblPassActual = new JLabel("Contrase\u00F1a actual:");
		lblPassActual.setBounds(10, 11, 200, 14);
		contentPane.add(lblPassActual);
		
		//passActualField
		passActualField = new JPasswordField();
		passActualField.setBounds(10, 34, 315, 22);
		contentPane.add(passActualField);
		
		//lblNuevaPass
		lblNuevaPass = new JLabel("Nueva contrase\u00F1a:");
		lblNuevaPass.setBounds(10, 67, 200, 14);
		contentPane.add(lblNuevaPass);
		
		//nuevaPassField
		nuevaPassField = new JPasswordField();
		nuevaPassField.setBounds(10, 90, 315, 22);
		contentPane.add(nuevaPassField);
		
		//lblRepetirNuevaPass
		lblRepetirNuevaPass = new JLabel("Repetir nueva contrase\u00F1a:");
		lblRepetirNuevaPass.setBounds(10, 123, 200, 14);
		contentPane.add(lblRepetirNuevaPass);
		
		//repetirNuevaPassField
		repetirNuevaPassField = new JPasswordField();
		repetirNuevaPassField.setBounds(10, 146, 315, 22);
		contentPane.add(repetirNuevaPassField);
		
		//lblMensajeError
		lblMensajeError = new JLabel("Error");
		lblMensajeError.setForeground(Color.RED);
		lblMensajeError.setBounds(10, 179, 315, 14);
		lblMensajeError.setVisible(false);
		contentPane.add(lblMensajeError);
		
		//btnAceptar
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 204, 315, 23);
		contentPane.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);

		//Lo hacemos visible en el controlador(para hacerlo modal)
	}

	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}


	public JLabel getLblPassActual() {
		return lblPassActual;
	}


	public JPasswordField getPassActualField() {
		return passActualField;
	}


	public JLabel getLblNuevaPass() {
		return lblNuevaPass;
	}


	public JPasswordField getNuevaPassField() {
		return nuevaPassField;
	}


	public JLabel getLblRepetirNuevaPass() {
		return lblRepetirNuevaPass;
	}


	public JPasswordField getRepetirNuevaPassField() {
		return repetirNuevaPassField;
	}


	public JLabel getLblMensajeError() {
		return lblMensajeError;
	}


	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
}