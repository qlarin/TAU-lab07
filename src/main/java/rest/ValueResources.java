
package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Value;

@Path("/value")
public class ValueResources {

	@PersistenceContext
	EntityManager em;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Value> getAllValue() {
		List<Value> result = em.createNamedQuery("value.all",Value.class).getResultList();
		if (result == null)
			return null;
		return result;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Value p) {
		Value result = em.createNamedQuery("value.id",Value.class).setParameter("valueId", id).getSingleResult();
		if (result == null)
			return Response.status(404).build();
		result.setValue(p.getValue());
		em.persist(result);
		return Response.ok("Update zakończony powodzeniem").build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Value result = em.createNamedQuery("value.id",Value.class).setParameter("valueId", id).getSingleResult();
		if (result == null)
			return Response.status(404).build();
		em.remove(result);
		return Response.ok("Usunięto ocenę: " + id).build();
	}

}
