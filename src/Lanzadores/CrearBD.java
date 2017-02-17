package Lanzadores;

import java.io.File;
import java.util.Date;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import dominio.CasaRural;
import dominio.Cliente;
import dominio.Oferta;
import dominio.Propietario;
import dominio.Reserva;
import accesoBD.DBManager;


public class CrearBD {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBManager.createDatabase();
		ObjectContainer db = DBManager.getDB();
		/*Propietario p1 = new Propietario("Erik", "e", "e", 123456789);
		CasaRural cr = new CasaRural(0,"Donostia", 1, 1, 1, 1, 4,90,"Aizkorri", "esta es una caslasdnosid sodifosidf osidfjos dsdhf asodhf sdo so sdofh souhfosdhf sau sd fsaduifh sd sd sidohòsdfh  fu sd sd`d ofh odsd sodfh sod sod soh so s  ss ",null);
		cr.setPropietario(p1);
		Date a = new Date();
		Oferta t = new Oferta(2,a,a,20);
		Oferta o=new Oferta(1,a,a, 10);
		o.setCasa(cr);
		t.setCasa(cr);
		
		Propietario p2 = new Propietario("gonzalo", "1234","g", 987654321);

		
		 Cliente c1 = new Cliente("David", "1234", "d");
		 Cliente c2 = new Cliente("Otro", "1234", "o");
		 o.reservarOferta(c1);
		 o.reservarOferta(c2);
		 System.out.println(o.getListaReservas().elementAt(0).toString());
		 System.out.println(o.getListaReservas().elementAt(1).toString());
		db.store(p1);		
		db.store(p2);		
		db.store(c1);
		db.store(c2);
		db.commit();
		*/db.close();
		/*System.out.println(p1.toString());*/

	}

}
