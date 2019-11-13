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

import models.PuntoRecoleccion;
import puntolimpio.EMF;

public class PuntoRecoleccionTest {
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

		PuntoRecoleccion p1 = new PuntoRecoleccion();
		p1.setId(0);
		p1.setCantNecesariaParaRecoleccion(10);
		p1.setLatitude(12);
		p1.setLongitude(14);

		PuntoRecoleccion p2 = new PuntoRecoleccion();
		p2.setId(0);
		p2.setCantNecesariaParaRecoleccion(11);
		p2.setLatitude(14);
		p2.setLongitude(17);

		PuntoRecoleccion p3 = new PuntoRecoleccion();
		p3.setId(0);
		p3.setCantNecesariaParaRecoleccion(2);
		p3.setLatitude(40);
		p3.setLongitude(60);

		entityManager.getTransaction().begin();
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityManager.getTransaction().commit();

		PuntoRecoleccion minPuntoRecoleccion = null;
		double cercania = 0;

		Query q = entityManager.createQuery("FROM PuntoRecoleccion");
		List<PuntoRecoleccion> puntosRecoleccion = q.getResultList();

		for (PuntoRecoleccion p : puntosRecoleccion) {
			if (minPuntoRecoleccion == null) {
				minPuntoRecoleccion = p;
				cercania = (p.getLatitude() - latitude) * (p.getLatitude() - latitude)
						+ (p.getLongitude() - longitude) * (p.getLongitude() - longitude);
			}

			if (((p.getLatitude() - latitude) * (p.getLatitude() - latitude)
					+ (p.getLongitude() - longitude) * (p.getLongitude() - longitude)) < cercania) {
				minPuntoRecoleccion = p;
				cercania = (p.getLatitude() - latitude) * (p.getLatitude() - latitude)
						+ (p.getLongitude() - longitude) * (p.getLongitude() - longitude);
			}
		}
		assertTrue(minPuntoRecoleccion.getLatitude() == p3.getLatitude());
		assertTrue(minPuntoRecoleccion.getLongitude() == p3.getLongitude());
	}
}
