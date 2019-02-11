package persistencia;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.Proveedor;
import modelo.TipoGarrafa;

public class ProveedorDAO {
	
	private static Proveedor Construir(List<HashMap<String, Object>> list) {
		Object cuit = list.get(0).get("cuit");
		Object razonSocial = list.get(0).get("razon_social");
		Object nombre = list.get(0).get("nombre");
		Object apellido = list.get(0).get("apellido");
		Object telefono = list.get(0).get("telefono");
		Object email = list.get(0).get("email");
		
		Proveedor proveedor = new Proveedor();
		
		proveedor.setApellido((String) apellido);
		proveedor.setNombre((String) nombre);
		proveedor.setRazonSocial((String) razonSocial);
		proveedor.setCuit((String) cuit);
		proveedor.setEmail((String) email);
		proveedor.setTelefono((String) telefono);
		
		ArrayList<TipoGarrafa> productos = new ArrayList<TipoGarrafa>();
		for (HashMap<String, Object> row : list) {
			Object id = row.get("id");
			Object descripcion = row.get("descripcion");
			BigDecimal stock = (BigDecimal) row.get("stock");
			Object precio = row.get("precio");
			Object fecha = row.get("fecha");
			
			TipoGarrafa tipoGarrafa = new TipoGarrafa();
			tipoGarrafa.setId((int) id);
			tipoGarrafa.setDescripcion((String) descripcion);
			tipoGarrafa.setStock(stock.intValue());
			tipoGarrafa.setPrecio((double) precio);
			tipoGarrafa.setFecha((Date) fecha);
			
			productos.add(tipoGarrafa);
		}
		proveedor.setProductos(productos);
		
		return proveedor;
	}
	
	public static ArrayList<Proveedor> GetAll(){
		ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();
		final String consulta = "call SelectAllProveedores()";
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta);
		
		int index = 0;
		do {
			String cuit = (String) results.get(index).get("cuit");
			int from = index;
			boolean comparacion = true;
			while (comparacion && (index < results.size())) {
				comparacion = results.get(index).get("cuit").equals(cuit); //Comparamos el registro actual con el siguiente para saber si pertenecen al mismo proveedor
				if (comparacion) index++;
			}
			Proveedor proveedor = Construir(results.subList(from, index));
			listaProveedores.add(proveedor);
		} while (index < results.size());
				
		return listaProveedores;
	}
}
