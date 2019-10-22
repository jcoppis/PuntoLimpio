package puntolimpio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Item {
	@Id
	@GeneratedValue
	private int id;
	
	private int volumen;
	private String tipo;
	private String nombre;
	
//	@OneToMany(mappedBy = "item", fetch=FetchType.LAZY)
//	private List<UserItem> userItems;

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

//	public List<UserItem> getUserItems() {
//		return userItems;
//	}
//
//	public void setUserItems(List<UserItem> userItems) {
//		this.userItems = userItems;
//	}
}
