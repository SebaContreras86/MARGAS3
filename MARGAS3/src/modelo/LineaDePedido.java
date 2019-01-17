package modelo;

public class LineaDePedido {
	private TipoGarrafa tipoGarrafa;
	private int cantidad;
	
	public LineaDePedido(TipoGarrafa tipoGarrafa, int cantidad) {
		this.tipoGarrafa = tipoGarrafa;
		this.cantidad = cantidad;
	}
	
	public LineaDePedido() {
		// TODO Auto-generated constructor stub
	}

	public TipoGarrafa getTipoGarrafa() {
		return tipoGarrafa;
	}
	public void setTipoGarrafa(TipoGarrafa tipoGarrafa) {
		this.tipoGarrafa = tipoGarrafa;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getImporte() {
		return tipoGarrafa.getPrecio()*this.cantidad;
	}

	public boolean isFor(TipoGarrafa tipoGarrafa) {
		return this.getTipoGarrafa().getId() == tipoGarrafa.getId();
	}
}
