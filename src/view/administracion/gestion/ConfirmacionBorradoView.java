package view.administracion.gestion;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ConfirmacionBorradoView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPass;
	private JButton btnSi;
	private JButton btnNo;

	/**
	 * Create the dialog.
	 */
	public ConfirmacionBorradoView() {
		//jDialog
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 150);

		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//lblMensaje
		lblPass = new JLabel("Esta seguro que desea eliminar el elemento");
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setBounds(10, 11, 300, 14);
		contentPane.add(lblPass);
		
		//btnSi
		btnSi = new JButton("Si");
		btnSi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSi.setBounds(44, 51, 100, 33);
		contentPane.add(btnSi);
		getRootPane().setDefaultButton(btnSi);
		
		//btnNo
		btnNo = new JButton("No");
		btnNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNo.setBounds(188, 51, 100, 33);
		contentPane.add(btnNo);

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

	public JButton getBtnSi() {
		return btnSi;
	}

	public JButton getBtnNo() {
		return btnNo;
	}

}
