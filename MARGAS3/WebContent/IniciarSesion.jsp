<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="CSS/signin.css">
	<title>Iniciar sesión - MarGas</title>
</head>

<body class="text-center">

    <form class="form-signin" action="Login" method="post">
    
		<img class="mb-4" src="logo_margas.jpg" alt="margas" width="300">
      
    	<h1 class="h3 mb-3 font-weight-normal">Iniciar sesión</h1>
      
      	<label for="inputEmail" class="sr-only">Usuario</label>
      	<input type="text" id="inputEmail" class="form-control" name="usuario" placeholder="Usuario" required autofocus>
      
      	<label for="inputPassword" class="sr-only">Password</label>
      	<input type="password" id="inputPassword" class="form-control" name="password" placeholder="Contraseña" required>
      
      	<button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
      
      	<p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
      
      	<span class="badge badge-danger"> <c:out value="${mensaje}"/> </span><br>
      
      	<a href="CrearCuenta.jsp">Clickea acá para crear una cuenta</a>
      
    </form>
    
</body>

</html>