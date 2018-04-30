package controller.administracion.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MenuAdministracionController();
			}
		});
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
}