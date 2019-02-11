<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/InicioAdmin.css">
	<link rel="stylesheet" type="text/css" href="/MARGAS3/CSS/Compras.css">
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
	
		<section class="compra">
			
			<form action="/MARGAS3/CompraSL" method="post">
				<fieldset>
					<legend>Proveedor</legend>
					<select name="proveedor">
						<c:forEach var="proveedor" items="${proveedores}">
							<option value="${proveedor.getCuit()}"> 
								${proveedor.getRazonSocial()} 
							</option>
						</c:forEach>
					</select>
					<input type="submit" name="cambiar_proveedor" value="Cambiar"/>
				</fieldset>
			</form>

			<table>
				<caption> Catálogo: ${ctrlCompra.getProveedor().getRazonSocial()}</caption>
				<tr>
					<th> PRODUCTO </th>
					<th title="Último precio registrado"> PRECIO </th>
					<th> CANTIDAD </th>
				</tr>
				
				<c:forEach items="${ctrlCompra.getProveedor().getProductos()}" var="producto">
					<tr>
						<td> ${producto.getDescripcion()} </td>
						<td> ${producto.getPrecio()} </td>
						<td>
							<form action="/MARGAS3/CompraSL" method="post">
								<input class="cantidad" type="number"  value="0" name="cantidad">
								<input type="hidden" value="${producto.getId()}" name="id"/>
								<input class="btn-agregar" type="submit" value="agregar" name="agregar">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>

			<form action="/MARGAS3/CompraSL" method="post">
				 <input class="btn-listo" type="submit" value="Listo" name="listo"> 
			</form>

			<hr>

			<table>
				<caption> COMPRA </caption>
				<tr>
					<th> PRODUCTO </th>
					<th> PRECIO </th>
					<th> CANTIDAD </th>
				</tr>
				<c:forEach var="linea" items="${ctrlCompra.getCompra().getLineas()}">
					<tr>
						<td> ${linea.getTipoGarrafa().getDescripcion()} </td>
						<td> ${linea.getTipoGarrafa().getPrecio()} </td>
						<td> ${linea.getCantidad()} </td>
					</tr>
				</c:forEach>
			</table>
			<span> <c:out value="${mensaje}"/> </span>
		</section>

		<section class="stock">

			<table class="tbl-stock">
				<caption> STOCK </caption>
				<tr>
					<th>Producto</th>
					<th >Stock</th>
				</tr>
				<c:forEach items="${ctrlCompra.getProveedor().getProductos()}" var="producto">
					<tr>
						<td class="td-stock">${producto.getDescripcion()}</td>
						<td class="td-stock">${producto.getStock()}</td>
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