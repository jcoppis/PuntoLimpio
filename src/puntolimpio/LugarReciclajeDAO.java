package puntolimpio;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LugarReciclajeDAO implements DAO<LugarReciclaje, Integer>{
private static LugarReciclajeDAO daoLR;
	private LugarReciclajeDAO() {
		
	}
	public static LugarReciclajeDAO getInstance() {
		if(daoLR == null)
			daoLR = new LugarReciclajeDAO();
		return daoLR;
	}
	@Override
	public LugarReciclaje persist(LugarReciclaje lugarReciclaje) {
		EntityManager entityManager=EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(lugarReciclaje);
		entityManager.getTransaction().commit();
		entityManager.close();
		return lugarReciclaje;
	}
	@Override
	public LugarReciclaje update(Integer id, LugarReciclaje newEntityValues) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LugarReciclaje findById(Integer id) {
		EntityManager entityManager=EMF.createEntityManager();
		LugarReciclaje lugarReciclaje = entityManager.find(LugarReciclaje.class, id);
		entityManager.close();
		return lugarReciclaje;
	}
	@Override
	public List<LugarReciclaje> findAll() {
		EntityManager entityManager=EMF.createEntityManager();
		Query q = entityManager.createQuery("FROM LugarReciclaje");
		List<LugarReciclaje> lugarReciclajes = q.getResultList();
		entityManager.close();
		return lugarReciclajes;
	}
	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
