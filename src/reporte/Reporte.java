package reporte;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import item.Item;
import puntorecoleccion.PuntoRecoleccion;
import usuario.Usuario;

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

	private int cantidad;
	private boolean recycled;

	public boolean isRecycled() {
		return recycled;
	}
	public void setRecycled(boolean recycled) {
		this.recycled = recycled;
	}
	@Column(name="fecha_reciclaje")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Timestamp fechaReciclaje;
	
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
				+ puntoRecoleccion + ", cantidad=" + cantidad + ", fechaReciclaje=" + fechaReciclaje + "]";
	}
}
