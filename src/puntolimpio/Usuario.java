package puntolimpio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	private double latitude;
	private double longitude;

//	@OneToMany(mappedBy = "usuario", fetch=FetchType.LAZY)
//	private List<UserItem> userItems;

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


//	public List<UserItem> getUserItems() {
//		return userItems;
//	}
//
//	public void setUserItems(List<UserItem> userItems) {
//		this.userItems = userItems;
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

	

}