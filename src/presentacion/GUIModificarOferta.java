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
import dominio.Oferta;
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


public class GUIModificarOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AccesManagerDBInterface control;
	private Date inicio = new Date();
	private Date fin= new Date();
	private JTextField txtPrecio;
	private DefaultListModel casas = new DefaultListModel();
	private Collection listacasas;


	public GUIModificarOferta(AccesManagerDBInterface logicaNegocio,final GUIPropietarioInfoCasa guiPropietarioInfoCasa,final Oferta o) {
		control=logicaNegocio;
		System.out.println("Iniciada GUI GUIModificar Oferta...");
		setVisible(true);
		setBounds(100, 100, 465, 427);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		final JTextPane txtError = new JTextPane();
		txtError.setEditable(false);
		txtError.setBounds(20, 267, 204, 102);
		getContentPane().add(txtError);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblPrecio.setBounds(90, 227, 79, 14);
		getContentPane().add(lblPrecio);
		
		final JDateChooser fechaFin = new JDateChooser();
		fechaFin.setDateFormatString("dd/MMM/yyyy");
		fechaFin.setBounds(259, 136, 143, 33);
		getContentPane().add(fechaFin);
		
		final JDateChooser fechaInicio = new JDateChooser();
		fechaInicio.setDateFormatString("dd/MMM/yyyy");
		fechaInicio.setBounds(52, 136, 143, 33);
		getContentPane().add(fechaInicio);
		
	
		txtPrecio = new JTextField();
		txtPrecio.setBounds(183, 222, 86, 26);
		getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
			
		JLabel lblSeleccioneDaDe = new JLabel("Seleccione d\u00EDa de inicio y de fin de la oferta y su precio\r\n");
		lblSeleccioneDaDe.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblSeleccioneDaDe.setBounds(20, 23, 429, 59);
		getContentPane().add(lblSeleccioneDaDe);
		
		
		JLabel lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblFechaFin.setBounds(286, 111, 66, 14);
		getContentPane().add(lblFechaFin);
		
		
		JLabel lblPoblacin = new JLabel("Fecha inicio:");
		lblPoblacin.setBounds(70, 111, 79, 14);
		lblPoblacin.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblPoblacin);
		
		
		JButton btnModificarOferta = new JButton("Modificar Oferta");
		btnModificarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(fechaInicio.getDate()==null||fechaFin==null|| txtPrecio.getText().isEmpty()){
					txtError.setText("No has seleccionado/rellenado todos los campos");
					
				}else{
				System.out.println(inicio.toString());
				
			    Date inicioOferta = fechaInicio.getDate();
			    Date finOferta = fechaFin.getDate();
			    
				Oferta ofer = null;
				try {
					ofer = control.modificarOferta(o,inicioOferta,finOferta,Integer.parseInt(txtPrecio.getText()));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(ofer==null){
					
						txtError.setText("Las fechas no son válidas. Seleccione otras fechas");
					
				}else{
						System.out.println(ofer.toString());
						txtPrecio.setText("");
						ofer.getCasa().getListaOfertas().remove(o);
						dispose();
						guiPropietarioInfoCasa.setVisible(true);
				}
			}
			}
		});
		
		
		btnModificarOferta.setBounds(259, 288, 168, 52);
		getContentPane().add(btnModificarOferta);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUIModificarOferta.class.getResource("/imagenes/fondo.jpg")));
		label.setBounds(0, 0, 449, 392);
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
