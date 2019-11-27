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

import dao.PuntoRecoleccionDAO;
import models.PuntoRecoleccion;

@Path("/puntosRecoleccion")
public class PuntoRecoleccionController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPuntoRecoleccion(PuntoRecoleccion p) {
		try {
			PuntoRecoleccion res = PuntoRecoleccionDAO.getInstance().persist(p);
			return Response.status(201).entity(res).build();
		} catch(Exception e) {
			return Response.status(400).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PuntoRecoleccion> getAllPuntosRecoleccion() {
		return PuntoRecoleccionDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PuntoRecoleccion getPuntoRecoleccionById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		PuntoRecoleccion historial = PuntoRecoleccionDAO.getInstance().findById(id);
		if(historial != null)
			return historial;
		else
			throw new RecursoNoExiste(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePuntoRecoleccionById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Boolean res = PuntoRecoleccionDAO.getInstance().delete(id);
		if(res) {
			return Response.status(204).build();
		}
		return Response.status(404).build();
	}

	@GET
	@Path("/{puntoRecoleccionId}/volumenNecesario")
	@Produces(MediaType.APPLICATION_JSON)
	public int getVolumenNecesario(@PathParam("puntoRecoleccionId") String puntoRecoleccionId) {

		int id = Integer.valueOf(puntoRecoleccionId);
		
		PuntoRecoleccion puntoRecoleccion = PuntoRecoleccionDAO.getInstance().findById(id);
		if (puntoRecoleccion != null) {
			return puntoRecoleccion.getCantNecesariaParaRecoleccion();
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
