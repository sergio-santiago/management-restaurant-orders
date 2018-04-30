package view.administracion.historial_pedidos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.text.NumberFormat;

public class DetallesPedidoView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JLabel lblAtencion;
	private JLabel lblId;
	private JLabel id;
	private JLabel lblEnCurso;
	private JLabel enCurso;
	private JLabel lblFecha;
	private JLabel fecha;
	private JLabel lblMesa;
	private JLabel mesa;
	private JLabel lblPrecio;
	private JLabel precio;
	private JScrollPane scrollPane;
	private JTable tableDetallesPedido;

	/**
	 * Create the frame.
	 */
	public DetallesPedidoView(boolean lblAtencionVisible, int idP, boolean enCursoP, String fechaP, String mesaP, double precioP, TableModel tableDetallesPedidoModelP) {
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
		lblAtencion = new JLabel("ATENCION: el total del pedido no coincide con la suma de los subtotales porque el precio de algun producto ha sido modificado posteriormente de haber realizado el pedido");
		lblAtencion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAtencion.setForeground(Color.RED);
		lblAtencion.setBounds(10, 704, 1330, 14);
		lblAtencion.setVisible(lblAtencionVisible);
		contentPane.add(lblAtencion);
		
		//id
		lblId = new JLabel("ID de pedido:");
		lblId.setBounds(10, 45, 300, 14);
		contentPane.add(lblId);
		
		id = new JLabel(String.valueOf(idP));
		id.setFont(new Font("Tahoma", Font.PLAIN, 30));
		id.setBounds(10, 70, 300, 85);
		contentPane.add(id);
		
		//enCurso
		lblEnCurso = new JLabel("Pedido en curso:");
		lblEnCurso.setBounds(10, 166, 300, 14);
		contentPane.add(lblEnCurso);
		
		String enCursoString = (enCursoP) ? "SI" : "NO";
		enCurso = new JLabel(enCursoString);
		Color enCursoColor = (enCursoP) ? new Color(0, 255, 0) : new Color(255, 0, 0);
		enCurso.setForeground(enCursoColor);
		enCurso.setFont(new Font("Tahoma", Font.PLAIN, 30));
		enCurso.setBounds(10, 191, 300, 85);
		contentPane.add(enCurso);
		
		//fecha
		lblFecha = new JLabel("Fecha de pedido:");
		lblFecha.setBounds(10, 287, 300, 14);
		contentPane.add(lblFecha);
		
		fecha = new JLabel(fechaP);
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 30));
		fecha.setBounds(10, 312, 300, 85);
		contentPane.add(fecha);
		
		//mesa
		lblMesa = new JLabel("Mesa de pedido:");
		lblMesa.setBounds(10, 408, 300, 14);
		contentPane.add(lblMesa);
		
		mesa = new JLabel(mesaP);
		mesa.setFont(new Font("Tahoma", Font.PLAIN, 30));
		mesa.setBounds(10, 433, 300, 85);
		contentPane.add(mesa);
		
		//precio
		lblPrecio = new JLabel("Precio TOTAL:");
		lblPrecio.setBounds(10, 529, 300, 14);
		contentPane.add(lblPrecio);
		
		precio = new JLabel(NumberFormat.getCurrencyInstance().format(precioP));
		precio.setFont(new Font("Tahoma", Font.PLAIN, 50));
		precio.setBounds(10, 554, 300, 97);
		contentPane.add(precio);
		
		//scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 11, 1020, 682);
		contentPane.add(scrollPane);
		
		//tableDetallsePedido
		tableDetallesPedido = new JTable();
		tableDetallesPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDetallesPedido.setModel(tableDetallesPedidoModelP);
		scrollPane.setViewportView(tableDetallesPedido);

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

	public JLabel getLblId() {
		return lblId;
	}

	public JLabel getId() {
		return id;
	}

	public JLabel getLblEnCurso() {
		return lblEnCurso;
	}

	public JLabel getEnCurso() {
		return enCurso;
	}

	public JLabel getLblFecha() {
		return lblFecha;
	}

	public JLabel getFecha() {
		return fecha;
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
	
}
