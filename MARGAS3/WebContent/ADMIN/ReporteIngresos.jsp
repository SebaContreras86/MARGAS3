<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="controladores.ControladorReporte" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/reporte.css">
	<title>MarGas - Vista administrador</title>
	
</head>

<body>
	<header> <h1>Administrador: <c:out value="${usuario.getNombre()}"/> </h1> </header>

	<nav>
		<ul>
			<li><a href="/MARGAS3/ADMIN/InicioAdmin.jsp"> <img alt="Logo MarGas" src="/MARGAS3/llama-azul.jpg" width="100%"> </a></li>
			<li><a href="/MARGAS3/ADMIN/CrearCuenta.jsp"> Registrar usuario</a></li>
			<li><a href="/MARGAS3/ADMIN/BuscarUsuario.jsp"> Modificar usuario </a></li>
			<li><a href="/MARGAS3/PedidosPendientesSL"> Pedidos pendientes </a></li>
			<li><a href="/MARGAS3/ADMIN/ReporteIngresos.jsp"> Reporte ingresos </a></li>
			<li> <a href="/MARGAS3/CompraSL"> Compras </a> </li>
			<li><a href="/MARGAS3/ContactarProveedorSL"> Contactar proveedor </a></li>
			<li><a href="/MARGAS3/Logout"> Cerrar sesión </a></li>
		</ul>
	</nav>

	<main>
		<form action="/MARGAS3/ReporteSL" method="post">
			<fieldset>
				<legend>Seleccioná un rango de fechas</legend>

				<label class="container">Ésta semana
					<input type="radio" checked="checked" name="rango" value="${ControladorReporte.SEMANA}">
	  				<span class="checkmark"></span>
				</label>

				<label class="container">Éste mes
					<input type="radio" name="rango" value="${ControladorReporte.MES}">
	  				<span class="checkmark"></span>
				</label>

				<label class="container">Éste año
					<input type="radio" name="rango" value="${ControladorReporte.ANIO}">
	  				<span class="checkmark"></span>
				</label>

				<label class="container">El año pasado
					<input type="radio" name="rango" value="${ControladorReporte.ANIO_ANTERIOR}">
	  				<span class="checkmark"></span>
				</label>

				<input type="submit" value="Calcular">

			</fieldset>
		</form>

		<section>
			<table>
				<caption>
					Del  
					<c:out value="${ctrlReporte.getFechas().get('fechaDesde')}"/>
					al
					<c:out value="${ctrlReporte.getFechas().get('fechaHasta')}"/>
				 </caption>
				<tr> 
					<th>Número de pedidos: </th> 
					<td> ${ctrlReporte.getCantidadDePedidos()} </td>
				</tr>
				<tr>
					<th>Ingresos brutos: </th>
					<td> $${ctrlReporte.getIngresoBruto()} </td>
				</tr>
				<tr>
					<th>Ingresos netos: </th>
					<td>$${ctrlReporte.getIngresoNeto()}</td>
				</tr>
			</table>

			<hr>

			<table class="ranking">
				<caption>Unidades mas vendidas</caption>
				<tr>
					<th>Descripción</th>
					<th>Cantidad</th>
				</tr>
				
				<c:forEach var="map" items="${ctrlReporte.getCantidadProductosVendidos()}">
				
				<tr>
					<td> ${map.key.descripcion} </td>
					<td> ${map.value} </td>
				</tr>
					
				</c:forEach>
			</table>

		</section>
	</main>

	<footer>
		<span class="team-signature">UTN | JAVA | Contreras, Sebastián | Carballo, Mariano | Oshea, Mariano</span>
	</footer>

</body>

</html>