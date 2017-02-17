package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import java.awt.Color;
import java.awt.Font;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;
import logicaNegocio.CasasList;

import javax.swing.JTextField;
import javax.swing.JPasswordField;


import dominio.CasaRural;
import dominio.Propietario;

import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import javax.swing.border.BevelBorder;


import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class GUIUsuario extends JFrame implements CasasList{
	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private final JPasswordField txtPassword;
	private static AccesManagerDBInterface controlAcceso;
	private Collection<CasaRural> coleccion;
	private static CasasList casasList;
	private JTable table;
	private JLabel label;
	private static JButton btnBuscar;
	private JTextField txtPropietario;
	private JLabel lblPropietario;
	private JLabel lblHabitaciones;
	private JLabel lblBaos;
	private JTextField txtHabitaciones;
	private JTextField txtBaos;
	private JLabel lblCocinas;
	private JLabel lblSalones;
	private JTextField txtCocinas;
	private JTextField txtSalones;
	private JLabel lblPoblacin;
	private JTextField txtPoblacion;
	private JLabel label_1;
	private JLabel lblBademail;
	private JLabel lblBadpassword;
	private JLabel lblNewLabel;
	private JLabel lblAparcamientos;
	private JTextField txtAparcamientos;
	private GUIUsuario guiUsuario;
	


	public GUIUsuario(final AccesManagerDBInterface logicaNegocio) throws RemoteException  {


		controlAcceso=logicaNegocio;
		System.out.println("Iniciada GUI GUIUsuario...");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1062, 659);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		guiUsuario=this;
		
				//Generamos una tabla con todas las casas del propietario en la bd.
				String[][] matriz = {};
				String[] inicio ={"ID","Propietario","Ciudad","Habitaciones","Baños","Cocinas","Salones","Aparcamientos"};
				casasList = new CasasList(matriz, inicio);
				casasList.isCellEditable(0, 0);
				coleccion= controlAcceso.verCasas();
				if(!coleccion.equals(null)){

					for(Object v: coleccion){
						
						CasaRural cr = (CasaRural) v;
						
						 String contenido[]={String.valueOf(cr.getId()),String.valueOf(cr.getPropietario().getNombre()),cr.getCiudad(),String.valueOf(cr.getnHabitaciones()),
						 String.valueOf(cr.getnBaños()),String.valueOf(cr.getnCocinas()),String.valueOf(cr.getnSalones()),String.valueOf(cr.getnAparcamientos())};
						 casasList.addRow(contenido);
						
					}
					
				}
				else
					System.out.println("COLECCION VACIA");
				
				//Permite vincular la fila seleccionada a la GUIInfoCasa con la información de la casa clicada
				table = new JTable(casasList);
				table.getScrollableTracksViewportWidth();
				table.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2){ //dos clicks
							int fila=table.rowAtPoint(arg0.getPoint());
							int columna= table.columnAtPoint(arg0.getPoint());
							if ((fila >-1) && (columna > -1)){
								Vector<String> infor = new Vector<String>();
									for(int i=0;i<casasList.getColumnCount();i++){
										infor.add((String) casasList.getValueAt(fila, i));
									}
									setVisible(false);
									try {
										new GUIClienteInfoCasa(logicaNegocio,guiUsuario,controlAcceso.getPropietarioPorNombre(infor.get(1)),Integer.parseInt(infor.get(0)));
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
							}
						}
					}
				});
					



				table.setBounds(10, 55, 855, 530);	
				table.setVerifyInputWhenFocusTarget(false);
				table.setUpdateSelectionOnSort(false);
				//table.setVisible(true);
				
				JScrollPane scrollBar = new JScrollPane(table);
				scrollBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

				scrollBar.setBounds(10, 51, 858, 529);
				contentPanel.add(scrollBar);
				table.setVisible(true);
	
				
		txtEmail = new JTextField();
		txtEmail.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtEmail.setBackground(new Color(255, 255, 153));
		txtEmail.setBounds(66, 10, 163, 20);
		txtEmail.setText("");
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPassword.setBackground(new Color(255, 255, 153));
		txtPassword.setBounds(368, 10, 173, 20);
		txtPassword.setText("");
		contentPanel.add(txtPassword);
		txtPassword.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblEmail.setBounds(10, 11, 46, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblContrasea.setBounds(270, 11, 135, 14);
		contentPanel.add(lblContrasea);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setOpaque(false);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email= txtEmail.getText();
				String contraseña= String.copyValueOf(txtPassword.getPassword());
				if(!email.isEmpty() && !contraseña.isEmpty()){
						try {
							if(controlAcceso.tipoUsuario(email, contraseña).equalsIgnoreCase("propietario")){
								System.out.println("Tipo Usuario: Propietario");
								txtEmail.setText("");
								txtPassword.setText("");
								lblBademail.setVisible(false);
								lblBadpassword.setVisible(false);
								setVisible(false);
								new GUIPropietario(logicaNegocio,guiUsuario,controlAcceso.getPropietario(email));
							}else if(controlAcceso.tipoUsuario(email, contraseña).equalsIgnoreCase("cliente")){
								txtEmail.setText("");
								txtPassword.setText("");
								lblBademail.setVisible(false);
								lblBadpassword.setVisible(false);
								System.out.println("Tipo Usuario: Cliente");
								new GUICliente(logicaNegocio,guiUsuario,controlAcceso.getCliente(email));				
								dispose();
							}else{
								System.out.println("Usuario y/o Contraseña incorrectos");
								lblBademail.setVisible(true);
								lblBadpassword.setVisible(true);
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if (email.isEmpty() && !contraseña.isEmpty()){
						System.out.println("Email vacio");
						lblBademail.setVisible(true);
						lblBadpassword.setVisible(false);
					}else if (!email.isEmpty() && contraseña.isEmpty()){
						System.out.println("Contraseña vacia");
						lblBademail.setVisible(false);
						lblBadpassword.setVisible(true);
					}else{
						System.out.println("Campos vacios");
						lblBademail.setVisible(true);
						lblBadpassword.setVisible(true);
					}
						
			}
		});
		btnEntrar.setBounds(587, 9, 127, 23);
		contentPanel.add(btnEntrar);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setOpaque(false);
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GUIRegistro(logicaNegocio);
			}
		});
		btnRegistrarse.setBounds(736, 9, 132, 23);
		contentPanel.add(btnRegistrarse);
	
		
		label = new JLabel("Creado y dise\u00F1ado por: David Prieto, Erik Fustes y Gonzalo del Palacio");
		label.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label.setBounds(283, 596, 426, 14);
		contentPanel.add(label);
		
		JLabel lblBuscador = new JLabel("Buscador...");
		lblBuscador.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblBuscador.setBounds(905, 107, 97, 35);
		contentPanel.add(lblBuscador);
		
		btnBuscar = new JButton("Buscar");
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nHabitaciones;
				int nBanos;
				int nCocinas;
				int nSalones;
				int nAparcamientos;
				String prop = txtPropietario.getText();
				String ciudad=txtPoblacion.getText();
				
				if (txtHabitaciones.getText().isEmpty()) nHabitaciones= 0; else nHabitaciones = Integer.parseInt(txtHabitaciones.getText());
				if (txtBaos.getText().isEmpty()) nBanos= 0; else nBanos = Integer.parseInt(txtBaos.getText());
				if (txtCocinas.getText().isEmpty()) nCocinas= 0; else nCocinas = Integer.parseInt(txtCocinas.getText());
				if (txtSalones.getText().isEmpty()) nSalones= 0; else nSalones = Integer.parseInt(txtSalones.getText());
				if (txtAparcamientos.getText().isEmpty()) nAparcamientos= 0; else nAparcamientos = Integer.parseInt(txtAparcamientos.getText());
				
				System.out.println("Buscar:"+prop+ciudad+ nHabitaciones+nBanos+nCocinas+nSalones+nAparcamientos);
				//buscar por propietario
				if (!prop.isEmpty() && ciudad.isEmpty() && nHabitaciones==0 && nCocinas==0 && nBanos==0 && nSalones==0 && nAparcamientos==0){
					Propietario p;
					try {
						p = controlAcceso.getPropietarioPorNombre(prop);
					
					if (p !=null)// Si el nombre del propietario introducido es null el ver casas mostrara el primer propietariod de la lista y sus casas
								// POR ELLO, EN CASO DE QUE SEA NULL NO HACEMOS NADA!!!! (NO SE ACTUALIZA LA TABLA)
					coleccion = p.getCasasRurales();
				
				//buscar por propietario y casa
				else if (!prop.isEmpty() && (!ciudad.isEmpty() || nHabitaciones!=0 || nCocinas!=0 || nBanos!=0 || nSalones!=0 || nAparcamientos!=0)){
					p = controlAcceso.getPropietarioPorNombre(prop);
					if (ciudad.isEmpty()) ciudad=null;
					coleccion = controlAcceso.BuscarCasas(p ,ciudad, nHabitaciones, nBanos, nCocinas, nSalones, nAparcamientos);
					
				}
				//buscar por casa
				else if (prop.isEmpty() && (!ciudad.isEmpty() || nHabitaciones!=0 || nCocinas!=0 || nBanos!=0 || nSalones!=0 || nAparcamientos!=0)){
					if (ciudad.isEmpty()) ciudad=null;
					coleccion = controlAcceso.buscarCasa(ciudad, nHabitaciones, nBanos, nCocinas, nSalones, nAparcamientos);
				
				}
				else
					coleccion = controlAcceso.verCasas();
				
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				//Generamos una tabla con todas las casas del propietario en la bd.
				String[][] matriz = {};
				String[] inicio ={"ID","Propietario","Ciudad","Habitaciones","Baños","Cocinas","Salones","Aparcamientos"};
				casasList = new CasasList(matriz, inicio);
				casasList.isCellEditable(0, 0);
				if(!coleccion.equals(null)){

					for(Object v: coleccion){
						
						CasaRural cr = (CasaRural) v;
						
						 String contenido[]={String.valueOf(cr.getId()),String.valueOf(cr.getPropietario().getNombre()),cr.getCiudad(),String.valueOf(cr.getnHabitaciones()),
						 String.valueOf(cr.getnBaños()),String.valueOf(cr.getnCocinas()),String.valueOf(cr.getnSalones()),String.valueOf(cr.getnAparcamientos())};
						 casasList.addRow(contenido);
						
					}
					
				}
				else
					System.out.println("COLECCION VACIA");
				
				//Permite vincular la fila seleccionada a la GUIInfoCasa con la información de la casa clicada
				table = new JTable(casasList);
				table.getScrollableTracksViewportWidth();
				table.addMouseListener(new MouseAdapter() {

					public void mouseClicked(MouseEvent arg0) {
						if (arg0.getClickCount() == 2){ //dos clicks
							int fila=table.rowAtPoint(arg0.getPoint());
							int columna= table.columnAtPoint(arg0.getPoint());
							if ((fila >-1) && (columna > -1)){
								Vector<String> infor = new Vector<String>();
									for(int i=0;i<casasList.getColumnCount();i++){
										infor.add((String) casasList.getValueAt(fila, i));
									}
									try {
										new GUIClienteInfoCasa(logicaNegocio,guiUsuario,controlAcceso.getPropietarioPorNombre(infor.get(1)),Integer.parseInt(infor.get(0)));
									} catch (NumberFormatException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (RemoteException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
							}
						}setVisible(false);
					}
				});
					



				table.setBounds(10, 55, 855, 530);	
				table.setVerifyInputWhenFocusTarget(false);
				table.setUpdateSelectionOnSort(false);
				//table.setVisible(true);
				
				JScrollPane scrollBar = new JScrollPane(table);
				scrollBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));							
				scrollBar.setBounds(10, 51, 858, 529);
				contentPanel.add(scrollBar);
				table.setVisible(true);
	
				
				
			}
		});
		btnBuscar.setOpaque(false);
		btnBuscar.setBounds(905, 532, 109, 48);
		contentPanel.add(btnBuscar);
		
		txtPropietario = new JTextField();
		txtPropietario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPropietario.setBackground(new Color(255, 255, 153));
		txtPropietario.setText("");
		txtPropietario.setBounds(874, 179, 128, 20);
		contentPanel.add(txtPropietario);
		txtPropietario.setColumns(10);
		
		lblPropietario = new JLabel("Propietario:");
		lblPropietario.setBounds(874, 153, 86, 14);
		lblPropietario.setFont(new Font("Segoe Print", Font.BOLD, 11));
		contentPanel.add(lblPropietario);
		
		lblHabitaciones = new JLabel("Habitaciones:");
		lblHabitaciones.setBounds(874, 307, 74, 14);
		lblHabitaciones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		contentPanel.add(lblHabitaciones);
		
		lblBaos = new JLabel("Ba\u00F1os:");
		lblBaos.setBounds(976, 307, 46, 14);
		lblBaos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		contentPanel.add(lblBaos);
		
		txtHabitaciones = new JTextField();
		txtHabitaciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtHabitaciones.setBackground(new Color(255, 255, 153));
		txtHabitaciones.setText("");
		txtHabitaciones.setBounds(874, 332, 60, 20);
		contentPanel.add(txtHabitaciones);
		txtHabitaciones.setColumns(10);
		
		txtBaos = new JTextField();
		txtBaos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtBaos.setBackground(new Color(255, 255, 153));
		txtBaos.setText("");
		txtBaos.setBounds(976, 332, 60, 20);
		contentPanel.add(txtBaos);
		txtBaos.setColumns(10);
		
		lblCocinas = new JLabel("Cocinas:");
		lblCocinas.setBounds(874, 383, 46, 14);
		lblCocinas.setFont(new Font("Segoe Print", Font.BOLD, 11));
		contentPanel.add(lblCocinas);
		
		lblSalones = new JLabel("Salones:");
		lblSalones.setBounds(976, 383, 46, 14);
		lblSalones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		contentPanel.add(lblSalones);
		
		txtCocinas = new JTextField();
		txtCocinas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCocinas.setBackground(new Color(255, 255, 153));
		txtCocinas.setText("");
		txtCocinas.setBounds(874, 408, 60, 20);
		contentPanel.add(txtCocinas);
		txtCocinas.setColumns(10);
		
		txtSalones = new JTextField();
		txtSalones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSalones.setBackground(new Color(255, 255, 153));
		txtSalones.setText("");
		txtSalones.setBounds(976, 408, 60, 20);
		contentPanel.add(txtSalones);
		txtSalones.setColumns(10);
		
		lblPoblacin = new JLabel("Ciudad:");
		lblPoblacin.setBounds(874, 227, 60, 14);
		lblPoblacin.setFont(new Font("Segoe Print", Font.BOLD, 11));
		contentPanel.add(lblPoblacin);
		
		lblAparcamientos = new JLabel("Aparcamientos:");
		lblAparcamientos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblAparcamientos.setBounds(912, 439, 124, 20);
		contentPanel.add(lblAparcamientos);
		
		txtAparcamientos = new JTextField();
		txtAparcamientos.setBackground(new Color(255, 255, 153));
		txtAparcamientos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtAparcamientos.setBounds(916, 470, 86, 20);
		txtAparcamientos.setText("");
		contentPanel.add(txtAparcamientos);
		txtAparcamientos.setColumns(10);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPoblacion.setBackground(new Color(255, 255, 153));
		txtPoblacion.setText("");
		txtPoblacion.setBounds(874, 259, 86, 20);
		contentPanel.add(txtPoblacion);
		txtPoblacion.setColumns(10);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/granjosIco.png")));
		label_1.setBounds(949, 10, 97, 48);
		contentPanel.add(label_1);
		
		lblBademail = new JLabel();
		lblBademail.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/cross.png")));
		lblBademail.setVisible(false);
		lblBademail.setBounds(239, 10, 46, 20);
		contentPanel.add(lblBademail);
		
		lblBadpassword = new JLabel("");
		lblBadpassword.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/cross.png")));
		lblBadpassword.setVisible(false);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 1046, 621);
		contentPanel.add(lblNewLabel);
		lblBadpassword.setBounds(551, 10, 46, 20);
		contentPanel.add(lblBadpassword);
		
		this.setVisible(true);
		
	}

	
	public class CasasList extends DefaultTableModel{
		public CasasList(String[][] a, String[]b){
			super(a,b);
			
		}
	   public boolean isCellEditable (int row, int column)
	   {
	       return false;
	   }
	}


	
	
}
