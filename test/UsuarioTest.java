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

import puntolimpio.Usuario;
import puntolimpio.UsuarioDAO;
public class UsuarioTest {
	private static EntityManagerFactory emf;

	@BeforeClass
	public static void init() {

	  Map<String, String> properties = new HashMap<>();

		properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
		properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/puntolimpio?createDatabaseIfNotExist=true");
		properties.put("javax.persistence.jdbc.user", "root");
		properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");

		properties.put("hibernate.show_sql", "true");

		emf = Persistence.createEntityManagerFactory("my_persistence_unit", properties);
		
	}
	
	@AfterClass
	public static void close() {
		emf.close();
	}
	
	static UsuarioDAO userDAO = UsuarioDAO.getInstance();
	
	@Test
	public void usuarioTest() {
		assertEquals(1, 1);
	}
	@Test
	public void usuarioTestFail() {
		assertEquals(1, 2);
	}
		
	@Test
	public void usuarioPersistTest() {
		EntityManager entityManager = emf.createEntityManager();

		Usuario u = new Usuario();
		u.setId(0);
		u.setNombre("Pepito");
		u.setLatitude(10);
		u.setLongitude(90);
		entityManager.getTransaction().begin();
		entityManager.persist(u);
		entityManager.getTransaction().commit();
		entityManager.close();

		assertNotNull(u.getId());
	}
	
	@Test
	public void usuarioFindAllTest() {
		EntityManager entityManager = emf.createEntityManager();
		Query q = entityManager.createQuery("FROM Usuario");
		List<Usuario> usuarios = q.getResultList();
		usuarios.stream().forEach(e -> System.out.println(e));
		entityManager.close();
		
		assertNotNull(usuarios);
	}

	@Test
	public void usuarioFindByIdTest() {
		int id = 1;
		EntityManager entityManager = emf.createEntityManager();
		Usuario usuario = entityManager.find(Usuario.class, 1);
		System.out.println(usuario);
		entityManager.close();
		
		assertTrue(usuario.getId() == 1);
	}
}
