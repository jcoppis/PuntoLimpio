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
	
	private double latitude;
	private double longitude;

	@OneToMany(mappedBy="puntoRecoleccion")
	private List<UserItem> itemsArecolectar;

	
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
