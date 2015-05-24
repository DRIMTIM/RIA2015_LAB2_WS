package uy.com.jatrik.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Jugador implements Serializable{

	private static final long serialVersionUID = 7791382344698820999L;
	
	private Long id;
	private String nombre;
	private Integer edad;
	private LocalDateTime fechaNacimiento;
	//private Equipo equipo;
	private String nacionalidad;
	
	private Integer ataque;
	private Integer defensa;
	private Integer porteria;
	
	private Integer goles;
	private Integer rojas;
	private Integer faltas;
	private Integer amarillas;

	public Jugador(String nombre, int edad, LocalDateTime fechaNacimiento, /*Equipo equipo,*/ String nacionalidad) {
		this.nombre = nombre;
		this.edad = edad;
		this.fechaNacimiento = fechaNacimiento;
		//this.equipo = equipo;
		this.nacionalidad = nacionalidad;
	}
	
	public Jugador() { 
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
//	public Equipo getEquipo() {
//		return equipo;
//	}
//	public void setEquipo(Equipo equipo) {
//		this.equipo = equipo;
//	}
	public Integer getAtaque() {
		return ataque;
	}
	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}
	public Integer getDefensa() {
		return defensa;
	}
	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}
	public Integer getPorteria() {
		return porteria;
	}
	public void setPorteria(Integer porteria) {
		this.porteria = porteria;
	}
	public Integer getGoles() {
		return goles;
	}
	public void setGoles(Integer goles) {
		this.goles = goles;
	}
	public Integer getRojas() {
		return rojas;
	}
	public void setRojas(Integer rojas) {
		this.rojas = rojas;
	}
	public Integer getFaltas() {
		return faltas;
	}
	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}
	public Integer getAmarillas() {
		return amarillas;
	}
	public void setAmarillas(Integer amarillas) {
		this.amarillas = amarillas;
	}
	
	public void buildEstadisticas(int ataque, int defensa, int porteria, int goles, int rojas, int faltas, int amarillas) {
		
		this.ataque = ataque;
		this.defensa = defensa;
		this.porteria = porteria;
		
		this.goles = goles;
		this.rojas = rojas;
		this.faltas = faltas;
		this.amarillas = amarillas;
		
	}
	
	
	
}
