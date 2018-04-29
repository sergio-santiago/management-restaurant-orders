package view.administracion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class MenuAdministracionView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JButton btnHistorial;
	private JButton btnGestionCategorias;
	private JButton btnGestionProductos;
	private JButton btnGestionMesas;

	/**
	 * Create the frame.
	 */
	public MenuAdministracionView() {
		//jFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		
		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btnSalir
		btnSalir = new JButton("<< Salir");
		btnSalir.setBounds(10, 11, 89, 23);
		contentPane.add(btnSalir);
		
		//btnHistorial
		btnHistorial = new JButton("HISTORIAL de PEDIDOS");
		btnHistorial.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHistorial.setBounds(10, 45, 1330, 338);
		contentPane.add(btnHistorial);
		
		//btnGestionCategorias
		btnGestionCategorias = new JButton("Gestion de CATEGORIAS");
		btnGestionCategorias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGestionCategorias.setBounds(10, 394, 431, 324);
		contentPane.add(btnGestionCategorias);
		
		//btnGestionProductos
		btnGestionProductos = new JButton("Gestion de PRODUCTOS");
		btnGestionProductos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGestionProductos.setBounds(451, 394, 448, 324);
		contentPane.add(btnGestionProductos);
		
		//btnGestionMesas
		btnGestionMesas = new JButton("Gestion de MESAS");
		btnGestionMesas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGestionMesas.setBounds(909, 394, 431, 324);
		contentPane.add(btnGestionMesas);
		
		setVisible(true);
	}
	
	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public JButton getBtnHistorial() {
		return btnHistorial;
	}

	public JButton getBtnGestionCategorias() {
		return btnGestionCategorias;
	}

	public JButton getBtnGestionProductos() {
		return btnGestionProductos;
	}

	public JButton getBtnGestionMesas() {
		return btnGestionMesas;
	}

}
