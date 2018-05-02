package controller.sistema_pedidos.add_comanda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import model.sistema_pedidos.MesasModel;
import view.sistema_pedidos.MesasView;

public class MesasAddComandaController {
	
	private MesasModel model = null;
	private MesasView view = null;
	
	public MesasAddComandaController() {
		model = new MesasModel();
		generarVentana(model.getNombreMesasOcupadas());
	}
	
	public void generarVentana(String[] mesasButtonNames) {
		//Creamos la vista(pasamos la lista de nombres de las mesas con pedidos en curso)
		view = new MesasView(mesasButtonNames);
		
		//Eventos de la vista
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MenuSistemaPedidosController();
			}
		});
		if(mesasButtonNames.length != 0) {
			for(JButton mesaButton : view.getMesasButtons()) {
				mesaButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int idMesa = model.searchMesaIdByNombre(mesaButton.getText());
						System.out.println("Entramos a ADD COMANDA con el ID de mesa " + idMesa);
					}
				});
			}
		}
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
}
