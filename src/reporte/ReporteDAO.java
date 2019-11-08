package reporte;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import item.Item;
import puntolimpio.EMF;
import puntolimpio.ImplDAO;
import puntorecoleccion.PuntoRecoleccion;
import usuario.Usuario;

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
	
	public List<Reporte> itemsByUserAndRangeOfDates(Usuario user, Timestamp time1, Timestamp time2) {
		EntityManager entityManager = EMF.createEntityManager();
		Query qUitem = entityManager.createQuery
				("FROM Reporte ui WHERE ui.usuario = :user AND ui.fechaReciclaje BETWEEN :date1 AND :date2");
		qUitem.setParameter("user", user);
		qUitem.setParameter("date1", time1);
		qUitem.setParameter("date2", time2);
		List<Reporte> reportes = qUitem.getResultList();
		return reportes;
	}
	
	public Integer ahorroPorFecha(Usuario user, Timestamp time1, Timestamp time2) {
		int ahorro=0;
		List<Reporte> reportes = this.itemsByUserAndRangeOfDates(user, time1, time2);

		for (Reporte reporte : reportes) {
			ahorro+=(reporte.getItem().getVolumen()* reporte.getCantidad());
			//System.out.println(reporte.getItem().toString()); 
			}
		return ahorro;
	}
	
	public Integer ahorroTotal(Usuario user) {
		EntityManager entityManager = EMF.createEntityManager();
		int ahorro=0;
		Query qUitem = entityManager.createQuery
				("FROM Reporte ui WHERE ui.usuario = :user");
		qUitem.setParameter("user", user);
		List<Reporte> reportes = qUitem.getResultList();
		for (Reporte reporte : reportes) {
			ahorro+=(reporte.getItem().getVolumen()* reporte.getCantidad());
		}
		return ahorro;
	}
}
