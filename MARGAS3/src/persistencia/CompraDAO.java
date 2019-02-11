package persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.Compra;
import modelo.LineaDeCompra;
import modelo.TipoGarrafa;

public class CompraDAO {
	
	private static Compra Construir(List <HashMap<String, Object>> list) {
		Object id = list.get(0).get("id_compra");
		Object fecha = list.get(0).get("fecha");
		Object estado = list.get(0).get("estado");
		
		Compra compra = new Compra();
		compra.setId((long) id);
		compra.setFecha((Date) fecha);
		compra.setEstado((String) estado);
		
		ArrayList<LineaDeCompra> lineas = new ArrayList<LineaDeCompra>();
		for (HashMap<String, Object> row : list) {
			Object idGarrafa = row.get("id_garrafa");
			Object descripcion = row.get("descripcion"); 
			Object precio = row.get("precio");
			
			TipoGarrafa tipoGarrafa = new TipoGarrafa();
			tipoGarrafa.setId((int) idGarrafa);
			tipoGarrafa.setDescripcion((String) descripcion);
			tipoGarrafa.setPrecio((double) precio);
			
			Object cantidadComprada = row.get("cantidad_comprada");
			BigDecimal cantidadRestante = (BigDecimal) row.get("cantidad_restante");
			LineaDeCompra ldc = new LineaDeCompra(tipoGarrafa, (long) cantidadComprada);
			ldc.setCantidadRestante(cantidadRestante.intValue());
			
			lineas.add(ldc);
		}
		compra.setLineas(lineas);
		
		return compra;
	}
	
	public static void Save(Compra compra) {
		final String consulta1 = "call InsertCompra(?, ?)";
		
		String cuitProveedor = compra.getProveedor().getCuit();
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(cuitProveedor);
		params.add("activo");
		
		DataBase.Update(consulta1, params);
		params.clear();
		
		ArrayList<LineaDeCompra> lineas = compra.getLineas();
		for (LineaDeCompra linea : lineas) {
			final String consulta2 = "call InsertLineaDeCompra(?, ?)";
			
			int idTipoGarrafa = linea.getTipoGarrafa().getId();
			int cantidad = linea.getCantidad();
			
			params.add(idTipoGarrafa);
			params.add(cantidad);
			
			DataBase.Update(consulta2, params);
			params.clear();
		}
		
	}

	public static ArrayList<Compra> GetByEstado(String estado) {
		String consulta = "call SelectCompraByEstado(?)";
		ArrayList<Compra> compras = new ArrayList<Compra>();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(estado);
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta, params);
		
		int index = 0;
		do {
			long idCompra = (long) results.get(index).get("id_compra");
			int from = index;
			boolean comparacion = true;
			while (comparacion && (index < results.size())) {
				comparacion = results.get(index).get("id_compra").equals(idCompra); //Comparamos el registro actual con el siguiente para saber si pertenecen al mismo objeto
				if (comparacion) index++;
			}
			Compra compra = Construir(results.subList(from, index));
			compras.add(compra);
		} while (index < results.size());
		
		return compras;
	}
	
	public static ArrayList<Compra> GetByDate(Date fechaDesde, Date fechaHasta) {
		String consulta = "call SelectCompraByDate(?, ?)";
		ArrayList<Compra> compras = new ArrayList<Compra>();
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(fechaDesde);
		params.add(fechaHasta);
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta, params);
		
		if (!results.isEmpty()) {
			int index = 0;
			do {
				long idCompra = (long) results.get(index).get("id_compra");
				int from = index;
				boolean comparacion = true;
				while (comparacion && (index < results.size())) {
					comparacion = results.get(index).get("id_compra").equals(idCompra); //Comparamos el registro actual con el siguiente para saber si pertenecen al mismo objeto
					if (comparacion) index++;
				}
				Compra compra = Construir(results.subList(from, index));
				compras.add(compra);
			} while (index < results.size());
		}
		
		return compras;
	}

	public static void Update(Compra compra) {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(compra.getId());
		params.add(compra.getEstado());
		
		DataBase.Update("call UpDateCompra", params);
	}
}
