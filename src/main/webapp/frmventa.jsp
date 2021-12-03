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
            <h1>REALIZAR VENTA</h1>
            <br/>
            <jsp:include page="WEB-INF/menu.jsp">
                <jsp:param name="opcion" value="ventas" />
            </jsp:include>
            <br/>
            <br/>
            <form action="VentaControlador" method="post">
                <input type="hidden" name="id" value="${venta.id}" />
                <div class="form-group">
                    <label for="maquina">Maquina</label>
                    <select name="maquina_id" class="form-control">
                        <c:forEach var="item" items="${lista_maquinas}">
                            <option value="${item.id}" <c:if test="${venta.maquina_id == item.id}">selected
                                    </c:if>>${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="cliente">Cliente</label>
                    <select name="cliente_id" class="form-control">
                        <c:forEach var="item" items="${lista_clientes}">
                            <option value="${item.id}" <c:if test="${venta.cliente_id == item.id}">selected
                                    </c:if>>${item.nombre}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="correo">Fecha</label>
                    <input type="text" name="fecha" value="${venta.fecha}" class="form-control" placeholder="Fecha">
                </div>                
                <button type="submit" class="btn btn-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send-fill" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M15.964.686a.5.5 0 0 0-.65-.65L.767 5.855H.766l-.452.18a.5.5 0 0 0-.082.887l.41.26.001.002 4.995 3.178 3.178 4.995.002.002.26.41a.5.5 0 0 0 .886-.083l6-15Zm-1.833 1.89.471-1.178-1.178.471L5.93 9.363l.338.215a.5.5 0 0 1 .154.154l.215.338 7.494-7.494Z"/>
</svg></button>
            </form>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="static/js/jquery-3.2.1.js"></script>
        <script src="static/js/bootstrap.min.js"></script>
        <script src="static/js/bootstrap.js"></script>
    </body>
</html>