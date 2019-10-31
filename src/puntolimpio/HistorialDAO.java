package puntolimpio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HistorialDAO implements DAO<Historial, Integer>{
	private static HistorialDAO daoHistorial;
	
	private HistorialDAO() {
		
	}
    
	public static HistorialDAO getInstance() {
		if(daoHistorial == null)
			daoHistorial = new HistorialDAO();
		return daoHistorial;
	}
	
	@Override
	public Historial findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Historial historial = entityManager.find(Historial.class, id);
		entityManager.close();
		return historial;
	}

	@Override
	public Historial persist(Historial historial) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(historial);
		entityManager.getTransaction().commit();
		entityManager.close();
		return historial;
	}

	@Override
	public List<Historial> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		Query q = entityManager.createQuery("FROM Item");
		List<Historial> historial = q.getResultList();
		entityManager.close();
		return historial;
	}

	@Override
	public Historial update(Integer id, Historial newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
