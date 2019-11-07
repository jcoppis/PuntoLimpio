package puntorecoleccion;

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

	public int volumenNecesario() {
		return 0;
	}
}
