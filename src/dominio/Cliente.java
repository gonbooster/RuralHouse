package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Cliente extends Usuario implements Serializable{

	Collection<Reserva> listaReservas;

	public Cliente(String nombre, String contraseņa, String email) {
		super(nombre, contraseņa);
		this.email = email;
		listaReservas = new ArrayList<Reserva>();
		// TODO Auto-generated constructor stub
	}
	
	public Collection<Reserva> getListaReservas(){
		return this.listaReservas;
	}
	
}
