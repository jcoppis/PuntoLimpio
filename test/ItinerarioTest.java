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

import itinerario.Itinerario;
import puntorecoleccion.PuntoRecoleccion;

public class ItinerarioTest {
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
	public void getItinerario() {
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

		Timestamp timestamp = Timestamp.valueOf("2019-01-01 00:00:00.0");
		Timestamp timestamp2 = Timestamp.valueOf("2019-10-21 00:00:00.0");
		
		Itinerario i1 = new Itinerario();
		i1.setId(0);
		i1.setFecha(timestamp);
		i1.setIdCamion(1);
		i1.setPuntoRecoleccion(p1);
		
		Itinerario i2 = new Itinerario();
		i2.setId(0);
		i2.setFecha(timestamp2);
		i2.setIdCamion(2);
		i2.setPuntoRecoleccion(p2);
		

		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.getTransaction().commit();
		
		PuntoRecoleccion puntoRecoleccion = entityManager.find(PuntoRecoleccion.class, 1);
		System.out.println(puntoRecoleccion);
		
		Query q = entityManager.createQuery("FROM Itinerario i WHERE i.puntoRecoleccion = :puntoRecoleccion");
		q.setParameter("puntoRecoleccion", puntoRecoleccion);
		List<Itinerario> itinerario = q.getResultList();
		entityManager.close();
		
		assertTrue(itinerario.get(0).getIdCamion() == i1.getIdCamion());
		assertTrue(itinerario.get(0).getIdCamion() != i2.getIdCamion());
	}
}
