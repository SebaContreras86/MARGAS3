package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import persistencia.UsuarioDAO;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String user_name = request.getParameter("usuario");
		String password = request.getParameter("password");
		Usuario usuario = null;
			
		if (user_name != null && password != null) usuario = UsuarioDAO.GetOne(user_name, password);
		
		if (usuario != null) {
			request.getSession().setAttribute("usuario", usuario);
			if (usuario.getTipo().equals("admin")) {
				request.getRequestDispatcher("/ADMIN/InicioAdmin.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/CLIENTE/InicioCliente.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("mensaje", "Usuario no encontrado");
			request.getRequestDispatcher("/IniciarSesion.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
