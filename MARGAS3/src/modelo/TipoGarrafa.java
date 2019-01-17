package modelo;

public class TipoGarrafa {
	private int id;
	private String descripcion;
	private int stock;
	private double precio;
	
	public TipoGarrafa(int id, String descripcion, int stock, double precio) {
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
	}
	
	public TipoGarrafa() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPrecio() {
		return this.precio;
	}

	public void actualizarStock(int cantidad) {
		this.stock += cantidad;
	}
}

