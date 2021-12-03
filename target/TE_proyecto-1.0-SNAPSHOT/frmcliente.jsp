<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="static/css/bootstrap.css">
        <link rel="stylesheet" href="static/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilos.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
          
            <tr>
            <h1><b>REGISTRAR NUEVO CLIENTE</b></h1>
            <br/>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="clientes" />
            </jsp:include>
            <br />
            <br/>
            <br/>
            <jsp:useBean id="cliente" scope="request" class="com.emergentes.modelo.Cliente" />
            <form action="ClienteControlador" method="post">
                <input type="hidden" name="id" value="${cliente.id}" />
                <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input type="text" name="nombre" value="${cliente.nombre}" class="form-control" placeholder="Escriba su nombre y apellidos">
                </div>
                <div class="form-group">
                    <label for="correo">Correo</label>
                    <input type="email" name="correo" value="${cliente.correo}" class="form-control" placeholder="Correo electrónico">
                </div>
                <div class="form-group">
                    <label for="correo">Celular</label>
                    <input type="text" name="celular" value="${cliente.celular}" class="form-control" placeholder="Escriba su número de celular">
                </div>                
                <button type="submit" class="btn btn-primary">REGISTRAR</button>

            </form>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="static/js/jquery-3.2.1.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/js/bootstrap.js"></script>
    </body>
</html>