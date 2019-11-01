package puntolimpio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class itemDAO extends ImplDAO<Item, Integer>{

		private static itemDAO daoItem;
		
		private itemDAO() {
			super(Item.class, Integer.class);
		}
	    
		public static itemDAO getInstance() {
			if(daoItem == null)
				daoItem = new itemDAO();
			return daoItem;
		}

		public boolean isRecyclable(Item item) {
			EntityManager entityManager=EMF.createEntityManager();
			Query q = entityManager.createQuery("FROM Item i WHERE i.nombre = :nombreItem");
			q.setParameter("nombreItem", item.getNombre());
			List<Item> items = q.getResultList();
			return items.size() > 0;
		}

}
