package dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import models.Item;
import models.PuntoRecoleccion;
import models.Reporte;
import models.Usuario;
import puntolimpio.EMF;
import puntolimpio.ImplDAO;

public class ReporteDAO extends ImplDAO<Reporte, Integer> {

	private static ReporteDAO daoItem;

	private ReporteDAO() {
		super(Reporte.class, Integer.class);
	}

	public static ReporteDAO getInstance() {
		if (daoItem == null)
			daoItem = new ReporteDAO();
		return daoItem;
	}
	
	public void persist(int userId, int itemId, int puntoRecoleccionId, int cantidad) {
		EntityManager entityManager = EMF.createEntityManager();
		
		Usuario usuario = UsuarioDAO.getInstance().findById(userId);
		Item item = ItemDAO.getInstance().findById(itemId);
		PuntoRecoleccion puntoRecoleccion = PuntoRecoleccionDAO.getInstance().findById(puntoRecoleccionId);
		
		if(puntoRecoleccion != null && item != null && usuario != null) {
			Reporte r = new Reporte();
			r.setId(0);
			r.setUsuario(usuario);
			r.setItem(item);
			r.setPuntoRecoleccion(puntoRecoleccion);
			r.setCantidad(cantidad);
			Date d = new Date();
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			r.setFechaReciclaje(Timestamp.valueOf(sdf.format(d)));
			r.setRecycled(false);
			persist(r);
			
			if(this.isReadyForSend(puntoRecoleccion)) {
				ItinerarioDAO.getInstance().createItinerario(puntoRecoleccion);
			}
			
		}
		entityManager.close();
	}

	
	public List<Item> findItemsByUser(Usuario usuario) {
		EntityManager entityManager = EMF.createEntityManager();
		
		Query q = entityManager.createQuery("FROM Reporte ui WHERE ui.usuario = :user");
		q.setParameter("user", usuario);
		List<Reporte> reportes = q.getResultList();
		List<Item> items = reportes.stream().map(u -> u.getItem()).collect(Collectors.toList());
		return items;
	}

	public boolean isReadyForSend(PuntoRecoleccion pr) {
		EntityManager entityManager = EMF.createEntityManager();
		PuntoRecoleccion res = entityManager.find(PuntoRecoleccion.class, pr.getId());
		if (res != null) {
			int cantNec = res.getCantNecesariaParaRecoleccion();
			Query q = entityManager.createQuery("FROM Reporte ui WHERE ui.puntoRecoleccion = :prId");
			q.setParameter("prId", pr.getId());
			List<Reporte> reporte = q.getResultList();
			entityManager.close();
			int totalVolume = reporte.stream().mapToInt(x -> x.getItem().getVolumen()).sum();
			return totalVolume > cantNec;
		}

		return false;
	}
	
	public List<Item> findItemsByUserAndRangeOfDates(Usuario user, Timestamp time1, Timestamp time2) {
		EntityManager entityManager = EMF.createEntityManager();
		Query qUitem = entityManager.createQuery
				("FROM Reporte ui WHERE ui.usuario = :user AND ui.fechaReciclaje BETWEEN :date1 AND :date2");
		qUitem.setParameter("user", user);
		qUitem.setParameter("date1", time1);
		qUitem.setParameter("date2", time2);
		List<Reporte> reportes = qUitem.getResultList();
		List<Item> items = reportes.stream().map(u -> u.getItem()).collect(Collectors.toList());
		return items;
	}
	
	public int findAhorroPorFecha(Usuario user, Timestamp time1, Timestamp time2) {
		int ahorro=0;
		
		EntityManager entityManager = EMF.createEntityManager();
		Query qUitem = entityManager.createQuery
				("FROM Reporte ui WHERE ui.usuario = :user AND ui.fechaReciclaje BETWEEN :date1 AND :date2");
		qUitem.setParameter("user", user);
		qUitem.setParameter("date1", time1);
		qUitem.setParameter("date2", time2);
		List<Reporte> reportes = qUitem.getResultList();
		
		for (Reporte reporte : reportes) {
			ahorro+=(reporte.getItem().getVolumen()* reporte.getCantidad());
			//System.out.println(reporte.getItem().toString()); 
			}
		return ahorro;
	}
	
	public int findAhorroTotal(Usuario user) {
		int ahorro=0;
		
		EntityManager entityManager = EMF.createEntityManager();
		Query qUitem = entityManager.createQuery("FROM Reporte ui WHERE ui.usuario = :user");
		qUitem.setParameter("user", user);
		List<Reporte> reportes = qUitem.getResultList();
		for (Reporte reporte : reportes) {
			ahorro+=(reporte.getItem().getVolumen()* reporte.getCantidad());
		}
		return ahorro;
	}
}
