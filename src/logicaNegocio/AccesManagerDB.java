package logicaNegocio;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;

import com.db4o.*;

import accesoBD.DBManager;
import dominio.CasaRural;
import dominio.Comentario;
import dominio.EnviaEmail;
import dominio.Oferta;
import dominio.Propietario;
import dominio.Cliente;
import dominio.Reserva;
import dominio.Usuario;

public class AccesManagerDB extends UnicastRemoteObject implements AccesManagerDBInterface {
	
	public static final String FICH_POLITICA_SEGURIDAD ="java.policy";
	public static final int numPuerto=2000;
	public static final String nombreServicio = "casasRurales";
	
	public AccesManagerDB() throws RemoteException {
		DBManager.openDatabase();
		DBManager.closeDB();
	}

	//Devuelve el tipo de usuario introducido (cliente o propietario).
	public String tipoUsuario(String email, String contraseña) { 
		
		try {
			DBManager.openDatabase();

			ObjectSet<Propietario> resultp = DBManager.getDB().queryByExample(
					new Propietario(null, contraseña, email, 0));

			if (!resultp.isEmpty()) {
				DBManager.closeDB();
				return "propietario";
			}

			ObjectSet<Cliente> resultc = DBManager.getDB().queryByExample(
					new Cliente(null, contraseña, email));

			if (!resultc.isEmpty()) {
				DBManager.closeDB();
				return "cliente";
			}
		} catch (Exception e) {
			System.out.println("Error al comprobar el tipo de usuario");
		}
		DBManager.closeDB();
		return "no registrado";
	}
	
	//Devuelve el propietario almacenado en la bd con el email insertado.
	public Propietario getPropietario(String email) {
		try {
			DBManager.openDatabase();
			Propietario user = new Propietario(null, null, email, 0);
			List<Propietario> resultU = DBManager.getDB().queryByExample(user);
			Propietario p = resultU.get(0);
			DBManager.closeDB();
			return p;
		} catch (Exception e) {
			System.out.println("Error al obtener propietario de un email: "
					+ e.toString());
		}
		DBManager.closeDB();
		return null;
	}
	
	//Devuelve el propietario almacenado en la bd con el nombre insertado.
	public Propietario getPropietarioPorNombre(String nombre) {
		try {
			DBManager.openDatabase();
			Propietario user = new Propietario(nombre, null, null, 0);
			List<Propietario> resultU = DBManager.getDB().queryByExample(user);
			Propietario p = resultU.get(0);
			DBManager.closeDB();
			return p;
		} catch (Exception e) {
			System.out
					.println("Error al obtener el propietario a partir de un nombre: "
							+ e.toString());
		}
		DBManager.closeDB();
		return null;
	}

	//Devuelve el cliente almacenado en la bd con el email insertado.
	public Cliente getCliente(String email) {
		try {
			DBManager.openDatabase();
			Cliente user = new Cliente(null, null, email);
			List<Cliente> resultU = DBManager.getDB().queryByExample(user);
			Cliente c = resultU.get(0);
			DBManager.closeDB();
			return c;
		} catch (Exception e) {
			System.out
					.println("Error al obtener el cliente a partir de un email: "
							+ e.toString());
		}
		DBManager.closeDB();
		return null;
	}

	//Devuelve true si existe en la bd el usuario insertado, sino devuelve false.
	public boolean existeUsuario(String email, String password) { 		
		try {
			DBManager.openDatabase();
			System.out.println(email+"//"+password);
			
			ObjectSet<Usuario> resultU = DBManager.getDB().queryByExample(new Usuario(null, password, email));
			System.out.println(resultU.size());
			if (String.valueOf(resultU.size()).equals("0")){
				System.out.println("");
				DBManager.closeDB();
			return false;
			}
		} catch (Exception e) {
			System.out.println("Error al comprobar si existe el usuario: "
					+ e.toString());
		}
		DBManager.closeDB();
		return true;
	}

	//Guarda un cliente en la bd y lo devuelve.
	public Cliente registrarse(String nombre, String password, String email) { 		
		try {
			DBManager.openDatabase();
			Cliente client = new Cliente(nombre, password, email);
			DBManager.getDB().store(client);
			DBManager.getDB().commit();
			DBManager.closeDB();
			return client;
		} catch (Exception e) {
			System.out.println("Error al registrar cliente: " + e.toString());
		}
		DBManager.closeDB();
		return null;
	}

	//Guarda un propietario en la bd y lo devuelve.
	public Propietario registrarse(String nombre, String password,
			String email, int bankAccount) {		
		try {
			DBManager.openDatabase();
			Propietario propietario = new Propietario(nombre, password, email,
					bankAccount);
			DBManager.getDB().store(propietario);
			DBManager.getDB().commit();
			DBManager.closeDB();
			return propietario;
		} catch (Exception e) {
			System.out.println("Error al registrar propietario: "
					+ e.toString());
		}
		DBManager.closeDB();
		return null;
	}

	//Guarda una casa en la bd (Gracias al .updateDepth(3) también se actualiza las colecciones del objeto propietario).
	public CasaRural crearCasa(Propietario propietario, String ciudad,
			int nHabitaciones, int nBaños, int nCocinas, int nSalones,
			int nAparcamientos, int nCasa, String calle, String descripcion,File imagen) {
		CasaRural cr=null;
		try {
			DBManager.openDatabase();

			List<Propietario> prop = DBManager.getDB().queryByExample(propietario);
			if (!prop.isEmpty()) {
				
				Propietario prop1 = prop.get(0);
				 cr = new CasaRural(prop1.getNumCasas() + 1,
						ciudad, nHabitaciones, nBaños, nCocinas, nSalones,
						nAparcamientos,nCasa, calle,  descripcion,imagen);
				cr.setPropietario(prop1);
				DBManager.getDB().store(prop1);
				DBManager.getDB().commit();
				System.out.println("Casa creada: "+cr.toString());
				System.out.println("asignada a: "+prop1.getNombre());

			}
		} catch (Exception e) {
			System.out.println("Error al crear casa: " + e.toString());
		}

		DBManager.closeDB();
		return cr;

	}

	//Devuelve una colección de casas (Todas las de la bd).
	public Collection<CasaRural> verCasas() { 
		ArrayList<CasaRural> casas = new ArrayList<CasaRural>();
		try {
			DBManager.openDatabase();

			Collection<CasaRural> coleccionCasasRurales = DBManager.getDB()
					.query(CasaRural.class);
			for (CasaRural v : coleccionCasasRurales) {

				casas.add(v);
			}
			DBManager.closeDB();
			return casas;

			
		} catch (Exception e) {
			System.out.println("Error al publicar todas las casas: "
					+ e.toString());
		}
		DBManager.closeDB();
		return casas;

	}

	//Devuelve la casas correspondiente al propietario y al identificador insertado.
	public CasaRural verCasas(Propietario p, int id) { 
		try {
			DBManager.openDatabase();

			List<Propietario> prop = DBManager.getDB().queryByExample(p);
			if (!prop.isEmpty()) {

				Propietario prop1 = prop.get(0);
				System.out.println(prop1.toString());
				Collection<CasaRural> casas = prop1.getCasasRurales();
				for (CasaRural v : casas) {
					if (v.getId()==id) {
						DBManager.closeDB();
						return v;
					}

				}
			}
		} catch (Exception e) {
			System.out.println("Error al publicar una casa de un propietario: "
					+ e.toString());
		}
		DBManager.closeDB();

		return null;

	}
	
	//Actualiza la casa dada.
	public CasaRural updateCasaRural(CasaRural casaRural, String ciudad,
			int nHabitaciones, int nBaños, int nCocinas, int nSalones,
			int nAparcamientos, String descripcion) {
		
		try {
			DBManager.openDatabase();
			List<CasaRural> resultCasa = DBManager.getDB().queryByExample(casaRural);
			 if (resultCasa.isEmpty())
				 System.out.println("No se han encontrado elementos en la base de datos"); 
			 
			else { 
				CasaRural toUpdate = (CasaRural) resultCasa.get(0);
				toUpdate.setCiudad(ciudad);
				toUpdate.setnHabitaciones(nHabitaciones);
				toUpdate.setnBaños(nBaños);
				toUpdate.setnCocinas(nCocinas);
				toUpdate.setnSalones(nSalones);
				toUpdate.setnAparcamientos(nAparcamientos);
				toUpdate.setDescripcion(descripcion);
				DBManager.getDB().store(toUpdate); 
				DBManager.getDB().commit();
				DBManager.closeDB();
				return toUpdate;
				}
			 	System.out.println(resultCasa + " has been updated"); 
			} catch (Exception e) {
				System.out.println("Error al publicar una casa de un propietario: "
						+ e.toString());
			}
			DBManager.closeDB();
			return null;


	}
	
	public boolean crearOferta(int id, CasaRural c,Date fechaInicio,Date fechaFin,int precio){
		
		
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		try {			
			System.out.println(id+"/"+c.toString()+"/"+fechaInicio.toString()+"/"+fechaFin.toString()+"/"+precio+"\n");
			DBManager.openDatabase();
			List<CasaRural> resultCasa = DBManager.getDB().queryByExample(c);
			 
			if (!resultCasa.isEmpty()){
				 CasaRural aux=resultCasa.get(0);
				 Collection<Oferta> listaOfertas=aux.getListaOfertas();
				 
				 for(Oferta v:listaOfertas){
					 
					 if((fechaInicio.equals(v.getFechaInicio())||(fechaInicio.after(v.getFechaInicio()))) 
						&& (fechaInicio.equals(v.getFechaFin())||(fechaInicio.before(v.getFechaFin())))){
						 
						 DBManager.closeDB();
						 System.out.println(format.format(v.getFechaInicio())+"<--"+format.format(fechaInicio)+"-->"+format.format(v.getFechaFin()));
						 return false;
						 
					 }else if((fechaFin.equals(v.getFechaInicio())||(fechaFin.after(v.getFechaInicio()))) 
						&& (fechaFin.equals(v.getFechaFin())||(fechaFin.before(v.getFechaFin())))){
						 
						 DBManager.closeDB();
						 System.out.println(format.format(v.getFechaInicio())+"<--"+format.format(fechaFin)+"-->"+format.format(v.getFechaFin()));

						 return false;
					 }
						 
						 
				 }
				 if(fechaInicio.after(fechaFin)){
					 DBManager.closeDB();
					 System.out.println("Fecha fin antes que fecha inicio \"IMPOSIBLE\"");
					 return false;		 
				 }
				 Date r =new Date();
				 if(fechaInicio.before(r)){
					 
					 DBManager.closeDB();
					 System.out.println("Fecha inicio antes que hoy \"IMPOSIBLE\"");
					 return false;
				 }
				 
				Oferta o=new Oferta(id, fechaInicio,fechaFin, precio);
				o.setCasa(aux);
				DBManager.getDB().store(aux);
				DBManager.getDB().commit();
				DBManager.closeDB();
				return true;
				 
			 }	
		
			}catch(Exception e){
				System.out.println("Error al crear una oferta: "
						+ e.toString());
			}
		 System.out.println("Error al sacar las ofertas");
		DBManager.closeDB();
		return false;
	}

	public Oferta modificarOferta(Oferta o, Date fechaInicio, Date fechaFin,int Precio) {
		// TODO Auto-generated method stub	
		
	try {
						DBManager.openDatabase();
			List<Oferta> resultOferta = DBManager.getDB().queryByExample(o);
			 
			if (!resultOferta.isEmpty()){
				Oferta oferta=resultOferta.get(0);
				oferta.setFechaInicio(fechaInicio);
				oferta.setFechaFin(fechaFin);
				oferta.setPrecio(Precio);
				DBManager.getDB().store(oferta);
				DBManager.getDB().commit();
				return oferta;
				 
			 }	
		
			}catch(Exception e){
				System.out.println("Error al crear una oferta: "
						+ e.toString());
			}
		
		DBManager.closeDB();
		return null;
	}
	
	public Collection<CasaRural> BuscarCasas(Propietario p, String ciudad, int nHabitaciones,
			int nBanos, int nCocinas, int nSalones, int nAparcamientos) {


		try {
			DBManager.openDatabase();

			List<Propietario> prop = DBManager.getDB().queryByExample(p);
			if (!prop.isEmpty()) {

				Propietario prop1 = prop.get(0);
				System.out.println("PROPIETARIO "+prop1);
				Collection<CasaRural> casas = prop1.getCasasRurales( ciudad,  nHabitaciones, nBanos,  nCocinas,  nSalones,  nAparcamientos, null);
				System.out.println("CASAS "+casas);
				DBManager.closeDB();
				return casas;
			}
		} catch (Exception e) {
			System.out.println("Error al publicar una casa de un propietario: "
					+ e.toString());
		}
		DBManager.closeDB();

		return null;

		
	}

	public Collection<CasaRural> buscarCasa(String ciudad, int nHabitaciones, int nBanos,
			int nCocinas, int nSalones, int nAparcamientos) {

		ArrayList<CasaRural> casas = new ArrayList<CasaRural>();
		try {
			DBManager.openDatabase();
			Collection<CasaRural> coleccionCasasRurales = DBManager.getDB()
					.queryByExample(new CasaRural(0, ciudad,  nHabitaciones,  nBanos,nCocinas,  nSalones,  nAparcamientos, 0, null, null, null));
			for (CasaRural v : coleccionCasasRurales) {

				casas.add(v);
			}
			DBManager.closeDB();
			return casas;

		} catch (Exception e) {
			System.out.println("Error al publicar todas las casas: "
					+ e.toString());
		}
		DBManager.closeDB();
		return casas;	
	}

	//Guarda un comentario en la casa correspondiente
		public Comentario crearComentario(CasaRural casa, String autor, String comentario , Date fecha) {
			Comentario c=null;
			try {
				DBManager.openDatabase();

				List<CasaRural> cr = DBManager.getDB().queryByExample(casa);
				if (!cr.isEmpty()) {
					
					CasaRural casita = cr.get(0);
					 c = new Comentario(autor,fecha,comentario);
					DBManager.getDB().store(casita);
					DBManager.getDB().commit();
				}
			} catch (Exception e) {
				System.out.println("Error al guardar comentario: " + e.toString());
			}

			DBManager.closeDB();
			return c;

		}
	
		//Devuelve los comentarios de la casa dada
		public Collection<Comentario> verComentarios(CasaRural c) {
			Collection<Comentario> coment = null;
			try {
				DBManager.openDatabase();

				List<CasaRural> cr = DBManager.getDB().queryByExample(c);
				if (!cr.isEmpty()) {

					CasaRural casa = cr.get(0);
					System.out.println(casa.toString());
					coment = casa.getComentarios();
				}
			} catch (Exception e) {
				System.out.println("Error al publicar una casa de un propietario: "
						+ e.toString());
			}
			DBManager.closeDB();

			return coment;

		}
		
		public Collection<Reserva> verReservas(Cliente c){
			Collection<Reserva> lres = new ArrayList<Reserva>();
			try{
				DBManager.openDatabase();
				
				ObjectSet<Cliente> lc = DBManager.getDB().queryByExample(c);
				if(!lc.isEmpty()){
					Cliente cliente= lc.get(0);
					System.out.println(cliente.toString());
					lres = cliente.getListaReservas();
				}
			} catch(Exception e){
				System.out.println("Error al obtener el cliente: "+e.toString());
			}
			DBManager.closeDB();
			
			return lres;
		}
		
		public Collection<Reserva> verReservas(Oferta o){
			Collection<Reserva> lres = new ArrayList<Reserva>();
			try{
				DBManager.openDatabase();
				
				ObjectSet<Oferta> lo = DBManager.getDB().queryByExample(o);
				if(!lo.isEmpty()){
					Oferta oferta= lo.get(0);
					System.out.println(oferta.toString());
					lres = oferta.getListaReservas();
				}
			} catch(Exception e){
				System.out.println("Error al obtener el cliente: "+e.toString());
			}
			DBManager.closeDB();
			
			return lres;
		}
		
		public Reserva reservarOferta(Oferta o, Cliente c){
			try{
				
				DBManager.openDatabase();
				
				ObjectSet<Oferta> lo = DBManager.getDB().queryByExample(o);
				if(!lo.isEmpty()){
					Oferta oferta = lo.get(0);
					DBManager.getDB().delete(o);
					Reserva	r = oferta.reservarOferta(c);
					DBManager.getDB().store(r.getOferta().getCasa());
					DBManager.getDB().commit();
					DBManager.closeDB();
					return r;
				}
			}catch(Exception e){
				System.out.println("Error al reservar la oferta: "+e.toString());
			}
			
			DBManager.closeDB();
			
			return null;
		}
		
		public Boolean eliminarCasaRural(CasaRural cr){
			Reserva r = null;
			try{
				DBManager.openDatabase();
				
				ObjectSet<CasaRural> lo = DBManager.getDB().queryByExample(cr);
				
				Collection<Oferta> ofertas = lo.get(0).getListaOfertas();
				
				for(Oferta o: ofertas){
					
					int reservas = o.getNumReservas();
					if(reservas>0){
						System.out.println("La casa tiene ofertas. Borrado cancelado");
						DBManager.closeDB();
						return false;
					}
				}
				
				DBManager.getDB().delete(lo.get(0));
			}catch(Exception e){
				System.out.println("Error al eliminar la casa rural: "+e.toString());
			}
			DBManager.getDB().commit();
			DBManager.closeDB();
			
			return true;
		}
		
		public Boolean eliminarOferta(CasaRural cr,Oferta o){
			Reserva r = null;
			try{
				DBManager.openDatabase();
				ObjectSet<CasaRural> l = DBManager.getDB().queryByExample(cr);
				ObjectSet<Oferta> lo = DBManager.getDB().queryByExample(o);
				DBManager.getDB().delete(l.get(0));

				int reservas = lo.get(0).getNumReservas();
				
					if(reservas>0){
						System.out.println("La oferta tiene reservas. Borrado cancelado");
						DBManager.closeDB();
						return false;
					}else{
				
				DBManager.getDB().delete(lo.get(0));
				CasaRural c =l.get(0);
				c.eliminarOferta(o);
				DBManager.getDB().store(c);
					}
			}catch(Exception e){
				System.out.println("Error al eliminar la oferta: "+e.toString());
			}
			DBManager.getDB().commit();
			DBManager.closeDB();
			System.out.println("Oferta borrada correctamente");
			return true;
		}
		
		public static void main(String[] args) {

			AccesManagerDB objetoServidor;	
			try { 		  		
				System.setProperty("java.security.policy",FICH_POLITICA_SEGURIDAD);
				System.setSecurityManager(new RMISecurityManager());

			objetoServidor = new AccesManagerDB();
			System.out.println("lanzado objeto");

		  try { java.rmi.registry.LocateRegistry.createRegistry(numPuerto); // Equivalente a lanzar RMIREGISTRY
		  } catch (Exception e) 
		      {System.out.println(e.toString()+"\nSe supone que el error es porque el rmiregistry ya estaba lanzado");}
		  
		    String url="//127.0.0.1:"+numPuerto+"/"+nombreServicio;
		    System.out.println("REGISTRANDO con rebind el objeto SERVIDOR RMI: "+url);
			Naming.rebind(url,objetoServidor);
		 	
		  } catch (Exception e) 
			 {System.out.println("Error al lanzar el servidor: "+e.toString());}
		}
		
		public void mostrarMapa(String calle, int numero){
		    try {
		    	System.out.println("https://www.google.es/maps/place/"+calle+"%20"+numero+"%20");
		        Desktop.getDesktop().browse(new URI("https://www.google.es/maps/place/"+calle+"%20"+numero+"%20"));
		    } catch (Exception e) {
		    	System.out.printf("Error de mostrar mapa");
		    }
		}
	
		public void enviarCorreo(String direccion, String asunto, String texto){
		
			new EnviaEmail("losgranjos@gmail.com","losgranjos03",direccion, asunto, texto);
			
		}

}
