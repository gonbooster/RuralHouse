package dominio;

import java.awt.Image;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;

public class CasaRural implements Serializable {
	

	Propietario propietario;
	int id;
	String ciudad;
	int nHabitaciones;
	int nBaños;
	int nCocinas;
	int nSalones;
	int nAparcamientos;
	int nCasa;
	String calle;
	String descripcion;
	Collection<Oferta> listaOfertas;
	Collection<Comentario> Comentarios;
	int numOfertas;
	File imagen;
	
	@Override
	public String toString() {
		return "CasaRural [id=" + id + ", ciudad=" + ciudad
				+ ", nHabitaciones=" + nHabitaciones + ", nBaños=" + nBaños
				+ ", nCocinas=" + nCocinas + ", nSalones=" + nSalones
				+ ", nAparcamientos=" + nAparcamientos + ", nCasa=" + nCasa
				+ ", calle=" + calle + ", descripcion=" + descripcion
				+ ", listaOfertas=" + listaOfertas + ", listaComentarios="
				+ Comentarios + ", numOfertas=" + numOfertas + ", imagen="
				+ imagen + "]";
	}

	
	public CasaRural(int id, String ciudad, int nHabitaciones,int nBaños,
			int nCocinas, int nSalones, int nAparcamientos, int nCasa, String calle,String descripcion,File imagen) {
		super();
		this.ciudad = ciudad;
		this.nHabitaciones = nHabitaciones;
		this.nBaños = nBaños;
		this.nCocinas = nCocinas;
		this.nSalones = nSalones;
		this.nAparcamientos = nAparcamientos;
		this.nCasa = nCasa;
		this.calle = calle;
		this.descripcion = descripcion;
		this.id=id;
		this.listaOfertas = new ArrayList<Oferta>();
		this.numOfertas=0;
		System.out.println("Imagen colocada a la casa :+"+imagen);
		this.imagen=imagen;		
		System.out.println(this.toString());
	}

	
	public Propietario getPropietario() {
		return propietario;
	}


	public void setPropietario(Propietario propietario) {
		propietario.add(this);
		this.propietario=propietario;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public int getnHabitaciones() {
		return nHabitaciones;
	}


	public void setnHabitaciones(int nHabitaciones) {
		this.nHabitaciones = nHabitaciones;
	}


	public int getnBaños() {
		return nBaños;
	}


	public void setnBaños(int nBaños) {
		this.nBaños = nBaños;
	}


	public int getnCocinas() {
		return nCocinas;
	}


	public void setnCocinas(int nCocinas) {
		this.nCocinas = nCocinas;
	}


	public int getnSalones() {
		return nSalones;
	}


	public void setnSalones(int nSalones) {
		this.nSalones = nSalones;
	}


	public int getnAparcamientos() {
		return nAparcamientos;
	}


	public void setnAparcamientos(int nAparcamientos) {
		this.nAparcamientos = nAparcamientos;
	}


	public int getnCasa() {
		return nCasa;
	}


	public void setnCasa(int nCasa) {
		this.nCasa = nCasa;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Collection<Oferta> getListaOfertas() {
		return listaOfertas;
	}


	public void setListaOfertas(Collection<Oferta> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}


	public Collection<Comentario> getComentarios() {
		return Comentarios;
	}


	public void setListaComentarios(Collection<Comentario> listaComentarios) {
		this.Comentarios = listaComentarios;
	}


	public int getNumOfertas() {
		return numOfertas;
	}


	public void setNumOfertas(int numOfertas) {
		this.numOfertas = numOfertas;
	}


	public File getImagen() {
		return imagen;
	}


	public void setImagen(File imagen) {
		this.imagen = imagen;
	}
	
	public void eliminarOferta(Oferta o){
		listaOfertas.remove(o);
		numOfertas--;
		
	}

}