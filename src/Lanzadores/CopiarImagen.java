package Lanzadores;
import java.io.*;
    
public class CopiarImagen {
	
    	
  public static void copiar(String sourceFile, String destinationFile) {
	  
    System.out.println("Desde: " + sourceFile);
    System.out.println("Hacia: " + destinationFile);
    
    try {
	    File inFile = new File(sourceFile);
	    File outFile = new File(destinationFile);
	    FileInputStream in = new FileInputStream(inFile);
	    FileOutputStream out = new FileOutputStream(outFile);
	    
	    int c;
	    while( (c = in.read() ) != -1)
			    out.write(c);
			    in.close();
			    out.close();
    } catch(IOException e) {
    System.err.println("Hubo un error de entrada/salida!!!");
    }
    }
}
   