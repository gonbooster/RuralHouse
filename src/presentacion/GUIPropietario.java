package presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;
import logicaNegocio.CasasList;


import dominio.CasaRural;
import dominio.Propietario;
import javax.swing.border.BevelBorder;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class GUIPropietario extends JFrame implements CasasList{
	
	
	private final JPanel contentPanel = new JPanel();
	private static AccesManagerDBInterface controlAcceso;
	private Collection<CasaRural> coleccion;
	private CasasList casasList;
	private JTable table;
	private JLabel label1;
	private JLabel label2;
	private JLabel label;
	private Propietario pro;
	private JLabel lblNewLabel;
	private GUIPropietario guiPropietario;

	
	public GUIPropietario(final AccesManagerDBInterface logicaNegocio , final GUIUsuario guiUsuario, final Propietario propietario) {
		controlAcceso=logicaNegocio;
		pro=propietario;
		guiPropietario=this;
		System.out.println("Iniciada GUI GUIPropietario...");
		setBounds(100, 100, 1062, 659);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

			
					//Generamos una tabla con todas las casas del propietario en la bd.				
					String[][] matriz = {};
					String[] inicio ={"ID","Propietario","Ciudad","Habitaciones","Baños","Cocinas","Salones","Aparcamientos"};
					casasList = new CasasList(matriz, inicio);
					casasList.isCellEditable(0, 0);
						System.out.println("Ejecutando actualizar tabla para "+pro.getNombre());
						coleccion = new ArrayList<CasaRural>();
					coleccion= pro.getCasasRurales();
					if(coleccion!=null){

						for(Object v: coleccion){
							
							CasaRural cr = (CasaRural) v;
							
							 String contenido[]={String.valueOf(cr.getId()),String.valueOf(cr.getPropietario().getNombre()),cr.getCiudad(),String.valueOf(cr.getnHabitaciones()),
							 String.valueOf(cr.getnBaños()),String.valueOf(cr.getnCocinas()),String.valueOf(cr.getnSalones()),String.valueOf(cr.getnAparcamientos())};
							 casasList.addRow(contenido);
							
						}
						
					}
					else{
						
						System.out.println("COLECCION VACIA");	
					}
					table = new JTable(casasList);
					//Permite vincular la fila seleccionada a la GUIInfoCasa con la información de la casa clicada
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
											dispose();
											try {
												new GUIPropietarioInfoCasa(logicaNegocio,fila,guiUsuario,guiPropietario,controlAcceso.getPropietarioPorNombre(infor.get(1)),Integer.parseInt(infor.get(0)));
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
					
					JScrollPane scrollBar = new JScrollPane(table);
					scrollBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					scrollBar.setBounds(10, 51, 858, 529);
					contentPanel.add(scrollBar);

		
		label1 = new JLabel("Conectado como Propietario :");
		label1.setFont(new Font("Segoe Print", Font.BOLD, 15));
		label1.setBounds(62, 0, 235, 43);
		contentPanel.add(label1);
		
		label2 = new JLabel(propietario.getNombre());
		label2.setBounds(308, 4, 137, 34);
		label2.setFont(new Font("Segoe Print", Font.BOLD, 15));
		contentPanel.add(label2);
		
		
		JButton btnCrearCasa = new JButton("Crear casa");
		btnCrearCasa.setOpaque(false);
		btnCrearCasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new GUICrearCasa(logicaNegocio,guiUsuario,guiPropietario,propietario);
			}
		});
		btnCrearCasa.setBounds(874, 54, 162, 53);
		contentPanel.add(btnCrearCasa);
		
		label = new JLabel("Creado y dise\u00F1ado por: David Prieto, Erik Fustes y Gonzalo del Palacio");
		label.setFont(new Font("Segoe Print", Font.BOLD, 11));
		label.setBounds(279, 596, 426, 14);
		contentPanel.add(label);
		
		JButton btnCrearOferta = new JButton("Crear oferta");
		btnCrearOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			dispose();
		new GUICrearOferta(logicaNegocio,guiUsuario,guiPropietario,propietario);
			}
		});
		btnCrearOferta.setOpaque(false);
		btnCrearOferta.setBounds(874, 118, 162, 53);
		contentPanel.add(btnCrearOferta);
		
		JButton btnRegistrarPagos = new JButton("Registrar Pagos");
		btnRegistrarPagos.setOpaque(false);
		btnRegistrarPagos.setBounds(874, 182, 162, 53);
		contentPanel.add(btnRegistrarPagos);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(GUIPropietario.class.getResource("/imagenes/granjosIco.png")));
		label_1.setBounds(939, 534, 97, 48);
		contentPanel.add(label_1);

		
		JButton button = new JButton("Desconectarse");
		button.setOpaque(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					new GUIUsuario(logicaNegocio);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button.setBounds(455, 11, 127, 27);
		contentPanel.add(button);	
		

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GUIUsuario.class.getResource("/imagenes/fondo.jpg")));
		lblNewLabel.setBounds(0, 0, 1046, 621);
		contentPanel.add(lblNewLabel);	
		this.setVisible(true);

	}


	
	public class CasasList extends DefaultTableModel{
		public CasasList(String[][] a, String[]b){
			super(a,b);	
		}
	   public boolean isCellEditable (int row, int column){
	       return false;
	   }
	}
	public void actualizarTablaPropietario(CasaRural cr){
		
		String contenido[]={String.valueOf(cr.getId()),String.valueOf(cr.getPropietario().getNombre()),cr.getCiudad(),String.valueOf(cr.getnHabitaciones()),
				 String.valueOf(cr.getnBaños()),String.valueOf(cr.getnCocinas()),String.valueOf(cr.getnSalones()),String.valueOf(cr.getnAparcamientos())};
		casasList.addRow(contenido);
		
		
	}
	
public void actualizarFilaPropietario(CasaRural cr,int fila){
		
		String contenido[]={String.valueOf(cr.getId()),String.valueOf(cr.getPropietario().getNombre()),cr.getCiudad(),String.valueOf(cr.getnHabitaciones()),
				 String.valueOf(cr.getnBaños()),String.valueOf(cr.getnCocinas()),String.valueOf(cr.getnSalones()),String.valueOf(cr.getnAparcamientos())};
		casasList.removeRow(fila);
		casasList.addRow(contenido);
		
	}
}
