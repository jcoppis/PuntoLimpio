package puntolimpio;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getAllUsuarios() {
		return UsuarioDAO.getInstance().findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuariosById(@PathParam("id") String msg) {
		int id = Integer.valueOf(msg);
		Usuario usuario = UsuarioDAO.getInstance().findById(id);
		if(usuario != null)
			return usuario;
		else
			throw new RecursoNoExiste(id);
	}

	public class RecursoNoExiste extends WebApplicationException {
	     public RecursoNoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id " + id + " no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
