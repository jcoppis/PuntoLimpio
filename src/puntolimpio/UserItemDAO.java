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

		
		public List<UserItem> findUserById(Integer id) {
			EntityManager entityManager=EMF.createEntityManager();
			Query q = entityManager.createQuery("FROM UserItem u WHERE u.usuario.id = :userId", UserItem.class);
			q.setParameter("userId", id);
			List<UserItem> userItem = q.getResultList();
			entityManager.close();
			return userItem;
		}
		
		@Override
		public UserItem persist(UserItem useritem) {
			EntityManager entityManager=EMF.createEntityManager();
			if(this.isRecyclable(useritem.getItem())) {
				entityManager.getTransaction().begin();
				entityManager.persist(useritem);
				entityManager.getTransaction().commit();
				entityManager.close();
				return useritem;
			}
			return null;
		}

		@Override
		public List<UserItem> findAll() {
			EntityManager entityManager=EMF.createEntityManager();
			Query q = entityManager.createQuery("FROM UserItem");
			List<UserItem> useritems = q.getResultList();
			entityManager.close();
			return useritems;
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

		public boolean isRecyclable(Item item) {
			EntityManager entityManager=EMF.createEntityManager();
			Query q = entityManager.createQuery("FROM Tipo t WHERE t.tipo = :tipoItem");
			q.setParameter("tipoItem", item.getTipo());
			List<Tipo> tipes = q.getResultList();
			return tipes.size() > 0;
		}

}
