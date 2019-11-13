package controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controllers.UsuarioController.RecursoNoExiste;
import dao.HistorialDAO;
import dao.ItemDAO;
import dao.ReporteDAO;
import dao.UsuarioDAO;
import models.Historial;
import models.Item;
import models.Usuario;

@Path("/historial")
public class HistorialController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createHistorial(@QueryParam("reportId") String reportId, @QueryParam("lugarId") String lugarId) {
		int r = Integer.valueOf(reportId);
		int l = Integer.valueOf(lugarId);
		
		try {
			System.out.println("entre");
			HistorialDAO.getInstance().persist(r, l);
			return Response.status(201).build();
		} catch(Exception e) {
			return Response.status(404).build();
		}		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createHistorial(Historial historial) {
		Historial result = HistorialDAO.getInstance().persist(historial);
		if (result == null) {
			throw new RecursoDuplicado(historial.getId());
		} else {
			return Response.status(201).entity(historial).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Historial> getAllHistorial() {
		return HistorialDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Historial getHistorialById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Historial historial = HistorialDAO.getInstance().findById(id);
		if(historial != null)
			return historial;
		else
			throw new RecursoNoExiste(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteHistorialById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Boolean res = HistorialDAO.getInstance().delete(id);
		if(res) {
			return Response.status(204).build();
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/getItems/recycleSite/{recycleSiteId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Historial> getItemsByRecycleSiteAndRangeOfDates(@PathParam("recycleSiteId") String recycleSiteId,
			@QueryParam("startingDate") Timestamp startingDate, @QueryParam("endingDate") Timestamp endingDate) {

		int id = Integer.valueOf(recycleSiteId);
		List<Historial> items = HistorialDAO.getInstance().itemsByRecycleSiteAndRangeOfDates(id, startingDate, endingDate);

		if (items != null) {
			return items;
		} else {
			throw new RecursoNoExiste(id);
		}
	}
	
	public class RecursoNoExiste extends WebApplicationException {
		public RecursoNoExiste(int id) {
			super(Response.status(Response.Status.NOT_FOUND).entity("El recurso con id " + id + " no fue encontrado")
					.type(MediaType.TEXT_PLAIN).build());
		}
	}

	public class RecursoDuplicado extends WebApplicationException {
		public RecursoDuplicado(int id) {
			super(Response.status(Response.Status.CONFLICT).entity("El recurso con ID " + id + " ya existe")
					.type(MediaType.TEXT_PLAIN).build());
		}
	}
}
