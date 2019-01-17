<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MarGas - Inicio</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
	    	<div class="navbar-header">
	      		<a class="navbar-brand" href="/MARGAS3/CLIENTE/InicioCliente.jsp">
	      			<span class="glyphicon glyphicon-fire"> MarGas </span>
	      		</a>
	    	</div>
	    	
	    	<ul class="nav navbar-nav">
	      		<li>
	      			<a href="/MARGAS3/CLIENTE/InicioCliente.jsp"> 
	      				<span class="glyphicon glyphicon-home"></span> Inicio
	      			</a>
	      		</li>
	      		
	      		<li>
	      			<a href="/MARGAS3/PedidoSL">
	      				<span class="glyphicon glyphicon-shopping-cart"></span> Realizar pedido
	      			</a>
	      		</li>
	      		
	      		<li>
	      			<a href="/MARGAS3/CLIENTE/DatosPersonales.jsp">
	      				<span class="glyphicon glyphicon-pencil"></span> Modificar datos personales
	      			</a>
	      		</li>
	      		
	      		<li>
	      			<a href="/MARGAS3/CancelarPedidoSL">
	      				<span class="glyphicon glyphicon-remove"></span> Cancelar pedido
	      			</a>
	      		</li>
	    	</ul>
	    
	    	<ul class="nav navbar-nav navbar-right">
	      		<li>
	      			<a href="/MARGAS3/CLIENTE/DatosPersonales.jsp">
	      				<span class="glyphicon glyphicon-user"></span> 
	      				<c:out value="${usuario.getNombre()}"/>
	      			</a>
	      		</li>
	      		
	      		<li>
	      			<a href="/MARGAS3/Logout">
	      				<span class="glyphicon glyphicon-log-out"></span> Cerrar Sesión
	      			</a>
	      		</li>
	    	</ul>
		</div>
	</nav>
</body>
</html>