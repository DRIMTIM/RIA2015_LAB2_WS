package uy.com.jatrik.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import uy.com.jatrik.entities.Equipo;
import uy.com.jatrik.entities.Jugador;

/*Singleton*/
public class DataProvider {

	private List<Equipo> equiposSistema = new ArrayList<>();
	private List<Jugador> jugadoresSistema = new ArrayList<>();
	private List<String> posicionesJugadoresSistema;
	private static DataProvider instance;
	private static final String URL_IMAGEN_PATH = "/images/jugadores/{id}.jpg";
	
	private DataProvider() {
		equiposSistema = new ArrayList<>();
		initData();
	}
	
	private void initData() {
		
		Equipo e1 = createEquipo((long)1,"Peniarol", getBase64Image("/images/equipos/01.jpg"), LocalDateTime.of(1913, 12, 15, 5, 15).toString());
		Equipo e2 = createEquipo((long)2,"Naciomal", "/images/equipos/02.jpg", LocalDateTime.of(1899, 12, 15, 5, 15).toString());
		Equipo e3 = createEquipo((long)3,"Cerro", "/images/equipos/03.jpg", LocalDateTime.of(1922, 12, 15, 5, 15).toString());
		Equipo e4 = createEquipo((long)4,"Defensor", "/images/equipos/04.jpg", LocalDateTime.of(1913, 3, 15, 5, 15).toString());
		Equipo e5 = createEquipo((long)5,"Defensa y Justicia", "/images/equipos/05.jpg", LocalDateTime.of(1500, 6, 3, 2, 22).toString());
		Equipo e6 = createEquipo((long)6,"Torken", "/images/equipos/06.jpg", LocalDateTime.of(1300, 7, 7, 2, 7).toString());
		
		long generatedJugadorId = 0L;
		
		List<Equipo> equipos = Arrays.asList(e1, e2, e3, e4, e5, e6);
		
		this.posicionesJugadoresSistema = generarPosiciones();
		
		for(Equipo e : equipos ) {
			
			boolean salir = false;
			int cont = 0;
				
			while(!salir) {
				
				if(cont > 9) {
					salir = true;
				}
				
				String posicion = posicionesJugadoresSistema.get(cont);
				String urlImagen = URL_IMAGEN_PATH.replace("{id}", String.valueOf(generatedJugadorId));
				
				e.addJugador(createJugador(generatedJugadorId, DiccionarioNombres.randomNombre() + " " + DiccionarioNombres.randomApellido(), 
							 	e, posicion, urlImagen));
				generatedJugadorId++;
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
	
	private Jugador createJugador(Long id, String nombre, Equipo equipo, String posicion, String urlImagen) { 
		
		Jugador jugador = new Jugador(id, nombre, "Uruguay", posicion, urlImagen);
		
		int goles = Dado.tirar(0, 20);
		int rojas = Dado.tirar(0, 1);
		int faltas = Dado.tirar(0, 10);
		int amarillas = Dado.tirar(0, 2);
		int edad = Dado.tirar(18,35);
		
		jugador.buildDatosComplementarios(goles, rojas, faltas, amarillas, edad);
		
		return jugador;
	}
	
	public List<String> generarPosiciones() {
		
		List<String> result = new ArrayList<>();

		result.add("PosicionJugador.PORTERO");
		result.add("Defensor");
		result.add("Defensor");
		result.add("Defensor");
		result.add("Defensor");
		result.add("Mediocampista");
		result.add("Mediocampista");
		result.add("Mediocampista");
		result.add("Mediocampista");
		result.add("Delantero");
		result.add("Delantero");
		
		return result;
	
	}
		
	/*Funciones para los services*/
	
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
	
	public String getBase64Image(String ruta) {

	       File file = new File("/home/pelubook/workspace/RIA2015_LAB2_WS/src/uy/com/jatrik/data/images/equipos/01.jpeg");
	       String imageDataString = null;
	       
	        try {            
	            // Reading a Image file from file system
	            FileInputStream imageInFile = new FileInputStream(file);
	            byte imageData[] = new byte[(int) file.length()];
	            imageInFile.read(imageData);
	 
	            // Converting Image byte array into Base64 String
	            imageDataString = encodeImage(imageData);
	            
	            System.out.println(imageDataString);
	            System.out.println("Image Successfully Manipulated!");
	            
	        } catch (FileNotFoundException e) {
	            System.out.println("Image not found" + e);
	        } catch (IOException ioe) {
	            System.out.println("Exception while reading the Image " + ioe);
	        }
	        
	        return imageDataString;

	}
	
    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray) {
        return Base64.getEncoder().encodeToString(imageByteArray);
    }
 
    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return Base64.getDecoder().decode(imageDataString);
    }	
	
	
}
