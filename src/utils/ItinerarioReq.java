package utils;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ItinerarioReq {
	private int camionId;
	private int puntoRecoleccionId;
	private int lugarReciclajeId;

	private Timestamp fecha;

	public int getCamionId() {
		return camionId;
	}

	public void setCamionId(int camionId) {
		this.camionId = camionId;
	}

	public int getPuntoRecoleccionId() {
		return puntoRecoleccionId;
	}

	public void setPuntoRecoleccionId(int puntoRecoleccionId) {
		this.puntoRecoleccionId = puntoRecoleccionId;
	}

	public int getLugarReciclajeId() {
		return lugarReciclajeId;
	}

	public void setLugarReciclajeId(int lugarReciclajeId) {
		this.lugarReciclajeId = lugarReciclajeId;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
}