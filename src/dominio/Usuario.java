package dominio;

import java.io.Serializable;

public class Usuario implements Serializable{

	
	String nombre;
	String contrase�a;
	String email; //Email del usuario (cliente o propietario), ser� nuestra ID, que, permitir� que no exista m�s de un usuario repetido;
	
	
	public Usuario( String nombre, String contrase�a) {
		
		this.nombre = nombre;
		this.contrase�a = contrase�a;
	}
	
	public Usuario( String nombre, String contrase�a, String email) {
	
		this.nombre = nombre;
		this.contrase�a = contrase�a;
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	
	

}
