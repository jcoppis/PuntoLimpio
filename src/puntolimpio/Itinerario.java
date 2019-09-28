package puntolimpio;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Itinerario {

	@Id
	@GeneratedValue
	private int id;
	
	private int idCamion;
	private int fkPuntoRecoleccion;
	private Timestamp fecha;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCamion() {
		return idCamion;
	}
	public void setIdCamion(int idCamion) {
		this.idCamion = idCamion;
	}
	public int getFkPuntoRecoleccion() {
		return fkPuntoRecoleccion;
	}
	public void setFkPuntoRecoleccion(int fkPuntoRecoleccion) {
		this.fkPuntoRecoleccion = fkPuntoRecoleccion;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

}
