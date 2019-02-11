package modelo;

public class LineaDeVenta {
	Compra compra;
	TipoGarrafa tipoGarrafa;
	int cantidad;
	
	public LineaDeVenta(Compra compra, TipoGarrafa tipoGarrafa, int cantidad) {
		this.compra = compra;
		this.tipoGarrafa = tipoGarrafa;
		this.cantidad = cantidad;
	}

	/**
	 * @return the compra
	 */
	public Compra getCompra() {
		return compra;
	}

	/**
	 * @param compra the compra to set
	 */
	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	/**
	 * @return the tipoGarrafa
	 */
	public TipoGarrafa getTipoGarrafa() {
		return tipoGarrafa;
	}

	/**
	 * @param tipoGarrafa the tipoGarrafa to set
	 */
	public void setTipoGarrafa(TipoGarrafa tipoGarrafa) {
		this.tipoGarrafa = tipoGarrafa;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
