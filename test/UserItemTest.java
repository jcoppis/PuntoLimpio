import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import puntolimpio.Item;
import puntolimpio.LugarReciclaje;
import puntolimpio.Tipo;
import puntolimpio.UserItem;
import puntolimpio.UserItemDAO;
import puntolimpio.Usuario;

public class UserItemTest {
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
	
	static UserItemDAO userItemDAO = UserItemDAO.getInstance();

	@Test
	public void itemsByUser() {
		EntityManager entityManager=emf.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class,1);
		Query q = entityManager.createQuery("FROM UserItem ui WHERE ui.usuario = :user");
		q.setParameter("user", usuario);
		List<UserItem> userItems = q.getResultList();
		System.out.println("items del usuario con id 1: ");
		userItems.stream().forEach(elem -> {
			System.out.println(elem.getItem());
		});
		assertTrue(userItems.size() > 0);
	}
	
	@Test
	public void itemsByUserAndRangeOfDates() {
		EntityManager entityManager=emf.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class,1);
		//TODO: persist de un item por el "usuario". Por ahora usa los de la DB.
		Timestamp timestamp = Timestamp.valueOf("2019-01-01 00:00:00.0");
		Timestamp timestamp2 = Timestamp.valueOf("2019-10-21 00:00:00.0");

		Query q = entityManager.createQuery("FROM UserItem ui WHERE ui.usuario = :user AND ui.fechaReciclaje BETWEEN :date1 AND :date2");
		q.setParameter("user", usuario);
		q.setParameter("date1", timestamp);
		q.setParameter("date2", timestamp2);
		List<UserItem> userItems = q.getResultList();
		System.out.println("items del usuario con id 1 entre 1 ene y 21 oct: ");
		userItems.stream().forEach(elem -> {
			System.out.print(elem.getItem());
			System.out.println(elem.getFechaReciclaje());
		});
		assertTrue(userItems.size() > 0);
	}
	
}