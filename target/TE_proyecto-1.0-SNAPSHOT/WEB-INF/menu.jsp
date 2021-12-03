<%
    String opcion = request.getParameter("opcion");
%>         

<ul class="nav nav-tabs">
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("maquinas") ? "active" : "")%>" href="MaquinaControlador"><b>MAQUINAS</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("clientes") ? "active" : "")%>" href="ClienteControlador"><b>CLIENTES</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("ventas") ? "active" : "")%>" href="VentaControlador"><b>VENTAS</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= (opcion.equals("motores") ? "active" : "")%>" href="MotorControlador"><b>MOTORES</b></a>
    </li>
</ul>
<style>
    body{background-image: url("static/imagenes/descarga.png")}
</style>
