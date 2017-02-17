package logicaNegocio;
import java.io.File;
import java.rmi.*;
import java.util.Collection;
import java.util.Date;

import dominio.CasaRural;
import dominio.Cliente;
import dominio.Comentario;
import dominio.Oferta;
import dominio.Propietario;
import dominio.Reserva;

public interface AccesManagerDBInterface extends Remote {
	
	//Devuelve el tipo de usuario introducido (cliente o propietario).
		public String tipoUsuario(String email, String contraseña) throws RemoteException; 
		
		//Devuelve el propietario almacenado en la bd con el email insertado.
		public Propietario getPropietario(String email) throws RemoteException;
		
		//Devuelve el propietario almacenado en la bd con el nombre insertado.
		public Propietario getPropietarioPorNombre(String nombre) throws RemoteException;
		
		//Devuelve el cliente almacenado en la bd con el email insertado.
		public Cliente getCliente(String email) throws RemoteException;
		
		//Devuelve true si existe en la bd el usuario insertado, sino devuelve false.
		public boolean existeUsuario(String email, String password) throws RemoteException;		
		
		//Guarda un cliente en la bd y lo devuelve.
		public Cliente registrarse(String nombre, String password, String email)  throws RemoteException;		
		
		//Guarda un propietario en la bd y lo devuelve.
		public Propietario registrarse(String nombre, String password,
				String email, int bankAccount) 	throws RemoteException;	
		
		//Guarda una casa en la bd (Gracias al .updateDepth(3) también se actualiza las colecciones del objeto propietario).
		public CasaRural crearCasa(Propietario propietario, String ciudad,
				int nHabitaciones, int nBaños, int nCocinas, int nSalones,
				int nAparcamientos, int nCasa, String calle, String descripcion,File imagen) throws RemoteException;
		//Devuelve una colección de casas (Todas las de la bd).
		public Collection<CasaRural> verCasas() throws RemoteException;
		
		//Devuelve la casas correspondiente al propietario y al identificador insertado.
		public CasaRural verCasas(Propietario p, int id)  throws RemoteException;
			
		//Actualiza la casa dada.
		public CasaRural updateCasaRural(CasaRural casaRural, String ciudad,
				int nHabitaciones, int nBaños, int nCocinas, int nSalones,
				int nAparcamientos, String descripcion) throws RemoteException;
			
		//Crea una oferta
		public boolean crearOferta(int id, CasaRural c,Date fechaInicio,Date fechaFin,int precio) throws RemoteException;
			
		//Modifica la oferta
		public Oferta modificarOferta(Oferta o, Date fechaInicio, Date fechaFin,int Precio) throws RemoteException;
		
		//Busca casas por propietario
		public Collection<CasaRural> BuscarCasas(Propietario p, String ciudad, int nHabitaciones,
				int nBanos, int nCocinas, int nSalones, int nAparcamientos) throws RemoteException;

		//Busca casas
		public Collection<CasaRural> buscarCasa(String ciudad, int nHabitaciones, int nBanos,
				int nCocinas, int nSalones, int nAparcamientos) throws RemoteException;

		//Guarda un comentario en la casa correspondiente
		public Comentario crearComentario(CasaRural casa, String autor, String comentario , Date fecha) throws RemoteException;
		
		//Devuelve los comentarios de la casa dada
		public Collection<Comentario> verComentarios(CasaRural c) throws RemoteException;
			
		//Devuelve las reservas de una cliente
		public Collection<Reserva> verReservas(Cliente c) throws RemoteException;
		
		//Devuelve las reservas de una oferta
		public Collection<Reserva> verReservas(Oferta o) throws RemoteException;
			
		//Reservar oferta
		public Reserva reservarOferta(Oferta o, Cliente c) throws RemoteException;
				
		//Eliminar casa rural
		public Boolean eliminarCasaRural(CasaRural cr) throws RemoteException;
				
		//Eliminar una oferta
		public Boolean eliminarOferta(CasaRural cr,Oferta o) throws RemoteException;
		//Muestra el mapa de la casa rural
		public void mostrarMapa(String calle, int numero) throws RemoteException;
		
		//Manda un correo de verificación de pagod e casa
		public void enviarCorreo(String direccion, String asunto, String texto)throws RemoteException;
}
