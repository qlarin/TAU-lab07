
package lab07.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lab07.domain.Product;
import lab07.service.ProductManager;

@Path("products")
public class ProductResources {

	private ProductManager pm = new ProductManager();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return pm.getAllProducts();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProduct(Product product) {
		pm.addProduct(product);
		return Response.status(201).entity(product).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		Product product = pm.getProduct(id);
		if (product == null) {
			return Response.status(404).build();
		}
		return Response.ok(product).build();
	}
/*	
	@GET
	@Path("/searchByPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductByPrice(
			@QueryParam("min") int min,
            @QueryParam("max") int max) {
		BigDecimal bigMin = new BigDecimal(min);
		BigDecimal bigMax = new BigDecimal(max);
		List<Product> products = em.createNamedQuery("product.byPrice",Product.class).setParameter("min", bigMin)
				.setParameter("max", bigMax)
				.getResultList();
		return products;
	}
	
	@GET
	@Path("/searchByName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductByName(@QueryParam("name") String name) {
		List<Product> products = em.createNamedQuery("product.byName",Product.class).setParameter("name", name)
				.getResultList();
		
		return products;
	}
	
	@GET
	@Path("/searchByCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductByCategory(@QueryParam("category") String name) {
		List<Product> products = em.createNamedQuery("product.byCategory",Product.class)
				.setParameter("category", domain.Product.Category.valueOf(name))
				.getResultList();
		
		return products;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p) {
		Product product = em.createNamedQuery("product.id",Product.class).setParameter("productId", id).getSingleResult();
		
		if(product == null)
			return Response.status(404).build();
		product.setName(p.getName());
		product.setCategory(p.getCategory());
		product.setPrice(p.getPrice());
		em.persist(product);
		
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Product product = em.createNamedQuery("product.id",Product.class).setParameter("productId", id).getSingleResult();
		if (product == null)
			return Response.status(404).build();
		em.remove(product);
		return Response.ok("usunięto produkt " + id).build();
	}
*/
}
