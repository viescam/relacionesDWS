<%-- 
    Document   : listarActos
    Created on : 20-feb-2017, 10:25:15
    Author     : alumno
--%>

<%@page import="com.fpmislata.domain.Acto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                <th>Tipo</th>
                <th>Presupuesto</th>
                <th>Lugar</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                ArrayList<Acto> lista = (ArrayList) session.getAttribute("listaActos");
                for (Acto acto : lista) {
                    int id = acto.getId();
                    String tipo = acto.getTipo();
                    Float presupuesto = acto.getPresupuesto();
                    String lugar = acto.getLugar();                    
            %>                
            <tr>
                <td><%=id%></td>
                <td><%=tipo%></td>
                <td><%=presupuesto%></td>
                <td><%=lugar%></td>
                <td><a href="UpdateActo?accion=editar&id=<%=id%>">Modificar</a></td>
                <td><a href="DeleteActo?id=<%=id%>">Eliminar</a></td>
                <td><a href="ListarClientesPorActo?id=<%=id%>">Visualizar clientes</a></td>
            </tr>
            <% }%>
        </table>
        <br>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
