package historial;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import item.Item;
import lugarreciclaje.LugarReciclaje;
import reporte.Reporte;

@Entity
@Table(name="historial")
public class Historial {
	
	@Id
	@GeneratedValue
	private int id;

	@OneToOne
	private Reporte reporte;

	@ManyToOne
	private LugarReciclaje lugarReciclaje;

	@Column(name="fecha_reciclaje")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Timestamp fechaReciclaje;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}

	public LugarReciclaje getLugarReciclaje() {
		return lugarReciclaje;
	}

	public void setLugarReciclaje(LugarReciclaje lReciclaje) {
		this.lugarReciclaje = lReciclaje;
	}

	public Timestamp getFechaReciclaje() {
		return fechaReciclaje;
	}

	public void setFechaReciclaje(Timestamp fechaReciclaje) {
		this.fechaReciclaje = fechaReciclaje;
	}

	@Override
	public String toString() {
		return "Historial [id=" + id + ", reporte=" + reporte + ", lugarReciclaje=" + lugarReciclaje + ", fechaReciclaje="
				+ fechaReciclaje + "]";
	}
}
