package uy.com.jatrik.services;

import java.util.List;
import javax.ws.rs.Path;
import uy.com.jatrik.entities.Equipo;

@Path("equipos")
public class EquipoServiceImpl extends Service implements EquipoService {

	@Override
	public List<Equipo> getListaEquipos() {
		return dataProvider.getAllEquipos();
	}
	
	public EquipoServiceImpl() {
		super();
	}
}
