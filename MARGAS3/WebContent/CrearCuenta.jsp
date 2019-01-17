<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="CSS/estilo1.css">
	<title>MarGas - Crear cuenta</title>
</head>
<body>
	<div class="main">	
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
				<input type="email" name="email" id="email" placeholder="E-Mail">
				
				<input type="hidden" name="tipo" value="cliente"/>
				
				<input type="submit" value="Crear cuenta">
			</fieldset>	
		</form>
		<span> <c:out value="${mensaje}"/> </span>
	</div>
</body>
</html>