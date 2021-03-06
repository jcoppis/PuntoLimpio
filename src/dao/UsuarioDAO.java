package dao;

import models.Usuario;
import puntolimpio.ImplDAO;

public class UsuarioDAO extends ImplDAO<Usuario, Integer> {

	private static UsuarioDAO daoUsuario;
	
	private UsuarioDAO() {
		super(Usuario.class, Integer.class);
	}
    
	public static UsuarioDAO getInstance() {
		if(daoUsuario == null)
			daoUsuario = new UsuarioDAO();
		return daoUsuario;
	}
}
