package dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Itinerario;
import models.PuntoRecoleccion;
import puntolimpio.EMF;
import puntolimpio.ImplDAO;

public class ItinerarioDAO extends ImplDAO<Itinerario, Integer> {

	private static ItinerarioDAO daoItinerario;
	
	private ItinerarioDAO() {
		super(Itinerario.class, Integer.class);
	}
    
	public static ItinerarioDAO getInstance() {
		if(daoItinerario == null)
			daoItinerario = new ItinerarioDAO();
		return daoItinerario;
	}
	
	public void persist(int idCamion, Timestamp fecha, int puntoRecoleccionId) {
		EntityManager entityManager = EMF.createEntityManager();

//		Query q = entityManager.createNativeQuery("INSERT INTO Itinerario i (idCamion, fecha, puntoRecoleccion_id) VALUES (:idCamion, :fecha, :puntoRecoleccionId)");
		
		PuntoRecoleccion puntoRecoleccion = PuntoRecoleccionDAO.getInstance().findById(puntoRecoleccionId);
		
		if(puntoRecoleccion != null) {
			Itinerario i = new Itinerario();
			i.setId(0);
			i.setIdCamion(idCamion);
			i.setFecha(fecha);
			i.setPuntoRecoleccion(puntoRecoleccion);
			persist(i);
		}
		entityManager.close();
	}
	
	public void llevarItemsLugarMasCercano() {
		
	}
	
	public List<Itinerario> getItinerario(PuntoRecoleccion puntoRecoleccion){
		EntityManager entityManager = EMF.createEntityManager();

		Query q = entityManager.createQuery("FROM Itinerario i WHERE i.puntoRecoleccion = :puntoRecoleccion");
		q.setParameter("puntoRecoleccion", puntoRecoleccion);
		List<Itinerario> itinerario = q.getResultList();
		entityManager.close();
		return itinerario;
	}

}
