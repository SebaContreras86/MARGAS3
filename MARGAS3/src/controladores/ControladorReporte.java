package controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import modelo.Compra;
import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.TipoGarrafa;
import persistencia.CompraDAO;
import persistencia.PedidoDAO;

public class ControladorReporte {
	public static final int SEMANA = 0;
	public static final int MES = 1;
	public static final int ANIO = 2;
	public static final int ANIO_ANTERIOR = 3;
	
	private ArrayList<Pedido> listaPedidos;
	private ArrayList<Compra> listaCompras;
	private java.sql.Date sqlFechaDesde = null;
	private java.sql.Date sqlFechaHasta = null;
	
	public void setRango(int rango) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0); 
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		
		java.util.Date utilFechaDesde = null;;
		java.util.Date utilFechaHasta = null;
		
		switch (rango) {
		case SEMANA: 
			calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); 
			utilFechaDesde = calendar.getTime();
			sqlFechaDesde =  new java.sql.Date(utilFechaDesde.getTime()); // Lo convertimos a java.sql.date
			
			calendar.setTime(new java.util.Date());
			utilFechaHasta = calendar.getTime();
			sqlFechaHasta = new java.sql.Date(utilFechaHasta.getTime()); // Lo convertimos a java.sql.date
			break;
		
		case MES:
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			utilFechaDesde = calendar.getTime();
			sqlFechaDesde =  new java.sql.Date(utilFechaDesde.getTime()); // Lo convertimos a java.sql.date

			calendar.setTime(new java.util.Date());
			utilFechaHasta = calendar.getTime();
			sqlFechaHasta = new java.sql.Date(utilFechaHasta.getTime()); // Lo convertimos a java.sql.date
			break;
			
		case ANIO:
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			utilFechaDesde = calendar.getTime();
			sqlFechaDesde =  new java.sql.Date(utilFechaDesde.getTime()); // Lo convertimos a java.sql.date

			calendar.setTime(new java.util.Date());
			utilFechaHasta = calendar.getTime();
			sqlFechaHasta = new java.sql.Date(utilFechaHasta.getTime()); // Lo convertimos a java.sql.date
			break;
			
		case ANIO_ANTERIOR:
			calendar.add(Calendar.YEAR, -1);
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			utilFechaDesde = calendar.getTime();
			sqlFechaDesde =  new java.sql.Date(utilFechaDesde.getTime()); // Lo convertimos a java.sql.date
			
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			utilFechaHasta = calendar.getTime();
			sqlFechaHasta =  new java.sql.Date(utilFechaHasta.getTime()); // Lo convertimos a java.sql.date
			break;
			
		default:
			break;
		}
		
		listaPedidos = PedidoDAO.GetByDate(sqlFechaDesde, sqlFechaHasta);
		listaCompras = CompraDAO.GetByDate(sqlFechaDesde, sqlFechaHasta);
	}
	
	public HashMap<String, java.util.Date> getFechas(){
		HashMap<String, java.util.Date> hashMap = new HashMap<String, java.util.Date>();
		
		hashMap.put("fechaDesde", sqlFechaDesde);
		hashMap.put("fechaHasta", sqlFechaHasta);
		
		return hashMap;
	}
	
	public int getCantidadDePedidos() {
		return listaPedidos.size();
	}
	
	public HashMap<TipoGarrafa, Integer> getCantidadProductosVendidos() {
		
		HashMap<TipoGarrafa, Integer> hashMap = new HashMap<TipoGarrafa, Integer>();
		for (Pedido pedido : listaPedidos) {
			ArrayList<LineaDePedido> lineas = pedido.getLineas();
			for (LineaDePedido ldp : lineas) {
				
				hashMap.compute(ldp.getTipoGarrafa(), 
						(garrafa, cantidad) -> (cantidad == null) 
						? ldp.getCantidad() 
						: cantidad + ldp.getCantidad()); 
			}
		}
		return hashMap;
	}
	
	public double getIngresoBruto() {
		double ingresoBruto = 0;
		
		if (!listaPedidos.isEmpty()) {
			for (Pedido pedido : listaPedidos) ingresoBruto += pedido.getTotal();
		}
		
		return ingresoBruto;
	}
	
	public double getIngresoNeto() {
		double ingresoNeto = 0;
		double gasto = 0;
		
		if (!listaCompras.isEmpty()) {
			for (Compra compra : listaCompras) gasto += compra.getTotal();
		}
		
		ingresoNeto = this.getIngresoBruto() - gasto;
		
		return ingresoNeto;
	}
}
