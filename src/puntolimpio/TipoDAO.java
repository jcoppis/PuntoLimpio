package puntolimpio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TipoDAO implements DAO<Tipo, Integer>{

	private static TipoDAO daoTipo;
	
	private TipoDAO() {
		
	}
    
	public static TipoDAO getInstance() {
		if(daoTipo == null)
			daoTipo = new TipoDAO();
		return daoTipo;
	}
	@Override
	public Tipo findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		Tipo tipo = entityManager.find(Tipo.class, id);
		entityManager.close();
		return tipo;
	}
	@Override
	public Tipo persist(Tipo tipo) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(tipo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tipo;
	}

	@Override
	public List<Tipo> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		Query q = entityManager.createQuery("FROM Tipo");
		List<Tipo> tipos = q.getResultList();
		entityManager.close();
		return tipos;
	}

	@Override
	public Tipo update(Integer id, Tipo newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
