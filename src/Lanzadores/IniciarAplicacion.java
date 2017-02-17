package Lanzadores;

import java.rmi.*;

import logicaNegocio.AccesManagerDBInterface;
import presentacion.GUIInicio;

public class IniciarAplicacion {


	public static final int numPuerto=2000;
	public static final String nombreServicio = "casasRurales";
	public static final String maquinaRemota = "127.0.0.1";
	public static final String FICH_POLITICA_SEGURIDAD ="java.policy";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GUIInicio p = new GUIInicio();
		try {
			//colocamos la política
			System.setProperty("java.security.policy",FICH_POLITICA_SEGURIDAD);
			System.setSecurityManager(new RMISecurityManager());
			
						try {
							String url="rmi://"+maquinaRemota+":"+numPuerto+"/"+nombreServicio;
							System.out.println("BUSCANDO con lookup el objeto SERVIDOR RMI: "+url);			
							p.setBusinessLogic((AccesManagerDBInterface)Naming.lookup(url));
		
						}catch (Exception e) {
						// TODO Auto-generated catch block
							System.out.println("Error al conseguir la lógica del negocio: "+ e.toString());						}
						
			p.setVisible(true);

			}catch (Exception e) {
			// TODO Auto-generated catch block
				 System.out.println("Error al lanzar el cliente: "+e.toString());		}		
	}
}
