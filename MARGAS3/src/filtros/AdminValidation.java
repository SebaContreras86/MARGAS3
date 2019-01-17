package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;

public class AdminValidation implements Filter {

    public AdminValidation() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		if (usuario != null) {
			if (usuario.getTipo().equals("admin")) chain.doFilter(request, response);
			else res.sendRedirect("http://localhost:8080/MARGAS3/CLIENTE/InicioCliente.jsp");
		}
		else {
			res.sendRedirect("http://localhost:8080/MARGAS3/");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
