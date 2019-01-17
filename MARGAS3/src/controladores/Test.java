package controladores;

import java.util.ArrayList;

import modelo.LineaDePedido;
import modelo.Pedido;
import persistencia.PedidoDAO;

public class Test {
	
	public static void main(String[] args) {
		ArrayList<Pedido> listaPedidos = PedidoDAO.GetAllPedidosPendiente();
		
		for (Pedido pedido : listaPedidos) {
			System.out.println("Número de pedido: " + pedido.getNro_pedido());
			System.out.println("Dirección: " + pedido.getDireccion());
			System.out.println("----------------");
		}
	}
}
