<%-- 
    Document   : listarPartituras
    Created on : 15-feb-2017, 21:14:32
    Author     : Vicente
--%>

<%@page import="com.fpmislata.domain.Partitura"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de partituras</title>
    </head>
    <body>
        <h1>Listado de Partituras</h1>

        <a href="agregarPartitura.jsp">Agregar Partitura</a>
        <br/>
        <br/>

        <table border="1">
            <tr>
                <th>id</th>
                <th>Tipo</th>
                <th>Compositor</th>
                <th>Nombre</th>
                <th>Propietario</th>
                <th></th>
                <th></th>

            </tr>

            <%
                ArrayList<Partitura> lista = (ArrayList) session.getAttribute("partituras");
                for (Partitura partitura : lista) {

                    int id = partitura.getId();
                    String tipo = partitura.getTipo();
                    String compositor = partitura.getCompositor();
                    String nombre_partitura = partitura.getNombre();
                    String propietario = partitura.getPersona().getNombre();                    
            %>                
            <tr>
                <td><%=id%></a></td>
                <td><%=tipo%></td>
                <td><%=compositor%></td>
                <td><%=nombre_partitura%></td>
                <td><%=propietario%></td>
                <td><a href="ModificarPartitura?accion=editar&id=<%=id%>">Modificar</td>
                <td><a href="EliminarPartitura?id=<%=id%>">Eliminar</a></td>
                
            </tr>
            <% }%>
        </table>
        <br>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
