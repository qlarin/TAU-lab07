
package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.Comment;
import domain.Film;
import domain.Value;

@Path("/film")
@Stateless
public class FilmResources {

	@PersistenceContext
	EntityManager em;
	
//	private FilmService dbFilm = new FilmService();
//	private CommentService dbComment = new CommentService();
//	private ValueService dbValue = new ValueService();
//	private ActorService dbActor = new ActorService();

	// ------------------FILM------------------//

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Film> getAll() {
		return em.createNamedQuery("film.all", Film.class).getResultList();
//		return dbFilm.getAll();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addFilm(Film film) {
//		dbFilm.addFilm(film);
		em.persist(film);
		return Response.ok("Dodano film o id " + film.getId() + " i tytule: " + film.getTitle()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
//		Film film = dbFilm.get(id);
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", id).getSingleResult();
		if (film == null) {
			return Response.status(404).build();
		}
		return Response.ok(film).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Film p) {
//		dbFilm.updateFilm(p);
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", id).getSingleResult();
		
		if(film == null)
			return Response.status(404).build();
		film.setOcena(p.getOcena());
		film.setTitle(p.getTitle());
		em.persist(film);
		
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
//		Film result = dbFilm.get(id);
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", id).getSingleResult();
		if (film == null)
			return Response.status(404).build();
//		dbFilm.deleteFilm(film);
		em.remove(film);
		return Response.ok("usunięto film " + id).build();
	}

	// ------------------KOMENTARZ------------------//

	@GET
	@Path("/{filmId}/comment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComment(@PathParam("filmId") int filmId) {
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", filmId).getSingleResult();
//		Film film = dbFilm.get(FilmId);
		if (film == null)
			return new ArrayList<>();
		if (film.getComments() == null)
			film.setComments(new ArrayList<Comment>());
		return film.getComments();
	}

	@POST
	@Path("/{id}/comment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("id") int filmId, Comment comment) {
//		Film film = dbFilm.get(filmId);
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", filmId).getSingleResult();
		if (film == null)
			return Response.status(404).build();
		if (film.getComments() == null)
			film.setComments(new ArrayList<Comment>());
//		dbComment.addComment(comment);
		comment.setFilm(film);
		film.getComments().add(comment);
		em.persist(comment);
		return Response.ok("Dodano komentarz do filmu " + filmId + " o tytule: " + film.getTitle()).build();
	}
	

	// ------------------OCENA FILMU------------------//

	@GET
	@Path("/{filmId}/value")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Value> getValue(@PathParam("filmId") int filmId) {
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", filmId).getSingleResult();
//		Film film = dbFilm.get(FilmId);
		if (film == null)
			return new ArrayList<>();
		if (film.getValues() == null)
			film.setValues(new ArrayList<Value>());
		return film.getValues();
	}

	@POST
	@Path("/{id}/value")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addValue(@PathParam("id") int filmId, Value value) {
//		Film film = dbFilm.get(filmId);
		Film film = em.createNamedQuery("film.id",Film.class).setParameter("filmId", filmId).getSingleResult();
		if (film == null)
			return Response.status(404).build();
		if (film.getValues() == null)
			film.setValues(new ArrayList<Value>());
	
//		dbComment.addComment(comment);
		film.getValues().add(value);
		value.setFilm(film);
		em.persist(value);
		
//		dbValue.addValue(value);
//		film.getValues().add(value);
		return Response.ok("Dodano ocene do filmu " + filmId + " o tytule: " + film.getTitle()).build();
	}

	// ------------------AKTOR------------------//

	@GET
	@Path("/actor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAllActor() {
		List<Actor> actors = em.createNamedQuery("actor.all",Actor.class).getResultList();
//		return dbActor.getAll();
		return actors;
	}

	@POST
	@Path("/actor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActor(Actor actor) {
//		dbActor.addActor(actor);
		em.persist(actor);
		
		return Response.ok("Dodany aktor: " + actor.getName() + " " + actor.getSurname()).build();
	}

	@GET
	@Path("/actor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActor(@PathParam("id") int id) {
//		Actor result = dbActor.get(id);
		Actor result = em.createNamedQuery("actor.id",Actor.class).setParameter("actor", id).getSingleResult();
		if (result == null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}

	@PUT
	@Path("/actor/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateActor(@PathParam("id") int id, Actor p) {
//		Actor result = dbActor.get(id);
		Actor result = em.createNamedQuery("actor.id",Actor.class).setParameter("actor", id).getSingleResult();
		if (result == null)
			return Response.status(404).build();
//		p.setId(id);
		result.setName(p.getName());
		result.setSurname(p.getSurname());
//		dbActor.updateActor(p);
		em.persist(result);
		return Response.ok("Update zakończony powodzeniem").build();
	}

	@DELETE
	@Path("/actor/{id}")
	public Response deleteActor(@PathParam("id") int id) {
//		Actor result = dbActor.get(id);
		Actor result = em.createNamedQuery("actor.id",Actor.class).setParameter("actor", id).getSingleResult();
		if (result == null)
			return Response.status(404).build();
//		dbActor.deleteActor(result);
		em.remove(result);
		return Response.ok("Usunięcie aktora " + id).build();
	}

	// ------------------POŁĄCZENIE AKTORA Z FILMEM------------------//

//	private FilmActorService dbFilmActor = new FilmActorService();
//
//	@GET
//	@Path("/{actorId}/filmsByActor")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Film> getFilmsByActor(@PathParam("actorId") int actorId) {
//		List<FilmActor> result = dbFilmActor.getAllFilmActor();
//		if (result == null)
//			return null;
//
//		List<Film> resultFilm = new ArrayList<>();
//		for (FilmActor actor : result) {
//			if (actor.getActorId() == actorId) {
//				for (Film film : dbFilm.getAll()) {
//					if (film.getId() == actor.getFilmId()) {
//						resultFilm.add(film);
//					}
//				}
//			}
//		}
//
//		return resultFilm;
//	}
//
//	@GET
//	@Path("/{filmId}/actorsFromFilm")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Actor> getActorsFromFilm(@PathParam("filmId") int actorId) {
//		List<FilmActor> result = dbFilmActor.getAllFilmActor();
//		if (result == null)
//			return null;
//
//		List<Actor> resultFilm = new ArrayList<>();
//		for (FilmActor filmActor : result) {
//			if (filmActor.getFilmId() == actorId) {
//				for (Actor actor : dbActor.getAll()) {
//					if (actor.getId() == filmActor.getActorId()) {
//						resultFilm.add(actor);
//					}
//				}
//			}
//		}
//
//		return resultFilm;
//	}
}
