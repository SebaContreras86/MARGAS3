package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ControladorPedido;
import modelo.TipoGarrafa;
import modelo.Usuario;

public class PedidoSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PedidoSL() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		/* =========== Si la sesión no tiene controlador de pedido se le asigna uno =========== */		
		ControladorPedido controlador = null;
		if (request.getSession().getAttribute("ctrl_pedido") == null) {
			controlador = new ControladorPedido();
			request.getSession().setAttribute("ctrl_pedido", controlador);
				
			/* =========== Se inicia el pedido y se envían todos los productos de la BD a la UI  =========== */		
			String dni = ((Usuario) request.getSession().getAttribute("usuario")).getDni();
			ArrayList<TipoGarrafa> lista_productos = controlador.iniciarPedido(dni);
			request.getSession().setAttribute("lista_productos", lista_productos);
			request.getSession().setAttribute("lineas_de_pedido", controlador.getPedido().getLineas());
		}
		else controlador = (ControladorPedido) request.getSession().getAttribute("ctrl_pedido");
			
		String opcion = request.getParameter("opcion");
		if (opcion == null) {
			opcion = "";
			request.getRequestDispatcher("/CLIENTE/IniciarPedido.jsp").forward(request, response);
		}
			
		switch (opcion) {
			case "Agregar":
				if (request.getParameter("cantidad").matches("[0-9]+")) {
					int id_producto = Integer.parseInt(request.getParameter("id"));
					int cantidad = Integer.parseInt(request.getParameter("cantidad"));
					List<TipoGarrafa> lista = (List<TipoGarrafa>) request.getSession().getAttribute("lista_productos");
					for (TipoGarrafa tipoGarrafa : lista) {
						if (tipoGarrafa.getId() == id_producto) {
							if (cantidad <= tipoGarrafa.getStock()) controlador.agregarProducto(id_producto, cantidad);
						}
					}
				}
				else request.setAttribute("mensaje", "Entrada no válida...");
				request.getRequestDispatcher("/CLIENTE/IniciarPedido.jsp").forward(request, response);
				break;
				
			case "Quitar":
				int id_producto_eliminar = Integer.parseInt(request.getParameter("id_producto"));
				controlador.eliminarLinea(id_producto_eliminar);
				request.getRequestDispatcher("/CLIENTE/IniciarPedido.jsp").forward(request, response);
				break;
				
			case "Enviar pedido":
				if (controlador.enviar()) {
					request.getSession().removeAttribute("ctrl_pedido");
					request.getRequestDispatcher("/CLIENTE/PedidoExitoso.jsp").forward(request, response);
				}
				else  request.getRequestDispatcher("/CLIENTE/InicioCliente.jsp").forward(request, response);
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
