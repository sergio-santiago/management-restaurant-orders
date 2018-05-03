package controller.administracion.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.text.NumberFormat;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import controller.administracion.MenuAdministracionController;
import model.administracion.gestion.GestionProductosModel;
import view.administracion.gestion.GestionProductosView;

public class GestionProductosController {

	private GestionProductosModel model = null;
	private GestionProductosView view = null;
	
	public GestionProductosController() {
		this.model = new GestionProductosModel();
		generarVentana(this.model.getModelProductos(), this.model.getComboBoxModelCategorias(), this.model.getComboBoxModelCategorias());
	}
	
	public void generarVentana(DefaultTableModel productosTableModel, DefaultComboBoxModel<String> categoriaComboBoxModelNew, DefaultComboBoxModel<String> categoriaComboBoxModelEdit) {
		//Creamos la vista(pasamos el modelo de la tabla)
		this.view = new GestionProductosView(productosTableModel, categoriaComboBoxModelNew, categoriaComboBoxModelEdit);
		
		//Eventos de la vista
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MenuAdministracionController();
			}
		});
		view.getBtnCrear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!view.getTextFieldNewNombre().getText().equals("")) {
					if(!thereIsNameInTable(view.getTextFieldNewNombre().getText())) {
						DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
						int nextId = (int) tableModel.getValueAt(tableModel.getRowCount() - 1, 0) + 1;//calculamos id siguiente
						String name = view.getTextFieldNewNombre().getText();
						double precio = (double) view.getSpinnerPrecioNew().getValue();
						String precioFormateado = NumberFormat.getCurrencyInstance().format(precio);//formateamos el precio para meterlo al modelo de la tabla
						String categoriaName = (String) view.getComboBoxCategoriaNew().getSelectedItem();
						int categoriaId = model.findCategoryIdByName(categoriaName);
						tableModel.addRow(new Object[]{nextId, name, precioFormateado, categoriaName});//Insertamos en la tabla de la vista
						model.insertNewProducto(nextId, name, precio, categoriaId);//Insertamos en BBDD
						//Restablecemos valores
						view.getTextFieldNewNombre().setText("");
						view.getSpinnerPrecioNew().setValue(new Double(0));
						view.getComboBoxCategoriaNew().setSelectedIndex(0);
						view.getLblNewVacio().setVisible(false);
						view.getLblNewUnico().setVisible(false);
						view.getTable().clearSelection();
					} else {
						view.getLblNewUnico().setVisible(true);
					}
				} else {
					view.getLblNewVacio().setVisible(true);
				}
			}
		});
		view.getBtnGuardar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean selectedRow = (view.getTable().getSelectedRow() != -1) ? true : false;
				boolean notEmptyTextField = (!view.getTextFieldEditNombre().getText().equals("") ? true : false);
				if(!selectedRow) {
					view.getLblEditSeleccion().setVisible(true);
				} else {
					view.getLblEditSeleccion().setVisible(false);
				}
				if(!notEmptyTextField) {
					view.getLblEditVacio().setVisible(true);
				} else {
					view.getLblEditVacio().setVisible(false);
				}
				if (selectedRow && notEmptyTextField) {
					if(!thereIsNameInTable(view.getTextFieldEditNombre().getText()) || nameIsSelectedRow(view.getTextFieldEditNombre().getText())) {
						DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
						int id = (int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 0);
						String name = view.getTextFieldEditNombre().getText();
						double precio = (double) view.getSpinnerPrecioEdit().getValue();
						String precioFormateado = NumberFormat.getCurrencyInstance().format(precio);//formateamos el precio para meterlo al modelo de la tabla
						String categoriaName = (String) view.getComboBoxCategoriaEdit().getSelectedItem();
						int categoriaId = model.findCategoryIdByName(categoriaName);
						//Actualizamos la tabla de la vista
						tableModel.setValueAt(name, view.getTable().getSelectedRow(), 1);
						tableModel.setValueAt(precioFormateado, view.getTable().getSelectedRow(), 2);
						tableModel.setValueAt(categoriaName, view.getTable().getSelectedRow(), 3);
						//Actualizamos BBDD
						model.updateProducto(id, name, precio, categoriaId);
						//Restablecemos valores
						view.getTextFieldEditNombre().setText("");
						view.getSpinnerPrecioEdit().setValue(new Double(0));
						view.getComboBoxCategoriaEdit().setSelectedIndex(0);
						view.getLblEditVacio().setVisible(false);
						view.getLblEditSeleccion().setVisible(false);
						view.getLblEditUnico().setVisible(false);
						view.getTable().clearSelection();
					} else {
						view.getLblEditUnico().setVisible(true);
					}
				}
			}
		});
		view.getBtnEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTable().getSelectedRow() != -1) {
					ConfirmacionBorradoController confirmacionBorradoController = new ConfirmacionBorradoController();
					if(confirmacionBorradoController.isStatus()) {
						int id = (int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 0);
						((DefaultTableModel)view.getTable().getModel()).removeRow(view.getTable().getSelectedRow());//Borramos de la tabla de la vista
						model.deleteProducto(id);//Borramos de BBDD
						//Restablecemos valores
						view.getTextFieldEditNombre().setText("");
						view.getSpinnerPrecioEdit().setValue(new Double(0));
						view.getComboBoxCategoriaEdit().setSelectedIndex(0);
						view.getLblEditVacio().setVisible(false);
						view.getLblEditSeleccion().setVisible(false);
						view.getTable().clearSelection();
					}
				} else {
					view.getLblEditSeleccion().setVisible(true);
				}
			}
		});
		view.getTable().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	view.getTextFieldEditNombre().setText((String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 1));
		    	view.getSpinnerPrecioEdit().setValue(precioStringToDouble((String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 2)));
		    	view.getComboBoxCategoriaEdit().setSelectedIndex(searchCategoriaIndexInComboBoxModel((String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 3)));
		    }
		});
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
	/**
	 * Transforma el precio formateado del modelo de la tabla a double
	 * @param precioString
	 * @return
	 */
	private double precioStringToDouble(String precioString) {
		int finalNumero = precioString.length() - 2;
		String precioStringSinDivisa = precioString.substring(0, finalNumero);
		Double precio = Double.valueOf(precioStringSinDivisa.replace(",", "*").replace(".", "").replace("*", "."));
		return precio;
	}
	
	/**
	 * Busca el indice de la lista de categorias que coincida con el String de la tabla
	 * @param categoriaTableString
	 * @return
	 */
	private int searchCategoriaIndexInComboBoxModel(String categoriaTableString) {
		ComboBoxModel<String> model = view.getComboBoxCategoriaEdit().getModel();
		int modelIndex = -1;
		for(int i = 0; i < model.getSize(); i++) {
			String modelString = model.getElementAt(i);
			String modelCategoria = modelString.substring(modelString.indexOf("-") + 1, modelString.length());
			if(modelCategoria.equals(categoriaTableString)) {
				modelIndex = i;
				break;
			}
		}
		return modelIndex;
	}
	
	/**
	 * Buscamos nombre en tabla para evitar errores SQL de campos unicos
	 * @param name
	 * @return
	 */
	private boolean thereIsNameInTable(String name) {
		boolean thereIsNameInTable = false;
		for(int i = 0; i < view.getTable().getModel().getRowCount(); i++) {
			String tableName = (String) view.getTable().getModel().getValueAt(i, 1);
			if(tableName.equalsIgnoreCase(name)) {
				thereIsNameInTable = true;
				break;
			}
		}
		return thereIsNameInTable;
	}
	
	private boolean nameIsSelectedRow(String name) {
		String selectedRowName = (String) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 1);
		return selectedRowName.equalsIgnoreCase(name);
	}
	
}