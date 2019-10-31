package puntolimpio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
