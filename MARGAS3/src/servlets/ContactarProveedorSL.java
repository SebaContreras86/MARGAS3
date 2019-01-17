package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Proveedor;
import persistencia.ProveedorDAO;
import util.Emailer;

public class ContactarProveedorSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContactarProveedorSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Proveedor> listaProveedores = ProveedorDAO.GetAll();
		request.setAttribute("lista_proveedores", listaProveedores);
		
		if (request.getParameter("enviar") != null) {
			System.out.println("Validando envío...\n---------------------");
			String nombreProveedor = request.getParameter("email");
			String mensaje = request.getParameter("mensaje");
			
			boolean proveedorValido = !nombreProveedor.equals("");
			boolean mensajeValido = !mensaje.equals(""); 
			
			String info = "";
			if (proveedorValido && mensajeValido) {
				Emailer emailer = new Emailer();
				try { 
					emailer.sendMail(mensaje, nombreProveedor);
					info = "Mensaje enviado!";
					request.setAttribute("info", info);
				}	
				catch(MessagingException e) {
					info = e.getMessage();
					request.setAttribute("info", info);
				}
			}
			else {
				request.setAttribute("info", "No puede haber campos vacíos");
			}
		}
		else {
			System.out.println("Served at: " + request.getContextPath());
		}
		request.getRequestDispatcher("/ADMIN/ContactoProveedores.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
