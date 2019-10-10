package puntolimpio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class UserItemDAO implements DAO<UserItem, Integer>{


		private static UserItemDAO daoItem;
		
		private UserItemDAO() {
			
		}
	    
		public static UserItemDAO getInstance() {
			if(daoItem == null)
				daoItem = new UserItemDAO();
			return daoItem;
		}
		
		@Override
		public UserItem findById(Integer id) {
			EntityManager entityManager=EMF.createEntityManager();
			UserItem useritem = entityManager.find(UserItem.class, id);
			entityManager.close();
			return useritem;
		}

		@Override
		public UserItem persist(UserItem useritem) {
			EntityManager entityManager=EMF.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(useritem);
			entityManager.getTransaction().commit();
			entityManager.close();
			return useritem;
		}

		@Override
		public List<UserItem> findAll() {
//			EntityManager entityManager=EMF.createEntityManager();
//			Query q = entityManager.createQuery("SELECT id, nombre, geolocalizacion FROM usuario;");
//			List<Usuario> items = q.getResultList();
//			entityManager.close();
			return null;
		}

		@Override
		public UserItem update(Integer id, UserItem newEntityValues) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean delete(Integer id) {
			// TODO Auto-generated method stub
			return false;
		}


}
