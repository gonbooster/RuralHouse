
package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;
import logicaNegocio.CasasList;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import dominio.CasaRural;
import dominio.Oferta;
import dominio.Propietario;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.io.IOException;

public class GUIClienteInfoCasa extends JFrame{
	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private final JPasswordField txtPassword;
	private  AccesManagerDBInterface controlAcceso;
	private JLabel label;
	private JLabel label_1;
	private JTextArea txtrDescripcion;
	private JLabel lblBademail;
	private JLabel lblBadpassword;
	private DefaultComboBoxModel listaOfertas= new DefaultComboBoxModel();
	private CasaRural cr;
	
	
	public GUIClienteInfoCasa(final AccesManagerDBInterface logicaNegocio,final GUIUsuario guiUsuario, final Propietario prop, final int id) {
		controlAcceso=logicaNegocio;
		
		System.out.println("Iniciada GUI GUIClienteInfoCasa...");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1062, 659);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		try {
			cr = controlAcceso.verCasas(prop, id);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 93, 1026, 463);
		contentPanel.add(tabbedPane);
		
		JPanel imagenPrincipal = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Imagen principal</body></html>", null, imagenPrincipal, null);
		imagenPrincipal.setLayout(null);
		
		JLabel imagen = new JLabel("");
		imagen.setBounds(0, 0, 897, 458);
		imagenPrincipal.add(imagen);
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		System.out.println(cr.toString());
		if(cr.getImagen()!=null){
			System.out.println("Imagen de la casa elegida: "+cr.getImagen().getPath());
			
		imagen.setIcon( new ImageIcon(cr.getImagen().getPath()));
		}else{	
		imagen.setIcon(new ImageIcon(GUIClienteInfoCasa.class.getResource("/imagenes/unavailable.png")));
		System.out.println("No hay imagen añadida");
		}
		
		JPanel información = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Información</body></html>", null, información, null);
		información.setLayout(null);
		
		
		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setBounds(10, 24, 119, 14);
		información.add(lblPropietario);
		lblPropietario.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrPropietario = new JTextArea(prop.getNombre());
		txtrPropietario.setBounds(10, 58, 119, 20);
		información.add(txtrPropietario);
		txtrPropietario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrPropietario.setBackground(new Color(255, 255, 153));
		txtrPropietario.setEditable(false);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblCiudad.setBounds(160, 24, 119, 14);
		información.add(lblCiudad);
		
		JTextArea txtrCiudad = new JTextArea(cr.getCiudad());
		txtrCiudad.setBounds(160, 58, 119, 20);
		información.add(txtrCiudad);
		txtrCiudad.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrCiudad.setBackground(new Color(255, 255, 153));
		txtrCiudad.setEditable(false);
		
		JLabel lblNaparcamientos = new JLabel("Número de aparcamientos:");
		lblNaparcamientos.setBounds(10, 346, 182, 20);
		información.add(lblNaparcamientos);
		lblNaparcamientos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNaparcamientos = new JTextArea(String.valueOf(cr.getnAparcamientos()));
		txtrNaparcamientos.setBounds(10, 377, 119, 20);
		información.add(txtrNaparcamientos);
		txtrNaparcamientos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNaparcamientos.setBackground(new Color(255, 255, 153));
		txtrNaparcamientos.setEditable(false);
		
		JLabel lblNhabitaciones = new JLabel("Número de habitaciones:");
		lblNhabitaciones.setBounds(10, 188, 145, 20);
		información.add(lblNhabitaciones);
		lblNhabitaciones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNhabitaciones = new JTextArea(String.valueOf(cr.getnHabitaciones()));
		txtrNhabitaciones.setBounds(10, 228, 119, 23);
		información.add(txtrNhabitaciones);
		txtrNhabitaciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNhabitaciones.setBackground(new Color(255, 255, 153));
		txtrNhabitaciones.setEditable(false);
		
		JLabel lblNbaos = new JLabel("Número de baños:");
		lblNbaos.setBounds(160, 190, 119, 17);
		información.add(lblNbaos);
		lblNbaos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNbaos = new JTextArea(String.valueOf(cr.getnBaños()));
		txtrNbaos.setBounds(160, 228, 119, 23);
		información.add(txtrNbaos);
		txtrNbaos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNbaos.setBackground(new Color(255, 255, 153));
		txtrNbaos.setEditable(false);
		
		JLabel lblNcocinas = new JLabel("Número de cocinas:");
		lblNcocinas.setBounds(10, 276, 119, 14);
		información.add(lblNcocinas);
		lblNcocinas.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNcocinas = new JTextArea(String.valueOf(cr.getnCocinas()));
		txtrNcocinas.setBounds(10, 311, 119, 23);
		información.add(txtrNcocinas);
		txtrNcocinas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNcocinas.setBackground(new Color(255, 255, 153));
		txtrNcocinas.setEditable(false);
		
		JLabel lblNsalones = new JLabel("Número de salones:");
		lblNsalones.setBounds(160, 276, 119, 14);
		información.add(lblNsalones);
		lblNsalones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNsalones = new JTextArea(String.valueOf(cr.getnSalones()));
		txtrNsalones.setBounds(160, 315, 119, 20);
		información.add(txtrNsalones);
		txtrNsalones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNsalones.setBackground(new Color(255, 255, 153));
		txtrNsalones.setEditable(false);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(364, 78, 119, 20);
		información.add(lblDescripcin);
		lblDescripcin.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		txtrDescripcion = new JTextArea(cr.getDescripcion());
		txtrDescripcion.setWrapStyleWord(true);
		txtrDescripcion.setBounds(363, 120, 524, 327);
		información.add(txtrDescripcion);
		txtrDescripcion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrDescripcion.setBackground(new Color(255, 255, 153));
		txtrDescripcion.setEditable(false);
		
		JTextArea txtrCalle = new JTextArea("0");
		txtrCalle.setEditable(false);
		txtrCalle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrCalle.setBackground(new Color(255, 255, 153));
		txtrCalle.setBounds(10, 137, 119, 23);
		información.add(txtrCalle);
		
		JLabel label_3 = new JLabel("Calle:");
		label_3.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label_3.setBounds(10, 106, 145, 20);
		información.add(label_3);
		
		JLabel label_4 = new JLabel("N\u00BA:");
		label_4.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label_4.setBounds(160, 106, 145, 20);
		información.add(label_4);
		
		JTextArea txtrNumero = new JTextArea("0");
		txtrNumero.setEditable(false);
		txtrNumero.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNumero.setBackground(new Color(255, 255, 153));
		txtrNumero.setBounds(160, 137, 119, 23);
		información.add(txtrNumero);
		
		JPanel comentarios = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Comentarios</body></html>", null, comentarios, null);
		
		JPanel ofertas_1 = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Ofertas</body></html>", null, ofertas_1, null);
		ofertas_1.setLayout(null);
		for(Oferta o : cr.getListaOfertas())
			listaOfertas.addElement(o);
		JList list = new JList(listaOfertas);
		list.setBounds(59, 26, 335, 55);
		
			JScrollPane scrollBar = new JScrollPane(list);
			scrollBar.setBounds(37, 25, 557, 234);
			ofertas_1.add(scrollBar);
			scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			JLabel label_5 = new JLabel("Necesitas estar logeado como cliente.");
			label_5.setHorizontalAlignment(SwingConstants.CENTER);
			label_5.setForeground(Color.RED);
			label_5.setFont(new Font("Segoe Print", Font.BOLD, 34));
			label_5.setBackground(new Color(240, 230, 140));
			label_5.setBounds(94, 274, 770, 90);
			ofertas_1.add(label_5);
			
			JLabel lblParaPoderReservar = new JLabel("para poder reservar.");
			lblParaPoderReservar.setHorizontalAlignment(SwingConstants.CENTER);
			lblParaPoderReservar.setForeground(Color.RED);
			lblParaPoderReservar.setFont(new Font("Segoe Print", Font.BOLD, 34));
			lblParaPoderReservar.setBackground(new Color(240, 230, 140));
			lblParaPoderReservar.setBounds(94, 357, 770, 90);
			ofertas_1.add(lblParaPoderReservar);
			
		JPanel localización = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Localización</body></html>", null, localización, null);
		localización.setLayout(null);
		
		JButton btnVerEnMapa = new JButton("Ver en mapa");
		btnVerEnMapa.setBounds(38, 44, 163, 35);
		localización.add(btnVerEnMapa);
		btnVerEnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controlAcceso.mostrarMapa(cr.getCalle(), cr.getnCasa());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
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
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblEmail.setBounds(10, 11, 46, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblContrasea.setBounds(272, 11, 135, 14);
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
								dispose();
								new GUIPropietario(logicaNegocio,guiUsuario,controlAcceso.getPropietario(email));						
							}else if(controlAcceso.tipoUsuario(email, contraseña).equalsIgnoreCase("cliente")){
								System.out.println("Tipo Usuario: Cliente");
								dispose();
								new GUICliente(logicaNegocio,guiUsuario,controlAcceso.getCliente(email));				
							}else{
								System.out.println("Usuario y/o Contraseña incorrectos");
								lblBademail.setVisible(true);
								lblBadpassword.setVisible(true);
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//Muestra las imagenes X (campos incorrectos).
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
		btnRegistrarse.setBounds(736, 9, 127, 23);
		contentPanel.add(btnRegistrarse);

		
		
		label = new JLabel("Creado y dise\u00F1ado por: David Prieto, Erik Fustes y Gonzalo del Palacio");
		label.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label.setBounds(283, 596, 426, 14);
		contentPanel.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GUIClienteInfoCasa.class.getResource("/imagenes/granjosIco.png")));
		label_1.setBounds(949, 10, 97, 48);
		contentPanel.add(label_1);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setOpaque(false);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				guiUsuario.setVisible(true);
				
			}
		});
		btnVolver.setBounds(643, 43, 173, 37);
		contentPanel.add(btnVolver);
		
		
		lblBademail = new JLabel();
		lblBademail.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/cross.png")));
		lblBademail.setVisible(false);
		lblBademail.setBounds(239, 10, 46, 20);
		contentPanel.add(lblBademail);
		
		lblBadpassword = new JLabel("");
		lblBadpassword.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/cross.png")));
		lblBadpassword.setVisible(false);
		lblBadpassword.setBounds(551, 10, 46, 20);
		contentPanel.add(lblBadpassword);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(GUIClienteInfoCasa.class.getResource("/imagenes/fondo.jpg")));
		label_2.setBounds(0, 0, 1046, 621);
		contentPanel.add(label_2);
		
		this.setVisible(true);

	}
	

}
