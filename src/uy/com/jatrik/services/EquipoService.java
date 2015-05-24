package uy.com.jatrik.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import uy.com.jatrik.entities.Equipo;


public interface EquipoService {

	@GET
	@Path("lista")
	@Produces(MediaType.APPLICATION_XML)
	List<Equipo> getListaEquipos();
}
