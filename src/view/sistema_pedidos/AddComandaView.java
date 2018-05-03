package view.sistema_pedidos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class AddComandaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCategorias;
	private JLabel lblProductos;
	private JLabel lblNoHayCategorias;
	private JLabel lblNoHayProductos;
	private JLabel lblNoSeleccion;
	private JLabel lblNoGuardar;
	private JButton btnAtras;
	private JButton btnEliminarSeleccion;
	private JButton btnGuardar;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel categoriaPanel;
	private JPanel productoPanel;
	private JButton[] categoriasButtons;
	private JButton[] productosButtons;
	private JLabel lblTotal;

	/**
	 * Create the frame.
	 */
	public AddComandaView(DefaultTableModel tableModel, String[] categoriasButtonNames) {
		//JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		
		//contentPane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//labelsTitulos
		lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(10, 43, 150, 32);
		contentPane.add(lblCategorias);
		lblCategorias.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblProductos = new JLabel("Productos");
		lblProductos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProductos.setBounds(10, 297, 150, 32);
		contentPane.add(lblProductos);
		
		//lblNoHayProductos
		lblNoHayProductos = new JLabel("No hay productos registrados para esta categoria");
		lblNoHayProductos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoHayProductos.setForeground(Color.RED);
		lblNoHayProductos.setBounds(170, 309, 748, 14);
		lblNoHayProductos.setVisible(false);
		contentPane.add(lblNoHayProductos);
		
		//lblNoEliminar
		lblNoSeleccion = new JLabel("No hay ningun producto selecionado en la tabla");
		lblNoSeleccion.setForeground(Color.RED);
		lblNoSeleccion.setBounds(109, 18, 449, 14);
		lblNoSeleccion.setVisible(false);
		contentPane.add(lblNoSeleccion);
		
		//lblNoGuardar
		lblNoGuardar = new JLabel("No se puede guardar porque no hay ningun producto a\u00F1adido");
		lblNoGuardar.setForeground(Color.RED);
		lblNoGuardar.setBounds(568, 18, 350, 14);
		lblNoGuardar.setVisible(false);
		contentPane.add(lblNoGuardar);
		
		//btnAtras
		btnAtras = new JButton("<< Atras");
		btnAtras.setBounds(10, 11, 89, 23);
		contentPane.add(btnAtras);
		
		//btnEliminarSeleccion
		btnEliminarSeleccion = new JButton("ELIMINAR seleccion");
		btnEliminarSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminarSeleccion.setBounds(940, 686, 400, 32);
		contentPane.add(btnEliminarSeleccion);
		
		//btnGuardar
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnGuardar.setBounds(940, 526, 400, 149);
		contentPane.add(btnGuardar);
		
		//scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(940, 17, 400, 437);
		contentPane.add(scrollPane);
		
		//table
		table = new JTable();
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		//categoriaPanel
		categoriaPanel = new JPanel();
		categoriaPanel.setBounds(10, 86, 920, 200);
		contentPane.add(categoriaPanel);
		categoriaPanel.setLayout(new GridLayout(2, 4, 0, 0));
		
		//productoPanel
		productoPanel = new JPanel();
		productoPanel.setBounds(10, 340, 920, 378);
		contentPane.add(productoPanel);
		productoPanel.setLayout(new GridLayout(2, 4, 0, 0));
		
		//lblTotal
		lblTotal = new JLabel("TOTAL: XX");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTotal.setBounds(940, 465, 400, 50);
		contentPane.add(lblTotal);

		if(categoriasButtonNames.length == 0) {
			//lblNoHayCategorias
			lblNoHayCategorias = new JLabel("No hay categorias registradas");
			lblNoHayCategorias.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNoHayCategorias.setForeground(Color.RED);
			lblNoHayCategorias.setBounds(170, 55, 748, 14);
			contentPane.add(lblNoHayCategorias);
		} else {
			//categoriasButtons
			this.categoriasButtons = new JButton[categoriasButtonNames.length];
			for(int i = 0; i < this.categoriasButtons.length; i++) {
				JButton btnCategoria = new JButton(categoriasButtonNames[i]);
				btnCategoria.setFont(new Font("Tahoma", Font.PLAIN, 20));
				categoriasButtons[i] = btnCategoria;
				categoriaPanel.add(btnCategoria);
			}
		}
		
		setVisible(true);
	}
	
	/**
	 * Añade los botones de los productos
	 * @param nombreProductos
	 */
	public void addProductosButtons(String[] nombreProductos) {
		if(nombreProductos.length == 0) {
			lblNoHayProductos.setVisible(true);
			productoPanel.removeAll();
			productoPanel.revalidate();
			productoPanel.repaint();
		} else {
			lblNoHayProductos.setVisible(false);
			productoPanel.removeAll();
			productoPanel.revalidate();
			productoPanel.repaint();
			//productosButtons
			productosButtons = new JButton[nombreProductos.length];
			for(int i = 0; i < productosButtons.length; i++) {
				JButton btnProducto = new JButton(nombreProductos[i]);
				btnProducto.setFont(new Font("Tahoma", Font.PLAIN, 20));
				productosButtons[i] = btnProducto;
				productoPanel.add(btnProducto);
			}
		}	
	}
	
	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JLabel getLblCategorias() {
		return lblCategorias;
	}

	public JLabel getLblProductos() {
		return lblProductos;
	}

	public JLabel getLblNoHayCategorias() {
		return lblNoHayCategorias;
	}

	public JLabel getLblNoHayProductos() {
		return lblNoHayProductos;
	}

	public JLabel getLblNoEliminar() {
		return lblNoSeleccion;
	}

	public JLabel getLblNoGuardar() {
		return lblNoGuardar;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JButton getBtnEliminarSeleccion() {
		return btnEliminarSeleccion;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public JPanel getCategoriaPanel() {
		return categoriaPanel;
	}

	public JPanel getProductoPanel() {
		return productoPanel;
	}

	public JButton[] getCategoriasButtons() {
		return categoriasButtons;
	}

	public JButton[] getProductosButtons() {
		return productosButtons;
	}

	public JLabel getLblTotal() {
		return lblTotal;
	}
	
}
