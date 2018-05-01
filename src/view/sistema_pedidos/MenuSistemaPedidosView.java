package view.sistema_pedidos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class MenuSistemaPedidosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JButton btnNuevoPedido;
	private JButton btnAddComanda;
	private JButton btnFinalizarPedido;

	/**
	 * Create the frame.
	 */
	public MenuSistemaPedidosView() {
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
		
		//btnNuevoPedido
		btnNuevoPedido = new JButton("NUEVO pedido");
		btnNuevoPedido.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnNuevoPedido.setBounds(10, 40, 1330, 210);
		contentPane.add(btnNuevoPedido);
		
		//btnAddComanda
		btnAddComanda = new JButton("A\u00D1ADIR comanda a pedido en curso");
		btnAddComanda.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnAddComanda.setBounds(10, 274, 1330, 210);
		contentPane.add(btnAddComanda);
		
		//btnFinalizarPedido
		btnFinalizarPedido = new JButton("FINALIZAR pedido");
		btnFinalizarPedido.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnFinalizarPedido.setBounds(10, 508, 1330, 210);
		contentPane.add(btnFinalizarPedido);
		
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

	public JButton getBtnNuevoPedido() {
		return btnNuevoPedido;
	}

	public JButton getBtnAddComanda() {
		return btnAddComanda;
	}

	public JButton getBtnFinalizarPedido() {
		return btnFinalizarPedido;
	}

}
