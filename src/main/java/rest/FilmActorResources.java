//
//package rest;
//
//import java.util.List;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.Status;
//
//import domain.FilmActor;
//import domain.services.FilmActorService;
//
//@Path("/filmactor")
//@Stateless
//public class FilmActorResources {
//
//	@PersistenceContext
//	EntityManager em;
//	
////	private FilmActorService dbFilmActor = new FilmActorService();
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<FilmActor> getAll() {
//		return dbFilmActor.getAllFilmActor();
//	}
//
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addFilmActor(FilmActor filmActor) {
//
//		for(FilmActor fa : dbFilmActor.getAllFilmActor()){
//			if(fa.getActorId() == filmActor.getActorId() && fa.getFilmId() == filmActor.getFilmId()){
//				return Response.status(Status.NOT_MODIFIED).build();
//			}
//		}
//		em.persist(filmActor);
////		dbFilmActor.addFilmActor(filmActor);
//		return Response.ok("połączono film o id" + filmActor.getId() + " z aktorem/aktorka " + filmActor.getActorId())
//				.build();
//	}
//
//	@DELETE
//	@Path("/{id}")
//	public Response delete(@PathParam("id") int id) {
//		FilmActor result = dbFilmActor.getFilmActor(id);
//		if (result == null)
//			return Response.status(404).build();
//		dbFilmActor.deleteFilmActor(result);
//		return Response.ok("Usunięto powiązanie: " + id).build();
//	}
//}
