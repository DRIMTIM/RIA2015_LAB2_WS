package uy.com.jatrik.data;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Dado implements Serializable{
	
	private static final long serialVersionUID = -187833057977742708L;
	private final static int _valorMinimo = 1;
	private final static int _valorMaximo = 100;
	
	public static int tirar() {
		return ThreadLocalRandom.current().nextInt(_valorMinimo,_valorMaximo);
	}
	
	public static int tirar(int valorMinimo, int valorMaximo){
		return ThreadLocalRandom.current().nextInt(valorMinimo, valorMaximo);
	}
	
}
