package dao;

import java.util.List;

import models.PuntoRecoleccion;
import puntolimpio.ImplDAO;

public class PuntoRecoleccionDAO extends ImplDAO<PuntoRecoleccion, Integer> {

	private static PuntoRecoleccionDAO daoPuntoRecoleccion;

	private PuntoRecoleccionDAO() {
		super(PuntoRecoleccion.class, Integer.class);
	}

	public static PuntoRecoleccionDAO getInstance() {
		if (daoPuntoRecoleccion == null)
			daoPuntoRecoleccion = new PuntoRecoleccionDAO();
		return daoPuntoRecoleccion;
	}

	public PuntoRecoleccion getPuntoMasCercano(double latitude, double longitude) {
        List<PuntoRecoleccion> puntosRecoleccion = daoPuntoRecoleccion.findAll();
        
        PuntoRecoleccion minPuntoRecoleccion = null;
        double cercania = 0;
        for(PuntoRecoleccion p : puntosRecoleccion) {
        	if (minPuntoRecoleccion == null) { 
        		minPuntoRecoleccion = p; 
        		cercania = (p.getLatitude() - latitude) * (p.getLatitude() - latitude) + (p.getLongitude() - longitude) * (p.getLongitude() - longitude);
        	}
        	
        	if (((p.getLatitude() - latitude) * (p.getLatitude() - latitude) + (p.getLongitude() - longitude) * (p.getLongitude() - longitude))
        			< cercania) {        		
        		minPuntoRecoleccion = p;
        		cercania = (p.getLatitude() - latitude) * (p.getLatitude() - latitude) + (p.getLongitude() - longitude) * (p.getLongitude() - longitude);
        	}
        }
        return minPuntoRecoleccion;
    }
}
