package dominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Oferta implements Serializable {
	
	int idOferta;
	CasaRural casa;
	Date fechaInicio;
	Date fechaFin;
	String estado; // Libre o reservado
	int Precio;
	

	Vector<Reserva> listaReservas;
	int numReservas;
	
	public int getPrecio() {
		return Precio;
	}

	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setListaReservas(Vector<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}


	
	public Oferta(int idOferta,Date fechaInicio, Date fechaFin,int precio){
		
		this.idOferta = idOferta;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.Precio=precio;
		this.estado = "libre";
		this.listaReservas = new Vector<Reserva>();
		this.numReservas=0;
		System.out.println("Oferta creada\n");
		
	}
	
	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
		return "FechaInicio=" + format.format(fechaInicio) + ", FechaFin=" + format.format(fechaFin)
				+ ", Estado=" + estado + ", Precio=" + Precio;
	}

	public int getIdOferta(){
		return this.idOferta;
	}
	
	public CasaRural getCasa(){
		return this.casa;
	}
	
	public Date getFechaInicio(){
		return this.fechaInicio;
	}
	
	public void setFechaInicio(Date fecha){
		this.fechaInicio = fecha;
	}
	
	public void setFechaFin(Date fecha){
		this.fechaFin = fecha;
	}
	
	public Date getFechaFin(){
		return this.fechaFin;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public void actualizarDisponibilidad(){
		if(this.listaReservas.size() == 0){
			this.estado = "libre";
		}else{
			this.estado = "reservado";
		}
	}	
	
	public Reserva reservarOferta(Cliente c){
		Reserva r = new Reserva(this, c);
		if(this.estado.equalsIgnoreCase("reservado")){
			r.cambiarBloqueado();
		}else{
			r.cambiarPendientePago();
			this.estado = "reservado";
		}
		this.listaReservas.add(r);
		c.listaReservas.add(r);
		this.numReservas++;
		r.setCliente(c);
		this.actualizarDisponibilidad();
		System.out.println("Reserva asignada\n");
		return r;
	}
	
	public void pagarOferta(){
		if(this.listaReservas.size() != 0){
			
			Reserva r = listaReservas.get(0);
			if(r.estadoPago.equalsIgnoreCase("reservado")){
				r.cambiarPagado();
			}else{
				System.out.println("PAGAR OFERTA ERROR INESPERADO: La primera reserva no estaba con estado 'reservado'.");
			}
			
		}else{
			System.out.println("PAGAR RESERVA: No se realizaron reservas sobre esta oferta.");
		}
		this.actualizarDisponibilidad();
	}
	/*
	public void eliminarReserva(Cliente c){
		Reserva r = new Reserva(this, c);
		if(this.estado.equalsIgnoreCase("reservado") && r.estadoPago.equalsIgnoreCase("pendiente")){
			r.getOferta().liberar();
		}else if(r.estadoPago.equalsIgnoreCase("pendiente") && r.oferta.listaReservas.size() > 0){
			r.oferta.listaReservas.elementAt(0).cambiarPendientePago();
		}
		this.listaReservas.remove(r);
		this.actualizarDisponibilidad();
	}
	*/
	public void setCasa(CasaRural c){
		this.casa=c;
		c.listaOfertas.add(this);
		c.numOfertas++;
		
	}

	public void setPrecio(int precio) {
		this.Precio=precio;	
	}
	
	public Vector<Reserva> getListaReservas(){
		System.out.println("<--reservas");
		return listaReservas;
	}
	public int getNumReservas() {
		return numReservas;
	}


	public void setNumReservas(int numReservas) {
		this.numReservas = numReservas;
	}
	
}
