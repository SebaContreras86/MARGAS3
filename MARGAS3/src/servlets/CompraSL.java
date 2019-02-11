package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ControladorCompra;
import modelo.Proveedor;

public class CompraSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CompraSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControladorCompra ctrlCompra = (ControladorCompra) request.getSession().getAttribute("ctrlCompra");
		
		if (ctrlCompra == null) {
			ctrlCompra = new ControladorCompra();
			ArrayList<Proveedor> proveedores = ctrlCompra.iniciarCompra();
			request.getSession().setAttribute("proveedores", proveedores);
			request.getSession().setAttribute("ctrlCompra", ctrlCompra);
		}
		else if(request.getParameter("cambiar_proveedor") != null){
			String cuit = request.getParameter("proveedor");
			ctrlCompra.setProveedor(cuit);
		}
		else if(request.getParameter("agregar") != null) {
			if (request.getParameter("cantidad").matches("[0-9]+")) {
				int id = Integer.parseInt(request.getParameter("id"));
				int cantidad = Integer.parseInt(request.getParameter("cantidad"));
				ctrlCompra.agregarProducto(id, cantidad);
			}
		}
		else if (request.getParameter("listo") != null) {
			boolean result = ctrlCompra.registrarCompra();
			request.getSession().removeAttribute("ctrlCompra");
			request.setAttribute("mensaje", "Compra registrada!");
		}
		request.getRequestDispatcher("/ADMIN/Compra.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
