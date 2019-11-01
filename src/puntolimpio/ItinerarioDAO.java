package puntolimpio;

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

}
