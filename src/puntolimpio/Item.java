package puntolimpio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private int id;
	
	private int volumen;
	private String tipo;
	private String nombre;
	
	@OneToMany(mappedBy = "item", fetch=FetchType.EAGER)
	private List<UserItem> userItems;
	
	@ManyToMany(mappedBy="items")
	private List<LugarReciclaje> lugaresDeReciclaje;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<UserItem> getUserItems() {
		return userItems;
	}

	public void setUserItems(List<UserItem> userItems) {
		this.userItems = userItems;
	}

	public List<LugarReciclaje> getLugaresDeReciclaje() {
		return lugaresDeReciclaje;
	}

	public void setLugaresDeReciclaje(List<LugarReciclaje> lugaresDeReciclaje) {
		this.lugaresDeReciclaje = lugaresDeReciclaje;
	}
	

}
