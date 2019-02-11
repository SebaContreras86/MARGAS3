<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/estilo1.css">
<title>MarGas - Crear cuenta</title>
</head>
<body>
	<header> <h1 align="center">Administrador: <c:out value="${usuario.getNombre()}"/> </h1> </header>
	
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
		<form action="/MARGAS3/CrearCuentaSL" method="post" name="form">
			<fieldset>
				<legend>Crear Cuenta</legend>
			
				<label for="usuario">Usuario</label>
				<input type="text" name="usuario" id="usuario" required placeholder="Nombre de usuario">
				
				<label for="password">Contraseña</label>
				<input type="password" name="password" id="password" required placeholder="Contraseña">
				
				<label for="nombre">Nombre</label>
				<input type="text" name="nombre" id="nombre" required placeholder="Nombre de pila">
				
				<label for="apellido">Apellido</label>
				<input type="text" name="apellido" id="apellido" required placeholder="Apellido">
				
				<label for="dni">DNI</label>
				<input type="text" name="dni" id="dni" placeholder="DNI">
				
				<label for="telefono">Teléfono</label>
				<input type="text" name="telefono" id="telefono" required placeholder="Teléfono">
				
				<label for="direccion">Dirección</label>
				<input type="text" name="direccion" id="direccion" required placeholder="Dirección">
				
				<label for="email">E-Mail</label>
				<input type="text" name="email" id="email" placeholder="E-Mail">
				
				<label for="tipo">Tipo de usuario</label>
				<select name="tipo" id="tipo">
					<option value="cliente">Cliente</option>
					<option value="admin">Administrador</option>
				</select>
				<br>
				<input type="submit" value="Crear cuenta">
			</fieldset>	
		</form>
			
		<span> <c:out value="${mensaje}"/> </span>
	</main>
	
	<footer>
		<span class="team-signature">UTN | JAVA | Contreras, Sebastián | Carballo, Mariano | Oshea, Mariano</span>
	</footer>

</body>
</html>