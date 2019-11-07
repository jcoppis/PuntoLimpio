package puntolimpio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ImplDAO<Entity, ID extends Serializable> implements DAO<Entity, ID> {
	EntityManager entityManager;
	Class<Entity> entityClass;
	Class<ID> idClass;

	public ImplDAO(Class<Entity> entityClass, Class<ID> idClass) {
		this.entityClass = entityClass;
		this.idClass = idClass;
	}

	@Override
	public Entity persist(Entity entity) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}

	@Override
	public Entity findById(ID id) {
		EntityManager entityManager = EMF.createEntityManager();
		Entity entity = entityManager.find(entityClass, id);
		entityManager.close();
		return entity;
	}

	@Override
	public List<Entity> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createQuery("FROM " + this.entityClass.getSimpleName());
		List<Entity> entitys = q.getResultList();
		entityManager.close();
		return entitys;
	}

	@Override
	public boolean delete(ID id) {
		Entity entity = entityManager.find(entityClass, id);

		if (entity != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			entityManager.close();

			return true;
		} else {
			return false;
		}
	}
}
