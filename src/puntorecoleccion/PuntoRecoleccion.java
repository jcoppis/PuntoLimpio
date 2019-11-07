package puntorecoleccion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PuntoRecoleccion {

	@Id
	@GeneratedValue
	private int id;
	
	private double latitude;
	private double longitude;
	private int cantNecesariaParaRecoleccion;

//	@OneToMany(mappedBy="puntoRecoleccion", fetch=FetchType.LAZY)
//	private List<Reporte> itemsArecolectar;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


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

	public int getCantNecesariaParaRecoleccion() {
		return cantNecesariaParaRecoleccion;
	}

	public void setCantNecesariaParaRecoleccion(int cantNecesariaParaRecoleccion) {
		this.cantNecesariaParaRecoleccion = cantNecesariaParaRecoleccion;
	}

//	public List<Reporte> getItemsArecolectar() {
//		return itemsArecolectar;
//	}
//
//
//	public void setItemsArecolectar(List<Reporte> itemsArecolectar) {
//		this.itemsArecolectar = itemsArecolectar;
//	}

}
