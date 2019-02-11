package controladores;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Compra;
import modelo.LineaDeCompra;
import modelo.LineaDeVenta;
import modelo.TipoGarrafa;
import persistencia.CompraDAO;

public class Test2 {

	public static void main(String[] args) {
		ControladorReporte cr = new ControladorReporte();
		cr.setRango(ControladorReporte.SEMANA);
		HashMap<TipoGarrafa, Integer> map = cr.getCantidadProductosVendidos();
		
		cr.getFechas();
		
		System.out.println("Cantidad de pedidos: " + cr.getCantidadDePedidos());
		
		map.forEach((garrafa, cantidad) -> {System.out.println("Producto: " + garrafa.getDescripcion());
			System.out.println("Cantidad: " + cantidad + "\n");
		});
		
		System.out.println("Ingresos brutos: $" + cr.getIngresoBruto());
		System.out.println("Ingresos netos: $" + cr.getIngresoNeto());
	}
}
