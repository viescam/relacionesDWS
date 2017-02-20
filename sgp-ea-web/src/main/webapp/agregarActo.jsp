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
        <title>Agregar Acto</title>
    </head>
    <body>

        <h1>Agregar Acto</h1>

        <form action="AltaActo" method="post">
            <input type="hidden" name="accion" value="agregar"/>

            <label for="tipo">Tipo:</label>
            <input type="text" name="tipo" style="display: block;" />

            <label for="presupuesto">Presupuesto:</label>
            <input type="text" name="presupuesto" style="display: block;"/>

            <label for="lugar">Lugar:</label>
            <input type="text" name="lugar" style="display: block;"/>

            <label for="persona">Personas:</label>
            <%
                ArrayList<Persona> lista = (ArrayList) session.getAttribute("personas");
                for (Persona persona : lista) {
                    int id = persona.getId();
                    String nombre = persona.getNombre();
                    String email = persona.getEmail();

            %>
            <input type="checkbox" name="personasActo" value="<%=id%>"><%=nombre%>
            <%}%>
        

        <input type="submit" value="Enviar" />
    </form>

    <a href="index.jsp">Regresar al Inicio</a>
</body>
</html>
