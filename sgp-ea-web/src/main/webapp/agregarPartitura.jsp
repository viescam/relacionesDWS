<%-- 
    Document   : agregarPersona.jsp
    Created on : 07-nov-2016, 8:36:25
    Author     : lodiade
--%>

<%@page import="com.fpmislata.domain.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agregar Partitura</title>
    </head>
    <body>

        <h1>Agregar Partitura</h1>

        <form action="AltaPartitura" method="post">
            <input type="hidden" name="accion" value="agregar"/>

            <label for="tipo">Tipo:</label>
            <input type="text" name="tipo" style="display: block;" />

            <label for="compositor">Compositor:</label>
            <input type="text" name="compositor" style="display: block;"/>

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;"/>

            <label for="propietario">Propietario:</label>
            <select name="propietario" id="propietario" style="display: block;"/>
            <%
                ArrayList<Persona> lista = (ArrayList) session.getAttribute("personas");
                for (Persona persona : lista) {
                    int id = persona.getId();
                    String nombre = persona.getNombre();
                    String email = persona.getEmail();

            %>
            <option value="<%=id%>"><%=nombre%> - <%=email%> </option> 
            <%}%>
        </select>

        <input type="submit" value="Enviar" />
    </form>

    <a href="index.jsp">Regresar al Inicio</a>
</body>
</html>
