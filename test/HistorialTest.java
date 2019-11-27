import static org.junit.Assert.*;
import java.sql.Timestamp;
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

import dao.HistorialDAO;
import models.Historial;
import models.Item;
import models.LugarReciclaje;
import models.PuntoRecoleccion;
import models.Reporte;
import models.Usuario;

public class HistorialTest {
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

		LugarReciclaje lugarReciclajeAPersistir = new LugarReciclaje();

		lugarReciclajeAPersistir.setId(0);
		lugarReciclajeAPersistir.setLatitude(123.3);
		lugarReciclajeAPersistir.setLongitude(432.4);
		lugarReciclajeAPersistir.setNombre("lugar1");

		Item i1 = new Item();
		Item i2 = new Item();
		Item i3 = new Item();
		Item i4 = new Item();
		Item i5 = new Item();

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

		i5.setId(0);
		i5.setNombre("item5");
		i5.setTipo("carton");
		i5.setVolumen(150);

		Usuario user = new Usuario();
		user.setId(0);
		user.setLatitude(38.4161);
		user.setLongitude(63.6167);
		user.setNombre("Javier");

		Usuario user2 = new Usuario();
		user2.setId(0);
		user2.setLatitude(37.4161);
		user2.setLongitude(62.6167);
		user2.setNombre("Juan");

		Usuario user3 = new Usuario();
		user3.setId(0);
		user3.setLatitude(36.4161);
		user3.setLongitude(64.6167);
		user3.setNombre("Nelson");

		Usuario user4 = new Usuario();
		user4.setId(0);
		user4.setLatitude(38.0000);
		user4.setLongitude(63.1111);
		user4.setNombre("Manuel");

		Usuario user5 = new Usuario();
		user5.setId(0);
		user5.setLatitude(37.5678);
		user5.setLongitude(62.1234);
		user5.setNombre("Jorge");

		PuntoRecoleccion p1 = new PuntoRecoleccion();
		p1.setId(0);
		p1.setLatitude(222);
		p1.setLongitude(234);
		p1.setCantNecesariaParaRecoleccion(50);

		PuntoRecoleccion p2 = new PuntoRecoleccion();
		p2.setId(0);
		p2.setLatitude(222);
		p2.setLongitude(234);
		p2.setCantNecesariaParaRecoleccion(50);

		PuntoRecoleccion p3 = new PuntoRecoleccion();
		p3.setId(0);
		p3.setLatitude(222);
		p3.setLongitude(234);
		p3.setCantNecesariaParaRecoleccion(50);

		PuntoRecoleccion p4 = new PuntoRecoleccion();
		p4.setId(0);
		p4.setLatitude(222);
		p4.setLongitude(234);
		p4.setCantNecesariaParaRecoleccion(50);

		Timestamp time1 = Timestamp.valueOf("2019-01-01 00:00:00.0");
		Timestamp time2 = Timestamp.valueOf("2019-10-24 00:00:00.0");
		Timestamp time3 = Timestamp.valueOf("2019-01-26 00:00:00.0");
		Timestamp time4 = Timestamp.valueOf("2019-04-03 00:00:00.0");

		Reporte Ui1 = new Reporte();
		Ui1.setId(0);
		Ui1.setCantidadItems(2);
		Ui1.setFechaReciclaje(time1);
		Ui1.setItem(i1);
		Ui1.setPuntoRecoleccion(p2);
		Ui1.setUsuario(user2);

		Reporte Ui2 = new Reporte();
		Ui2.setId(0);
		Ui2.setCantidadItems(2);
		Ui2.setFechaReciclaje(time3);
		Ui2.setItem(i4);
		Ui2.setPuntoRecoleccion(p1);
		Ui2.setUsuario(user2);

		Reporte Ui3 = new Reporte();
		Ui3.setId(0);
		Ui3.setCantidadItems(3);
		Ui3.setFechaReciclaje(time4);
		Ui3.setItem(i4);
		Ui3.setPuntoRecoleccion(p3);
		Ui3.setUsuario(user2);

		Reporte Ui4 = new Reporte();
		Ui4.setId(0);
		Ui4.setCantidadItems(1);
		Ui4.setFechaReciclaje(time2);
		Ui4.setItem(i3);
		Ui4.setPuntoRecoleccion(p4);
		Ui4.setUsuario(user2);

		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(i3);
		entityManager.persist(i4);
		entityManager.persist(i5);

		entityManager.persist(user);
		entityManager.persist(user2);
		entityManager.persist(user3);
		entityManager.persist(user4);
		entityManager.persist(user5);

		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityManager.persist(p4);

		entityManager.persist(Ui1);
		entityManager.persist(Ui2);
		entityManager.persist(Ui3);
		entityManager.persist(Ui4);

		entityManager.persist(lugarReciclajeAPersistir);
		entityManager.getTransaction().commit();

		entityManager.getTransaction().begin();
		Historial historial = new Historial();
		historial.setReporte(Ui1);
		historial.setLugarReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist = Timestamp.valueOf("2019-02-01 00:00:00.0");
		historial.setFechaReciclaje(timestampHistorialPersist);
		entityManager.persist(historial);

		Historial historial2 = new Historial();
		historial2.setReporte(Ui2);
		historial2.setLugarReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist2 = Timestamp.valueOf("2019-02-02 00:00:00.0");
		historial2.setFechaReciclaje(timestampHistorialPersist2);
		entityManager.persist(historial2);

		Historial historial3 = new Historial();
		historial3.setReporte(Ui3);
		historial3.setLugarReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist3 = Timestamp.valueOf("2019-10-24 00:00:00.0");
		historial3.setFechaReciclaje(timestampHistorialPersist3);
		entityManager.persist(historial3);

		Historial historial4 = new Historial();
		historial4.setReporte(Ui4);
		historial4.setLugarReciclaje(lugarReciclajeAPersistir);
		Timestamp timestampHistorialPersist4 = Timestamp.valueOf("2019-10-20 00:00:00.0");
		historial4.setFechaReciclaje(timestampHistorialPersist4);
		entityManager.persist(historial4);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	@AfterClass
	public static void close() {
		emf.close();
	}

	static HistorialDAO itemDao = HistorialDAO.getInstance();

	@Before
	public void createEm() {
		entityManager = emf.createEntityManager();
	}

	@After
	public void closeEm() {
		entityManager.close();
	}

	@Test
	public void itemsByRecycleSiteAndRangeOfDates() {

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
//		System.out.println("items del punto de reciclaje con nombre " + lugarReciclajeAPersistir.getNombre()
//				+ " entre 1 ene y 21 oct: ");
		historialItems.stream().forEach(elem -> {
			System.out.print(elem.getReporte().getItem() + " ");
			System.out.println(elem.getFechaReciclaje());
		});
		assertTrue(historialItems.size() == 3);
//		entityManager.close();
//		assertTrue(1 == 1);
	}

	@Test
	public void ahorroONG() {
		int ahorro = 0;
		Query qHist = entityManager.createQuery("FROM Historial");
		List<Historial> historiales = qHist.getResultList();

		for (Historial historial : historiales) {
			System.out.println(
					historial.getReporte().getItem().getVolumen() + " " + historial.getReporte().getCantidadItems());
			ahorro += (historial.getReporte().getItem().getVolumen() * historial.getReporte().getCantidadItems());
		}
		System.out.println(ahorro);
		assertEquals(ahorro, 1990);

	}
}
