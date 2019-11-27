package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="reporte")
public class Reporte {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Item item;

	@ManyToOne
	private PuntoRecoleccion puntoRecoleccion;

	private int cantidadItems;

	private boolean recycled;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Timestamp fechaReciclaje;

	public boolean isRecycled() {
		return recycled;
	}
	public void setRecycled(boolean recycled) {
		this.recycled = recycled;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public PuntoRecoleccion getPuntoRecoleccion() {
		return puntoRecoleccion;
	}
	public void setPuntoRecoleccion(PuntoRecoleccion puntoRecoleccion) {
		this.puntoRecoleccion = puntoRecoleccion;
	}
	public int getCantidadItems() {
		return cantidadItems;
	}
	public void setCantidadItems(int cantidadItems) {
		this.cantidadItems = cantidadItems;
	}
	public Timestamp getFechaReciclaje() {
		return fechaReciclaje;
	}
	public void setFechaReciclaje(Timestamp fechaReciclaje) {
		this.fechaReciclaje = fechaReciclaje;
	}
	@Override
	public String toString() {
		return "Reporte [id=" + id + ", usuario=" + usuario + ", item=" + item + ", puntoRecoleccion="
				+ puntoRecoleccion + ", cantidadItems=" + cantidadItems + ", fechaReciclaje=" + fechaReciclaje + "]";
	}
}
