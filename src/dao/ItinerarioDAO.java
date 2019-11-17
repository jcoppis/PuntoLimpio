package dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Itinerario;
import models.LugarReciclaje;
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
	
	public void persist(int camionId, Timestamp fecha, int puntoRecoleccionId, int lugarReciclajeId) {
		EntityManager entityManager = EMF.createEntityManager();
		
		PuntoRecoleccion puntoRecoleccion = PuntoRecoleccionDAO.getInstance().findById(puntoRecoleccionId);
		LugarReciclaje lugarReciclaje = LugarReciclajeDAO.getInstance().findById(lugarReciclajeId);
		
		if(puntoRecoleccion != null && lugarReciclaje != null) {
			Itinerario i = new Itinerario();
			i.setId(0);
			i.setCamionId(camionId);
			i.setFecha(fecha);
			i.setPuntoRecoleccion(puntoRecoleccion);
			i.setLugarReciclaje(lugarReciclaje);
			persist(i);
		}
		entityManager.close();
	}
	
	public List<Itinerario> getItinerario(PuntoRecoleccion puntoRecoleccion){
		EntityManager entityManager = EMF.createEntityManager();

		Query q = entityManager.createQuery("FROM Itinerario i WHERE i.puntoRecoleccion = :puntoRecoleccion");
		q.setParameter("puntoRecoleccion", puntoRecoleccion);
		List<Itinerario> itinerario = q.getResultList();
		entityManager.close();
		return itinerario;
	}
	

	public void createItinerario(PuntoRecoleccion puntoRecoleccion) {
		int camionId = this.getCamionLibre();
		Timestamp fecha = this.getHorarioCamion(camionId, puntoRecoleccion.getLatitude(), puntoRecoleccion.getLongitude());
		LugarReciclaje l = LugarReciclajeDAO.getInstance().getLugarMasCercano(puntoRecoleccion.getLatitude(), puntoRecoleccion.getLongitude());
		
		this.persist(camionId, fecha, puntoRecoleccion.getId(), l.getId());
	}


	// usado como api externa por eso se pasa latitud y longitude en lugar des PuntoRecoleccion
	public Timestamp getHorarioCamion(int camionId, double latitude, double longitude) {
		return Timestamp.valueOf("2019-01-01 00:00:00.0");
	}
	
	// usado como api externa
	public int getCamionLibre() {
		return 1;
	}

}
