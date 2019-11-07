package puntolimpio;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
