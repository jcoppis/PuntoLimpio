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
import utils.HistorialReq;

@Path("/historial")
public class HistorialController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createHistorial(HistorialReq h) {
		try {
			Historial res = HistorialDAO.getInstance().persist(h.getReporteId(), h.getLugarReciclajeId());
			return Response.status(201).entity(res).build();
		} catch (Exception e) {
			return Response.status(400).build();
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
		if (historial != null)
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
		if (res) {
			return Response.status(204).build();
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/getItems/lugarReciclaje/{lugarReciclajeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Historial> getItemsByRecycleSiteAndRangeOfDates(@PathParam("lugarReciclajeId") String lugarReciclajeId,
			@QueryParam("startingDate") Timestamp startingDate, @QueryParam("endingDate") Timestamp endingDate) {

		int id = Integer.valueOf(lugarReciclajeId);
		List<Historial> items;
		if (startingDate == null && endingDate == null) {
			items = HistorialDAO.getInstance().itemsByRecycleSite(id);
		} else {
			items = HistorialDAO.getInstance().itemsByRecycleSiteAndRangeOfDates(id, startingDate, endingDate);
		}
		
		if (items != null) {
			return items;
		} else {
			throw new RecursoNoExiste(id);
		}
	}
	
	@GET
	@Path("/ahorroONG")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAhorroONG(@QueryParam("startingDate") Timestamp startingDate, @QueryParam("endingDate") Timestamp endingDate) {

		if (startingDate == null && endingDate == null) {
			return HistorialDAO.getInstance().ahorroONG();
		} else {
			return HistorialDAO.getInstance().ahorroONGAndRangeOfDates(startingDate, endingDate);
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
