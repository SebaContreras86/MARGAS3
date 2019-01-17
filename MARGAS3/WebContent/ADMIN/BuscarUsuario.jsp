<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MarGas - Buscar usuario</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
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
		<form action="/MARGAS3/BuscarUsuarioSL" method="post">
	    	<div class="input-group input-group-lg">
	      		<input type="text" class="form-control" name="dni" placeholder="Ingrese DNI de un usuario" width="50%">
	      		<div class="input-group-btn">
	        		<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
	      		</div>
	    	</div>
	    </form>	
	    	
	    <div class="main">
			<form action="/MARGAS3/ModificarDatosSL" method="post">
				<table class="table table-hover">
					<tr>
						<th> Dato </th>
						<th> Dato actual </th>
						<th> Dato reemplazo </th>
					</tr>
			
					<tr>
						<td> E-Mail </td>
						<td> <c:out value="${usuario_buscado.getEmail()}"/> </td>
						<td> <input type="email" name="nuevoEmail" placeholder="E-Mail"> </td>
					</tr>
				
					<tr>
						<td> Dirección </td>
						<td> <c:out value="${usuario_buscado.getDireccion()}"/> </td>
						<td> <input type="text" name="nuevaDireccion" placeholder="Dirección"> </td>
					</tr>
				
					<tr>
						<td> Teléfono </td>
						<td> <c:out value="${usuario_buscado.getTelefono()}"/> </td>
						<td> <input type="text" name="nuevoTelefono" placeholder="Teléfono"> </td>
					</tr>
				
					<tr>
						<td> Contraseña </td>
						<td> <c:out value="${usuario_buscado.getPassword()}"/> </td>
						<td> <input type="password" name="nuevaPassword" placeholder="Contraseña"> </td>
					</tr>
			</table>
			
		<input type="submit" class="btn btn-info" name="modificar" value="Modificar"/>
			
	</form>
	</div>
	</main>
	
	<footer>
		<span class="team-signature">UTN | JAVA | Contreras, Sebastián | Carballo, Mariano | Oshea, Mariano</span>
	</footer>
	
</body>
</html>