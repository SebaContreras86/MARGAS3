package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistencia.PedidoDAO;

public class CobrarPedidoSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CobrarPedidoSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nroPedido = Integer.parseInt(request.getParameter("nro_pedido"));
		
		PedidoDAO.UpdateEstado(nroPedido, "enviado");
		request.getRequestDispatcher("PedidosPendientesSL").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
