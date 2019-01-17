<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="InicioCliente.jsp" flush="true"/>
	
	<c:if test="${pedidos_pendiente != null}">
		<div class="container">
			<h2>Pedidos pendientes</h2>
			<table class="table table-hover">
				<tr>
					<th>ID</th>
					<th>Fecha</th>
					<th>Hora</th>
					<th>Estado</th>
					<th>Cancelar</th>
				</tr>
				
				<c:forEach items="${pedidos_pendiente}" var="pedido">
				<tr>
					<td> <c:out value="${pedido.getNro_pedido()}"/> </td> 
					<td> <c:out value="${pedido.getFecha()}"/> </td> 
					<td> <c:out value="${pedido.getHora()}"/> </td> 
					<td> <c:out value="${pedido.getEstado()}"/> </td>
					<td>
						<form action="CancelarPedidoSL" method="post">
							<input type="hidden" name="nro_pedido" value="${pedido.getNro_pedido()}">
							<input type="submit" class="btn btn-danger" name="eliminar" value="Cancelar pedido">
						</form>
					</td>
				</tr> 
			</c:forEach>
			</table>
		</div>
	
	</c:if>
	
	<c:out value="${mensaje_no_hay_pedidos}"/>
	
</body>
</html>