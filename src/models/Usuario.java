package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	private double latitude;
	private double longitude;

//	@OneToMany(mappedBy = "usuario", fetch=FetchType.LAZY)
//	private List<Reporte> reportes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public List<Reporte> getReportes() {
//		return reportes;
//	}
//
//	public void setReportes(List<Reporte> reportes) {
//		this.reportes = reportes;
//	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
