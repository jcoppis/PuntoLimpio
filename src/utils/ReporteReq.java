package utils;

public class ReporteReq {
	
	private int usuarioId;
	private int itemId;
	private int puntoRecoleccionId;
	private int cantidadItems;
	
	public int getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getPuntoRecoleccionId() {
		return puntoRecoleccionId;
	}
	public void setPuntoRecoleccionId(int puntoRecoleccionId) {
		this.puntoRecoleccionId = puntoRecoleccionId;
	}
	public int getCantidadItems() {
		return cantidadItems;
	}
	public void setCantidadItems(int cantidadItems) {
		this.cantidadItems = cantidadItems;
	}
}
