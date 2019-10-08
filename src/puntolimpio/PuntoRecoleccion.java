package puntolimpio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PuntoRecoleccion {

	@Id
	@GeneratedValue
	private int id;
	
	private String geolocalizacion;

	@OneToMany(mappedBy="puntoRecoleccion")
	private List<UserItem> itemsArecolectar;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getGeolocalizacion() {
		return geolocalizacion;
	}


	public void setGeolocalizacion(String geolocalizacion) {
		this.geolocalizacion = geolocalizacion;
	}


	public List<UserItem> getItemsArecolectar() {
		return itemsArecolectar;
	}


	public void setItemsArecolectar(List<UserItem> itemsArecolectar) {
		this.itemsArecolectar = itemsArecolectar;
	}


	public int volumenNecesario() {
		return 0;
	}
}
