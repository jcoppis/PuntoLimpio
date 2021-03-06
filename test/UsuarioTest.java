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

import dao.UsuarioDAO;
import models.Usuario;

public class UsuarioTest {
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

	}

	@AfterClass
	public static void close() {
		emf.close();
	}
	
	static UsuarioDAO userDAO = UsuarioDAO.getInstance();
	
	@Before
	public void createEm(){
		entityManager= emf.createEntityManager();
	}
	
	@After
	public void closeEm() {
		entityManager.close();
	}
	
	@Test
	public void usuarioFindByIdTest() {
		Usuario user = new Usuario();
		user.setId(0);
		user.setLatitude(38.4161);
		user.setLongitude(63.6167);
		user.setNombre("Javier");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();

		Usuario usuario = entityManager.find(Usuario.class, 1);
		System.out.println(usuario);
		entityManager.close();
		assertTrue(usuario.getId() == 1);
		assertEquals(usuario.getNombre(), "Javier");
		assertTrue(usuario.getLatitude() == 38.4161);
		assertTrue(usuario.getLongitude() == 63.6167);
	}

	@Test
	public void usuarioDeleteTest() {
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery("FROM Usuario u WHERE u.nombre = :usuario");
		q.setParameter("usuario", "Pepito");
		List<Usuario> usuarios = q.getResultList();
		usuarios.stream().forEach(e -> entityManager.remove(e));
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		Query q2 = entityManager.createQuery("FROM Usuario");
		List<Usuario> usuariosNoTest = q2.getResultList();
		entityManager.getTransaction().commit();
		assertTrue(usuariosNoTest.size() == 0);
	}
	
	
}


