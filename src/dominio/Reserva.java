package dominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Reserva implements Serializable {
	Oferta oferta;
	String estadoPago; // pendiente(a la espera de que le paguen), pagado(realizado el pago) o bloqueado(alguien realizó una reserva antes)
	Cliente cliente;
	
	
	public Reserva(Oferta o, Cliente c){
		
		this.oferta = o;
		this.cliente = c;
		this.estadoPago = "pendiente";
		System.out.println("Reserva creada");
		
	}
	
	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public Oferta getOferta(){
		return oferta;
	}
	
	public void setOferta(Oferta o){
		this.oferta = o;
	}
	
	public Oferta getCliente(){
		return oferta;
	}
	
	public void setCliente(Cliente c){
		this.cliente = c;
	}
	
	public void cambiarPendientePago(){
		this.estadoPago = "pendiente";
	}
	
	public void cambiarPagado(){
		this.estadoPago = "pagado";
	}
	
	public void cambiarBloqueado(){
		this.estadoPago = "bloqueado";
	}
	
	@Override
	public String toString() {
		return "Oferta=" + this.oferta.idOferta + ", Cliente=" + this.cliente.email
				+ ", Estado=" + this.estadoPago;
	}

}
