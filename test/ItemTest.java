import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import puntolimpio.Item;
import puntolimpio.itemDAO;

public class ItemTest {
	private static EntityManagerFactory emf;

	@BeforeClass
	public static void init() {

		Map<String, String> properties = new HashMap<>();

		properties.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
		properties.put("javax.persistence.jdbc.url", "jdbc:derby:derbyDB;create=true");
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "");

		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");

		properties.put("hibernate.show_sql", "true");

		emf = Persistence.createEntityManagerFactory("my_persistence_unit", properties);

	}

	@AfterClass
	public static void close() {
		emf.close();
	}

	static itemDAO itemDao = itemDAO.getInstance();

	@Test
	public void isNotRecyclable() {
		Item item = new Item();
		item.setId(0);
		item.setNombre("papel de aluminio");
		item.setTipo("aluminio");
		item.setVolumen(120);

		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createQuery("FROM Item i WHERE i.tipo = :tipoItem AND i.nombre = :nombreItem");
		q.setParameter("tipoItem", item.getTipo());
		q.setParameter("nombreItem", item.getNombre());
		List<Item> tipes = q.getResultList();
		assertTrue(tipes.size() == 0);
	}

	@Test
	public void isRecyclable() {
		EntityManager entityManager = emf.createEntityManager();
		Item item = entityManager.find(Item.class, 1);
		String itemTipo = item.getTipo();
		String itemNombre = item.getNombre();
		Query q = entityManager.createQuery("FROM Item i WHERE i.tipo = :tipoItem AND i.nombre = :nombreItem");
		q.setParameter("tipoItem", itemTipo);
		q.setParameter("nombreItem", itemNombre);
		List<Item> itemsRecyclable = q.getResultList();
		assertTrue(itemsRecyclable.size() > 0);
	}

}
