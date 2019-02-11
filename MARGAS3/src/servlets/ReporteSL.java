package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controladores.ControladorReporte;

public class ReporteSL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReporteSL() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession().getAttribute("ctrlReporte") == null) {
			ControladorReporte ctrlReporte = new ControladorReporte();
			request.getSession().setAttribute("ctrlReporte", ctrlReporte);
		}
		
		ControladorReporte ctrlReporte = (ControladorReporte) request.getSession().getAttribute("ctrlReporte");
		int rango = Integer.parseInt(request.getParameter("rango"));
		ctrlReporte.setRango(rango);
		
		request.getRequestDispatcher("/ADMIN/ReporteIngresos.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
