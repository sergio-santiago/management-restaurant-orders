package controller.sistema_pedidos.nuevo_pedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import model.sistema_pedidos.AddProductosModel;
import view.sistema_pedidos.AddProductosView;

public class NuevoPedidoController {
	
	private AddProductosModel model = null;
	private AddProductosView view = null;
	
	public NuevoPedidoController(int idMesa) {
		model = new AddProductosModel();
		generarVentana(model.getEmptyTableModel(), model.getNombreCategorias());
	}
	
	public void generarVentana(DefaultTableModel emptyTableModel, String[] categoriasButtonNames) {
		view = new AddProductosView(emptyTableModel, categoriasButtonNames);
		//Eventos de la vista
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MesasNuevoPedidoController();
			}
		});
		view.getBtnEliminarSeleccion().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTable().getSelectedRow() != -1) {
					view.getLblNoEliminar().setVisible(false);
					((DefaultTableModel)view.getTable().getModel()).removeRow(view.getTable().getSelectedRow());
				} else {
					view.getLblNoEliminar().setVisible(true);
				}
			}
		});
		view.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Crear pedido
				//Crear comanda
				//TENEMOS QUE SUMAR PARA METER EN PRODUCT, se puede aprovechar para poner un total
				//Recorrer el modelo
				//Sacar del modelo el nombre de producto(debemos pasarlo a id a traves de una consulta), la cantidad
				//Meter en BBDD
			}
		});
		//Listener de los botones de categorias
		if(categoriasButtonNames.length != 0) {
			for(JButton categoriaButton : view.getCategoriasButtons()) {
				categoriaButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//Sacamos de BBDD el nombre de los productos de la categoria
						String[] nombreProductos = model.getNombreProductosFromCategoria(categoriaButton.getText());
						//Añadimos a la vista los botones con esos productos(borramos si habia otros)
						view.addProductosButtons(nombreProductos);
						addActionListenerToNewButtons();
					}
				});
			}
		}
	}
	
	/**
	 * Listener de los botones de productos (se debe ejecutar cada vez que se crean)
	 */
	public void addActionListenerToNewButtons() {
		if(view.getProductosButtons() != null) {
			for(JButton productoButton : view.getProductosButtons()) {
				productoButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						addProductToTableModel(productoButton.getText());
					}
				});
			}
		}
	}
	
	/**
	 * Añade al modelo de la tabla el producto pasado por parametro, si ya existe añade una unidad mas
	 * @param nombreProducto
	 */
	public void addProductToTableModel(String nombreProducto) {
		double precio = model.getPrecioProductoFromNombre(nombreProducto);//Sacamos el precio de 1 unidad
		int indexProductoEnModel = getIndexOfProductoInTableModel(nombreProducto);//Sacamos la fila en la que esta(si esta)
		if(indexProductoEnModel == -1) {
			//Si no esta en la tabla lo añadimos con 1 unidad
			DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
			model.addRow(new Object[]{nombreProducto, NumberFormat.getCurrencyInstance().format(precio), 1, NumberFormat.getCurrencyInstance().format(precio)});
		} else {
			//Si ya esta en la tabla añadimos 1 unidad y recalculamos el subtotal
			int nuevaCantidad = (int) view.getTable().getModel().getValueAt(indexProductoEnModel, 2) + 1;
			double nuevoSubtotal = precio * nuevaCantidad;
			//Cambiamos los datos
			view.getTable().getModel().setValueAt(nuevaCantidad, indexProductoEnModel, 2);
			view.getTable().getModel().setValueAt(NumberFormat.getCurrencyInstance().format(nuevoSubtotal), indexProductoEnModel, 3);
		}
	}
	
	/**
	 * Saca el index de la fila en la que se encuentra el producto pasado
	 * Si no esta devuelve -1
	 * @param nombreProducto
	 * @return
	 */
	public int getIndexOfProductoInTableModel(String nombreProducto) {
		int index = -1;
		for(int i = 0; i < view.getTable().getModel().getRowCount(); i++) {
			String nombreProductoEnModelo = (String) view.getTable().getModel().getValueAt(i, 0);
			if(nombreProductoEnModelo.equals(nombreProducto)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
}