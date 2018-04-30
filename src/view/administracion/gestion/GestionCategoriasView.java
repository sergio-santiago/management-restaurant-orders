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

public class GestionCategoriasView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNewNombre;
	private JTextField textFieldEditNombre;
	private JButton btnAtras;
	private JLabel lblNew;
	private JLabel lblNewNombre;
	private JButton btnCrear;
	private JLabel lblNewVacio;
	private JLabel lblEdit;
	private JLabel lblEditNombre;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private JLabel lblEditVacio;
	private JLabel lblEditSeleccion;
	private JScrollPane scrollPane;
	private JTable table;
	
	/**
	 * Create the frame.
	 * @param tableModel 
	 */
	public GestionCategoriasView(TableModel tableModel) {
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
		lblNew = new JLabel("NUEVA CATEGORIA");
		lblNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNew.setBounds(10, 90, 325, 23);
		contentPane.add(lblNew);
		
		lblNewNombre = new JLabel("Nombre:");
		lblNewNombre.setBounds(10, 124, 325, 23);
		contentPane.add(lblNewNombre);
		
		//textFieldNewNombre
		textFieldNewNombre = new JTextField();
		textFieldNewNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldNewNombre.setBounds(10, 158, 325, 23);
		contentPane.add(textFieldNewNombre);
		textFieldNewNombre.setColumns(10);
		
		//btnCrear
		btnCrear = new JButton("CREAR categoria");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrear.setBounds(10, 192, 325, 50);
		contentPane.add(btnCrear);
		
		//lblNewVacio
		lblNewVacio = new JLabel("No puede haber campos vacios");
		lblNewVacio.setForeground(Color.RED);
		lblNewVacio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewVacio.setBounds(10, 253, 325, 23);
		lblNewVacio.setVisible(false);
		contentPane.add(lblNewVacio);
		
		//editLabels
		lblEdit = new JLabel("EDITAR CATEGORIA");
		lblEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdit.setBounds(10, 350, 325, 23);
		contentPane.add(lblEdit);
		
		lblEditNombre = new JLabel("Nombre:");
		lblEditNombre.setBounds(10, 384, 325, 23);
		contentPane.add(lblEditNombre);
		
		//textFieldEditNombre
		textFieldEditNombre = new JTextField();
		textFieldEditNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldEditNombre.setColumns(10);
		textFieldEditNombre.setBounds(10, 418, 325, 23);
		contentPane.add(textFieldEditNombre);
		
		//btnGuardar
		btnGuardar = new JButton("GUARDAR cambios");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(10, 452, 325, 50);
		contentPane.add(btnGuardar);
		
		//btnEliminar
		btnEliminar = new JButton("ELIMINAR seleccion");
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminar.setBounds(10, 513, 325, 50);
		contentPane.add(btnEliminar);
		
		//lblEditVacio
		lblEditVacio = new JLabel("No puede haber campos vacios");
		lblEditVacio.setForeground(Color.RED);
		lblEditVacio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditVacio.setBounds(10, 574, 325, 23);
		lblEditVacio.setVisible(false);
		contentPane.add(lblEditVacio);
		
		//lblEditSeleccion
		lblEditSeleccion = new JLabel("No hay ninguna categoria selecionada en la tabla");
		lblEditSeleccion.setForeground(Color.RED);
		lblEditSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditSeleccion.setBounds(10, 608, 325, 23);
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

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JLabel getLblNew() {
		return lblNew;
	}

	public JLabel getLblNewNombre() {
		return lblNewNombre;
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
	
}
