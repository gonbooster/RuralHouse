package dominio;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;


public class Propietario extends Usuario implements Serializable{

	
	

	int bankAccount;
	Collection<CasaRural> casasRurales; //Colección de casas pertenecientes al propietario.
	int numCasas; //Indica la cantidad de casas del propietario (id para las casas).
	
	public Propietario(String nombre, String contraseña) {
		super(nombre, contraseña);
	}
	
	public Propietario(String nombre, String contraseña, String email, int bankAccount) {
		super(nombre, contraseña, email);
		this.bankAccount=bankAccount;
		casasRurales = new ArrayList<CasaRural>();
		this.numCasas=0;
	}

	
	public int bankAccount() {
		return bankAccount;
		
	}
	
	public void bankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
		
	}
	
	public void add(CasaRural e){
		casasRurales.add(e);
		numCasas++;
	}
	// Obtiene una lista de casas que coinciden con los valores introducidos.
	public Collection<CasaRural> getCasasRurales(String ciudad, int nHabitaciones, int nBanos,
			int nCocinas, int nSalones, int nAparcamientos, String descripcion) {
		
		Collection<CasaRural> casasRurales2 = new ArrayList<CasaRural>();
		for (CasaRural v : casasRurales) {
			if(v.getCiudad().equalsIgnoreCase(ciudad)
				&& v.getnHabitaciones() == nHabitaciones
				&& v.getnBaños() == nBanos
				&& v.getnCocinas() == nCocinas
				&& v.getnSalones() == nSalones
				&&  v.getnAparcamientos() == nAparcamientos
			)
				casasRurales2.add(v);			
		}
		return casasRurales2;
	}
	
	public Collection<CasaRural> getCasasRurales() {
		return casasRurales;
	}
	
	public void setCasasRurales(Collection<CasaRural> casasRurales) {
		this.casasRurales = casasRurales;
	}

	public int getNumCasas() {
		return numCasas;
	}

	@Override
	public String toString() {
		return "Propietario [bankAccount=" + bankAccount + ", casasRurales="
				+ casasRurales + ", numCasas=" + numCasas + "]";
	}
	public void remove(CasaRural cr){
		
		casasRurales.remove(cr);
		numCasas--;
	}

	
	} 


