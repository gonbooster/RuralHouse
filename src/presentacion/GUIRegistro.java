package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;

import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class GUIRegistro extends JDialog {
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JLabel nNombre;
	private JLabel nContraseña;
	private JLabel nRepContraseña;
	private JLabel nEmail;
	private JLabel nCuenta;
	private JPasswordField txtContraseña;
	private JPasswordField txtRepContraseña;
	private JFormattedTextField txtCuenta;
	private JRadioButton rdbtnCliente;
	private JRadioButton rdbtnPropietario;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static AccesManagerDBInterface controlAcceso;
	private JTextPane txtpnerrores;
	private JButton btnRegistrarse;
	private JLabel lblCuentaBancaria;
	private JLabel label_2;

	
	public GUIRegistro(AccesManagerDBInterface logicaNegocio) {
		controlAcceso=logicaNegocio;
		System.out.println("Iniciada GUI GUIRegistro...");
		setModal(true);
		setBounds(100, 100, 457, 509);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(49, 119, 78, 18);
		label.setFont(new Font("Segoe Print", Font.BOLD, 15));
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setBounds(47, 166, 104, 18);
		label_1.setFont(new Font("Segoe Print", Font.BOLD, 15));
		getContentPane().add(label_1);
		
		txtNombre = new JTextField();
		txtNombre.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNombre.setBackground(new Color(255, 255, 153));
		txtNombre.setBounds(217, 119, 129, 20);
		txtNombre.setColumns(10);
		getContentPane().add(txtNombre);
		
		JLabel lblRepetirContrasea = new JLabel("Repetir Contrase\u00F1a:");
		lblRepetirContrasea.setBounds(46, 210, 160, 18);
		lblRepetirContrasea.setFont(new Font("Segoe Print", Font.BOLD, 15));
		getContentPane().add(lblRepetirContrasea);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(46, 250, 104, 18);
		lblEmail.setFont(new Font("Segoe Print", Font.BOLD, 15));
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtEmail.setBackground(new Color(255, 255, 153));
		txtEmail.setBounds(217, 251, 129, 20);
		txtEmail.setColumns(10);
		getContentPane().add(txtEmail);
		
		lblCuentaBancaria = new JLabel("Cuenta bancaria:");
		lblCuentaBancaria.setBounds(49, 287, 160, 18);
		lblCuentaBancaria.setFont(new Font("Segoe Print", Font.BOLD, 15));
		getContentPane().add(lblCuentaBancaria);
		
		nNombre = new JLabel("New label");
		nNombre.setBounds(356, 124, 19, 14);
		nNombre.setIcon(new ImageIcon(GUIRegistro.class.getResource("/imagenes/cross.png")));
		getContentPane().add(nNombre);
		nNombre.setVisible(false);
		
		nContraseña = new JLabel("New label");
		nContraseña.setBounds(356, 170, 19, 14);
		nContraseña.setIcon(new ImageIcon(GUIRegistro.class.getResource("/imagenes/cross.png")));
		getContentPane().add(nContraseña);
		nContraseña.setVisible(false);
		
		nRepContraseña = new JLabel("New label");
		nRepContraseña.setBounds(356, 214, 19, 14);
		nRepContraseña.setIcon(new ImageIcon(GUIRegistro.class.getResource("/imagenes/cross.png")));
		getContentPane().add(nRepContraseña);
		nRepContraseña.setVisible(false);
		
		nEmail = new JLabel("New label");
		nEmail.setBounds(356, 254, 19, 14);
		nEmail.setIcon(new ImageIcon(GUIRegistro.class.getResource("/imagenes/cross.png")));
		getContentPane().add(nEmail);
		nEmail.setVisible(false);
		
		nCuenta = new JLabel("New label");
		nCuenta.setBounds(356, 291, 19, 14);
		nCuenta.setIcon(new ImageIcon(GUIRegistro.class.getResource("/imagenes/cross.png")));
		getContentPane().add(nCuenta);
		nCuenta.setVisible(false);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtContraseña.setBackground(new Color(255, 255, 153));
		txtContraseña.setBounds(216, 168, 130, 20);
		getContentPane().add(txtContraseña);
		
		txtRepContraseña = new JPasswordField();
		txtRepContraseña.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtRepContraseña.setBackground(new Color(255, 255, 153));
		txtRepContraseña.setBounds(216, 211, 130, 20);
		getContentPane().add(txtRepContraseña);

		txtCuenta = new JFormattedTextField();
		txtCuenta.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCuenta.setBackground(new Color(255, 255, 153));
		txtCuenta.setBounds(217, 289, 129, 20);
		getContentPane().add(txtCuenta);
		
		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCuenta.setVisible(false);
				lblCuentaBancaria.setVisible(false);		
			}
		});
		rdbtnCliente.setOpaque(false);
		buttonGroup.add(rdbtnCliente);
		rdbtnCliente.setBounds(76, 69, 109, 23);
		getContentPane().add(rdbtnCliente);
		
		rdbtnPropietario = new JRadioButton("Propietario");
		rdbtnPropietario.setOpaque(false);
		buttonGroup.add(rdbtnPropietario);
		rdbtnPropietario.setBounds(237, 69, 109, 23);
		getContentPane().add(rdbtnPropietario);
		
		JLabel lblRellenaLosSiguientes = new JLabel("Rellene los siguientes campos para registrarte");
		lblRellenaLosSiguientes.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRellenaLosSiguientes.setBounds(44, 11, 354, 33);
		getContentPane().add(lblRellenaLosSiguientes);
		
		txtpnerrores = new JTextPane();
		txtpnerrores.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtpnerrores.setBackground(new Color(255, 255, 153));
		txtpnerrores.setForeground(Color.RED);
		txtpnerrores.setBounds(38, 372, 360, 93);
		txtpnerrores.setEditable(false);
		getContentPane().add(txtpnerrores);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setOpaque(false);
		btnRegistrarse.setBounds(144, 320, 149, 33);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Nombre=txtNombre.getText();
				String Contraseña=String.copyValueOf(txtContraseña.getPassword());
				String RepContraseña=String.copyValueOf(txtRepContraseña.getPassword());
				String Email = txtEmail.getText();
				String CuentaBancaria =  txtCuenta.getText();
				
			
						
				if(rdbtnCliente.isSelected()){
					lblCuentaBancaria.setVisible(false);
					nCuenta.setVisible(false);
					txtCuenta.setVisible(false);
					//Muestra las imagenes X (campos incorrectos).
					if(Nombre.isEmpty()){nNombre.setVisible(true);}else nNombre.setVisible(false);
					if(Contraseña.isEmpty()){nContraseña.setVisible(true);}else nContraseña.setVisible(false);
					if(RepContraseña.isEmpty()){nRepContraseña.setVisible(true);}else nRepContraseña.setVisible(false);
					if(Email.isEmpty()){nEmail.setVisible(true);}else nEmail.setVisible(false);
					
					if(!Nombre.isEmpty() && !Contraseña.isEmpty() && !RepContraseña.isEmpty() && !Email.isEmpty()){
						try {
							if (!controlAcceso.existeUsuario(Email, Contraseña)){
									controlAcceso.registrarse(Nombre,Contraseña,Email);
									dispose();
							}
							else
								txtpnerrores.setText("Ya existe un usuario con ese email!!");
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
						txtpnerrores.setText("Los campos necesarios para registrarse como cliente están vacios!!");	
					
				}else if (rdbtnPropietario.isSelected()){
					lblCuentaBancaria.setVisible(true);
					txtCuenta.setVisible(true);
					//Muestra las imagenes X (campos incorrectos).
					if(Nombre.isEmpty()){nNombre.setVisible(true);}else nNombre.setVisible(false);
					if(Contraseña.isEmpty()){nContraseña.setVisible(true);}else nContraseña.setVisible(false);
					if(RepContraseña.isEmpty()){nRepContraseña.setVisible(true);}else nRepContraseña.setVisible(false);
					if(Email.isEmpty()){nEmail.setVisible(true);}else nEmail.setVisible(false);
					if(CuentaBancaria.isEmpty()){nCuenta.setVisible(true);}else nCuenta.setVisible(false);
					if(!Nombre.isEmpty() && !Contraseña.isEmpty() && !RepContraseña.isEmpty() && !Email.isEmpty() && !CuentaBancaria.isEmpty()){
						try {
							if (!controlAcceso.existeUsuario(Email, Contraseña)){
									controlAcceso.registrarse(Nombre,Contraseña,Email,Integer.parseInt(CuentaBancaria));
									dispose();
							}
							else
								txtpnerrores.setText("Ya existe un usuario con ese email!!");
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else
						txtpnerrores.setText("Los campos necesarios para registrarse como propietario están vacios!!");	
				}
				else
					txtpnerrores.setText("Seleccione una opción!!");
			}
		});
		getContentPane().add(btnRegistrarse);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(GUIRegistro.class.getResource("/imagenes/fondo.jpg")));
		label_2.setBounds(0, 0, 441, 471);
		getContentPane().add(label_2);
	
		setVisible(true);

	}
}
