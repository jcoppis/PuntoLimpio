package models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Itinerario {

	@Id
	@GeneratedValue
	private int id;
	
	private int idCamion;

	@ManyToOne
	private PuntoRecoleccion puntoRecoleccion;
	
	@ManyToOne
	private LugarReciclaje lugarReciclaje;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
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


	public PuntoRecoleccion getPuntoRecoleccion() {
		return puntoRecoleccion;
	}


	public void setPuntoRecoleccion(PuntoRecoleccion puntoRecoleccion) {
		this.puntoRecoleccion = puntoRecoleccion;
	}
	
	public LugarReciclaje getLugarReciclaje() {
		return lugarReciclaje;
	}

	public void setLugarReciclaje(LugarReciclaje lugarReciclaje) {
		this.lugarReciclaje = lugarReciclaje;
	}


	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
}