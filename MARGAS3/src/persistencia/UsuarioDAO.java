package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Usuario;

public class UsuarioDAO {
	
	public static Usuario GetOne(String user_name, String password) {
		String consulta = "call SelectOneUsuario(?, ?)";
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(user_name);
		params.add(password);
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta, params); 
		
		Usuario usuario = null;
		if (!results.isEmpty()) {
			HashMap<String, Object> row = results.get(0);
			
			Object nombre_usuario = row.get("nombre_usuario");
			Object pass = row.get("password");
			Object tipo = row.get("tipo");
			Object nombre = row.get("nombre");
			Object apellido = row.get("apellido");
			Object email = row.get("email");
			Object dni = row.get("dni");
			Object telefono = row.get("telefono");
			Object direccion = row.get("direccion");
			
			usuario = new Usuario();
			
			usuario.setUsuario((String) nombre_usuario);
			usuario.setPassword((String) pass);
			usuario.setTipo((String) tipo);
			usuario.setNombre((String) nombre);
			usuario.setApellido((String) apellido);
			usuario.setEmail((String) email);
			usuario.setDni((String) dni);
			usuario.setTelefono((String) telefono);
			usuario.setDireccion((String) direccion);
		}
		return usuario;
	}

	public static void Save(Usuario usuario) {
		String consulta = "call InsertUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		ArrayList<Object> params = new ArrayList<Object>();
		
		params.add(usuario.getUsuario());
		params.add(usuario.getPassword());
		params.add(usuario.getNombre());
		params.add(usuario.getApellido());
		params.add(usuario.getEmail());
		params.add(usuario.getDni());
		params.add(usuario.getTelefono());
		params.add(usuario.getDireccion());
		params.add(usuario.getTipo());
		
		DataBase.Update(consulta, params);
	}

	public static void Update(Usuario usuario) {
		String consulta = "call UpDateUsuario(?, ?, ?, ?, ?)";
		ArrayList<Object> params = new ArrayList<Object>();
		
		params.add(usuario.getPassword());
		params.add(usuario.getEmail());
		params.add(usuario.getTelefono());
		params.add(usuario.getDireccion());
		params.add(usuario.getUsuario());
		
		DataBase.Update(consulta, params);
	}
	
	public static Usuario GetByDNI(String dni) {
		String consulta = "call BuscarUsuarioPorDNI(?)";
		ArrayList<Object> params = new ArrayList<Object>();
		
		params.add(dni);
		
		ArrayList<HashMap<String, Object>> results = DataBase.CallStoredProcedure(consulta, params);
		
		Usuario usuario = null;
		if (!results.isEmpty()) {
			HashMap<String, Object> row = results.get(0);
			
			Object nombre_usuario = row.get("nombre_usuario");
			Object pass = row.get("password");
			Object tipo = row.get("tipo");
			Object nombre = row.get("nombre");
			Object apellido = row.get("apellido");
			Object email = row.get("email");
			Object telefono = row.get("telefono");
			Object direccion = row.get("direccion");
			
			usuario = new Usuario();
			
			usuario.setUsuario((String) nombre_usuario);
			usuario.setPassword((String) pass);
			usuario.setTipo((String) tipo);
			usuario.setNombre((String) nombre);
			usuario.setApellido((String) apellido);
			usuario.setEmail((String) email);
			usuario.setDni((String) dni);
			usuario.setTelefono((String) telefono);
			usuario.setDireccion((String) direccion);
		}
		return usuario;
	}
}
