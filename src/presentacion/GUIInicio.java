package presentacion;


import javax.swing.JDialog;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;

public class GUIInicio extends JDialog {

	private static GUIUsuario u;
	private JLabel label;
	private JLabel lblNewLabel;
	private JPanel panel;
	private AccesManagerDBInterface logicaNegocio;
	

	public GUIInicio() {

		
		System.out.println("Iniciada GUI GUIInicio...");
		this.setBounds(100, 100, 450, 300);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				try {
					new GUIUsuario(logicaNegocio);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		panel.setBounds(0, 0, 450, 300);
		getContentPane().add(panel);
		panel.setLayout(null);

		
		JLabel label_1 = new JLabel("Pulsame para iniciar la aplicaci\u00F3n...");
		label_1.setBounds(96, 5, 268, 27);
		label_1.setFont(new Font("Segoe Print", Font.BOLD, 15));
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Creado y dise\u00F1ado por: David Prieto, Erik Fustes y Gonzalo del Palacio");
		label_2.setBounds(34, 219, 388, 20);
		label_2.setFont(new Font("Segoe Print", Font.BOLD, 11));
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Working group by Sinking Soft");
		label_3.setBounds(119, 250, 236, 27);
		label_3.setFont(new Font("Segoe Print", Font.BOLD, 15));
		panel.add(label_3);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(GUIInicio.class.getResource("/imagenes/granjos.png")));
		label.setBounds(106, 51, 282, 157);
		label.setVisible(true);
		panel.add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GUIInicio.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 450, 300);
		lblNewLabel.setVisible(true);
		panel.add(lblNewLabel);
		this.setVisible(true);


	}

	public void setBusinessLogic(AccesManagerDBInterface lookup) {
		logicaNegocio=lookup;
		
	}
	
}
