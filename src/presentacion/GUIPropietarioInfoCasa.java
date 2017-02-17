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
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;
import logicaNegocio.CasasList;
import dominio.CasaRural;
import dominio.Oferta;
import dominio.Reserva;
import dominio.Propietario;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Component;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class GUIPropietarioInfoCasa extends JFrame implements CasasList{
	
	
	private final JPanel contentPanel = new JPanel();
	private  AccesManagerDBInterface controlAcceso;
	private JLabel label;
	private JLabel label_1;
	private JTextArea txtrDescripcion;
	private DefaultListModel listaOfertas= new DefaultListModel();
	private DefaultListModel listaReservas= new DefaultListModel();

	private Propietario propietario;
	private CasaRural cr;
	

	public GUIPropietarioInfoCasa(AccesManagerDBInterface logicaNegocio,final int fila,final GUIUsuario guiUsuario,final GUIPropietario guiPropietario,final Propietario prop, int id) {
		controlAcceso=logicaNegocio;
		System.out.println("Iniciada GUI GUIPropietarioInfoCasa...");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 1062, 659);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		propietario=prop;		
		try {
			cr = controlAcceso.verCasas(propietario, id);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel label_2 = new JLabel(propietario.getNombre());
		label_2.setBounds(324, 17, 119, 34);
		contentPanel.add(label_2);
		label_2.setFont(new Font("Segoe Print", Font.BOLD, 15));
		
		JButton button_2 = new JButton("Volver");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				guiPropietario.setVisible(true);
			}
		});
		button_2.setOpaque(false);
		button_2.setBounds(635, 19, 175, 34);
		contentPanel.add(button_2);
		
	
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
		
		txtrDescripcion = new JTextArea(cr.getDescripcion());
		txtrDescripcion.setWrapStyleWord(true);
		txtrDescripcion.setBounds(352, 109, 524, 327);
		información.add(txtrDescripcion);
		txtrDescripcion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrDescripcion.setBackground(new Color(255, 255, 153));
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(364, 78, 119, 20);
		información.add(lblDescripcin);
		lblDescripcin.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JTextArea txtrPropietario = new JTextArea(propietario.getNombre());
		txtrPropietario.setBounds(10, 58, 119, 20);
		información.add(txtrPropietario);
		txtrPropietario.setEditable(false);
		txtrPropietario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrPropietario.setBackground(new Color(255, 255, 153));
		
		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setBounds(10, 24, 119, 14);
		información.add(lblPropietario);
		lblPropietario.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		final JTextArea txtrCiudad = new JTextArea(cr.getCiudad());
		txtrCiudad.setBounds(160, 58, 119, 20);
		información.add(txtrCiudad);
		txtrCiudad.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrCiudad.setBackground(new Color(255, 255, 153));
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Segoe Print", Font.BOLD, 11));
		lblCiudad.setBounds(160, 24, 119, 14);
		información.add(lblCiudad);
		
		JLabel lblNhabitaciones = new JLabel("Número de habitaciones:");
		lblNhabitaciones.setBounds(10, 188, 145, 20);
		información.add(lblNhabitaciones);
		lblNhabitaciones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		final JTextArea txtrNhabitaciones = new JTextArea(String.valueOf(cr.getnHabitaciones()));
		txtrNhabitaciones.setBounds(10, 228, 119, 23);
		información.add(txtrNhabitaciones);
		txtrNhabitaciones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNhabitaciones.setBackground(new Color(255, 255, 153));
		
		JLabel lblNbaos = new JLabel("Número de baños:");
		lblNbaos.setBounds(165, 190, 119, 17);
		información.add(lblNbaos);
		lblNbaos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		final JTextArea txtrNbaos = new JTextArea(String.valueOf(cr.getnBaños()));
		txtrNbaos.setBounds(160, 228, 119, 23);
		información.add(txtrNbaos);
		txtrNbaos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNbaos.setBackground(new Color(255, 255, 153));
		
		final JTextArea txtrNcocinas = new JTextArea(String.valueOf(cr.getnCocinas()));
		txtrNcocinas.setBounds(10, 311, 119, 23);
		información.add(txtrNcocinas);
		txtrNcocinas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNcocinas.setBackground(new Color(255, 255, 153));
		
		JLabel lblNcocinas = new JLabel("Número de cocinas:");
		lblNcocinas.setBounds(10, 276, 119, 14);
		información.add(lblNcocinas);
		lblNcocinas.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		final JTextArea txtrNsalones = new JTextArea(String.valueOf(cr.getnSalones()));
		txtrNsalones.setBounds(160, 311, 119, 23);
		información.add(txtrNsalones);
		txtrNsalones.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNsalones.setBackground(new Color(255, 255, 153));
		
		JLabel lblNsalones = new JLabel("Número de salones:");
		lblNsalones.setBounds(160, 276, 119, 14);
		información.add(lblNsalones);
		lblNsalones.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		final JTextArea txtrNaparcamientos = new JTextArea(String.valueOf(cr.getnAparcamientos()));
		txtrNaparcamientos.setBounds(10, 377, 119, 20);
		información.add(txtrNaparcamientos);
		txtrNaparcamientos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNaparcamientos.setBackground(new Color(255, 255, 153));
		
		JLabel lblNaparcamientos = new JLabel("Número de aparcamientos:");
		lblNaparcamientos.setBounds(10, 346, 182, 20);
		información.add(lblNaparcamientos);
		lblNaparcamientos.setFont(new Font("Segoe Print", Font.BOLD, 11));
		
		JLabel label_5 = new JLabel("Calle:");
		label_5.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label_5.setBounds(10, 106, 145, 20);
		información.add(label_5);
		
		JTextArea txtrCalle = new JTextArea(cr.getCalle());
		txtrCalle.setEditable(false);
		txtrCalle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrCalle.setBackground(new Color(255, 255, 153));
		txtrCalle.setBounds(10, 135, 119, 23);
		información.add(txtrCalle);
		
		JLabel label_6 = new JLabel("N\u00BA:");
		label_6.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label_6.setBounds(160, 106, 145, 20);
		información.add(label_6);
		
		JTextArea txtrNumero = new JTextArea(String.valueOf(cr.getnCasa()));
		txtrNumero.setEditable(false);
		txtrNumero.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrNumero.setBackground(new Color(255, 255, 153));
		txtrNumero.setBounds(160, 135, 119, 23);
		información.add(txtrNumero);
			

		JPanel comentarios = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Comentarios</body></html>", null, comentarios, null);
		
		JPanel ofertas_1 = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Ofertas</body></html>", null, ofertas_1, null);
		ofertas_1.setLayout(null);
		
		
		for(Oferta v :cr.getListaOfertas()) listaOfertas.addElement(v);		

		
		JLabel lblOfertas = new JLabel("OFERTAS");
		lblOfertas.setFont(new Font("Segoe Print", Font.BOLD, 20));
		lblOfertas.setBounds(631, 91, 111, 38);
		ofertas_1.add(lblOfertas);
		
		
		final JLabel lblReservas = new JLabel("RESERVAS");
		lblReservas.setFont(new Font("Segoe Print", Font.BOLD, 20));
		lblReservas.setBounds(102, 328, 125, 38);
		ofertas_1.add(lblReservas);
		
			
			
			
			
			
			final JList list_1 = new JList();
			list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list_1.setBounds(0, 0, 524, 170);
			ofertas_1.add(list_1);
			list_1.setVisible(false);
			list_1.setCellRenderer(new MyCellRenderer());
			
			final JScrollPane scrollPane = new JScrollPane(list_1);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(295, 231, 526, 172);
			ofertas_1.add(scrollPane);
			scrollPane.setVisible(false);
			

			final JList list = new JList(listaOfertas);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(!listaOfertas.isEmpty()){
					list_1.setVisible(false);
					scrollPane.setVisible(false);
					listaReservas.removeAllElements();
					Oferta o=(Oferta)list.getSelectedValue();
					if(o!=null){
					System.out.println("Oferta elegida: "+o.toString());
					try {
						for(Reserva r : controlAcceso.verReservas(o)){
							System.out.println(r.toString());
							listaReservas.addElement(r);
							
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}}
					}
				}
			});
			list.setBounds(59, 26, 335, 55);
		
			JScrollPane scrollBar = new JScrollPane(list);
			scrollBar.setBounds(29, 27, 526, 172);
			ofertas_1.add(scrollBar);
			scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			JButton btnVerReservas = new JButton("Ver Reservas");
			btnVerReservas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					list_1.setModel(listaReservas);
					list_1.setVisible(true);
					scrollPane.setVisible(true);
				}
			});
			btnVerReservas.setBounds(29, 279, 186, 38);
			ofertas_1.add(btnVerReservas);
			
			final JLabel lblImposibleEliminarOferta = new JLabel("Imposible eliminar oferta. Ya hay reservas para esa ofera");
			lblImposibleEliminarOferta.setForeground(Color.RED);
			lblImposibleEliminarOferta.setFont(new Font("Segoe Print", Font.BOLD, 11));
			lblImposibleEliminarOferta.setBounds(565, 185, 253, 35);
			ofertas_1.add(lblImposibleEliminarOferta);
			lblImposibleEliminarOferta.setVisible(false);
			
			JButton btnBorrarOferta = new JButton("Borrar Oferta");
			btnBorrarOferta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Oferta o = (Oferta)list.getSelectedValue();
					try {
						if(o.getNumReservas()>0){
							lblImposibleEliminarOferta.setVisible(true);
						}else{
						controlAcceso.eliminarOferta(cr,o);
						lblImposibleEliminarOferta.setVisible(false);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listaOfertas.removeElement(o);
					list.setVisible(false);
					list.setModel(listaOfertas);
					list.setVisible(true);
				}
			});
			btnBorrarOferta.setBounds(584, 132, 186, 38);
			ofertas_1.add(btnBorrarOferta);
			
			
			
		JPanel localización = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Localización</body></html>", null, localización, null);
		localización.setLayout(null);
		
		JButton btnVerEnMapa = new JButton("Ver en mapa");
		btnVerEnMapa.setBounds(38, 44, 163, 35);
		localización.add(btnVerEnMapa);
		btnVerEnMapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String x= cr.getCalle().replaceAll(" ", "%20");
				try {
					controlAcceso.mostrarMapa(cr.getCalle(), cr.getnCasa());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JPanel alquilar = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Alquilar</body></html>", null, alquilar, null);
		alquilar.setLayout(null);
		
		JPanel eliminarCasa = new JPanel();
		tabbedPane.addTab("<html><body leftmargin=15 topmargin=12 marginwidth=15 marginheight=9>Eliminar Casa</body></html>", null, eliminarCasa, null);
		eliminarCasa.setLayout(null);
		
		
		final JPanel panel = new JPanel();
		panel.setBounds(208, 91, 631, 332);
		eliminarCasa.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		panel.setEnabled(false);
		
		final JLabel lblVaUstedA = new JLabel("Va usted a eliminar su casa rural");
		lblVaUstedA.setHorizontalAlignment(SwingConstants.CENTER);
		lblVaUstedA.setForeground(Color.RED);
		lblVaUstedA.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblVaUstedA.setBounds(25, 11, 596, 64);
		panel.add(lblVaUstedA);
		
		final JLabel lblEstaOpcinEs = new JLabel("Esta opci\u00F3n es irreversible");
		lblEstaOpcinEs.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstaOpcinEs.setForeground(Color.RED);
		lblEstaOpcinEs.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblEstaOpcinEs.setBounds(25, 78, 596, 64);
		panel.add(lblEstaOpcinEs);
		
		final JLabel lblestUstedSeguro = new JLabel("\u00BFEst\u00E1 usted seguro?");
		lblestUstedSeguro.setHorizontalAlignment(SwingConstants.CENTER);
		lblestUstedSeguro.setForeground(Color.RED);
		lblestUstedSeguro.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblestUstedSeguro.setBounds(25, 153, 596, 64);
		panel.add(lblestUstedSeguro);
		
		JButton btnSi = new JButton("SI");
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblVaUstedA.setText("Eliminando casa rural.");
				lblEstaOpcinEs.setText("Espere por favor");
				lblestUstedSeguro.setVisible(false);
				try {
					if(!controlAcceso.eliminarCasaRural(cr)){
						lblestUstedSeguro.setText("No se puede eliminar. Mire si tiene reservas");
						lblestUstedSeguro.setVisible(true);
					}else{
						
						propietario.remove(cr);

					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSi.setBounds(184, 232, 100, 30);
		panel.add(btnSi);
		
		final JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblVaUstedA.setText("Va usted a eliminar su casa rural");
				lblEstaOpcinEs.setText("Esta opci\u00F3n es irreversible");
				lblestUstedSeguro.setText("\u00BFEst\u00E1 usted seguro?");
				panel.setVisible(false);
				panel.setEnabled(false);
			}
		});
		btnNo.setBounds(377, 232, 100, 30);
		panel.add(btnNo);
				

		JButton btnBorrarCasaRural = new JButton("Borrar Casa Rural");
		btnBorrarCasaRural.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				panel.setEnabled(true);
			}
		});
		btnBorrarCasaRural.setBounds(33, 28, 172, 34);
		eliminarCasa.add(btnBorrarCasaRural);
		label = new JLabel("Creado y dise\u00F1ado por: David Prieto, Erik Fustes y Gonzalo del Palacio");
		label.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label.setBounds(283, 596, 426, 14);
		contentPanel.add(label);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GUIPropietarioInfoCasa.class.getResource("/imagenes/granjosIco.png")));
		label_1.setBounds(949, 17, 97, 48);
		contentPanel.add(label_1);
		
		
		JButton button = new JButton("Desconectarse");
		button.setOpaque(false);
		button.setBounds(453, 13, 143, 43);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				guiUsuario.setVisible(true);
			}
		});
		contentPanel.add(button);
		
		JLabel label_3 = new JLabel("Conectado como Propietario :");
		label_3.setFont(new Font("Segoe Print", Font.BOLD, 15));
		label_3.setBounds(92, 13, 232, 43);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(GUIPropietarioInfoCasa.class.getResource("/imagenes/fondo.jpg")));
		label_4.setBounds(0, 0, 1046, 621);
		contentPanel.add(label_4);
		this.setVisible(true);


	}
	
	class MyCellRenderer extends JLabel implements ListCellRenderer {
	     public MyCellRenderer() {
	         setOpaque(true);
	     }

	     public Component getListCellRendererComponent(JList list, Object value,int index, boolean isSelected, boolean cellHasFocus) {

	         setText(value.toString());

	         Color background=null;
	         Color foreground=null;

	    	 Reserva r =(Reserva) value;
	    	 if(! isSelected){
	    	 if(r.getEstadoPago().equals("pendiente")){
	    		 background = Color.ORANGE;
	    		 foreground = Color.BLACK;
	    		 
	         }else if(r.getEstadoPago().equals("pagado")){
	        	 background = Color.GREEN;
	        	 foreground = Color.BLACK;
	        	 
	         }else{
	        	 background=Color.RED;
	        	 foreground= Color.BLACK;
	         } 
	    	 
	    	 }else  if(isSelected){
	    		background = Color.WHITE;
	    		foreground = Color.BLACK;
	    	 }

	         setBackground(background);
	         setForeground(foreground);

	         return this;
	     }
	 }
}
