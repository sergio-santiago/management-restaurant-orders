package view.administracion.gestion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class GestionProductosView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNewNombre;
	private JTextField textFieldEditNombre;
	private JSpinner spinnerPrecioNew;
	private JSpinner spinnerPrecioEdit;
	private JButton btnAtras;
	private JLabel lblNew;
	private JLabel lblNewNombre;
	private JLabel lblNewPrecio;
	private JLabel lblNewCategoria;
	private JButton btnCrear;
	private JLabel lblNewVacio;
	private JLabel lblEdit;
	private JLabel lblEditNombre;
	private JLabel lblEditPrecio;
	private JLabel lblEditCategoria;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JLabel lblEditVacio;
	private JLabel lblEditSeleccion;
	private JScrollPane scrollPane;
	private JTable table;
	private JComboBox<String> comboBoxCategoriaNew;
	private JComboBox<String> comboBoxCategoriaEdit;
	
	/**
	 * Create the frame.
	 * @param tableModel 
	 */
	public GestionProductosView(TableModel tableModel, DefaultComboBoxModel<String> categoriaComboBoxModel) {
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
		
		//newLabels
		lblNew = new JLabel("NUEVO PRODUCTO");
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNew.setBounds(10, 45, 325, 23);
		contentPane.add(lblNew);
		
		lblNewNombre = new JLabel("Nombre:");
		lblNewNombre.setBounds(10, 79, 200, 23);
		contentPane.add(lblNewNombre);
		
		lblNewPrecio = new JLabel("Precio:");
		lblNewPrecio.setBounds(220, 79, 115, 23);
		contentPane.add(lblNewPrecio);
		
		lblNewCategoria = new JLabel("Categoria:");
		lblNewCategoria.setBounds(10, 147, 325, 23);
		contentPane.add(lblNewCategoria);
		
		//textFieldNewNombre
		textFieldNewNombre = new JTextField();
		textFieldNewNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNewNombre.setBounds(10, 113, 200, 23);
		contentPane.add(textFieldNewNombre);
		textFieldNewNombre.setColumns(10);
		
		//spinnerPrecioNew
		spinnerPrecioNew = new JSpinner();
		spinnerPrecioNew.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.5)));
		spinnerPrecioNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerPrecioNew.setBounds(220, 113, 115, 23);
		contentPane.add(spinnerPrecioNew);
		
		//btnCrear
		btnCrear = new JButton("CREAR producto");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrear.setBounds(10, 215, 325, 50);
		contentPane.add(btnCrear);
		
		//lblNewVacio
		lblNewVacio = new JLabel("No puede haber campos vacios");
		lblNewVacio.setForeground(Color.RED);
		lblNewVacio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewVacio.setBounds(10, 276, 325, 23);
		lblNewVacio.setVisible(false);
		contentPane.add(lblNewVacio);
		
		//editLabels
		lblEdit = new JLabel("EDITAR PRODUCTO");
		lblEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdit.setBounds(10, 335, 325, 23);
		contentPane.add(lblEdit);
		
		lblEditNombre = new JLabel("Nombre:");
		lblEditNombre.setBounds(10, 369, 200, 23);
		contentPane.add(lblEditNombre);
		
		lblEditPrecio = new JLabel("Precio:");
		lblEditPrecio.setBounds(220, 369, 115, 23);
		contentPane.add(lblEditPrecio);
		
		lblEditCategoria = new JLabel("Categoria:");
		lblEditCategoria.setBounds(10, 437, 325, 23);
		contentPane.add(lblEditCategoria);
		
		//textFieldEditNombre
		textFieldEditNombre = new JTextField();
		textFieldEditNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEditNombre.setColumns(10);
		textFieldEditNombre.setBounds(10, 403, 200, 23);
		contentPane.add(textFieldEditNombre);
		
		//spinnerPrecioEdit
		spinnerPrecioEdit = new JSpinner();
		spinnerPrecioEdit.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(0.5)));
		spinnerPrecioEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spinnerPrecioEdit.setBounds(220, 403, 115, 23);
		contentPane.add(spinnerPrecioEdit);
		
		//btnGuardar
		btnGuardar = new JButton("GUARDAR cambios");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(10, 505, 325, 50);
		contentPane.add(btnGuardar);
		
		//btnEliminar
		btnEliminar = new JButton("ELIMINAR seleccion");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(10, 566, 325, 50);
		contentPane.add(btnEliminar);
		
		//lblEditVacio
		lblEditVacio = new JLabel("No puede haber campos vacios");
		lblEditVacio.setForeground(Color.RED);
		lblEditVacio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditVacio.setBounds(10, 627, 325, 23);
		lblEditVacio.setVisible(false);
		contentPane.add(lblEditVacio);
		
		//lblEditSeleccion
		lblEditSeleccion = new JLabel("No hay ningun producto selecionado en la tabla");
		lblEditSeleccion.setForeground(Color.RED);
		lblEditSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditSeleccion.setBounds(10, 661, 325, 23);
		lblEditSeleccion.setVisible(false);
		contentPane.add(lblEditSeleccion);
		
		//scrollPane
		scrollPane = new JScrollPane();
		scrollPane.setBounds(345, 11, 995, 707);
		contentPane.add(scrollPane);
		
		//table
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		//comboBoxCategoriaNew
		comboBoxCategoriaNew = new JComboBox<String>();
		comboBoxCategoriaNew.setModel(categoriaComboBoxModel);		
		comboBoxCategoriaNew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCategoriaNew.setBounds(10, 181, 325, 23);
		contentPane.add(comboBoxCategoriaNew);
		
		//comboBoxCategoriaEdit
		comboBoxCategoriaEdit = new JComboBox<String>();
		comboBoxCategoriaEdit.setModel(categoriaComboBoxModel);
		comboBoxCategoriaEdit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCategoriaEdit.setBounds(10, 471, 325, 23);
		contentPane.add(comboBoxCategoriaEdit);
		
		setVisible(true);
	}
	
	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JTextField getTextFieldNewNombre() {
		return textFieldNewNombre;
	}

	public JTextField getTextFieldEditNombre() {
		return textFieldEditNombre;
	}

	public JSpinner getSpinnerPrecioNew() {
		return spinnerPrecioNew;
	}

	public JSpinner getSpinnerPrecioEdit() {
		return spinnerPrecioEdit;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JLabel getLblNew() {
		return lblNew;
	}

	public JLabel getLblNewNombre() {
		return lblNewNombre;
	}

	public JLabel getLblNewPrecio() {
		return lblNewPrecio;
	}

	public JLabel getLblNewCategoria() {
		return lblNewCategoria;
	}

	public JButton getBtnCrear() {
		return btnCrear;
	}

	public JLabel getLblNewVacio() {
		return lblNewVacio;
	}

	public JLabel getLblEdit() {
		return lblEdit;
	}

	public JLabel getLblEditNombre() {
		return lblEditNombre;
	}

	public JLabel getLblEditPrecio() {
		return lblEditPrecio;
	}

	public JLabel getLblEditCategoria() {
		return lblEditCategoria;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JLabel getLblEditVacio() {
		return lblEditVacio;
	}

	public JLabel getLblEditSeleccion() {
		return lblEditSeleccion;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTable getTable() {
		return table;
	}

	public JComboBox<String> getComboBoxCategoriaNew() {
		return comboBoxCategoriaNew;
	}

	public JComboBox<String> getComboBoxCategoriaEdit() {
		return comboBoxCategoriaEdit;
	}
	
}
