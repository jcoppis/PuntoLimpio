package historial;

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
}
