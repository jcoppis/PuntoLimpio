package dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Historial;
import models.LugarReciclaje;
import models.Reporte;
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
	
	public Historial persist(int reportId, int lugarReciclajeId) {
		EntityManager entityManager = EMF.createEntityManager();
		
		Reporte reporte = ReporteDAO.getInstance().findById(reportId);
		LugarReciclaje lugarReciclaje = LugarReciclajeDAO.getInstance().findById(lugarReciclajeId);
		
		Historial h = new Historial();
		if(reporte != null && lugarReciclaje != null) {
			h.setId(0);
			h.setReporte(reporte);
			h.setLugarReciclaje(lugarReciclaje);
			Date d = new Date();
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			h.setFechaReciclaje(Timestamp.valueOf(sdf.format(d)));
			persist(h);
		}
		entityManager.close();
		return h;
	}
	
	public List<Historial> itemsByRecycleSite(int id) {
		EntityManager entityManager= EMF.createEntityManager();
		Query qLugarReciclaje = entityManager.createQuery("FROM LugarReciclaje lr WHERE lr.id = :id");
		qLugarReciclaje.setParameter("id", id);
		List<LugarReciclaje> lrRes = qLugarReciclaje.getResultList();
	
		System.out.println("//////////////" + lrRes.get(0));
		Query q = entityManager.createQuery(
				"FROM Historial h WHERE h.lugarReciclaje = :lr");
		q.setParameter("lr", lrRes.get(0));
		List<Historial> historialItems = q.getResultList();
		entityManager.close();
		return historialItems;		
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
		
		entityManager.close();
		return ahorro;
	}
}
