package historial;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import lugarreciclaje.LugarReciclaje;
import puntolimpio.EMF;
import puntolimpio.ImplDAO;

public class HistorialDAO extends ImplDAO<Historial, Integer>{
	private static HistorialDAO daoHistorial;
	
	private HistorialDAO() {
		super(Historial.class, Integer.class);
	}
    
	public static HistorialDAO getInstance() {
		if(daoHistorial == null)
			daoHistorial = new HistorialDAO();
		return daoHistorial;
	}
	
	public List<Historial> itemsByRecycleSiteAndRangeOfDates(int id, Timestamp startingDate, Timestamp endingDate) {
		EntityManager entityManager= EMF.createEntityManager();
		Query qLugarReciclaje = entityManager.createQuery("FROM LugarReciclaje lr WHERE lr.id = :id");
		qLugarReciclaje.setParameter("id", id);
		List<LugarReciclaje> lrRes = qLugarReciclaje.getResultList();
	
		Query q = entityManager.createQuery(
				"FROM Historial h WHERE h.lugarReciclaje = :lr AND h.fechaReciclaje BETWEEN :startingDate AND :endingDate");
		q.setParameter("lr", lrRes.get(0));
		q.setParameter("startingDate", startingDate);
		q.setParameter("endingDate", endingDate);
		List<Historial> historialItems = q.getResultList();
		entityManager.close();
		return historialItems;
		
	}

	public Integer ahorroONG() {
		EntityManager entityManager= EMF.createEntityManager();
		int ahorro=0;
		Query qHist = entityManager.createQuery("FROM Historial");
		List<Historial> historiales = qHist.getResultList();
		
		for (Historial historial : historiales) {
			System.out.println(historial.getReporte().getItem().getVolumen() +" " +historial.getCantidad() );
			ahorro+=(historial.getReporte().getItem().getVolumen() * historial.getCantidad());
		}
		entityManager.close();
		return ahorro;
	}
	
	public Integer ahorroONGAndRangeOfDates(Timestamp startingDate, Timestamp endingDate) {
		EntityManager entityManager= EMF.createEntityManager();
		int ahorro=0;
		Query q = entityManager.createQuery("FROM Historial h AND h.fechaReciclaje BETWEEN :startingDate AND :endingDate");
		q.setParameter("startingDate", startingDate);
		q.setParameter("endingDate", endingDate);
		List<Historial> historiales = q.getResultList();
		
		for (Historial historial : historiales) {
			System.out.println(historial.getReporte().getItem().getVolumen() +" " +historial.getCantidad() );
			ahorro+=(historial.getReporte().getItem().getVolumen() * historial.getCantidad());
		}
		entityManager.close();
		return ahorro;
	}
}
