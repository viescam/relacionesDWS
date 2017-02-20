<%-- 
    Document   : listarActos
    Created on : 20-feb-2017, 12:33:30
    Author     : alumno
--%>

<%@page import="com.fpmislata.domain.Acto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Actos</title>
    </head>
    <body>
        <h1>Listado de Actos</h1>
        <h2>Versión con controlador</h2>
        <a href="agregarActo.jsp">Agregar Acto</a>
        <br/>
        <br/>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Lugar</th>
                <th>Presupuesto</th>
                <th>Tipo</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                ArrayList<Acto> lista = (ArrayList) session.getAttribute("actos");
                for (Acto acto : lista) {
                    int id = acto.getId();
                    String lugar = acto.getLugar();
                    Float precio = acto.getPresupuesto();
                    String tipo = acto.getTipo();
            %>                
            <tr>
                <td><%=id%></td>
                <td><%=lugar%></td>
                <td><%=precio%></td>
                <td><%=tipo%></td>
                <td><a href="UpdateCategoria?accion=editar&id=<%=id%>">Modificar</a></td>
                <td><a href="EliminarActo?id=<%=id%>">Eliminar</a></td>
                <td><a href="ListarPersonasPorActo?id=<%=id%>">Visualizar personas</a></td>
            </tr>
            <% }%>
        </table>
        <br>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
</html>
