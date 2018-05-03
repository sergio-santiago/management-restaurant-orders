package view.administracion.historial_pedidos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class HistorialPedidosView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JButton btnDetalles;
	private JLabel lblNoDetalles;
	private JScrollPane scrollPane;
	private JTable tablePedidos;
	
	/**
	 * Create the frame.
	 */
	public HistorialPedidosView(DefaultTableModel pedidosTableModel) {
		//jFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		
		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btnAtras
		btnAtras = new JButton("<< Atras");
		btnAtras.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtras);
		
		//btnDetalles
		btnDetalles = new JButton("Ver DETALLES");
		btnDetalles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDetalles.setBounds(10, 656, 1330, 62);
		contentPane.add(btnDetalles);
		
		//lblNoDetalles
		lblNoDetalles = new JLabel("No se pueden mostrar detalles porque no hay ningun pedido selecionado en la tabla");
		lblNoDetalles.setForeground(Color.RED);
		lblNoDetalles.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoDetalles.setBounds(109, 15, 1231, 14);
		lblNoDetalles.setVisible(false);
		contentPane.add(lblNoDetalles);
		
		//scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1330, 600);
		contentPane.add(scrollPane);
		
		//tablePedidos
		tablePedidos = new JTable();
		tablePedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePedidos.setModel(pedidosTableModel);
		scrollPane.setViewportView(tablePedidos);
		
		setVisible(true);
	}

	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnDetalles() {
		return btnDetalles;
	}

	public JLabel getLblNoDetalles() {
		return lblNoDetalles;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTablePedidos() {
		return tablePedidos;
	}
	
}
