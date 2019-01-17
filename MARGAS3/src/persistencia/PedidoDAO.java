/**
 * Paquete de modelo
 */
package persistencia;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ListIterator;

import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.TipoGarrafa;

/**
 * Clase encargada de manejar el acceso a los datos 
 * registrados en la BD de los objetos de la clase Pedido. 
 * @author Seba
 *
 */
public class PedidoDAO {

	public static void Save(Pedido pedido) {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add("pendiente");
		params.add(pedido.getDni());
		DataBase.Update("call InsertPedido(?, ?)", params);
		
		params.clear();
		ArrayList<LineaDePedido> lineas = pedido.getLineas();
		for (LineaDePedido lineaDePedido : lineas) {
			params.add(lineaDePedido.getTipoGarrafa().getId());
			params.add(lineaDePedido.getCantidad());
			DataBase.Update("call InsertLineaDePedido(?, ?)", params);
			params.clear();
		}
	}

	public static ArrayList<Pedido> getAllByUsuario(String dni) {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(dni);
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure("call PedidosPendientes(?)", params);
		
		ArrayList<Pedido> lista_pedidos = new ArrayList<Pedido>();
		Pedido pedido = null;
		for (HashMap<String, Object> row : results) {
			Object fecha = row.get("fecha");
			Object estado = row.get("estado");
			Object hora = row.get("hora");
			Object nro_pedido = row.get("nro_pedido");
			
			pedido = new Pedido(dni);
			pedido.setFecha((Date) fecha);
			pedido.setEstado((String) estado);
			pedido.setHora((Time) hora);
			pedido.setNro_pedido((int) nro_pedido);
			
			lista_pedidos.add(pedido);
		}
		return lista_pedidos;
	}

	public static void UpdateEstado(int nro_pedido, String estado)  {
		String consulta = "call UpdateEstado(?, ?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(nro_pedido);
		params.add(estado);
		
		DataBase.Update(consulta, params);
	}

	public static ArrayList<Pedido> GetAllPedidosPendiente() {
		ArrayList<Pedido> pedidos_pendiente = new ArrayList<Pedido>();
		String consulta = "call TodosLosPedidosPendientes()";
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta);
		
		Pedido pedido = null;
		LineaDePedido lineaDePedido = null;
		TipoGarrafa tipoGarrafa = null;
		HashMap<String, Object> row = null;
		ListIterator<HashMap<String, Object>> iterator = results.listIterator();
		while(iterator.hasNext()) {
			
			// Se construye el pedido
			row = iterator.next();
			
			Object dni = row.get("dni");
			Object estado = row.get("estado");
			Object direccion = row.get("direccion");
			Object fecha = row.get("fecha");
			Object hora = row.get("hora");
			Object nro_pedido = row.get("nro_pedido");
			
			pedido = new Pedido((String) dni);
			pedido.setEstado((String) estado);
			pedido.setDireccion((String) direccion);
			pedido.setFecha((Date) fecha);
			pedido.setHora((Time) hora);
			pedido.setNro_pedido((int) nro_pedido);
			
			// Se construye cada una de las líneas del pedido
			boolean mismoPedido = false;
			do {
				Object cantidad = row.get("cantidad");
				
				lineaDePedido = new LineaDePedido();
				lineaDePedido.setCantidad((int) cantidad);
				
				// Se construye el TipoGarrafa que corresponde a la línea
				Object id = row.get("id");
				Object descripcion = row.get("descripcion");
				Object precio = row.get("precio");
				
				tipoGarrafa = new TipoGarrafa();
				tipoGarrafa.setId((int) id);
				tipoGarrafa.setDescripcion((String) descripcion);
				tipoGarrafa.setPrecio((double) precio);
				
				lineaDePedido.setTipoGarrafa(tipoGarrafa);
				pedido.agregarLinea(lineaDePedido);
				
				HashMap<String, Object> previousRow = row;
				if (iterator.hasNext()) {
					row = iterator.next();
					mismoPedido = (row.get("nro_pedido") == previousRow.get("nro_pedido"));
					if (!mismoPedido) iterator.previous();
				}
				else {
					mismoPedido = false;
				}
			} while(mismoPedido);
			
			pedidos_pendiente.add(pedido);
		}
		return pedidos_pendiente;
	}
}
