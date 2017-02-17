package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import logicaNegocio.AccesManagerDB;
import logicaNegocio.AccesManagerDBInterface;

import dominio.Cliente;
import dominio.Reserva;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GUIReservasCliente extends JDialog{

	private JPanel contentPane;
	
	private Collection<Reserva> listReservas;
	private DefaultListModel defaultListModel = new DefaultListModel();
	private static AccesManagerDBInterface controlAcceso;

	public GUIReservasCliente(AccesManagerDBInterface logicaNegocio,Cliente cliente) {
		controlAcceso=logicaNegocio;
		setBounds(100, 100, 635, 431);
		setModal(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			listReservas = controlAcceso.verReservas(cliente);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Reserva v: listReservas){
			defaultListModel.addElement(v);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		JList list = new JList(defaultListModel);
		scrollPane.setViewportView(list);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 595, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(262)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addComponent(btnVolver))
		);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
	}
}
