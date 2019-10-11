package puntolimpio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ItinerarioDAO implements DAO<Itinerario, Integer> {

	private static ItinerarioDAO daoItinerario;
	
	private ItinerarioDAO() {
		
	}
    
	public static ItinerarioDAO getInstance() {
		if(daoItinerario == null)
			daoItinerario = new ItinerarioDAO();
		return daoItinerario;
	}
	
	@Override
	public Itinerario findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Itinerario itinerario = entityManager.find(Itinerario.class, id);
		entityManager.close();
		return itinerario;
	}

	@Override
	public Itinerario persist(Itinerario usuario) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Itinerario> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		Query q = entityManager.createQuery("FROM Itinerario");
		List<Itinerario> usuarios = q.getResultList();
		entityManager.close();
		return usuarios;
	}

	@Override
	public Itinerario update(Integer id, Itinerario newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void llevarItemsLugarMasCercano() {
		
	}

}
