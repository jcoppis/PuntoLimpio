package puntolimpio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
public class PuntoRecoleccionDAO implements DAO<PuntoRecoleccion, Integer>{


		private static PuntoRecoleccionDAO daoPuntoRecoleccion;
		
		private PuntoRecoleccionDAO() {
			
		}
	    
		public static PuntoRecoleccionDAO getInstance() {
			if(daoPuntoRecoleccion == null)
				daoPuntoRecoleccion = new PuntoRecoleccionDAO();
			return daoPuntoRecoleccion;
		}
		
		@Override
		public PuntoRecoleccion findById(Integer id) {
			EntityManager entityManager=EMF.createEntityManager();
			PuntoRecoleccion puntoRecoleccion = entityManager.find(PuntoRecoleccion.class, id);
			entityManager.close();
			return puntoRecoleccion;
		}

		@Override
		public PuntoRecoleccion persist(PuntoRecoleccion puntoRecoleccion) {
			EntityManager entityManager=EMF.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(puntoRecoleccion);
			entityManager.getTransaction().commit();
			entityManager.close();
			return puntoRecoleccion;
		}

		@Override
		public List<PuntoRecoleccion> findAll() {
//			EntityManager entityManager=EMF.createEntityManager();
//			Query q = entityManager.createQuery("SELECT id, nombre, geolocalizacion FROM usuario;");
//			List<Usuario> items = q.getResultList();
//			entityManager.close();
			return null;
		}

		@Override
		public PuntoRecoleccion update(Integer id, PuntoRecoleccion newEntityValues) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean delete(Integer id) {
			// TODO Auto-generated method stub
			return false;
		}


}
