package accesoBD;


import java.io.File;

import com.db4o.*;

public class DBManager {

	private static ObjectContainer db;
	private static final String dbFilename = "DBo4/casasrurales.db4o";
	
	
	public static void createDatabase(){
		new File(dbFilename).delete();
		db=Db4o.openFile(Db4o.newConfiguration(), dbFilename);
		db.ext().configure().updateDepth(3); //Actualiza los objetos relacionados en profundidad 3
		db.commit();
		System.out.println("Base de datos creada...");
	}
	
	public static void openDatabase(){
		db=Db4o.openFile(Db4o.newConfiguration(), dbFilename);
		db.ext().configure().updateDepth(3); //Actualiza los objetos relacionados en profundidad 3
	}
	

	public static ObjectContainer getDB(){
		return db;
	}
	
	public static void closeDB(){
		db.close();
	}
	
}
