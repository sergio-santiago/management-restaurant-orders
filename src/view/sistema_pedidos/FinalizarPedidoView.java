package view.sistema_pedidos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.text.NumberFormat;

public class FinalizarPedidoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JLabel lblAtencion;
	private JLabel lblMesa;
	private JLabel mesa;
	private JLabel lblPrecio;
	private JLabel precio;
	private JScrollPane scrollPane;
	private JTable tableDetallesPedido;
	private JButton btnFinalizarPedido;

	/**
	 * Create the frame.
	 */
	public FinalizarPedidoView(boolean lblAtencionVisible, String mesaP, double precioP, TableModel tableDetallesPedidoModelP) {
		//JFrame
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
		
		//lblAtencion
		lblAtencion = new JLabel("ATENCION: el total del pedido no coincide con la suma de los subtotales porque el precio de algun producto ha sido modificado tras haberlo a\u00F1adido al pedido");
		lblAtencion.setForeground(Color.RED);
		lblAtencion.setBounds(109, 15, 1231, 14);
		lblAtencion.setVisible(lblAtencionVisible);
		contentPane.add(lblAtencion);
				
		//mesa
		lblMesa = new JLabel("Mesa:");
		lblMesa.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMesa.setBounds(1040, 48, 300, 50);
		contentPane.add(lblMesa);
		
		mesa = new JLabel(mesaP);
		mesa.setFont(new Font("Tahoma", Font.PLAIN, 50));
		mesa.setBounds(1040, 109, 300, 100);
		contentPane.add(mesa);
		
		//precio
		lblPrecio = new JLabel("Precio TOTAL:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPrecio.setBounds(1040, 229, 300, 50);
		contentPane.add(lblPrecio);
		
		precio = new JLabel(NumberFormat.getCurrencyInstance().format(precioP));
		precio.setFont(new Font("Tahoma", Font.PLAIN, 60));
		precio.setBounds(1040, 290, 300, 100);
		contentPane.add(precio);
		
		//scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 1020, 673);
		contentPane.add(scrollPane);
		
		//tableDetallsePedido
		tableDetallesPedido = new JTable();
		tableDetallesPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDetallesPedido.setModel(tableDetallesPedidoModelP);
		scrollPane.setViewportView(tableDetallesPedido);
		
		//btnFinalizarPedido
		btnFinalizarPedido = new JButton("FINALIZAR pedido");
		btnFinalizarPedido.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnFinalizarPedido.setBounds(1040, 418, 300, 300);
		contentPane.add(btnFinalizarPedido);

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

	public JLabel getLblAtencion() {
		return lblAtencion;
	}

	public JLabel getLblMesa() {
		return lblMesa;
	}

	public JLabel getMesa() {
		return mesa;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}

	public JLabel getPrecio() {
		return precio;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTableDetallesPedido() {
		return tableDetallesPedido;
	}

	public JButton getBtnFinalizarPedido() {
		return btnFinalizarPedido;
	}
	
}
