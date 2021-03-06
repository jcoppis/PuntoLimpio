package controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ItinerarioDAO;
import dao.PuntoRecoleccionDAO;
import models.Itinerario;
import models.PuntoRecoleccion;
import utils.ItinerarioReq;

@Path("/itinerarios")
public class ItinerarioController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createItinerario(ItinerarioReq i) {
		
		try {
			Itinerario res = ItinerarioDAO.getInstance().persist(i.getCamionId(), i.getFecha(), i.getPuntoRecoleccionId(), i.getLugarReciclajeId());
			return Response.status(201).entity(res).build();
		} catch(Exception e) {
			return Response.status(400).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Itinerario> getAllItinerario() {
		return ItinerarioDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Itinerario getItinerarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Itinerario historial = ItinerarioDAO.getInstance().findById(id);
		if(historial != null)
			return historial;
		else
			throw new RecursoNoExiste(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItinerarioById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Boolean res = ItinerarioDAO.getInstance().delete(id);
		if(res) {
			return Response.status(204).build();
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/puntoRecoleccion/{puntoRecoleccionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Itinerario> getItinerarios(@PathParam("puntoRecoleccionId") String puntoRecoleccionId) {

		int id = Integer.valueOf(puntoRecoleccionId);
		
		PuntoRecoleccion puntoRecoleccion = PuntoRecoleccionDAO.getInstance().findById(id);
		List<Itinerario> items = ItinerarioDAO.getInstance().getItinerario(puntoRecoleccion);

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
