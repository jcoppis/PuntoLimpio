package dao;

import java.util.List;

import models.LugarReciclaje;
import models.PuntoRecoleccion;
import puntolimpio.ImplDAO;

public class LugarReciclajeDAO extends ImplDAO<LugarReciclaje, Integer> {
	private static LugarReciclajeDAO daoLR;

	private LugarReciclajeDAO() {
		super(LugarReciclaje.class, Integer.class);
	}

	public static LugarReciclajeDAO getInstance() {
		if (daoLR == null)
			daoLR = new LugarReciclajeDAO();
		return daoLR;
	}
	
	public LugarReciclaje getLugarMasCercano(double latitude, double longitude) {
        List<LugarReciclaje> lugarReciclaje = daoLR.findAll();
        
        LugarReciclaje minLugarReciclaje = null;
        double cercania = 0;
        for(LugarReciclaje l : lugarReciclaje) {
        	if (minLugarReciclaje == null) { 
        		minLugarReciclaje = l; 
        		cercania = (l.getLatitude() - latitude) * (l.getLatitude() - latitude) + (l.getLongitude() - longitude) * (l.getLongitude() - longitude);
        	}
        	
        	if (((l.getLatitude() - latitude) * (l.getLatitude() - latitude) + (l.getLongitude() - longitude) * (l.getLongitude() - longitude))
        			< cercania) {        		
        		minLugarReciclaje = l;
        		cercania = (l.getLatitude() - latitude) * (l.getLatitude() - latitude) + (l.getLongitude() - longitude) * (l.getLongitude() - longitude);
        	}
        }
        return minLugarReciclaje;
    }
}
