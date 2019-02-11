package controladores;

import java.util.ArrayList;

import modelo.Compra;
import modelo.LineaDeCompra;
import modelo.LineaDePedido;
import modelo.Proveedor;
import modelo.TipoGarrafa;
import persistencia.CompraDAO;
import persistencia.ProveedorDAO;
import persistencia.TipoGarrafaDAO;

public class ControladorCompra {
	Compra compra;
	ArrayList<Proveedor> proveedores;
	
	public Compra getCompra() {
		return this.compra;
	}
	
	public ArrayList<Proveedor> iniciarCompra() {
		compra = new Compra();
		proveedores = ProveedorDAO.GetAll();
		
		this.setProveedor(proveedores.get(0).getCuit());
		
		return proveedores;
	}
	
	public void setProveedor(String cuit) {
		for (Proveedor proveedor : proveedores) {
			if (proveedor.getCuit().equals(cuit)) {
				this.compra.setProveedor(proveedor);
				break;
			}
		}
	}
	
	public Proveedor getProveedor() {
		return this.compra.getProveedor();
	}
	
	public void agregarProducto(int idProducto, int cantidad) {
		TipoGarrafa tipoGarrafa = TipoGarrafaDAO.GetOne(idProducto);
		if(tipoGarrafa != null) {
			this.compra.agregarLinea(tipoGarrafa, cantidad);
		}
	}

	public boolean registrarCompra() {
		ArrayList<LineaDeCompra> lineas = compra.getLineas(); 
		
		if (!lineas.isEmpty()) {
			CompraDAO.Save(this.compra);
			
			TipoGarrafa tipoGarrafa = null;
			int cantidad = 0;
			for (LineaDePedido lineaDePedido : lineas) {
				tipoGarrafa = lineaDePedido.getTipoGarrafa();
				cantidad = lineaDePedido.getCantidad();
				tipoGarrafa.actualizarStock(cantidad);
				
				TipoGarrafaDAO.UpdateStock(tipoGarrafa);
			}
		}
		
		return !lineas.isEmpty();
	}
}
