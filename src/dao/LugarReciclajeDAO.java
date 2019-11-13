package dao;

import models.LugarReciclaje;
import puntolimpio.ImplDAO;

public class LugarReciclajeDAO extends ImplDAO<LugarReciclaje, Integer> {
	private static LugarReciclajeDAO daoLR;

	private LugarReciclajeDAO() {
		super(LugarReciclaje.class, Integer.class);
	}

	public static LugarReciclajeDAO getInstance() {
		if (daoLR == null)
			daoLR = new LugarReciclajeDAO();
		return daoLR;
	}
}
