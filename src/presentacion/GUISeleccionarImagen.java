package presentacion;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.JLabel;

import Lanzadores.CopiarImagen;

public class GUISeleccionarImagen extends JDialog{
	
	private JFileChooser examinador=examinador = new JFileChooser();

	private GUISeleccionarImagen guiSeleccionarImagen;
	private JFileChooser jfcExaminarEntrada=  new JFileChooser();
	private JTextField textField;
	private File imagenSeleccionada;
	
		public GUISeleccionarImagen(final GUICrearCasa guiCrearCasa){
			setModal(true);
			guiSeleccionarImagen=this;
			getContentPane().setLayout(null);
			setBounds(100, 100, 616, 533);
			
			
			final JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBounds(10, 182, 580, 299);
			getContentPane().add(desktopPane);

			JLabel lblRutalDeLa = new JLabel("Ruta de la imagen:");
			lblRutalDeLa.setBounds(25, 117, 130, 35);
			getContentPane().add(lblRutalDeLa);
			
			textField = new JTextField();
			textField.setBounds(139, 120, 394, 28);
			getContentPane().add(textField);
			textField.setColumns(10);

			
			final JButton btnGuardarImagen = new JButton("Guardar imagen");
			btnGuardarImagen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						
						BufferedImage buffered = (BufferedImage) ImageIO.read(imagenSeleccionada);
						File nuevaImagenFile= new File("imagenesCasa/",imagenSeleccionada.getName());
						ImageIO.write(buffered, "",nuevaImagenFile);
						CopiarImagen.copiar(imagenSeleccionada.getAbsolutePath(), "imagenesCasa/"+imagenSeleccionada.getName());
						guiCrearCasa.setImagen(nuevaImagenFile);					

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dispose();
				}
			});
			btnGuardarImagen.setBounds(305, 39, 241, 53);
			getContentPane().add(btnGuardarImagen);
			btnGuardarImagen.setEnabled(false);

			
			
			JButton btnSeleccionarIamgen = new JButton("Seleccionar imagen");
			btnSeleccionarIamgen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagenes", ImageIO.getReaderFileSuffixes());
					examinador.setFileFilter(filtro);
					int returnVal = examinador.showOpenDialog(guiSeleccionarImagen);
					 if (returnVal == JFileChooser.APPROVE_OPTION) {
				            imagenSeleccionada = examinador.getSelectedFile();
				            //This is where a real application would open the file.
				           textField.setText(imagenSeleccionada.getAbsolutePath());
							try {
								BufferedImage imagen = ImageIO.read(imagenSeleccionada);
								desktopPane.setBorder(new PintaImagen(imagen));
							} catch (IOException e) {}
							btnGuardarImagen.setEnabled(true);
				        } else {
				         textField.setText("No ha elegido ninguna iamgen");
							btnGuardarImagen.setEnabled(true);
				        }
				}
			});
			btnSeleccionarIamgen.setBounds(46, 39, 241, 53);
			getContentPane().add(btnSeleccionarIamgen);
			
			
			setVisible(true);

	}
		

		public class PintaImagen implements Border{

			private   BufferedImage image ;

			public PintaImagen(BufferedImage image ) {
				this.image=image;}

			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			int x0 = x+ (width-image.getWidth())/2;
			int y0 = y+ (height-image.getHeight())/2;
			g.drawImage(image,x0,y0,null); }

		    public Insets getBorderInsets(Component c) {
			return new Insets(0,0,0,0);}

		    public boolean isBorderOpaque() {
			return true; }

		}
}
