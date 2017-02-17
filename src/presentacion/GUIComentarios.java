package presentacion;

import java.awt.Color;
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

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JScrollBar;


public class GUIComentarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AccesManagerDBInterface control;
	private MaskFormatter mascara;
	private JTextArea txtrDescrip;
	private GUIUsuario guiUsuarios;

	public GUIComentarios(AccesManagerDBInterface logicaNegocio) {
		control=logicaNegocio;
		System.out.println("Iniciada GUI GUICrearCasa...");
		setBounds(100, 100, 465, 582);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 457, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		txtrDescrip = new JTextArea();
		txtrDescrip.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtrDescrip.setBackground(new Color(255, 255, 153));
		txtrDescrip.setBounds(19, 341, 393, 101);
		getContentPane().add(txtrDescrip);
		
		
		JButton btnCrear = new JButton("Enviar");
		btnCrear.setOpaque(false);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCrear.setBounds(276, 455, 130, 58);
		getContentPane().add(btnCrear);
		

		
		JLabel lblRellenar = new JLabel("Rellene los siguientes campos para crear una casa");
		lblRellenar.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblRellenar.setBounds(29, 12, 383, 27);
		getContentPane().add(lblRellenar);
		
		JList list = new JList();
		list.setBounds(19, 61, 420, 268);

		JScrollPane scrollBar = new JScrollPane(list);
		scrollBar.setBounds(422, 61, 17, 268);
		getContentPane().add(scrollBar);
		
		setVisible(true);
	}
}
