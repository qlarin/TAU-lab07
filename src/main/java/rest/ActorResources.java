//	
//	package rest;
//	
//import java.util.List;
//
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import domain.Actor;
//import domain.services.ActorService;
//	
//	@Path("/actor")
//	public class ActorResources {
//	
//		private ActorService dbActor = new ActorService();
//		
//		@GET
//		@Path("/actor")
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<Actor> getAll()
//		{
//			return dbActor.getAll();
//		}
//		
//		@POST
//		@Consumes(MediaType.APPLICATION_JSON)
//		public Response addActor(Actor actor){
//			dbActor.addActor(actor);
//			return Response.ok("dodał aktora "+actor.getName()).build();
//		}
//		
//		@GET
//		@Path("/actor/{id}")
//		@Produces(MediaType.APPLICATION_JSON)
//		public Response get(@PathParam("id") int id){
//			Actor result = dbActor.get(id);
//			if(result==null){
//				return Response.status(404).build();
//			}
//			return Response.ok(result).build();
//		}
//		
//		@PUT
//		@Path("/actor/{id}")
//		@Consumes(MediaType.APPLICATION_JSON)
//		public Response updateActor(@PathParam("id") int id, Actor p){
//			Actor result = dbActor.get(id);
//			if(result==null)
//				return Response.status(404).build();
//			p.setId(id);
//			dbActor.updateActor(p);
//			return Response.ok("update zakończony powodzeniem").build();
//		}
//		
//		@DELETE
//		@Path("/actor/{id}")
//		public Response deleteActor(@PathParam("id") int id){
//			Actor result = dbActor.get(id);
//			if(result==null)
//				return Response.status(404).build();
//			dbActor.updateActor(result);
//			return Response.ok("usunięcia aktora "+id).build();
//		}
//		
//	}
//	
//	
//	
//	
//
//	