package controller.sistema_pedidos.nuevo_pedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import model.sistema_pedidos.AddProductosModel;
import view.sistema_pedidos.AddProductosView;

public class NuevoPedidoController {
	
	private AddProductosModel model = null;
	private AddProductosView view = null;
	private double precioTotal = 0;
	private int idMesa;
	
	public NuevoPedidoController(int idMesa) {
		this.idMesa = idMesa;
		model = new AddProductosModel();
		generarVentana(model.getEmptyTableModel(), model.getNombreCategorias());
	}
	
	public void generarVentana(DefaultTableModel emptyTableModel, String[] categoriasButtonNames) {
		view = new AddProductosView(emptyTableModel, categoriasButtonNames);
		recalcularTotal();
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
					view.getLblNoSeleccion().setVisible(false);
					((DefaultTableModel)view.getTable().getModel()).removeRow(view.getTable().getSelectedRow());
					recalcularTotal();
				} else {
					view.getLblNoSeleccion().setVisible(true);
				}
			}
		});
		view.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTable().getRowCount() != 0) {
					//Crear pedido
					int idPedido = model.getLastIdFrom("pedido") + 1;
					String fecha = getFecha();
					model.insertNewPedido(idPedido, precioTotal, fecha, idMesa);
					//Crear comanda
					int idComanda = model.getLastIdFrom("comanda") + 1;
					model.insertNewComanda(idComanda, idPedido);
					//Recorremos el modelo para ir metiendo en BBDD relaciones
					for(int i = 0; i < view.getTable().getRowCount(); i++) {
						int idProducto = model.getIdProductoFromNombre((String) view.getTable().getModel().getValueAt(i, 0));
						int cantidad = (int) view.getTable().getModel().getValueAt(i, 2);
						model.inserNewRelacionComandaProducto(idComanda, idProducto, cantidad);
					}
					destruirVentana();
					new MenuSistemaPedidosController();
				} else {
					view.getLblNoGuardar().setVisible(true);
				}
			}
		});
		view.getBtnMenos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTable().getSelectedRow() != -1) {
					if((int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 2) == 1) {
						((DefaultTableModel)view.getTable().getModel()).removeRow(view.getTable().getSelectedRow());
					} else {
						view.getTable().getModel().setValueAt((int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 2) - 1, view.getTable().getSelectedRow(), 2);//Cambiamos las unidades
						//Recalculamos subtotal
						String precioFormateado = (String) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 1);
						int finalNumero = precioFormateado.length() - 2;
						String precioSinDivisa = precioFormateado.substring(0, finalNumero);
						Double precio = Double.valueOf(precioSinDivisa.replace(",", "*").replace(".", "").replace("*", "."));
						int unidades = (int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 2);
						double nuevoSubtotal = precio * unidades;
						String nuevoSubtotalFormateado = NumberFormat.getCurrencyInstance().format(nuevoSubtotal);
						view.getTable().getModel().setValueAt(nuevoSubtotalFormateado, view.getTable().getSelectedRow(), 3);
					}
					view.getLblNoSeleccion().setVisible(false);
					recalcularTotal();
				} else {
					view.getLblNoSeleccion().setVisible(true);
				}
			}
		});
		view.getBtnMas().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTable().getSelectedRow() != -1) {
					view.getTable().getModel().setValueAt((int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 2) + 1, view.getTable().getSelectedRow(), 2);//Cambiamos las unidades
					//Recalculamos subtotal
					String precioFormateado = (String) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 1);
					int finalNumero = precioFormateado.length() - 2;
					String precioSinDivisa = precioFormateado.substring(0, finalNumero);
					Double precio = Double.valueOf(precioSinDivisa.replace(",", "*").replace(".", "").replace("*", "."));
					int unidades = (int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 2);
					double nuevoSubtotal = precio * unidades;
					String nuevoSubtotalFormateado = NumberFormat.getCurrencyInstance().format(nuevoSubtotal);
					view.getTable().getModel().setValueAt(nuevoSubtotalFormateado, view.getTable().getSelectedRow(), 3);
					
					view.getLblNoSeleccion().setVisible(false);
					recalcularTotal();
				} else {
					view.getLblNoSeleccion().setVisible(true);
				}
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
						view.getLblNoGuardar().setVisible(false);
						addProductToTableModel(productoButton.getText());
						recalcularTotal();
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
	
	/**
	 * Reclacula el atributo que establece la suma de los subtotales y establece el label en pantalla que lo muestra
	 */
	public void recalcularTotal() {
		double sumaSubtotales = 0;
		//Recorremos cada registro de la tabla, lo convertimos a double y lo sumamos
		for(int i = 0; i < view.getTable().getRowCount(); i++) {
			String subtotalFormateado = (String) view.getTable().getModel().getValueAt(i, 3);
			int finalNumero = subtotalFormateado.length() - 2;
			String subtotalSinDivisa = subtotalFormateado.substring(0, finalNumero);
			Double subtotal = Double.valueOf(subtotalSinDivisa.replace(",", "*").replace(".", "").replace("*", "."));
			sumaSubtotales += subtotal;
		}
		this.precioTotal = sumaSubtotales;
		String sumaSubtotalesFormateado = NumberFormat.getCurrencyInstance().format(sumaSubtotales);
		view.getLblTotal().setText("TOTAL: " + sumaSubtotalesFormateado);
	}
	
	@SuppressWarnings("deprecation")
	public String getFecha() {
		StringBuilder fechaString = new StringBuilder();
		java.util.Date fecha = new Date();
		fechaString.append(fecha.getMonth() + 1).append("-");
		fechaString.append(fecha.getDate()).append("-");
		fechaString.append(fecha.getYear() + 1900);
		return fechaString.toString();
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
}