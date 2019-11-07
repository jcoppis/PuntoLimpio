package puntolimpio;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserItemDAO extends ImplDAO<UserItem, Integer> {

	private static UserItemDAO daoItem;

	private UserItemDAO() {
		super(UserItem.class, Integer.class);
	}

	public static UserItemDAO getInstance() {
		if (daoItem == null)
			daoItem = new UserItemDAO();
		return daoItem;
	}

	public List<Item> findItemsByUser(Usuario usuario) {
		EntityManager entityManager = EMF.createEntityManager();

		Query q = entityManager.createQuery("FROM UserItem ui WHERE ui.usuario = :user");
		q.setParameter("user", usuario);
		List<UserItem> userItems = q.getResultList();
		List<Item> items = userItems.stream().map(u -> u.getItem()).collect(Collectors.toList());
		entityManager.close();
		return items;
	}

	public boolean isReadyForSend(PuntoRecoleccion pr) {
		EntityManager entityManager = EMF.createEntityManager();
		PuntoRecoleccion res = entityManager.find(PuntoRecoleccion.class, pr.getId());
		if (res != null) {
			int cantNec = res.getCantNecesariaParaRecoleccion();
			Query q = entityManager.createQuery("FROM UserItem ui WHERE ui.puntoRecoleccion = :prId");
			q.setParameter("prId", pr.getId());
			List<UserItem> useritems = q.getResultList();
			entityManager.close();
			int totalVolume = useritems.stream().mapToInt(x -> x.getItem().getVolumen()).sum();
			return totalVolume > cantNec;
		}

		return false;
	}
}
