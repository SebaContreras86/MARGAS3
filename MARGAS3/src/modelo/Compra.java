package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Compra {
	private long id;
	private Date fecha;
	private String estado;
	private Proveedor proveedor;
	private ArrayList<LineaDeCompra> lineas;
	
	public Compra() {
		lineas = new ArrayList<LineaDeCompra>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public Proveedor getProveedor() {
		return this.proveedor;
	}
	
	public ArrayList<LineaDeCompra> getLineas(){
		return this.lineas;
	}

	public void agregarLinea(TipoGarrafa tipoGarrafa, int cantidad) {
		LineaDeCompra ldp = new LineaDeCompra(tipoGarrafa, cantidad);
		boolean producto_incluido = false;
		for (LineaDeCompra lineaDeCompra : lineas) {
			producto_incluido = lineaDeCompra.isFor(ldp.getTipoGarrafa());
			if (producto_incluido) {
				lineaDeCompra.setCantidad(ldp.getCantidad());
				break;
			}
		}
		if (!producto_incluido) {
			this.lineas.add(ldp);
		}
	}

	public LineaDeVenta getLineaDeVenta(TipoGarrafa tipoGarrafa, int cantidad) {
		LineaDeVenta lineaDeVenta = null;
		for (LineaDeCompra lineaDeCompra : lineas) {
			if (lineaDeCompra.isFor(tipoGarrafa) && (lineaDeCompra.getCantidadRestante() > 0)) {
				lineaDeVenta = new LineaDeVenta(this, tipoGarrafa, lineaDeCompra.getNUnidades(cantidad));
				break;
			}
		}
		
		int totalUnidadesRestantes = 0;
		for (LineaDeCompra lineaDeCompra : lineas) {
			totalUnidadesRestantes +=  lineaDeCompra.getCantidadRestante();
		}
		if (totalUnidadesRestantes == 0) this.estado = "agotado";
		
		return lineaDeVenta;
	}

	public void setLineas(ArrayList<LineaDeCompra> lineas) {
		this.lineas = lineas;
	}
	
	public double getTotal() {
		double total = 0;
		for (LineaDeCompra linea : lineas) {
			total += linea.getImporte();
		}
		return total;
	}

}
