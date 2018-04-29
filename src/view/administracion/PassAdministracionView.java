package view.administracion;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class PassAdministracionView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPass;
	private JLabel lblPassIncorrecta;	
	private JPasswordField passwordField;
	private JButton btnEntrar;

	/**
	 * Create the dialog.
	 */
	public PassAdministracionView() {
		//jDialog
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 115);

		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lblPass
		lblPass = new JLabel("Introduzca la contrase\u00F1a:");
		lblPass.setBounds(10, 11, 200, 14);
		contentPane.add(lblPass);
		
		//lblPassIncorrecta
		lblPassIncorrecta = new JLabel("Contrase\u00F1a incorrecta");
		lblPassIncorrecta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassIncorrecta.setForeground(Color.RED);
		lblPassIncorrecta.setBounds(224, 11, 200, 14);
		lblPassIncorrecta.setVisible(false);
		contentPane.add(lblPassIncorrecta);
		
		//passwordField
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 34, 315, 22);
		contentPane.add(passwordField);
		
		//btnEntrar
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(335, 33, 89, 23);
		contentPane.add(btnEntrar);
		getRootPane().setDefaultButton(btnEntrar);

		//Lo hacemos visible en el controlador(para hacerlo modal)
	}

	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JLabel getLblPass() {
		return lblPass;
	}

	public JLabel getLblPassIncorrecta() {
		return lblPassIncorrecta;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getBtnEntrar() {
		return btnEntrar;
	}
	
}
