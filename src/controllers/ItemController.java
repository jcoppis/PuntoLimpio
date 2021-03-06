package controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ItemDAO;
import dao.UsuarioDAO;
import models.Item;

@Path("/items")
public class ItemController {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItem(Item item) {
		Item result= ItemDAO.getInstance().persist(item);
		if(result == null) {
			throw new RecursoDuplicado(item.getId());
		}else {
			return Response.status(201).entity(item).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getAllItems() {
		return ItemDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item getItemById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Item item = ItemDAO.getInstance().findById(id);
		if(item != null)
			return item;
		else
			throw new RecursoNoExiste(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("id") String deleteId) {
		int id = Integer.valueOf(deleteId);
		boolean delete = ItemDAO.getInstance().delete(id);
		if(delete) {
			 return Response.status(204).build();
		}
			return Response.status(404).build();
	}
	
	public class RecursoNoExiste extends WebApplicationException {
	     public RecursoNoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id " + id + " no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	
	public class RecursoDuplicado extends WebApplicationException {
	     public RecursoDuplicado(int id) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
