import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import item.Item;
import item.itemDAO;

public class ItemTest {
	private static EntityManagerFactory emf;
	private static EntityManager entityManager;
	@BeforeClass
	public static void init() {

		Map<String, String> properties = new HashMap<>();

		properties.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
		properties.put("javax.persistence.jdbc.url", "jdbc:derby:derbyDB;create=true");
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "");

		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");

		properties.put("hibernate.show_sql", "false");

		emf = Persistence.createEntityManagerFactory("my_persistence_unit", properties);
		EntityManager entityManager = emf.createEntityManager();
		Item item = new Item();
		item.setId(0);
		item.setNombre("Caja vengadora");
		item.setTipo("carton");
		item.setVolumen(120);
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();

	}

	@AfterClass
	public static void close() {
		emf.close();
	}
	
	static itemDAO itemDao = itemDAO.getInstance();
	@Before
	public void createEm(){
		entityManager= emf.createEntityManager();
	}
	
	
	@After
	public void closeEm() {
		entityManager.close();
	}
	
	@Test
	public void isNotRecyclable() {

		Item item = new Item();
		item.setId(0);
		item.setNombre("papel de aluminio");
		item.setTipo("aluminio");
		item.setVolumen(120);
		Query q = entityManager.createQuery("FROM Item i WHERE i.tipo = :tipoItem AND i.nombre = :nombreItem");
		q.setParameter("tipoItem", item.getTipo());
		q.setParameter("nombreItem", item.getNombre());
		List<Item> tipes = q.getResultList();
		assertTrue(tipes.size() == 0);
	}

	@Test
	public void isRecyclable() {
		Query qItem = entityManager.createQuery("FROM Item i WHERE i.nombre = :nombreItem");
		qItem.setParameter("nombreItem", "Caja vengadora");
		List<Item> ir = qItem.getResultList();
		String itemTipo = ir.get(0).getTipo();
		String itemNombre = ir.get(0).getNombre();
		Query q = entityManager.createQuery("FROM Item i WHERE i.tipo = :tipoItem AND i.nombre = :nombreItem");
		q.setParameter("tipoItem", itemTipo);
		q.setParameter("nombreItem", itemNombre);
		List<Item> itemsRecyclable = q.getResultList();
		assertTrue(itemsRecyclable.size() == 1);
	}

}
