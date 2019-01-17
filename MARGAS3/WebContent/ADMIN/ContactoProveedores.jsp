<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/envio-mail.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MarGas - Contactar proveedor</title>
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
		<form action="/MARGAS3/ContactarProveedorSL" method="post">
			<fieldset>
				<legend>Enviar Mail</legend>
				<label for="proveedor">Seleccioná un proveedor de la base de datos</label>
				<select id="proveedor" name="proveedor">
					<c:forEach items="${lista_proveedores}" var="proveedor">
						<option value="${proveedor.getEmail()}"> 
							${proveedor.getEmail()}
						</option>
					</c:forEach>
				</select>
				
				<label for="email">o también podés ingresar una dirección manualmente:</label>
				<input class="dir-manual" type="text" id="email" name="email">
				
				<label for="mensaje">Mensaje: </label>
				<textarea id="mensaje" name="mensaje" placeholder="Ingrese su mensaje">
				</textarea>
				
				<input class="boton-enviar" type="submit" name="enviar" value="Enviar pedido">
			</fieldset>
		</form>
		<span class="mensaje"> ${info} </span>
	</main>
	
	<footer>
		<span class="team-signature">UTN | JAVA | Contreras, Sebastián | Carballo, Mariano | Oshea, Mariano</span>
	</footer>
	
</body>

</html>