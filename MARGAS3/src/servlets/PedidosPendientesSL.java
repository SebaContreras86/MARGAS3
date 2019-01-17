package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Pedido;
import persistencia.PedidoDAO;

public class PedidosPendientesSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PedidosPendientesSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Pedido> pedidos_pendiente = PedidoDAO.GetAllPedidosPendiente();
		
		request.getSession().setAttribute("pedidos_pendiente", pedidos_pendiente);
		response.sendRedirect("http://localhost:8080/MARGAS3/ADMIN/PedidosPendientes.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
