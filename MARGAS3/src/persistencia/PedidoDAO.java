/**
 * Paquete de modelo
 */
package persistencia;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import modelo.LineaDePedido;
import modelo.LineaDeVenta;
import modelo.Pedido;
import modelo.TipoGarrafa;

/**
 * Clase encargada de manejar el acceso a los datos 
 * registrados en la BD de los objetos de la clase Pedido. 
 * @author Seba
 *
 */
public class PedidoDAO {
	
	private static Pedido Construir(List<HashMap<String, Object>> list) {
		Object nro_pedido = list.get(0).get("nro_pedido");
		Object direccion = list.get(0).get("direccion");
		Object fecha = list.get(0).get("fecha");
		Object dni = list.get(0).get("dni");
		
		Pedido pedido = new Pedido((String) dni);
		pedido.setNro_pedido((int) nro_pedido);
		pedido.setDireccion((String) direccion);
		pedido.setFecha((Date) fecha);
		
		for (HashMap<String, Object> row : list) {
			Object id = row.get("id");
			Object descripcion = row.get("descripcion");
			Object precio = row.get("precio");
			
			TipoGarrafa tipoGarrafa = new TipoGarrafa();
			tipoGarrafa.setId((int) id);
			tipoGarrafa.setDescripcion((String) descripcion);
			tipoGarrafa.setPrecio((double) precio);
			
			int cantidad = (int) row.get("cantidad");
			LineaDePedido ldp = new LineaDePedido(tipoGarrafa, cantidad);
			
			pedido.agregarLinea(ldp);
		}
		
		return pedido;
	}
	
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
		
		ArrayList<LineaDeVenta> lineasDeVenta = pedido.getLineasDeVenta();
		for (LineaDeVenta lineaDeVenta : lineasDeVenta) {
			params.add(lineaDeVenta.getTipoGarrafa().getId());	//ID del producto
			params.add(lineaDeVenta.getCompra().getId());		//ID de la compra
			params.add(lineaDeVenta.getCantidad());				//Cantidad de unidades que se tomaron de la compra
			DataBase.Update("call InsertLineaDeVenta(?, ?, ?)", params);
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

	public static ArrayList<Pedido> GetByDate(Date fechaDesde, Date fechaHasta) {
		ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();
		String consulta = "call SelectPedidosByDate(?, ?)";
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(fechaDesde);
		params.add(fechaHasta);
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta, params);
		
		if (!results.isEmpty()) {
			int index = 0;
			do {
				int nro_pedido = (int) results.get(index).get("nro_pedido");
				int from = index;
				boolean comparacion = true;
				while (comparacion && (index < results.size())) {
					comparacion = results.get(index).get("nro_pedido").equals(nro_pedido); //Comparamos el registro actual con el siguiente para saber si pertenecen al mismo proveedor
					if (comparacion) index++;
				}
				Pedido pedido = Construir(results.subList(from, index));
				listaPedidos.add(pedido);
			} while (index < results.size());
		}
		
		return listaPedidos;
	}
}
