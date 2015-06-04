package uy.com.jatrik.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.jatrik.entities.Equipo;
import uy.com.jatrik.entities.Jugador;


public interface EquipoService {

	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_JSON)
	List<Equipo> getListaEquipos();
	
	@GET
	@Path("detalle/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	List<Jugador> getJugadoresEquipo(@PathParam(value = "id") Long id);
	
	@GET
	@Path("detalle/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	List<Equipo> getEquipos(@PathParam(value = "key") String key);	
	
}
