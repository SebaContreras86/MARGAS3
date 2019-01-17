<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MarGas - Datos personales</title>
</head>
<body>
	<jsp:include page="InicioCliente.jsp" flush="true"/>
	
	<div class="container">
		<h2>Modificar datos</h2>
		<form action="/MARGAS3/ModificarDatosSL" method="post">            
			<table class="table table-hover">
		    	<thead>
		      		<tr>
		        		<th> Dato </th>
						<th> Dato actual </th>
						<th> Dato reemplazo </th>
		      		</tr>
		    	</thead>
		    	<tbody>
					<tr>
						<td> E-Mail </td>
						<td> <c:out value="${usuario.getEmail()}"/> </td>
						<td> <input type="email" name="nuevoEmail" placeholder="E-Mail"> </td>
					</tr>
				
					<tr>
						<td> Dirección </td>
						<td> <c:out value="${usuario.getDireccion()}"/> </td>
						<td> <input type="text" name="nuevaDireccion" placeholder="Dirección"> </td>
					</tr>
				
					<tr>
						<td> Teléfono </td>
						<td> <c:out value="${usuario.getTelefono()}"/> </td>
						<td> <input type="text" name="nuevoTelefono" placeholder="Teléfono"> </td>
					</tr>
				
					<tr>
						<td> Contraseña </td>
						<td> <c:out value="${usuario.getPassword()}"/> </td>
						<td> <input type="password" name="nuevaPassword" placeholder="Contraseña"> </td>
					</tr>
						
					<tr>
						<td colspan="3"> <input type="submit" class="btn btn-info" name="modificar" value="Modificar"> </td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>