package dominio;

import java.io.Serializable;
import java.util.Date;

public class Comentario implements Serializable {

	String Autor;
	Date Fecha;
	String Comentario;
	public Comentario(String autor, Date fecha, String comentario) {
		super();
		Autor = autor;
		Fecha = fecha;
		Comentario = comentario;
	}
	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getComentario() {
		return Comentario;
	}
	public void setComentario(String comentario) {
		Comentario = comentario;
	}
	@Override
	public String toString() {
		return "Comentarios [Autor=" + Autor + ", Fecha=" + Fecha
				+ ", Comentario=" + Comentario + "]";
	}
	
	
}
