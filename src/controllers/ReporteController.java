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

import dao.PuntoRecoleccionDAO;
import dao.ReporteDAO;
import dao.UsuarioDAO;
import models.Item;
import models.PuntoRecoleccion;
import models.Reporte;
import models.Usuario;
import utils.ReporteReq;

@Path("/reportes")
public class ReporteController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReporte(ReporteReq r) {
		try {
			Reporte res = ReporteDAO.getInstance().persist(r.getUsuarioId(), r.getItemId(), r.getPuntoRecoleccionId(), r.getCantidadItems());
			return Response.status(201).entity(res).build();
		} catch(Exception e) {
			System.out.println(e);
			return Response.status(400).build();
		}		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reporte> getAllReportes() {
		return ReporteDAO.getInstance().findAll();
	}

	@GET
	@Path("/getItems/usuario/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getItemsByUserAndRangeOfDates(@PathParam("userId") String userId,
			@QueryParam("startingDate") String startingDate, @QueryParam("endingDate") String endingDate) {
		int id = Integer.valueOf(userId);
		
		Usuario user = UsuarioDAO.getInstance().findById(id);
		
		System.out.println(startingDate + " " + endingDate);

		List<Item> items;
		if (startingDate == null && endingDate == null) {
			items = ReporteDAO.getInstance().findItemsByUser(user);
		} else {
			Timestamp sDate = Timestamp.valueOf(startingDate + " 00:00:00"); // Tener en cuenta que hay que pasar una fecha en formato yyyy-MM-dd
			Timestamp eDate = Timestamp.valueOf(endingDate + " 00:00:00"); // Tener en cuenta que hay que pasar una fecha en formato yyyy-MM-dd
			items = ReporteDAO.getInstance().findItemsByUserAndRangeOfDates(user, sDate, eDate);
		}

		if (items != null) {
			return items;
		} else {
			throw new RecursoNoExiste(id);
		}
	}
	
	@GET
	@Path("/getTotalVolume/usuario/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAhorroPorFecha(@PathParam("userId") String userId,
			@QueryParam("startingDate") String startingDate, @QueryParam("endingDate") String endingDate) {
		int id = Integer.valueOf(userId);

		Usuario user = UsuarioDAO.getInstance().findById(id);

		if (startingDate == null && endingDate == null) {
			return ReporteDAO.getInstance().findAhorroTotal(user);
		} else {
			Timestamp sDate = Timestamp.valueOf(startingDate + " 00:00:00"); // Tener en cuenta que hay que pasar una fecha en formato yyyy-MM-dd
			Timestamp eDate = Timestamp.valueOf(endingDate + " 00:00:00"); // Tener en cuenta que hay que pasar una fecha en formato yyyy-MM-dd
			return ReporteDAO.getInstance().findAhorroPorFecha(user, sDate, eDate);
		}
	}
	
	@GET
	@Path("/puntoRecoleccion/{puntoRecoleccionId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getHistorialPuntoRecoleccion(@PathParam("puntoRecoleccionId") String puntoRecoleccionId,
			@QueryParam("startingDate") String startingDate, @QueryParam("endingDate") String endingDate) {
		
		int id = Integer.valueOf(puntoRecoleccionId);

		PuntoRecoleccion p = PuntoRecoleccionDAO.getInstance().findById(id);

		List<Item> items;
		if (startingDate == null && endingDate == null) {
			items = ReporteDAO.getInstance().findItemsByPuntoRecoleccion(p);
		} else {
			Timestamp sDate = Timestamp.valueOf(startingDate + " 00:00:00"); // Tener en cuenta que hay que pasar una fecha en formato yyyy-MM-dd
			Timestamp eDate = Timestamp.valueOf(endingDate + " 00:00:00"); // Tener en cuenta que hay que pasar una fecha en formato yyyy-MM-dd
			items = ReporteDAO.getInstance().findItemsByPuntoRecoleccionAndRangeOfDates(p, sDate, eDate);
		}
		
		if (items != null) {
			return items;
		} else {
			throw new RecursoNoExiste(id);
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("id") String deleteId) {
		int id = Integer.valueOf(deleteId);
		boolean delete = ReporteDAO.getInstance().delete(id);
		if(delete) {
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
