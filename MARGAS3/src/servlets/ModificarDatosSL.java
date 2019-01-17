package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import persistencia.UsuarioDAO;

public class ModificarDatosSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificarDatosSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");			
			Usuario usuario_a_modificar = null;
			if (usuario.getTipo().equals("admin")) {
				usuario_a_modificar = (Usuario) request.getSession().getAttribute("usuario_buscado");
			}
			else {
				usuario_a_modificar = usuario;
			}
					
			String nuevo_email = request.getParameter("nuevoEmail");
			String nuevo_telefono = request.getParameter("nuevoTelefono");
			String nueva_direccion = request.getParameter("nuevaDireccion");
			String nueva_password = request.getParameter("nuevaPassword");
			
			boolean datos_modificados = false;
					
			if (!nuevo_email.equals("")) {
				usuario_a_modificar.setEmail(nuevo_email);
				datos_modificados = true;
			}
			if (!nuevo_telefono.equals("")) {
				usuario_a_modificar.setTelefono(nuevo_telefono);
				datos_modificados = true;
			}
			if (!nueva_direccion.equals("")) {
				usuario_a_modificar.setDireccion(nueva_direccion);
				datos_modificados = true;
			}
			if (!nueva_password.equals("")) {
				usuario_a_modificar.setPassword(nueva_password);
				datos_modificados = true;
			}
					
			if (datos_modificados) UsuarioDAO.Update(usuario_a_modificar);
			
			if (usuario.getTipo().equals("admin")) {
				request.getRequestDispatcher("/ADMIN/BuscarUsuario.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/CLIENTE/DatosPersonales.jsp").forward(request, response);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
