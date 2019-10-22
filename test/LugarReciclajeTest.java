import static org.junit.Assert.assertTrue;

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

import puntolimpio.EMF;
import puntolimpio.LugarReciclaje;
import puntolimpio.LugarReciclajeDAO;
import puntolimpio.UserItem;
import puntolimpio.itemDAO;

public class LugarReciclajeTest {
	private static EntityManagerFactory emf;

	@BeforeClass
	public static void init() {

	  Map<String, String> properties = new HashMap<>();

		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/puntolimpio?createDatabaseIfNotExist=true");
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.jdbc.password", "secret");
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");

		properties.put("hibernate.show_sql", "true");

		emf = Persistence.createEntityManagerFactory("my_persistence_unit", properties);
		
	}

	@AfterClass
	public static void close() {
		emf.close();
	}
	
	static LugarReciclajeDAO lrDao = LugarReciclajeDAO.getInstance();
	@Test
	public void itemsByRecycleSiteAndRangeOfDates() {
		EntityManager entityManager=emf.createEntityManager();
		LugarReciclaje lugarReciclajeAPersistir = new LugarReciclaje();
		LugarReciclaje lugarReciclaje = entityManager.find(LugarReciclaje.class,1);
		//TODO: persist de un item por el "usuario" en un "punto de Reciclaje". Por ahora usa los de la DB.
		Timestamp timestamp = Timestamp.valueOf("2019-01-01 00:00:00.0");
		Timestamp timestamp2 = Timestamp.valueOf("2019-10-21 00:00:00.0");
		
		Query q = entityManager.createQuery("FROM UserItem ui WHERE ui.lugarReciclaje = :lr AND ui.fechaReciclaje BETWEEN :date1 AND :date2");
		q.setParameter("lr", lugarReciclaje);
		q.setParameter("date1", timestamp);
		q.setParameter("date2", timestamp2);
		List<UserItem> userItems = q.getResultList();
		System.out.println("items del punto de reciclaje con id 1 entre 1 ene y 21 oct: ");
		userItems.stream().forEach(elem -> {
			System.out.print(elem.getItem());
			System.out.println(elem.getFechaReciclaje());
		});
		assertTrue(userItems.size() > 0);
	}
}
