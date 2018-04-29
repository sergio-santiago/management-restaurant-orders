package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class InicioView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAdministracionDelSistema;
	private JButton btnInicioDelSistema;

	/**
	 * Create the frame.
	 */
	public InicioView() {
		//jFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		setVisible(true);
		
		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btnAdministracionDelSistema
		btnAdministracionDelSistema = new JButton("ADMINISTRACION del sistema");
		btnAdministracionDelSistema.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdministracionDelSistema.setBounds(10, 668, 1330, 50);
		contentPane.add(btnAdministracionDelSistema);
		
		//btnInicioDelSistema
		btnInicioDelSistema = new JButton("INICIO del sistema de pedidos");
		btnInicioDelSistema.setFont(new Font("Tahoma", Font.PLAIN, 50));
		btnInicioDelSistema.setBounds(10, 11, 1330, 646);
		contentPane.add(btnInicioDelSistema);
	}

	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnAdministracionDelSistema() {
		return btnAdministracionDelSistema;
	}

	public JButton getBtnInicioDelSistema() {
		return btnInicioDelSistema;
	}
	
}
