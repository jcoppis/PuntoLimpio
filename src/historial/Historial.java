package historial;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import item.Item;
import lugarreciclaje.LugarReciclaje;

@Entity
@Table(name="historial")
public class Historial {
	
	@Id
	@GeneratedValue
	private int id;
	
	
	@ManyToOne
	private Item item;
	@ManyToOne
	private LugarReciclaje lugarReciclaje;

	@Column(name="fecha_reciclaje")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Timestamp fechaReciclaje;
	
	private int cantidad;
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LugarReciclaje getlReciclaje() {
		return lugarReciclaje;
	}

	public void setlReciclaje(LugarReciclaje lReciclaje) {
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
		return "Historial [id=" + id + ", item=" + item + ", lugarReciclaje=" + lugarReciclaje + ", fechaReciclaje="
				+ fechaReciclaje + "]";
	}

			
}
