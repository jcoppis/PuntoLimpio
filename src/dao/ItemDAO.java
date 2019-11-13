package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Item;
import puntolimpio.EMF;
import puntolimpio.ImplDAO;

public class ItemDAO extends ImplDAO<Item, Integer> {

	private static ItemDAO daoItem;

	private ItemDAO() {
		super(Item.class, Integer.class);
	}

	public static ItemDAO getInstance() {
		if (daoItem == null)
			daoItem = new ItemDAO();
		return daoItem;
	}

	public boolean isRecyclable(Item item) {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createQuery("FROM Item i WHERE i.nombre = :nombreItem");
		q.setParameter("nombreItem", item.getNombre());
		List<Item> items = q.getResultList();
		return items.size() > 0;
	}

}
