import static org.junit.Assert.*;
import java.sql.Timestamp;
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

import puntolimpio.Historial;
import puntolimpio.Item;
import puntolimpio.LugarReciclaje;

public class HistorialTest {
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

	@Test
	public void itemsByRecycleSiteAndRangeOfDates() {

		EntityManager entityManager = emf.createEntityManager();
		LugarReciclaje lugarReciclajeAPersistir = new LugarReciclaje();
		Item i1 = new Item();
		Item i2 = new Item();
		Item i3 = new Item();
		Item i4 = new Item();

		lugarReciclajeAPersistir.setId(0);
		lugarReciclajeAPersistir.setLatitude(123.3);
		lugarReciclajeAPersistir.setLongitude(432.4);
		lugarReciclajeAPersistir.setNombre("lugar1");

		i1.setId(0);
		i1.setNombre("item1");
		i1.setTipo("carton");
		i1.setVolumen(20);

		i2.setId(0);
		i2.setNombre("item2");
		i2.setTipo("vidrio");
		i2.setVolumen(320);

		i3.setId(0);
		i3.setNombre("item3");
		i3.setTipo("plastico");
		i3.setVolumen(200);

		i4.setId(0);
		i4.setNombre("item4");
		i4.setTipo("vidrio");
		i4.setVolumen(350);

		entityManager.getTransaction().begin();
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(i3);
		entityManager.persist(i4);
		entityManager.persist(lugarReciclajeAPersistir);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		Historial historial = new Historial();
		historial.setItem(i1);
		historial.setlReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist = Timestamp.valueOf("2019-02-01 00:00:00.0");
		historial.setFechaReciclaje(timestampHistorialPersist);
		entityManager.persist(historial);

		Historial historial2 = new Historial();
		historial2.setItem(i2);
		historial2.setlReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist2 = Timestamp.valueOf("2019-02-02 00:00:00.0");
		historial2.setFechaReciclaje(timestampHistorialPersist2);
		entityManager.persist(historial2);

		Historial historial3 = new Historial();
		historial3.setItem(i3);
		historial3.setlReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist3 = Timestamp.valueOf("2019-10-24 00:00:00.0");
		historial3.setFechaReciclaje(timestampHistorialPersist3);
		entityManager.persist(historial3);

		Historial historial4 = new Historial();
		historial4.setItem(i4);
		historial4.setlReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist4 = Timestamp.valueOf("2019-10-20 00:00:00.0");
		historial4.setFechaReciclaje(timestampHistorialPersist4);
		entityManager.persist(historial4);
		entityManager.getTransaction().commit();

		Query qLugarReciclaje = entityManager.createQuery("FROM LugarReciclaje lr WHERE lr.nombre = :lrNombre");
		qLugarReciclaje.setParameter("lrNombre", "lugar1");
		List<LugarReciclaje> lrRes = qLugarReciclaje.getResultList();
		Timestamp timestamp = Timestamp.valueOf("2019-01-01 00:00:00.0");
		Timestamp timestamp2 = Timestamp.valueOf("2019-10-21 00:00:00.0");

		Query q = entityManager.createQuery(
				"FROM Historial h WHERE h.lugarReciclaje = :lr AND h.fechaReciclaje BETWEEN :date1 AND :date2");
		q.setParameter("lr", lrRes.get(0));
		q.setParameter("date1", timestamp);
		q.setParameter("date2", timestamp2);
		List<Historial> historialItems = q.getResultList();
		System.out.println("items del punto de reciclaje con nombre " + lugarReciclajeAPersistir.getNombre()
				+ " entre 1 ene y 21 oct: ");
		historialItems.stream().forEach(elem -> {
			System.out.print(elem.getItem() + " ");
			System.out.println(elem.getFechaReciclaje());
		});
		assertTrue(historialItems.size() > 0);
//		entityManager.close();
//		assertTrue(1 == 1);
	}
}
