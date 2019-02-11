package controladores;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.LineaDePedido;
import modelo.Pedido;
import modelo.TipoGarrafa;
import persistencia.PedidoDAO;

public class Test {
	
	public static void main(String[] args) {
		ControladorPedido cp = new ControladorPedido();
		ArrayList<TipoGarrafa> productos =  cp.iniciarPedido("33333333");
		cp.agregarProducto(1, 1);
		cp.enviar();
		
		ArrayList<Pedido> listaPedidos = PedidoDAO.GetAllPedidosPendiente();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		
		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		
		Date fechaInicio = cal.getTime();
		cal.add(Calendar.DATE, 6);
		Date fechaFin = cal.getTime();
		
		System.out.println(fechaInicio);
		System.out.println(fechaFin + "\n");
		
		for (Pedido pedido : listaPedidos) {
			Date fecha = pedido.getFecha();
			
			int anterior = fecha.compareTo(fechaInicio);
			int posterior = fecha.compareTo(fechaFin);

			System.out.println("fecha: " + fecha);
			System.out.println("Anterior: " + anterior);
			System.out.println("Posterior: " + posterior + "\n");
		}
		
	/*	// get today and clear time of day
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println("Start of this week:       " + cal.getTime());
		System.out.println("... in milliseconds:      " + cal.getTimeInMillis());

		// start of the next week
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		System.out.println("Start of the next week:   " + cal.getTime());
		System.out.println("... in milliseconds:      " + cal.getTimeInMillis());
	*/
	}
}
