package uy.com.jatrik.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Equipo implements Serializable{
	
	private static final long serialVersionUID = 4818888650671043533L;
	
	private Long id;
	private String nombre;
	private String urlImage;
	private String fechaCreacion;
	private List<Jugador> jugadores;
	
	public Equipo() { 
		jugadores = new ArrayList<>();
	} 
	
	public Equipo(Long id, String nombre, String urlImage, String fechaCreacion) {
		jugadores = new ArrayList<>();
		this.nombre = nombre;
		this.urlImage = urlImage;
		this.fechaCreacion = fechaCreacion;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public void addAllJugadores(Jugador... args) {
		for(Jugador j : args) {
			jugadores.add(j);
		}
	}
	
	public boolean addJugador(Jugador j) {
		return jugadores.add(j);
	}
	
	public Jugador findJugador(Long id) {
		return jugadores.stream()
				.filter(i-> i.getId().equals(id))
				.findFirst().orElse(null);
				
	}
	
	
}
