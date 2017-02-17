package presentacion;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
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
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class GUICrearCasa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AccesManagerDBInterface control;
	private JTextField txtPoblacion;
	private MaskFormatter mascara;
	private JTextArea txtrDescrip;
	private JTextPane txtpnErrores;
	private GUIUsuario guiUsuarios;
	private GUICrearCasa guiCrearCasa;
	private File imagen;
	private JTextField textField_calle;
	private JTextField textField_numCasa;

	public GUICrearCasa(AccesManagerDBInterface logicaNegocio,final GUIUsuario guiUsuario,final GUIPropietario guiPropietario, final Propietario propietario) {
		control=logicaNegocio;
		System.out.println("Iniciada GUI GUICrearCasa...");
		setBounds(100, 100, 465, 582);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		final JSpinner nApar = new JSpinner();
		nApar.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		nApar.setBounds(241, 270, 45, 17);
		getContentPane().add(nApar);
		
		final JSpinner nSal = new JSpinner();
		nSal.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		nSal.setBounds(241, 242, 45, 20);
		getContentPane().add(nSal);
		
		final JSpinner nCoc = new JSpinner();
		nCoc.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		nCoc.setBounds(241, 220, 45, 17);
		getContentPane().add(nCoc);
		
		final JSpinner nBan = new JSpinner();
		nBan.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		nBan.setBounds(241, 183, 45, 20);
		getContentPane().add(nBan);
		
		final JSpinner nHabit = new JSpinner();
		nHabit.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		nHabit.setBounds(241, 161, 45, 17);
		getContentPane().add(nHabit);
		contentPanel.setBounds(0, 0, 457, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		guiUsuarios=guiUsuario;
		guiCrearCasa=this;
		
		final JLabel n1 = new JLabel("New label");
		n1.setBounds(276, 71, 19, 14);
		n1.setVisible(false);
		n1.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		getContentPane().add(n1);
		
		final JLabel n2 = new JLabel("New label");
		n2.setBounds(296, 164, 17, 14);
		n2.setVisible(false);
		n2.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		getContentPane().add(n2);
		
		final JLabel n3 = new JLabel("New label");
		n3.setBounds(296, 189, 17, 14);
		n3.setVisible(false);
		n3.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		getContentPane().add(n3);
		
		final JLabel n4 = new JLabel("New label");
		n4.setBounds(296, 223, 17, 14);
		n4.setVisible(false);
		n4.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		getContentPane().add(n4);

		final JLabel n5 = new JLabel("New label");
		n5.setBounds(296, 248, 17, 14);
		n5.setVisible(false);
		n5.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		getContentPane().add(n5);
		
		final JLabel n6 = new JLabel("New label");
		n6.setBounds(150, 317, 19, 14);
		n6.setVisible(false);
		n6.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		getContentPane().add(n6);
		
		
		final JLabel n7 = new JLabel("");
		n7.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		n7.setBounds(296, 273, 17, 14);
		n7.setVisible(false);
		getContentPane().add(n7);
	
		final JLabel n8 = new JLabel("");
		n8.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		n8.setBounds(249, 118, 17, 14);
		getContentPane().add(n8);
		
		final JLabel n9 = new JLabel("");
		n9.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/cross.png")));
		n9.setBounds(408, 118, 24, 14);
		getContentPane().add(n9);
		
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(50, 316, 90, 14);
		lblDescripcin.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblDescripcin);
		
		txtPoblacion = new JTextField();
		txtPoblacion.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtPoblacion.setBackground(new Color(255, 255, 153));
		txtPoblacion.setBounds(110, 68, 129, 20);
		getContentPane().add(txtPoblacion);
		txtPoblacion.setColumns(10);
		
		
		JLabel lblPoblacin = new JLabel("Ciudad:");
		lblPoblacin.setBounds(50, 70, 90, 14);
		lblPoblacin.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblPoblacin);
		
		
		JLabel lblNHabitaciones = new JLabel("Número de habitaciones:");
		lblNHabitaciones.setBounds(50, 164, 158, 14);
		lblNHabitaciones.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblNHabitaciones);
		
		JLabel lblNBaos = new JLabel("Número de baños:");
		lblNBaos.setBounds(50, 195, 158, 14);
		lblNBaos.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblNBaos);
		
		JLabel lblNCocinas = new JLabel("Número de cocinas:");
		lblNCocinas.setBounds(50, 224, 158, 14);
		lblNCocinas.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblNCocinas);
		
		JLabel lblNSalones = new JLabel("Número de salones:");
		lblNSalones.setBounds(50, 249, 145, 14);
		lblNSalones.setFont(new Font("Segoe Print", Font.BOLD, 12));
		getContentPane().add(lblNSalones);
		
	
		txtpnErrores = new JTextPane();
		txtpnErrores.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtpnErrores.setBackground(new Color(255, 255, 153));
		txtpnErrores.setForeground(Color.RED);
		txtpnErrores.setEditable(false);
		txtpnErrores.setBounds(19, 453, 189, 80);
		getContentPane().add(txtpnErrores);
		
		txtrDescrip = new JTextArea();
		txtrDescrip.setWrapStyleWord(true);
		txtrDescrip.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrDescrip.setBackground(new Color(255, 255, 153));
		txtrDescrip.setBounds(19, 341, 393, 101);
		getContentPane().add(txtrDescrip);
		
		
		
		JLabel lblNaparcamientos = new JLabel("Número de aparcamientos:");
		lblNaparcamientos.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblNaparcamientos.setBounds(50, 275, 189, 14);
		getContentPane().add(lblNaparcamientos);
		
		
		JLabel label_calle = new JLabel("Calle:");
		label_calle.setFont(new Font("Segoe Print", Font.BOLD, 12));
		label_calle.setBounds(50, 118, 45, 14);
		getContentPane().add(label_calle);
		
		textField_calle = new JTextField();
		textField_calle.setColumns(10);
		textField_calle.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_calle.setBackground(new Color(255, 255, 153));
		textField_calle.setBounds(110, 116, 129, 20);
		getContentPane().add(textField_calle);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(new Font("Segoe Print", Font.BOLD, 12));
		lblNmero.setBounds(287, 118, 64, 14);
		getContentPane().add(lblNmero);
		
		textField_numCasa = new JTextField();
		textField_numCasa.setColumns(10);
		textField_numCasa.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_numCasa.setBackground(new Color(255, 255, 153));
		textField_numCasa.setBounds(361, 116, 37, 20);
		getContentPane().add(textField_numCasa);
		
		
		JButton btnCrear = new JButton("CREAR");
		btnCrear.setOpaque(false);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String Poblacion=txtPoblacion.getText();
				String Habitaciones=nHabit.getValue().toString();
				String Baños=(String) nBan.getValue().toString();
				String Cocinas = (String) nCoc.getValue().toString();
				String Salones =(String) nSal.getValue().toString();
				String Aparcamientos=(String) nApar.getValue().toString();
				String nCasa = textField_numCasa.getText();
				String calle= textField_calle.getText();
				String Descripcion= txtrDescrip.getText();
				if(!Poblacion.isEmpty() && !Habitaciones.isEmpty() && !Baños.isEmpty() 
					&& !Cocinas.isEmpty() && !Salones.isEmpty() && !Aparcamientos.isEmpty()
					&& !nCasa.isEmpty() && !calle.isEmpty() && !Descripcion.isEmpty()){
					
						
					CasaRural cr = null;
					try {
						cr = control.crearCasa(propietario, Poblacion, Integer.parseInt(Habitaciones),  Integer.parseInt(Cocinas), 
								Integer.parseInt(Cocinas), Integer.parseInt(Salones), Integer.parseInt(Aparcamientos),Integer.parseInt(nCasa),calle, Descripcion,imagen);
						propietario.add(cr);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					dispose();
					guiPropietario.actualizarTablaPropietario(cr);
					//guiUsuario.actualizarTablaUsuario(cr);
					guiPropietario.setVisible(true);
		
				}else{
					txtpnErrores.setText("Faltan campos por completar!!");
					//Muestra las imagenes X (campos incorrectos).
					if(Poblacion.isEmpty()){n1.setVisible(true);}else n1.setVisible(false);
					if(Habitaciones.isEmpty()){n2.setVisible(true);}else n2.setVisible(false);
					if(Baños.isEmpty()){n3.setVisible(true);}else n3.setVisible(false);
					if(Cocinas.isEmpty()){n4.setVisible(true);}else n4.setVisible(false);
					if(Salones.isEmpty()){n5.setVisible(true);}else n5.setVisible(false);
					if(Aparcamientos.isEmpty()){n7.setVisible(true);}else n7.setVisible(false);
					if(nCasa.isEmpty()){n8.setVisible(true);}else n8.setVisible(false);
					if(calle.isEmpty()){n9.setVisible(true);}else n9.setVisible(false);
					if(Descripcion.isEmpty()){n6.setVisible(true);}else n6.setVisible(false);

					
				}
			}
		});
		btnCrear.setBounds(276, 455, 130, 58);
		getContentPane().add(btnCrear);
		

		
		JLabel lblRellenar = new JLabel("Rellene los siguientes campos para crear una casa");
		lblRellenar.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRellenar.setBounds(29, 12, 383, 27);
		getContentPane().add(lblRellenar);
		
		JButton btnAadirImagen = new JButton("A\u00F1adir Imagen");
		btnAadirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new GUISeleccionarImagen(guiCrearCasa);
			}
		});
		btnAadirImagen.setBounds(306, 308, 126, 23);
		getContentPane().add(btnAadirImagen);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GUICrearCasa.class.getResource("/imagenes/fondo.jpg")));
		label.setBounds(0, 0, 457, 544);
		getContentPane().add(label);
		
		setVisible(true);
	}

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
		System.out.println("Imgenes guardada: "+this.imagen);
	}
}
