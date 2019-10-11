package puntolimpio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class itemDAO implements DAO<Item, Integer>{


		private static itemDAO daoItem;
		
		private itemDAO() {
			
		}
	    
		public static itemDAO getInstance() {
			if(daoItem == null)
				daoItem = new itemDAO();
			return daoItem;
		}
		
		@Override
		public Item findById(Integer id) {
			EntityManager entityManager=EMF.createEntityManager();
			Item item = entityManager.find(Item.class, id);
			entityManager.close();
			return item;
		}

		@Override
		public Item persist(Item item) {
			EntityManager entityManager=EMF.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(item);
			entityManager.getTransaction().commit();
			entityManager.close();
			return item;
		}

		@Override
		public List<Item> findAll() {
//			EntityManager entityManager=EMF.createEntityManager();
//			Query q = entityManager.createQuery("SELECT id, nombre, geolocalizacion FROM usuario;");
//			List<Usuario> items = q.getResultList();
//			entityManager.close();
			return null;
		}

		@Override
		public Item update(Integer id, Item newEntityValues) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean delete(Integer id) {
			// TODO Auto-generated method stub
			return false;
		}


}
