package persistencia;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.TipoGarrafa;

public class TipoGarrafaDAO {
	
	/*	Este método devuelve un ArrayList con todos los tipos de garrafas
	 *  registrados en la base de datos invocando un procedimiento almacenado
	 */
	public static ArrayList<TipoGarrafa> GetAll() {
		ArrayList<TipoGarrafa> lista_tipos_garrafa = new ArrayList<TipoGarrafa>();

		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure("call SelectAllTipoGarrafa()");
		
		TipoGarrafa tipoGarrafa = null;
		for (HashMap<String, Object> row : results) {
			tipoGarrafa = new TipoGarrafa();
			
			Object id = row.get("id");
			Object descripcion = row.get("descripcion");
			Object precio = row.get("precio");
			BigDecimal stock = (BigDecimal) row.get("stock");
			
			tipoGarrafa.setId((int) id);
			tipoGarrafa.setDescripcion((String) descripcion);
			tipoGarrafa.setPrecio((double) precio);
			tipoGarrafa.setStock(stock.intValue());
			
			lista_tipos_garrafa.add(tipoGarrafa);
		}
		
		return lista_tipos_garrafa;
	}
	
	/*	Este método devuelve un tipo de garrafa
 	 *	registrado en la base de datos que coincida con el ID que se le pasa por parámetro
 	 *	invocando un procedimiento almacenado, o null si no lo encuentra	
 	 */
	public static TipoGarrafa GetOne(int id_producto) {
		String consulta = "{call SelectOneTipoGarrafa(?)}"; // nombre del procedimiento almacenado
		ArrayList<Object> params = new ArrayList<Object>();	// lista de parámetros que lleva la consulta
		params.add(id_producto);
		
		//La clase DataBase devuelve un ArrayList con la información contenida en el ResultSet
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta, params); 
		
		TipoGarrafa tipoGarrafa = null;
		if (!results.isEmpty()) {
			tipoGarrafa = new TipoGarrafa();
			tipoGarrafa.setId((int) results.get(0).get("id"));
			tipoGarrafa.setDescripcion((String) results.get(0).get("descripcion"));
			tipoGarrafa.setPrecio((double) results.get(0).get("precio"));
			BigDecimal stock = (BigDecimal) results.get(0).get("stock");
			tipoGarrafa.setStock(stock.intValue());
		}
		
		return tipoGarrafa;
	}

	public static void UpdateStock(TipoGarrafa tipoGarrafa)  {
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(tipoGarrafa.getStock());
		params.add(tipoGarrafa.getId());
		
		String consulta = "call UpdateStock(?, ?)";
		DataBase.Update(consulta, params);
	}

}
