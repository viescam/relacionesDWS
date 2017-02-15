<%-- 
    Document   : modificarPersona
    Created on : 07-nov-2016, 11:47:02
    Author     : lodiade
--%>
<%@page import="com.fpmislata.domain.Partitura"%>
<%@page import="com.fpmislata.domain.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Modificar Persona</title>
    </head>
    <body>

        <h1>Modificar Persona</h1>

        <form action="ModificarPartitura?accion=modificar&id=${partitura.id}" method="post">

            <label for="tipo">Tipo:</label>
            <input type="text" name="tipo" value="${partitura.tipo}" style="display: block;" />

            <label for="compositor">Compositor:</label>
            <input type="text" name="compositor" value="${partitura.compositor}" style="display: block;"/>

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" value="${partitura.nombre}" style="display: block;"/>

            <label for="propietario">Propietario:</label>
            <select name="propietario" id="propietario" style="display: block;"/>
            <%
                ArrayList<Persona> lista = (ArrayList) session.getAttribute("personas");
                Partitura partitura = (Partitura) request.getAttribute("partitura");
                for (Persona persona : lista) {
                    int id = persona.getId();
                    String nombre = persona.getNombre();
                    String email = persona.getEmail();
                    if (id == partitura.getId()) {
            %>            
                        <option value="<%=id%>" selected="selected"><%=nombre%> - <%=email%> </option>     
                    <%} else {%>            
                        <option value="<%=id%>"><%=nombre%> - <%=email%> </option> 
                    <%}
                }%>
        </select>

        <input type="submit" name="guardar" value="guardar">
    </form>
</body>
</html>
