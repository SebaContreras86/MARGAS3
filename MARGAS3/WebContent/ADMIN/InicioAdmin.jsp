<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
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

	<main class="inicio-main">

		<img src="/MARGAS3/llama-azul.jpg" height="100%">
		
	</main>

	<footer>
		<span class="team-signature">UTN | JAVA | Contreras, Sebastián | Carballo, Mariano | Oshea, Mariano</span>
	</footer>

</body>
</html>