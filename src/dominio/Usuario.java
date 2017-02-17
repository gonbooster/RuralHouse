package dominio;

import java.io.Serializable;

public class Usuario implements Serializable{

	
	String nombre;
	String contraseña;
	String email; //Email del usuario (cliente o propietario), será nuestra ID, que, permitirá que no exista más de un usuario repetido;
	
	
	public Usuario( String nombre, String contraseña) {
		
		this.nombre = nombre;
		this.contraseña = contraseña;
	}
	
	public Usuario( String nombre, String contraseña, String email) {
	
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
	

}
