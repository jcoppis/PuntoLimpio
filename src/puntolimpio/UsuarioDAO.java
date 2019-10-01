package puntolimpio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UsuarioDAO implements DAO<Usuario, Integer> {

	private static UsuarioDAO daoUsuario;
	
	private UsuarioDAO() {
		
	}
    
	public static UsuarioDAO getInstance() {
		if(daoUsuario == null)
			daoUsuario = new UsuarioDAO();
		return daoUsuario;
	}
	
	@Override
	public Usuario findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, id);
		entityManager.close();
		return usuario;
	}

	@Override
	public Usuario persist(Usuario usuario) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
//		EntityManager entityManager=EMF.createEntityManager();
//		Query q = entityManager.createQuery("SELECT id, nombre, geolocalizacion FROM usuario;");
//		List<Usuario> usuarios = q.getResultList();
//		entityManager.close();
		return null;
	}

	@Override
	public Usuario update(Integer id, Usuario newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
