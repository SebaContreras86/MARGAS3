<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/pedido.css">
<title>MarGas - Pedidos pendientes</title>
</head>
<body>
	<header> <h1 align="center">Administrador: <c:out value="${usuario.getNombre()}"/> </h1> </header>
	
	<nav>
		<ul>
			<li><a href="/MARGAS3/ADMIN/InicioAdmin.jsp"> <img alt="Logo MarGas" src="/MARGAS3/llama-azul.jpg" width="100%"> </a></li>
			<li><a href="/MARGAS3/ADMIN/CrearCuenta.jsp"> Registrar usuario</a></li>
			<li><a href="/MARGAS3/ADMIN/BuscarUsuario.jsp"> Modificar usuario </a></li>
			<li><a href="/MARGAS3/PedidosPendientesSL"> Pedidos pendientes </a></li>
			<li><a href="#">Ranking de garrafas</a></li>
			<li><a href="/MARGAS3/ContactarProveedorSL"> Contactar proveedor </a></li>
			<li><a href="/MARGAS3/Logout"> Cerrar sesión </a></li>
		</ul>
	</nav>
	
	<main>
		<c:forEach var="pedido" items="${pedidos_pendiente}">
			<div class="pedido-box">
				<span>Número de pedido: ${pedido.getNro_pedido()}</span>
				<span>Fecha: ${pedido.getFecha()}</span>
				<span>Dirección: ${pedido.getDireccion()}</span>
				<hr>
				<c:forEach var="linea" items="${pedido.getLineas()}">
					<span>${linea.getTipoGarrafa().getDescripcion()} 	x${linea.getCantidad()}  Importe: $<c:out value="${linea.getImporte()}"/> </span>
				</c:forEach>
				<hr>
				<span>TOTAL: $ ${pedido.getTotal()}</span>
				<form action="/MARGAS3/CobrarPedidoSL" method="post">
					<input type="hidden" name="nro_pedido" value="${pedido.getNro_pedido()}">					
					<input type="submit" value="Cobrar">
				</form>
			</div>
		</c:forEach>
		<c:out value="${mensaje_no_hay_pedidos}"/>
	</main>
	
	<footer>
		<span class="team-signature">UTN | JAVA | Contreras, Sebastián | Carballo, Mariano | Oshea, Mariano</span>
	</footer>

</body>
</html>