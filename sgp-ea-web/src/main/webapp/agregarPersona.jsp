<%-- 
    Document   : agregarPersona.jsp
    Created on : 07-nov-2016, 8:36:25
    Author     : lodiade
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Agregar Persona</title>
    </head>
    <body>

        <h1>Agregar Persona</h1>

        <form action="AltaPersona" method="post">
            <input type="hidden" name="accion" value="agregar"/>

            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" />

            <label for="email">eMail:</label>
            <input type="text" name="email" style="display: block;"/>

            <label for="telefono">Teléfono:</label>
            <input type="text" name="telefono" style="display: block;"/>
            
            <label for="nombre_instrumento">Instrumento:</label>
            <input type="text" name="nombre_instrumento" style="display: block;" />

            <label for="cuerda">Cuerda del instrumento:</label>
            <input type="text" name="cuerda" style="display: block;" />

            <label for="marca">Marca del instrumento:</label>
            <input type="text" name="marca" style="display: block;" />

            <input type="submit" value="Enviar" />
        </form>

        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
