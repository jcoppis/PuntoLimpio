package usuario;

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

@Path("/")
public class UsuarioController {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario(Usuario usuario) {
		Usuario result= UsuarioDAO.getInstance().persist(usuario);
		if(result == null) {
			throw new RecursoDuplicado(usuario.getId());
		}else {
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
	
	public class RecursoDuplicado extends WebApplicationException {
	     public RecursoDuplicado(int id) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+ id +" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
