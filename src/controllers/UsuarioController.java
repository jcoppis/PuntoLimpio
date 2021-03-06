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

import controllers.HistorialController.RecursoNoExiste;
import dao.HistorialDAO;
import dao.ItinerarioDAO;
import dao.PuntoRecoleccionDAO;
import dao.UsuarioDAO;
import models.Historial;
import models.Itinerario;
import models.PuntoRecoleccion;
import models.Usuario;

@Path("/usuarios")
public class UsuarioController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		Usuario result = UsuarioDAO.getInstance().persist(usuario);
		if (result == null) {
			throw new RecursoDuplicado(usuario.getId());
		} else {
			return Response.status(201).entity(usuario).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getAllUsuarios() {
		return UsuarioDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuariosById(@PathParam("id") String userId) {
		int id = Integer.valueOf(userId);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		if (usuario != null) {
			return usuario;
		} else {
			throw new RecursoNoExiste(id);
		}
	}

	@GET
	@Path("/{id}/itinerarios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Itinerario> getItinerario(@PathParam("id") String userId) {
		int id = Integer.valueOf(userId);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		
		if (usuario != null) {
			PuntoRecoleccion p = PuntoRecoleccionDAO.getInstance().getPuntoMasCercano(usuario.getLatitude(), usuario.getLongitude());
			List<Itinerario> li = ItinerarioDAO.getInstance().getItinerario(p);
			return li;
		} else {
			throw new RecursoNoExiste(id);
		}
	}
	
	@GET
	@Path("/{id}/puntoRecoleccionMasCercano")
	@Produces(MediaType.APPLICATION_JSON)
	public PuntoRecoleccion getPuntoRecoleccionMasCercano(@PathParam("id") String userId) {
		int id = Integer.valueOf(userId);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		
		if (usuario != null) {
			PuntoRecoleccion p = PuntoRecoleccionDAO.getInstance().getPuntoMasCercano(usuario.getLatitude(), usuario.getLongitude());
			return p;
		} else {
			throw new RecursoNoExiste(id);
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("id") String deleteId) {
		int id = Integer.valueOf(deleteId);
		boolean delete = UsuarioDAO.getInstance().delete(id);
		if (delete) {
			return Response.status(204).build();
		}
		return Response.status(404).build();
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
