package persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
	private static String Driver = "org.gjt.mm.mysql.Driver";
	private static String Url = "jdbc:mysql://localhost/margas_2";
	private static String Usuario = "root";
	private static String Password = "root";
	private static Connection Conexion = null;
	private static CallableStatement Cs = null;
	private static ResultSet Rs = null;
	
	
	/*------------------------- MÉTODOS PRIVADOS -------------------------*/
	private static void Init () throws DataBaseException {
		try {
			Class.forName(Driver);
			Conexion = DriverManager.getConnection(Url, Usuario, Password);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new DataBaseException("Clase no encontrada", e);
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Error de SQL", e);
		}	
	}
	
	/*	Método que guarda el ResultSet en un ArrayList
	 *  Es un ArrayList de HashMaps, tal que
	 *	cada HashMap guarda un registro del ResultSet
	 *	donde las claves son los nombres de las columnas */
	private static ArrayList<HashMap<String, Object>> SaveResultSetIntoArray () throws SQLException {
		ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
		
		ResultSetMetaData md = Rs.getMetaData();
		int columns = md.getColumnCount();
		
	    while (Rs.next()) {
	        HashMap<String, Object> row = new HashMap<String, Object>();
	        results.add(row);

	        for(int i=1; i<=columns; i++){
	          row.put(md.getColumnLabel(i), Rs.getObject(i));
	        }
	    }
		return results;
	}
	
	private static void SetParameters(ArrayList<Object> params) throws SQLException {
		int index = 1;
		for (Object object : params) {
			System.out.println("Param. index: " + index);
			String paramType = object.getClass().getSimpleName();
			System.out.println("Param. type: " + paramType);
			System.out.println("Param. value: " + object + "\n");
			switch (paramType) {
				case "Integer":	Cs.setInt(index++, (int) object);
					break;
				case "Float": 	Cs.setFloat(index++, (float) object);
					break;
				case "Double":	Cs.setDouble(index++, (double) object);
					break;
				case "Long":	Cs.setLong(index++, (long) object);
					break;
				case "String":	Cs.setString(index++, (String) object);
					break;	
				case "Date":	Cs.setDate(index++, (Date) object);
					break;
				default:
					break;
			}
		}
	}
	
	/*------------------------- MÉTODOS PÚBLICOS -------------------------*/
	public static ArrayList<HashMap<String, Object>> CallStoredProcedure(String consulta) throws DataBaseException {
		Init();
		ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
		try {
			Cs = Conexion.prepareCall(consulta);
			System.out.println("Query: " + consulta);
			Rs = Cs.executeQuery();
			System.out.println("-------------------------");
			results = SaveResultSetIntoArray();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Error de SQL", e);
		}
		finally {
			Close();
		}
		return results;
	}
	
	public static ArrayList<HashMap<String, Object>> CallStoredProcedure(String consulta, ArrayList<Object> params) throws DataBaseException {
		Init();
		
		//	En la variable results se guardarán los registros del ResultSet. Ver método SaveResultSetIntoArray()
		ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
		
		try {
			Cs = Conexion.prepareCall(consulta);
			System.out.println("Query: " + consulta);
			SetParameters(params);
			
			Rs = Cs.executeQuery();
			System.out.println("-------------------------");
			results = SaveResultSetIntoArray();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Error de SQL", e);
		}
		finally {
			Close();
		}
		return results;
	}
	
	
	public static void Close() throws DataBaseException {
		try {
			if (Conexion != null) Conexion.close();
			
			if (Cs != null) Cs.close();
			
			if (Rs != null) Rs.close();
		}
		catch(SQLException e) {
			throw new DataBaseException("Error al liberar recursos", e);
		}
	}

	public static boolean Existe(String usuario) throws DataBaseException {
		boolean Existe = false;
		
		Init();
		try {
			Cs = Conexion.prepareCall("{call SelectUsuario(?)}");
			Cs.setString(1, usuario);
			
			Rs = Cs.executeQuery();	
			Existe = Rs.first();
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Error de SQL", e);
		}
		finally {
			Close();
		}
		return Existe;
	}

	public static void Update(String consulta, ArrayList<Object> params) throws DataBaseException {
		Init();
		try {
			Cs = Conexion.prepareCall(consulta);
			System.out.println("Query: " + consulta);
			SetParameters(params);
			System.out.println("----------------------------------");
			Cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException("Error al realizar actualización", e);
		}
		finally {
			Close();
		}
	}
}
