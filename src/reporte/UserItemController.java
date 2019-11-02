package reporte;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import item.Item;
import usuario.Usuario;
import usuario.UsuarioDAO;

@Path("/useritems")
public class UserItemController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuarItem(UserItem usuario) {
		UserItem result = UserItemDAO.getInstance().persist(usuario);
		if (result == null) {
			throw new RecursoDuplicado(usuario.getId());
		} else {
			return Response.status(201).entity(usuario).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserItem> getAllUserItems() {
		return UserItemDAO.getInstance().findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getItemsByUserId(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);

		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		List<Item> items = UserItemDAO.getInstance().findItemsByUser(usuario);
		if (items != null)
			return items;
		else
			throw new RecursoNoExiste(id);
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
