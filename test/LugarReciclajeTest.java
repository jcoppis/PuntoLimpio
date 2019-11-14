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

import models.LugarReciclaje;
import puntolimpio.EMF;

public class LugarReciclajeTest {
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

		properties.put("hibernate.show_sql", "true");

		emf = Persistence.createEntityManagerFactory("my_persistence_unit", properties);

	}

	@AfterClass
	public static void close() {
		emf.close();
	}

	@Before
	public void createEm() {
		entityManager = emf.createEntityManager();
	}

	@After
	public void closeEm() {
		entityManager.close();
	}

	@Test
	public void getPuntoMasCercano() {
		double latitude = 38.4161;
		double longitude = 63.6167;

		LugarReciclaje l1 = new LugarReciclaje();
		l1.setId(0);
		l1.setNombre("a");
		l1.setLatitude(12);
		l1.setLongitude(14);

		LugarReciclaje l2 = new LugarReciclaje();
		l2.setId(0);
		l2.setNombre("b");
		l2.setLatitude(14);
		l2.setLongitude(17);

		LugarReciclaje l3 = new LugarReciclaje();
		l3.setId(0);
		l3.setNombre("c");
		l3.setLatitude(40);
		l3.setLongitude(60);

		entityManager.getTransaction().begin();
		entityManager.persist(l1);
		entityManager.persist(l2);
		entityManager.persist(l3);
		entityManager.getTransaction().commit();

		LugarReciclaje minLugarReciclaje = null;
		double cercania = 0;

		Query q = entityManager.createQuery("FROM LugarReciclaje");
		List<LugarReciclaje> lugaresReciclaje = q.getResultList();

		for (LugarReciclaje l : lugaresReciclaje) {
			if (minLugarReciclaje == null) {
				minLugarReciclaje = l;
				cercania = (l.getLatitude() - latitude) * (l.getLatitude() - latitude)
						+ (l.getLongitude() - longitude) * (l.getLongitude() - longitude);
			}

			if (((l.getLatitude() - latitude) * (l.getLatitude() - latitude)
					+ (l.getLongitude() - longitude) * (l.getLongitude() - longitude)) < cercania) {
				minLugarReciclaje = l;
				cercania = (l.getLatitude() - latitude) * (l.getLatitude() - latitude)
						+ (l.getLongitude() - longitude) * (l.getLongitude() - longitude);
			}
		}
		assertTrue(minLugarReciclaje.getLatitude() == l3.getLatitude());
		assertTrue(minLugarReciclaje.getLongitude() == l3.getLongitude());
	}
}
