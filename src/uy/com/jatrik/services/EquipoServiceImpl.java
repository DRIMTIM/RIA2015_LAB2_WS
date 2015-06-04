package uy.com.jatrik.services;

import java.util.List;

import javax.ws.rs.Path;

import uy.com.jatrik.entities.Equipo;
import uy.com.jatrik.entities.Jugador;

@Path("equipos")
public class EquipoServiceImpl extends Service implements EquipoService {

	
	public EquipoServiceImpl() {
		super();
	}
	
	@Override
	public List<Equipo> getListaEquipos() {
		return dataProvider.getAllEquipos();
	}

	@Override
	public List<Jugador> getJugadoresEquipo(Long id) {
		return dataProvider.getJugadoresEquipo(id);
	}
	
	@Override
	public List<Equipo> getEquipos(String key) {
		return dataProvider.getEquiposPorNom(key);
	}	

	@Override
	public List<Equipo> getJugadoresEquipoPorNombre(String nombre) {
		return dataProvider.getEquiposPorNom(nombre);
	}

	
}
