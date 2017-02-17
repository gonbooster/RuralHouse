package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;
import logicaNegocio.CasasList;
import dominio.CasaRural;
import dominio.Cliente;
import dominio.EnviaEmail;
import dominio.Oferta;
import dominio.Propietario;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class GUIClienteInfoCasa2 extends JFrame{
	
	
	private final JPanel contentPanel = new JPanel();
	private Collection<CasaRural> coleccion;
	private CasasList casasList;
	private JLabel label;
	private JLabel label_1;
	private JTextArea txtrDescripcion;
	private DefaultComboBoxModel listaOfertas= new DefaultComboBoxModel();
	private Collection<Oferta> ofertas;
	private AccesManagerDBInterface control;
	private CasaRural cr;
	private JTextField textPropietario;
	private JTextField textCiudad;
	private JTextField textCalle;
	private JTextField textNº;
	private JTextField textFechaInicio;
	private JTextField textFechaFin;
	private JTextField textPrecio;
	private Collection reservasCliente;

	public GUIClienteInfoCasa2(final AccesManagerDBInterface logicaNegocio,final GUICliente guiCliente,final Cliente cliente,final Propietario prop, final int id) {
		control=logicaNegocio;
		System.out.println("Iniciada GUI GUIClienteInfoCasa2...");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1062, 659);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		try {
			cr = control.verCasas(prop, id);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		actualizarOfertas(cr);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPanel.add(tabbedPane);
		tabbedPane.setBounds(10, 106, 1026, 463);
		
		
		JPanel imagenPrincipal = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Imagen principal</body></html>", null, imagenPrincipal, null);
		imagenPrincipal.setLayout(null);
		
		JLabel imagen = new JLabel("");
		imagen.setHorizontalAlignment(SwingConstants.CENTER);

		if(cr.getImagen()!=null){
			System.out.println("Imagen de la casa elegida: "+cr.getImagen().getPath());
			
		imagen.setIcon( new ImageIcon(cr.getImagen().getPath()));
		}else{	
		imagen.setIcon(new ImageIcon(GUIClienteInfoCasa.class.getResource("/imagenes/unavailable.png")));
		System.out.println("No hay imagen añadida");
		}

	
		
		
		imagen.setBounds(0, 0, 897, 458);
		imagenPrincipal.add(imagen);
		
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
		
		JTextArea txtrCiudad = new JTextArea(cr.getCiudad());
		txtrCiudad.setBounds(174, 58, 119, 20);
		información.add(txtrCiudad);
		txtrCiudad.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrCiudad.setBackground(new Color(255, 255, 153));
		txtrCiudad.setEditable(false);
		
		JLabel lblNaparcamientos = new JLabel("Número de aparcamientos:");
		lblNaparcamientos.setBounds(10, 318, 182, 20);
		información.add(lblNaparcamientos);
		lblNaparcamientos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNaparcamientos = new JTextArea(String.valueOf(cr.getnAparcamientos()));
		txtrNaparcamientos.setBounds(10, 349, 119, 20);
		información.add(txtrNaparcamientos);
		txtrNaparcamientos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNaparcamientos.setBackground(new Color(255, 255, 153));
		txtrNaparcamientos.setEditable(false);
		
		JLabel lblNhabitaciones = new JLabel("Número de habitaciones:");
		lblNhabitaciones.setBounds(10, 183, 145, 20);
		información.add(lblNhabitaciones);
		lblNhabitaciones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNhabitaciones = new JTextArea(String.valueOf(cr.getnHabitaciones()));
		txtrNhabitaciones.setBounds(10, 214, 119, 23);
		información.add(txtrNhabitaciones);
		txtrNhabitaciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNhabitaciones.setBackground(new Color(255, 255, 153));
		txtrNhabitaciones.setEditable(false);
		
		JLabel lblNbaos = new JLabel("Número de baños:");
		lblNbaos.setBounds(174, 185, 119, 17);
		información.add(lblNbaos);
		lblNbaos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNbaos = new JTextArea(String.valueOf(cr.getnBaños()));
		txtrNbaos.setBounds(174, 214, 119, 23);
		información.add(txtrNbaos);
		txtrNbaos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNbaos.setBackground(new Color(255, 255, 153));
		txtrNbaos.setEditable(false);
		
		JLabel lblNcocinas = new JLabel("Número de cocinas:");
		lblNcocinas.setBounds(10, 255, 119, 14);
		información.add(lblNcocinas);
		lblNcocinas.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNcocinas = new JTextArea(String.valueOf(cr.getnCocinas()));
		txtrNcocinas.setBounds(10, 280, 119, 23);
		información.add(txtrNcocinas);
		txtrNcocinas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNcocinas.setBackground(new Color(255, 255, 153));
		txtrNcocinas.setEditable(false);
		
		JLabel lblNsalones = new JLabel("Número de salones:");
		lblNsalones.setBounds(174, 255, 119, 14);
		información.add(lblNsalones);
		lblNsalones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrNsalones = new JTextArea(String.valueOf(cr.getnSalones()));
		txtrNsalones.setBounds(174, 280, 119, 28);
		información.add(txtrNsalones);
		txtrNsalones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNsalones.setBackground(new Color(255, 255, 153));
		txtrNsalones.setEditable(false);
		
		txtrDescripcion = new JTextArea(cr.getDescripcion());
		txtrDescripcion.setWrapStyleWord(true);
		txtrDescripcion.setBounds(363, 120, 524, 327);
		información.add(txtrDescripcion);
		txtrDescripcion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrDescripcion.setBackground(new Color(255, 255, 153));
		txtrDescripcion.setEditable(false);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(363, 80, 119, 20);
		información.add(lblDescripcin);
		lblDescripcin.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JLabel lblcalle = new JLabel("Calle:");
		lblcalle.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblcalle.setBounds(10, 106, 145, 20);
		información.add(lblcalle);
		
		JTextArea textArea = new JTextArea("0");
		textArea.setEditable(false);
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBackground(new Color(255, 255, 153));
		textArea.setBounds(10, 137, 119, 23);
		información.add(textArea);
		
		JLabel lblNPsio = new JLabel("N\u00BA:");
		lblNPsio.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblNPsio.setBounds(171, 106, 145, 20);
		información.add(lblNPsio);
		
		JTextArea textArea_1 = new JTextArea("0");
		textArea_1.setEditable(false);
		textArea_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea_1.setBackground(new Color(255, 255, 153));
		textArea_1.setBounds(171, 137, 119, 23);
		información.add(textArea_1);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblCiudad.setBounds(171, 24, 145, 20);
		información.add(lblCiudad);
		
		JPanel comentarios = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Comentarios</body></html>", null, comentarios, null);
		
		JPanel ofertas_1 = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Ofertas</body></html>", null, ofertas_1, null);
		ofertas_1.setLayout(null);
		
		final JList list = new JList(listaOfertas);
		list.setBounds(59, 26, 335, 55);
		
			JScrollPane scrollBar = new JScrollPane(list);
			scrollBar.setBounds(31, 25, 470, 158);
			ofertas_1.add(scrollBar);
			scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			final JPanel panel = new JPanel();
			panel.setBounds(352, 218, 519, 213);
			ofertas_1.add(panel);
			panel.setLayout(null);
			panel.setVisible(false);
			
			JLabel lblPropietario_1 = new JLabel("Propietario:");
			lblPropietario_1.setBounds(10, 24, 62, 25);
			panel.add(lblPropietario_1);
			
			JLabel lblCiudad_1 = new JLabel("Ciudad:");
			lblCiudad_1.setBounds(241, 29, 46, 14);
			panel.add(lblCiudad_1);
			
			JLabel lblCalle = new JLabel("Calle:");
			lblCalle.setBounds(10, 60, 46, 14);
			panel.add(lblCalle);
			
			JLabel lblNewLabel = new JLabel("N\u00BA:");
			lblNewLabel.setBounds(273, 60, 23, 14);
			panel.add(lblNewLabel);
			
			JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
			lblFechaInicio.setBounds(10, 96, 75, 14);
			panel.add(lblFechaInicio);
			
			JLabel lblFechaFin = new JLabel("Fecha Fin:");
			lblFechaFin.setBounds(212, 96, 55, 14);
			panel.add(lblFechaFin);
			
			JLabel lblPrecio = new JLabel("Precio del paquete:");
			lblPrecio.setBounds(10, 141, 105, 14);
			panel.add(lblPrecio);
			
			final JButton btnAnularReserva = new JButton("Anular reserva");
			btnAnularReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					textPropietario.setText("");
					textCiudad.setText("");
					textCalle.setText("");
					textNº.setText("");
					textFechaInicio.setText("");
					textFechaFin.setText("");
					textPrecio.setText("");
					panel.setVisible(false);
				}
			});
			btnAnularReserva.setBounds(244, 178, 143, 25);
			panel.add(btnAnularReserva);
			
			textPropietario = new JTextField();
			textPropietario.setEditable(false);
			textPropietario.setBounds(83, 26, 148, 20);
			panel.add(textPropietario);
			textPropietario.setColumns(10);
			
			textCiudad = new JTextField();
			textCiudad.setEditable(false);
			textCiudad.setBounds(297, 27, 156, 17);
			panel.add(textCiudad);
			textCiudad.setColumns(10);
			
			textCalle = new JTextField();
			textCalle.setEditable(false);
			textCalle.setColumns(10);
			textCalle.setBounds(47, 57, 180, 17);
			panel.add(textCalle);
			
			textNº = new JTextField();
			textNº.setEditable(false);
			textNº.setColumns(10);
			textNº.setBounds(299, 57, 36, 20);
			panel.add(textNº);

			textFechaInicio = new JTextField();
			textFechaInicio.setEditable(false);
			textFechaInicio.setColumns(10);
			textFechaInicio.setBounds(83, 93, 119, 20);
			panel.add(textFechaInicio);
			
			textFechaFin = new JTextField();
			textFechaFin.setEditable(false);
			textFechaFin.setColumns(10);
			textFechaFin.setBounds(268, 93, 119, 20);
			panel.add(textFechaFin);
			
			textPrecio = new JTextField();
			textPrecio.setEditable(false);
			textPrecio.setColumns(10);
			textPrecio.setBounds(154, 138, 73, 20);
			panel.add(textPrecio);
			
			JLabel label_5 = new JLabel("\u20AC");
			label_5.setFont(new Font("Segoe Print", Font.BOLD, 12));
			label_5.setBounds(234, 141, 15, 14);
			panel.add(label_5);
			

			final JButton btnConfirmarReserva = new JButton("Confirmar reserva");
			btnConfirmarReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Oferta o = (Oferta) list.getSelectedValue();
					try {
						control.reservarOferta(o, cliente);
						textPropietario.setText("");
						textCiudad.setText("");
						textCalle.setText("");
						textNº.setText("");
						textFechaInicio.setText("");
						textFechaFin.setText("");
						textPrecio.setText("");
						panel.setVisible(false);
						listaOfertas.removeAllElements();
						for(Oferta of:cr.getListaOfertas())
							listaOfertas.addElement(of);
						list.setModel(listaOfertas);
						list.setVisible(false);
						
						list.setModel(listaOfertas);
						list.setVisible(true);
						reservasCliente=control.verReservas(cliente);
						control.enviarCorreo(cliente.getEmail(), "Los Granjos - Reserva", "Gracias por confiar en nosotros, que pases una agradable estancia ^^");
						control.enviarCorreo(cr.getPropietario().getEmail(), "Los Granjos - Reserva", "El usuario "+cliente.getNombre()+" ha reservado una de sus casas,compruebe sus solicitudes porfavor.");
						btnAnularReserva.setVisible(false);
						btnConfirmarReserva.setVisible(false);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnConfirmarReserva.setBounds(84, 177, 143, 25);
			panel.add(btnConfirmarReserva);
		JPanel localización = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Localización</body></html>", null, localización, null);
		

			JButton btnReservar = new JButton("Reservar");
			btnReservar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!list.isSelectionEmpty()){
					Oferta o=(Oferta)list.getSelectedValue();
					
					textPropietario.setText(o.getCasa().getPropietario().getNombre());
					textCiudad.setText(o.getCasa().getCiudad());
					textCalle.setText(o.getCasa().getCalle());
					textNº.setText(String.valueOf(o.getCasa().getnCasa()));
					SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");

					textFechaInicio.setText(format.format(o.getFechaInicio()));
					textFechaFin.setText(format.format(o.getFechaFin()));
					
					textPrecio.setText(String.valueOf(o.getPrecio()));
					panel.setVisible(true);
					btnAnularReserva.setVisible(true);
					btnConfirmarReserva.setVisible(true);
					}
				}
			});
			btnReservar.setBounds(538, 66, 170, 38);
			ofertas_1.add(btnReservar);
		
		JButton btnVerEnMapa = new JButton("Ver en mapa");
		btnVerEnMapa.setBounds(38, 44, 163, 35);
		localización.add(btnVerEnMapa);
		btnVerEnMapa.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				try {
					control.mostrarMapa(cr.getCalle(), cr.getnCasa());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		label = new JLabel("Creado y dise\u00F1ado por: David Prieto, Erik Fustes y Gonzalo del Palacio");
		label.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label.setBounds(283, 596, 426, 14);
		contentPanel.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GUIClienteInfoCasa2.class.getResource("/imagenes/granjosIco.png")));
		label_1.setBounds(949, 11, 97, 48);
		contentPanel.add(label_1);
		

		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setOpaque(false);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GUICliente(logicaNegocio,guiCliente.getGuiUsuario(),cliente);
			}
		});
		btnVolver.setBounds(520, 22, 173, 37);
		contentPanel.add(btnVolver);
		
		JLabel label_2 = new JLabel("Conectado como Cliente:");
		label_2.setFont(new Font("Segoe Print", Font.BOLD, 15));
		label_2.setBounds(65, 23, 184, 32);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel(cliente.getNombre());
		label_3.setFont(new Font("Segoe Print", Font.BOLD, 15));
		label_3.setBounds(259, 23, 112, 32);
		contentPanel.add(label_3);
		
		JButton btnDesconectarse = new JButton("Desconectarse");
		btnDesconectarse.setOpaque(false);
		btnDesconectarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new GUIUsuario(logicaNegocio);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnDesconectarse.setBounds(381, 23, 129, 32);
		contentPanel.add(btnDesconectarse);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(GUIClienteInfoCasa2.class.getResource("/imagenes/fondo.jpg")));
		label_4.setBounds(0, 0, 1046, 621);
		contentPanel.add(label_4);

		this.setVisible(true);


	}
	
public void actualizarOfertas(CasaRural cr){
		
		
		for(Oferta v :cr.getListaOfertas()) listaOfertas.addElement(v);			
		
	}
	
	public class CasasList extends DefaultTableModel
	{
		public CasasList(String[][] a, String[]b){
			super(a,b);
			
		}
	   public boolean isCellEditable (int row, int column)
	   {
	       return false;
	   }
	}
}
