package uy.com.jatrik.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uy.com.jatrik.entities.Equipo;
import uy.com.jatrik.entities.Jugador;
/*Singleton*/
public class DataProvider {

	private List<Equipo> equiposSistema = new ArrayList<>();
	private List<Jugador> jugadoresSistema = new ArrayList<>();
	private static DataProvider instance;
	
	
	private DataProvider() {
		equiposSistema = new ArrayList<>();
		initData();
	}
	
	private void initData() {
		
		Equipo e1 = createEquipo((long)1,"Peniarol", "/images/1.jpg", LocalDateTime.of(1913, 12, 15, 5, 15).toString());
		Equipo e2 = createEquipo((long)2,"Nacional", "/images/2.jpg", LocalDateTime.of(1899, 12, 15, 5, 15).toString());
		Equipo e3 = createEquipo((long)3,"Cerro", "/images/3.jpg", LocalDateTime.of(1922, 12, 15, 5, 15).toString());
		Equipo e4 = createEquipo((long)4,"Defensor", "/images/4.jpg", LocalDateTime.of(1913, 3, 15, 5, 15).toString());
		
		List<Equipo> equipos = Arrays.asList(e1,e2,e3,e4);
		
		for(Equipo e : equipos ) {
			
			boolean salir = false;
			int cont = 1;
			
			while(!salir) {
				if(cont > 11) {
					salir = true;
				}
				int edad = Dado.tirar(18, 35);
				e.addJugador(createJugador(DiccionarioNombres.randomNombre() + " " + DiccionarioNombres.randomApellido(), 
							 edad, LocalDateTime.now().minusYears(edad).toString(), e));
				cont++;
			}
			jugadoresSistema.addAll(e.getJugadores());
		}
		equiposSistema.addAll(equipos);
		
	}
	
	public static DataProvider getInstance() {
		if(instance == null) {
			instance = new DataProvider();
		}
		return instance;
	}
	
	private Equipo createEquipo(Long id, String nombre, String urlImage, String fechaCreacion) {
		return new Equipo(id, nombre,urlImage,fechaCreacion);
	}
	
	private Jugador createJugador(String nombre, int edad, String fechaNacimiento, Equipo equipo) { 
		
		Jugador jugador = new Jugador(nombre,edad,fechaNacimiento, /*equipo,*/ "Uruguay");
		
		int ataque = Dado.tirar(0, 100);
		int defensa = Dado.tirar(0, 100);
		int porteria = Dado.tirar(0, 100);
		
		int goles = Dado.tirar(0, 20);
		int rojas = Dado.tirar(0, 1);
		int faltas = Dado.tirar(0, 10);
		int amarillas = Dado.tirar(0, 2);
		
		jugador.buildEstadisticas(ataque, defensa, porteria, goles, rojas, faltas, amarillas);
		
		return jugador;
	}
	
	public List<Equipo> getAllEquipos() {
		return this.equiposSistema;
	}
	
	public List<Jugador> getAllJugadores() {
		return this.jugadoresSistema;
	}
	
	public List<Jugador> getJugadoresEquipo(Long id) {
		final Long idFinal = id;
		Equipo equipo = equiposSistema.stream()
				.filter(e->e.getId()
						.equals(idFinal))
						.findFirst()
						.orElse(null);
		
		return equipo == null ? Collections.emptyList() : equipo.getJugadores();
		
	}
	
	public Equipo getEquipo(Long id) {
		
		final Long idFinal = id;
		Equipo equipo = equiposSistema.stream()
				.filter(e->e.getId()
						.equals(idFinal))
						.findFirst()
						.orElse(null);
		
		return equipo == null ? null : equipo;
		
	}
	
	public Equipo getEquipo(String nombre) {
		
		final String nomFinal = nombre;
		Equipo equipo = equiposSistema.stream()
				.filter(e->e.getId()
						.equals(nomFinal))
						.findFirst()
						.orElse(null);
		
		return equipo == null ? null : equipo;
		
	}	
	
	public List<Equipo> getEquiposPorNom(String nombre) {
		
		final String nomFinal = nombre;
		List<Equipo> equipos = new ArrayList<Equipo>();
		
		for(Equipo e : equiposSistema){
			
			if(e.getNombre().contains(nomFinal)){
				
				equipos.add(e);
				
			}
			
		}
		
		return equipos.isEmpty() ? Collections.emptyList() : equipos;
		
	}	
				
}
