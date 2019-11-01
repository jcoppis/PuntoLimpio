import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import puntolimpio.EMF;
import puntolimpio.Item;
import puntolimpio.LugarReciclaje;
import puntolimpio.LugarReciclajeDAO;
import puntolimpio.UserItem;
import puntolimpio.itemDAO;

public class LugarReciclajeTest {
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
	
	static LugarReciclajeDAO lrDao = LugarReciclajeDAO.getInstance();
	
	
}
