package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import persistencia.DataBase;
import persistencia.UsuarioDAO;

public class CrearCuentaSL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CrearCuentaSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("usuario");
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		if (DataBase.Existe(user_name)) {
			String mensaje = "Nombre de usuario ya registrado";
			request.setAttribute("mensaje", mensaje);
			if (usuario == null) {
				request.getRequestDispatcher("/CrearCuenta.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/ADMIN/InicioAdmin.jsp").forward(request, response);
			}
		}
		else {
			String password = request.getParameter("password");
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String email = request.getParameter("email");
			String dni = request.getParameter("dni");
			String tipo = request.getParameter("tipo");
			String direccion = request.getParameter("direccion");
			String telefono = request.getParameter("telefono");
			
			Usuario usuario_creado = new Usuario();
			
			usuario_creado.setUsuario(user_name);
			usuario_creado.setPassword(password);
			usuario_creado.setNombre(nombre);
			usuario_creado.setApellido(apellido);
			usuario_creado.setEmail(email);
			usuario_creado.setTelefono(telefono);
			usuario_creado.setDni(dni);
			usuario_creado.setDireccion(direccion);
			usuario_creado.setTipo(tipo);
				
			UsuarioDAO.Save(usuario_creado);
			
			if (usuario != null) {
				request.getRequestDispatcher("/ADMIN/InicioAdmin.jsp").forward(request, response);
			}
			else {
				request.getSession().setAttribute("usuario", usuario_creado);
				request.getRequestDispatcher("/CLIENTE/InicioCliente.jsp").forward(request, response);
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
