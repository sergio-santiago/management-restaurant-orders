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
import javax.swing.SwingConstants;

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
	private JButton btn1c;
	private JButton btn2c;
	private JButton btn5c;
	private JButton btn10c;
	private JButton btn20c;
	private JButton btn50c;
	private JButton btn1e;
	private JButton btn2e;
	private JButton btn5e;
	private JButton btn10e;
	private JButton btn20e;
	private JButton btn50e;
	private JButton btn100e;
	private JButton btn200e;
	private JButton btn500e;
	private JButton btnTarjeta;
	private JLabel lblPagado;
	private JLabel lblDevolucion;
	private JLabel pagado;
	private JLabel devolucion;
	private JButton btnReiniciarPago;

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
		lblAtencion.setBounds(415, 15, 925, 14);
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
		scrollPane.setBounds(415, 45, 615, 673);
		contentPane.add(scrollPane);
		
		//tableDetallsePedido
		tableDetallesPedido = new JTable();
		tableDetallesPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDetallesPedido.setModel(tableDetallesPedidoModelP);
		scrollPane.setViewportView(tableDetallesPedido);
		
		//btnFinalizarPedido
		btnFinalizarPedido = new JButton("FINALIZAR pedido");
		btnFinalizarPedido.setEnabled(false);
		btnFinalizarPedido.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnFinalizarPedido.setBounds(1040, 418, 300, 300);
		contentPane.add(btnFinalizarPedido);
		
		//botones de pago
		btn1c = new JButton("1 cent");
		btn1c.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn1c.setBounds(10, 45, 125, 38);
		contentPane.add(btn1c);
		
		btn2c = new JButton("2 cent");
		btn2c.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn2c.setBounds(145, 45, 125, 38);
		contentPane.add(btn2c);
		
		btn5c = new JButton("5 cent");
		btn5c.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn5c.setBounds(280, 45, 125, 38);
		contentPane.add(btn5c);
		
		btn10c = new JButton("10 cent");
		btn10c.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn10c.setBounds(10, 94, 125, 38);
		contentPane.add(btn10c);
		
		btn20c = new JButton("20 cent");
		btn20c.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn20c.setBounds(145, 94, 125, 38);
		contentPane.add(btn20c);
		
		btn50c = new JButton("50 cent");
		btn50c.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn50c.setBounds(280, 94, 125, 38);
		contentPane.add(btn50c);
		
		btn1e = new JButton("1 \u20AC");
		btn1e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn1e.setBounds(10, 143, 125, 38);
		contentPane.add(btn1e);
		
		btn2e = new JButton("2 \u20AC");
		btn2e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn2e.setBounds(145, 143, 125, 38);
		contentPane.add(btn2e);
		
		btn5e = new JButton("5 \u20AC");
		btn5e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn5e.setBounds(280, 143, 125, 38);
		contentPane.add(btn5e);
		
		btn10e = new JButton("10 \u20AC");
		btn10e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn10e.setBounds(10, 192, 125, 38);
		contentPane.add(btn10e);
		
		btn20e = new JButton("20 \u20AC");
		btn20e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn20e.setBounds(145, 192, 125, 38);
		contentPane.add(btn20e);
		
		btn50e = new JButton("50 \u20AC");
		btn50e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn50e.setBounds(280, 192, 125, 38);
		contentPane.add(btn50e);
		
		btn100e = new JButton("100 \u20AC");
		btn100e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn100e.setBounds(10, 241, 125, 38);
		contentPane.add(btn100e);
		
		btn200e = new JButton("200 \u20AC");
		btn200e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn200e.setBounds(145, 241, 125, 38);
		contentPane.add(btn200e);
		
		btn500e = new JButton("500 \u20AC");
		btn500e.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn500e.setBounds(280, 241, 125, 38);
		contentPane.add(btn500e);
		
		btnTarjeta = new JButton("Pago con tarjeta");
		btnTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTarjeta.setBounds(10, 290, 395, 38);
		contentPane.add(btnTarjeta);
		
		//pagado
		lblPagado = new JLabel("Pagado:");
		lblPagado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPagado.setBounds(10, 365, 395, 50);
		contentPane.add(lblPagado);

		pagado = new JLabel("0,00 \u20AC");
		pagado.setForeground(Color.RED);
		pagado.setHorizontalAlignment(SwingConstants.CENTER);
		pagado.setFont(new Font("Tahoma", Font.PLAIN, 60));
		pagado.setBounds(10, 426, 395, 100);
		contentPane.add(pagado);
		
		//devolucion
		lblDevolucion = new JLabel("Devolucion");
		lblDevolucion.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDevolucion.setBounds(10, 557, 395, 50);
		contentPane.add(lblDevolucion);
		
		devolucion = new JLabel("-");
		devolucion.setHorizontalAlignment(SwingConstants.CENTER);
		devolucion.setFont(new Font("Tahoma", Font.PLAIN, 60));
		devolucion.setBounds(10, 618, 395, 100);
		contentPane.add(devolucion);
		
		//reiniciar
		btnReiniciarPago = new JButton("Reiniciar pago");
		btnReiniciarPago.setBounds(109, 11, 296, 23);
		contentPane.add(btnReiniciarPago);

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

	public JButton getBtn1c() {
		return btn1c;
	}

	public JButton getBtn2c() {
		return btn2c;
	}

	public JButton getBtn5c() {
		return btn5c;
	}

	public JButton getBtn10c() {
		return btn10c;
	}

	public JButton getBtn20c() {
		return btn20c;
	}

	public JButton getBtn50c() {
		return btn50c;
	}

	public JButton getBtn1e() {
		return btn1e;
	}

	public JButton getBtn2e() {
		return btn2e;
	}

	public JButton getBtn5e() {
		return btn5e;
	}

	public JButton getBtn10e() {
		return btn10e;
	}

	public JButton getBtn20e() {
		return btn20e;
	}

	public JButton getBtn50e() {
		return btn50e;
	}

	public JButton getBtn100e() {
		return btn100e;
	}

	public JButton getBtn200e() {
		return btn200e;
	}

	public JButton getBtn500e() {
		return btn500e;
	}

	public JButton getBtnTarjeta() {
		return btnTarjeta;
	}

	public JLabel getLblPagado() {
		return lblPagado;
	}

	public JLabel getLblDevolucion() {
		return lblDevolucion;
	}

	public JLabel getPagado() {
		return pagado;
	}

	public JLabel getDevolucion() {
		return devolucion;
	}

	public JButton getBtnReiniciarPago() {
		return btnReiniciarPago;
	}
	
}
