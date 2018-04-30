package controller.administracion.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.table.DefaultTableModel;
import controller.administracion.MenuAdministracionController;
import model.administracion.gestion.GestionCategoriasModel;
import view.administracion.gestion.GestionCategoriasView;

public class GestionCategoriasController {

	private GestionCategoriasModel model = null;
	private GestionCategoriasView view = null;
	
	public GestionCategoriasController() {
		this.model = new GestionCategoriasModel();
		generarVentana(this.model.getModelCategorias());
	}
	
	public void generarVentana(DefaultTableModel pedidosTableModel) {
		//Creamos la vista(pasamos el modelo de la tabla)
		this.view = new GestionCategoriasView(pedidosTableModel);
		
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
					DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
					int nextId = (int) tableModel.getValueAt(tableModel.getRowCount() - 1, 0) + 1;
					String name = view.getTextFieldNewNombre().getText();
					tableModel.addRow(new Object[]{nextId, name});//Insertamos en la tabla de la vista
					model.insertNewCategoria(nextId, name);//Insertamos en BBDD
					view.getTextFieldNewNombre().setText("");
					view.getLblNewVacio().setVisible(false);
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
					view.getLblEditSeleccion().setVisible(false);
				}
				if (selectedRow && notEmptyTextField) {
					view.getTable().getModel().setValueAt(view.getTextFieldEditNombre().getText(), view.getTable().getSelectedRow(), 1);//Actualizamos de la tabla de la vista
					model.updateCategoria((int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 0), view.getTextFieldEditNombre().getText());//Actualizamos de la BBDD
				}
			}
		});
		view.getBtnEliminar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(view.getTable().getSelectedRow() != -1) {
					int id = (int) view.getTable().getModel().getValueAt(view.getTable().getSelectedRow(), 0);
					((DefaultTableModel)view.getTable().getModel()).removeRow(view.getTable().getSelectedRow());//Borramos de la tabla de la vista
					model.deleteCategoria(id);//Borramos de BBDD
					view.getLblEditSeleccion().setVisible(false);
				} else {
					view.getLblEditSeleccion().setVisible(true);
				}
			}
		});
		view.getTable().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	view.getTextFieldEditNombre().setText((String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 1));
		    }
		});
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
}