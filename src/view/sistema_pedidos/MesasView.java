package view.sistema_pedidos;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class MesasView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnAtras;
	private JLabel lblNoMesas;
	private JButton[] mesasButtons;

	/**
	 * Create the frame.
	 */
	public MesasView(String[] mesasButtonNames) {
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
		
		//panelMesas
		JPanel panelMesas = new JPanel();
		panelMesas.setBounds(10, 45, 1330, 673);
		contentPane.add(panelMesas);
		panelMesas.setLayout(new GridLayout(5, 5, 10, 10));
		
		if(mesasButtonNames.length == 0) {
			//lblNoMesas
			lblNoMesas = new JLabel("No hay ninguna mesa en estos momentos");
			lblNoMesas.setHorizontalAlignment(SwingConstants.CENTER);
			lblNoMesas.setFont(new Font("Tahoma", Font.PLAIN, 50));
			lblNoMesas.setForeground(Color.RED);
			panelMesas.add(lblNoMesas);
		} else {
			//bucle creacion de botones de mesas dinamicamente
			this.mesasButtons = new JButton[mesasButtonNames.length];
			for(int i = 0; i < this.mesasButtons.length; i++) {
				JButton btnMesa = new JButton(mesasButtonNames[i]);
				btnMesa.setFont(new Font("Tahoma", Font.PLAIN, 20));
				mesasButtons[i] = btnMesa;
				panelMesas.add(btnMesa);
			}
		}
		
		setVisible(true);
	}
	
	/**
	 * Components Getters
	 */
	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JLabel getLblNoMesas() {
		return lblNoMesas;
	}

	public JButton[] getMesasButtons() {
		return mesasButtons;
	}

}
