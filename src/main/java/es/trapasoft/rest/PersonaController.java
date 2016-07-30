package es.trapasoft.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

	// obtener todas las personas de la b.d.
	// o filtradas si 'filtro' tiene un valor
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> getPersonas(@QueryParam("filtro") String filtro) {
		List<Persona> listamendas = new ArrayList<Persona>();

		
		listamendas = dao.getPersonas(filtro);
		return listamendas;
	}
	
	// lista de personas por departamento
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Persona> getPersonasByDept(@QueryParam("deptId") int deptId) {
		List<Persona> listamendas = new ArrayList<Persona>();
		
		listamendas = dao.getPersonasByDept(deptId);
		return listamendas;
	}
	
	// persona con o sin el nombre del departamento dependiendo del booleano
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Persona getPersonaWithDept(@PathParam("id") int id, @QueryParam("dept") Boolean withDept) {
		Persona menda = new Persona();
		if (withDept)
			menda = dao.getPersonaWithDept(id);
		else
			menda = dao.getPersona(id);

		return menda;
	}
	

	// insertar
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Persona addPersona(Persona p) {
		return dao.addPersona(p);
	}
	
	// actualizar
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Persona updatePersona(Persona p) {
		return dao.updatePersona(p);
	}
	
	// borrar
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletePersona(@PathParam("id") int id) {
		 dao.deletePersona(id);
	}
}
