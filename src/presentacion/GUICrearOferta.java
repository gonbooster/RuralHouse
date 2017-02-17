package presentacion;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import dominio.CasaRural;
import dominio.Propietario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;


public class GUICrearOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AccesManagerDBInterface control;
	private Date inicio = new Date();
	private Date fin= new Date();
	private JTextField txtPrecio;
	private DefaultListModel casas = new DefaultListModel();
	private Collection listacasas;


	public GUICrearOferta(final AccesManagerDBInterface logicaNegocio,final GUIUsuario guiUsuario,final GUIPropietario guiPropietario,final Propietario propietario) {
		control=logicaNegocio;
		System.out.println("Iniciada GUI GUICrearCasa...");
		setVisible(true);
		setBounds(100, 100, 465, 590);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		final JDateChooser fechaFin = new JDateChooser();
		fechaFin.setBounds(245, 290, 182, 102);
		getContentPane().add(fechaFin);
		
		final JDateChooser fechaInicio = new JDateChooser();
		fechaInicio.setBounds(27, 290, 182, 102);
		getContentPane().add(fechaInicio);
		
		JLabel label_1 = new JLabel("\u20AC");
		label_1.setFont(new Font("Segoe Print", Font.BOLD, 12));
		label_1.setBounds(375, 457, 15, 14);
		getContentPane().add(label_1);
		
		final JTextPane txtError = new JTextPane();
		txtError.setEditable(false);
		txtError.setBounds(25, 439, 204, 102);
		getContentPane().add(txtError);
		
		JLabel lblPrecio = new JLabel("Precio del paquete:");
		lblPrecio.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblPrecio.setBounds(242, 427, 148, 14);
		getContentPane().add(lblPrecio);
		
				
				
		final JList list = new JList();

		list.setModel(casas);
		list.setBounds(1, 1, 412, 146);
		list.setCellRenderer(new MyListRenderer());
		getContentPane().add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(25, 62, 414, 117);
		getContentPane().add(scrollPane);
		
	
		txtPrecio = new JTextField();
		txtPrecio.setBounds(276, 452, 86, 26);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		listacasas=propietario.getCasasRurales();
		for (Object v : listacasas)  casas.addElement(v);
		
		JLabel lblSeleccioneDaDe = new JLabel("Seleccione d\u00EDa de inicio y de fin de la oferta y su precio\r\n");
		lblSeleccioneDaDe.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblSeleccioneDaDe.setBounds(10, 183, 429, 59);
		getContentPane().add(lblSeleccioneDaDe);
		
		
		JLabel lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblFechaFin.setBounds(346, 239, 66, 14);
		getContentPane().add(lblFechaFin);
		
		
		JLabel lblPoblacin = new JLabel("Fecha inicio:");
		lblPoblacin.setBounds(25, 239, 79, 14);
		lblPoblacin.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblPoblacin);
		
		
		JButton btnCrearOferta = new JButton("Crear Oferta");
		btnCrearOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(list.isSelectionEmpty()||fechaInicio.getDate()==null||fechaFin.getDate()==null|| txtPrecio.getText().isEmpty()){
					txtError.setText("No has seleccionado/rellenado todos los campos");
					
				}else{
				System.out.println(inicio.toString());

			    Date inicioOferta = fechaInicio.getDate();
			    Date finOferta = fechaFin.getDate();
			    CasaRural seleccionada= (CasaRural) list.getSelectedValue();
				boolean creada = false;
				try {
					creada = control.crearOferta(seleccionada.getNumOfertas()+1,seleccionada,inicioOferta,finOferta,Integer.parseInt(txtPrecio.getText()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!creada){
					
						txtError.setText("Las fechas no son válidas. Seleccione otras fechas");
					
				}else{
						txtPrecio.setText("");
						dispose();
						new GUIPropietario(logicaNegocio,guiUsuario, propietario);
				}
			}
			}
		});
		
		
		btnCrearOferta.setBounds(259, 489, 168, 52);
		getContentPane().add(btnCrearOferta);
				
		JLabel lblRellenar = new JLabel("Seleccione la casa rural\r\n");
		lblRellenar.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRellenar.setBounds(128, 25, 190, 33);
		getContentPane().add(lblRellenar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUICrearOferta.class.getResource("/imagenes/fondo.jpg")));
		label.setBounds(0, 0, 449, 552);
		getContentPane().add(label);
		contentPanel.setBounds(0, 0, 457, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
			
		
		
	}
	
	
	
	
    private class MyListRenderer extends DefaultListCellRenderer     
    {     
              
      
        public Component getListCellRendererComponent( JList list,  Object value, int index, boolean isSelected, boolean cellHasFocus )     
        {     
        	
        super.getListCellRendererComponent(list, value, index,isSelected, cellHasFocus );     
      
         setText("ID:"+((CasaRural) value).getId()+" Ciudad: "+((CasaRural) value).getCiudad()+" Habitaciones: "+((CasaRural) value).getnHabitaciones());  
               
         	return this ;     
        }     
    }   
}
