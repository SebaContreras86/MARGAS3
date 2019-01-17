package controladores;

import java.util.ArrayList;

import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.TipoGarrafa;
import persistencia.PedidoDAO;
import persistencia.TipoGarrafaDAO;

/**
 * Controlador asociado al Caso de Uso [Realizar pedido].
 * @author Seba
 *
 */
public class ControladorPedido {
	private Pedido pedido;
	
	/**
	 * 
	 * @return Pedido en curso
	 */
	public Pedido getPedido(){
		return this.pedido;
	}
	
	private ArrayList<TipoGarrafa> getTiposGarrafas() {
		ArrayList<TipoGarrafa> lista_tipos_garrafa = TipoGarrafaDAO.GetAll();
		
		return lista_tipos_garrafa;
	}
	
	/**
	 * Instancia un nuevo pedido.
	 * @param dni  DNI del usuario que realiza el pedido.
	 * @return Un ArrayList que contiene todos los Tipos de Garrafa registrados en la BD.
	 */
	public ArrayList<TipoGarrafa> iniciarPedido(String dni) {
		this.pedido = new Pedido(dni);
		
		return this.getTiposGarrafas();
	}
	
	/**
	 * Busca el producto con el ID solicitado en la BD y lo agrega al pedido.
	 * @param id_producto ID del producto que se agregar� al pedido.
	 * @param cantidad Cantidad de unidades de dicho producto.
	 */
	public void agregarProducto(int id_producto, int cantidad) {
		TipoGarrafa tipoGarrafa = TipoGarrafaDAO.GetOne(id_producto);
		if (tipoGarrafa != null) {
			LineaDePedido ldp = new LineaDePedido(tipoGarrafa, cantidad);
			this.pedido.agregarLinea(ldp);
		}
	}
	
	/**
	 * Env�a al pedido en curso el mensaje de eliminar la l�nea
	 * que contenga el producto con el ID especificado.
	 * @param id_producto ID del producto que se eliminar�.
	 */
	public void eliminarLinea(int id_producto) {
		this.pedido.eliminarLinea(id_producto);
	}
	
	/**
	 * Guarda el pedido en la BD s�lo si se han agregado productos al mismo y
	 * actualiza el stock de los productos comprados.
	 * @see PedidoDAO
	 * @see TipoGarrafaDAO 
	 * @return false si el pedido estaba vac�o, true en otro caso.
	 */
	public boolean enviar() {
		
		ArrayList<LineaDePedido> lineas = pedido.getLineas(); 
		
		if (!lineas.isEmpty()) {
			PedidoDAO.Save(this.pedido);
			
			TipoGarrafa tipoGarrafa = null;
			int cantidad = 0;
			for (LineaDePedido lineaDePedido : lineas) {
				tipoGarrafa = lineaDePedido.getTipoGarrafa();
				cantidad = lineaDePedido.getCantidad();
				tipoGarrafa.actualizarStock(-cantidad);
				
				TipoGarrafaDAO.UpdateStock(tipoGarrafa);
			}
		}
		
		return !lineas.isEmpty();
	}
}
