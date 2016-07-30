package es.trapasoft.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.trapasoft.dao.DAOManager;
import es.trapasoft.modelo.Persona;

@Path("/persons")
public class PersonaController {

	DAOManager dao = new DAOManager();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> getPersonas() {
		List<Persona> listamendas = new ArrayList<Persona>();

		// FIXME : OJO PONER AQUI UN PARAMETRO EN CONDICIONES
		listamendas = dao.getPersonas("es");
		return listamendas;
	}

	
	@GET
	@Path("{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona getPersonaWithDept(@PathParam("id") int id, @QueryParam("dept") boolean withDept) {
		Persona menda = new Persona();
		if (withDept)
			menda = dao.getPersonaWithDept(id);
		else
			menda = dao.getPersona(id);

		return menda;
	}
}