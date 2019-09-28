package puntolimpio;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserItem {

	@Id
	@GeneratedValue
	private int id;
	
	private int fkUser;
	private int fkItem;
	private Timestamp fechaReciclaje;
}
