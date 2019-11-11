package reporte;

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

import item.Item;
import usuario.Usuario;
import usuario.UsuarioDAO;

@Path("/")
public class ReporteController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReporte(@QueryParam("userId") String userId, @QueryParam("itemId") String itemId, @QueryParam("puntoRecoleccionId") String puntoRecoleccionId, @QueryParam("cantidad") String cantidad) {
		int u = Integer.valueOf(userId);
		int i = Integer.valueOf(itemId);
		int p = Integer.valueOf(puntoRecoleccionId);
		int c = Integer.valueOf(cantidad);
		
		try {
			System.out.println("entre");
			ReporteDAO.getInstance().persist(u, i, p, c);
			return Response.status(201).build();
		} catch(Exception e) {
			return Response.status(404).build();
		}		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createReporte(Reporte reporte) {
		Reporte result = ReporteDAO.getInstance().persist(reporte);
		if (result == null) {
			throw new RecursoDuplicado(reporte.getId());
		} else {
			return Response.status(201).entity(reporte).build();
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
			@QueryParam("startingDate") Timestamp startingDate, @QueryParam("endingDate") Timestamp endingDate) {
		int id = Integer.valueOf(userId);

		Usuario user = UsuarioDAO.getInstance().findById(id);

		List<Item> items;
		if (startingDate != null && endingDate != null) {
			items = ReporteDAO.getInstance().findItemsByUser(user);
		} else {
			items = ReporteDAO.getInstance().findItemsByUserAndRangeOfDates(user, startingDate, endingDate);
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
			@QueryParam("startingDate") Timestamp startingDate, @QueryParam("endingDate") Timestamp endingDate) {
		int id = Integer.valueOf(userId);

		Usuario user = UsuarioDAO.getInstance().findById(id);

		if (startingDate != null && endingDate != null) {
			return ReporteDAO.getInstance().findAhorroTotal(user);
		} else {
			return ReporteDAO.getInstance().findAhorroPorFecha(user, startingDate, endingDate);
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
