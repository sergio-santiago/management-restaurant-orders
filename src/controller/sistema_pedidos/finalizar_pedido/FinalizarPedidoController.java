package controller.sistema_pedidos.finalizar_pedido;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import model.sistema_pedidos.FinalizarPedidoModel;
import view.sistema_pedidos.FinalizarPedidoView;

public class FinalizarPedidoController {
	private FinalizarPedidoModel model = null;
	private FinalizarPedidoView view = null;
	private double pagado = 0;
	private double precio;
	
	FinalizarPedidoController(int idPedido) {
		model = new FinalizarPedidoModel();
		DefaultTableModel detallesPedido = model.getDetallesPedidoModel(idPedido);
		Object[] mesaAndPrecio = model.getMesaAndPrecioPedido(idPedido);	
		boolean lblAtencionVisible = ((double) mesaAndPrecio[1] == calcularSumaSubtotales(detallesPedido)) ? false : true;//Si la suma de subtotales no coincide con precio mostramos mensaje
		generarVentana(idPedido, lblAtencionVisible, (String) mesaAndPrecio[0], (double) mesaAndPrecio[1], detallesPedido);
	}
	
	public void generarVentana(int idPedido, boolean lblAtencionVisible, String mesa, double precio, TableModel tableDetallesPedidoModel) {
		//Creamos la vista
		view = new FinalizarPedidoView(lblAtencionVisible, mesa, precio, tableDetallesPedidoModel);
		this.precio = precio;
		//Eventos de la vista
		view.getBtnAtras().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				destruirVentana();
				new MesasFinalizarPedidoController();
			}
		});
		view.getBtnFinalizarPedido().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pagado >= precio) {
					model.finalizarPedido(idPedido);
					destruirVentana();
					new MenuSistemaPedidosController();
				}
			}
		});
		view.getBtn1c().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 0.01;
				actualizarCambio();
			}
		});
		view.getBtn2c().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 0.02;
				actualizarCambio();
			}
		});
		view.getBtn5c().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 0.05;
				actualizarCambio();
			}
		});
		view.getBtn10c().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 0.10;
				actualizarCambio();
			}
		});
		view.getBtn20c().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 0.20;
				actualizarCambio();
			}
		});
		view.getBtn50c().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 0.50;
				actualizarCambio();
			}
		});
		view.getBtn1e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 1;
				actualizarCambio();
			}
		});
		view.getBtn2e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 2;
				actualizarCambio();
			}
		});
		view.getBtn5e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 5;
				actualizarCambio();
			}
		});
		view.getBtn10e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 10;
				actualizarCambio();
			}
		});
		view.getBtn20e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 20;
				actualizarCambio();
			}
		});
		view.getBtn50e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 50;
				actualizarCambio();
			}
		});
		view.getBtn100e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 100;
				actualizarCambio();
			}
		});
		view.getBtn200e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 200;
				actualizarCambio();
			}
		});
		view.getBtn500e().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado += 500;
				actualizarCambio();
			}
		});
		view.getBtnTarjeta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado = precio;
				actualizarCambio();
			}
		});
		view.getBtnReiniciarPago().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pagado = 0;
				actualizarCambio();
			}
		});
		
	}
	
	public void destruirVentana() {
		this.view.dispose();
	}
	
	/**
	 * Refresca algunos campos de texto como pagado, devolucion...etc
	 * Tambien los cambia de color y habilita el finalizar
	 */
	private void actualizarCambio() {
		view.getPagado().setText(NumberFormat.getCurrencyInstance().format(this.pagado));
		if(this.pagado >= this.precio) {
			view.getBtnFinalizarPedido().setEnabled(true);
			view.getPagado().setForeground(Color.BLACK);
			view.getDevolucion().setForeground(new Color(0, 204, 0));
			view.getDevolucion().setText(NumberFormat.getCurrencyInstance().format(this.pagado - this.precio));
		} else {
			view.getBtnFinalizarPedido().setEnabled(false);
			view.getPagado().setForeground(Color.RED);
			view.getDevolucion().setForeground(Color.BLACK);
			view.getDevolucion().setText("-");
		}
	}
	
	/**
	 * Recorremos el modelo de la tabla para calcular la suma de los subtotales (estan formateados asi que hay que convertirlos manualmente)
	 * @param detallesPedido
	 * @return
	 */
	private double calcularSumaSubtotales(DefaultTableModel detallesPedido) {
		double sumaSubtotales = 0;
		for(int i = 0; i < detallesPedido.getRowCount(); i++) {
			String subtotalFormateado = (String) detallesPedido.getValueAt(i, 3);
			int finalNumero = subtotalFormateado.length() - 2;
			String subtotalSinDivisa = subtotalFormateado.substring(0, finalNumero);
			Double subtotal = Double.valueOf(subtotalSinDivisa.replace(",", "*").replace(".", "").replace("*", "."));
			sumaSubtotales += subtotal;
		}
		return sumaSubtotales;
	}

}
