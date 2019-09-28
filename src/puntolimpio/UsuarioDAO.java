package puntolimpio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

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
		Usuario u = new Usuario();
		
		u.setNombre("oeu");
//		EntityManager entityManager=EMF.createEntityManager();
//		Usuario usuario=entityManager.find(Usuario.class, id);
//		entityManager.close();
		return u;
	}

	@Override
	public Usuario persist(Usuario usuario) {
//		EntityManager entityManager=EMF.createEntityManager();
//		entityManager.getTransaction().begin();
//		entityManager.persist(usuario);
//		entityManager.getTransaction().commit();
//		entityManager.close();
		return usuario;
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> a = new ArrayList<Usuario>();
		Usuario u = new Usuario();
		u.setNombre("aoe");
		a.add(u);
		
		Usuario u2 = new Usuario();
		u.setNombre("sosso");
		a.add(u2);
		
		Usuario u3 = new Usuario();
		u.setNombre("dola");
		a.add(u3);
		return a;
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
