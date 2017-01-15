
package rest;

import java.util.List;

import javax.ejb.Stateless;
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

import domain.Comment;
import domain.Film;
import domain.Product;

@Path("/comment")
@Stateless
public class CommentResources {
	
	@PersistenceContext
	EntityManager em;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getAllComment() {
		List<Comment> result = em.createNamedQuery("comment.all",Comment.class).getResultList();
		if (result == null)
			return null;
		return result;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Comment p) {
		Comment result = em.createNamedQuery("comment.id",Comment.class).setParameter("commentId", id).getSingleResult();
		if (result == null)
			return Response.status(404).build();
		result.setDescription(p.getDescription());
		em.persist(result);
		return Response.ok("Update zakończony powodzeniem").build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Comment result = em.createNamedQuery("comment.id",Comment.class).setParameter("commentId", id).getSingleResult();
		if (result == null)
			return Response.status(404).build();
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", result.getFilm().getId()).getSingleResult();
		film.getComments().remove(result);
		
		em.persist(film);
		
		return Response.ok("Usunięto komentarz " + id).build();
	}

}
