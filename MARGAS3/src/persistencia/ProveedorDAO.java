package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Proveedor;

public class ProveedorDAO {
	
	public static ArrayList<Proveedor> GetAll(){
		ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();
		final String consulta = "call SelectAllProveedores()";
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta);
		
		Proveedor proveedor = null;
		for (HashMap<String, Object> row : results) {
			Object cuit = row.get("cuit");
			Object razonSocial = row.get("razon_social");
			Object nombre = row.get("nombre");
			Object apellido = row.get("apellido");
			Object telefono = row.get("telefono");
			Object email = row.get("email");
			
			proveedor = new Proveedor();
			
			proveedor.setApellido((String) apellido);
			proveedor.setNombre((String) nombre);
			proveedor.setRazonSocial((String) razonSocial);
			proveedor.setCuit((String) cuit);
			proveedor.setEmail((String) email);
			proveedor.setTelefono((String) telefono);
			
			listaProveedores.add(proveedor);
		}
		
		return listaProveedores;
	}
}
