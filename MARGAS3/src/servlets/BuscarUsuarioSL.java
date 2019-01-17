package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import persistencia.UsuarioDAO;

public class BuscarUsuarioSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuscarUsuarioSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");

		Usuario usuario_buscado = UsuarioDAO.GetByDNI(dni);
			
		if (usuario_buscado != null) request.getSession().setAttribute("usuario_buscado", usuario_buscado);
		
		request.getRequestDispatcher("/ADMIN/BuscarUsuario.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
