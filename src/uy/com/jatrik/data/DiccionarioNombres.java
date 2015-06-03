package uy.com.jatrik.data;

import java.util.Arrays;
import java.util.List;

public class DiccionarioNombres {

	private final static List<String> nombres = Arrays.asList("Pepe", "Diego",
			"Anthony", "Ignacio", "Roberto", "Rob", "Rodolfo", "Daniel",
			"Pedro", "Lionel", "Luis", "Dondo", "Mauro");
	private final static List<String> apellidos = Arrays.asList("Messi",
			"Davidson", "Kluviert", "Pedrofilo", "Suarez", "Pacheco", "Stark",
			"Targarien", "Gomez", " Gomeza", "Peruano");
	
	public static String randomNombre() {
		int lastIndex = nombres.size() -1 ;
		return nombres.get(Dado.tirar(0, lastIndex));
	}
	
	public static String randomApellido() {
		int lastIndex = apellidos.size() -1 ;
		return apellidos.get(Dado.tirar(0, lastIndex));
	}
}
