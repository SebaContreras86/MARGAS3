package modelo;

import java.util.Date;

public class TipoGarrafa {
	private int id;
	private String descripcion;
	private int stock;
	private double precio;
	private Date fecha;
	
	public TipoGarrafa(int id, String descripcion, int stock, double precio) {
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
	}
	
	public TipoGarrafa() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TipoGarrafa)) {
			return false;
		}
		TipoGarrafa other = (TipoGarrafa) obj;
		if (id != other.id) {
			return false;
		}
		return true;
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
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

