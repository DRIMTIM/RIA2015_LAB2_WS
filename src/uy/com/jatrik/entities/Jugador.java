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
	private String fechaNacimiento;
	private String nacionalidad;
	private String posicion;
	private String urlImagen;
	
	private Integer goles;
	private Integer rojas;
	private Integer faltas;
	private Integer amarillas;

	public Jugador(Long id, String nombre, String nacionalidad, String posicion, String urlImagen) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.posicion = posicion;
		this.id = id;
		this.urlImagen = urlImagen;
	}
	
	public Jugador() { 
		
	}
	
	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
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
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPosicion() {
		return posicion;
	}
	
	public void setPosicion(String posicion) {
		this.posicion = posicion;
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
	
	public void buildDatosComplementarios(int goles, int rojas, int faltas, int amarillas, int edad) {
		
		this.goles = goles;
		this.rojas = rojas;
		this.faltas = faltas;
		this.amarillas = amarillas;
		this.edad = edad;
		this.fechaNacimiento = LocalDateTime.now().minusYears(edad).toString();
		
	}
	
	
	
}
