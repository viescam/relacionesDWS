/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Acto;
import com.fpmislata.domain.Persona;
import com.fpmislata.domain.Instrumento;
import com.fpmislata.service.ActoServiceLocal;
import com.fpmislata.service.PersonaServiceLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author borja
 */
@WebServlet(name = "ControllerPersona",
        loadOnStartup = 1,
        urlPatterns = {"/ListarPersonas",
            "/AltaPersona",
            "/EliminarPersona",
            "/ModificarPersona",
            "/ControllerPersona",
            "/ListarPersonasPorActo"
        })
public class ControllerPersona extends HttpServlet {

    @EJB
    private ActoServiceLocal actoService;

    @EJB
    private PersonaServiceLocal personaService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();

        if (userPath.equals("/ListarPersonas")) {
            listarPersonas(request, response);
        } else if (userPath.equals("/AltaPersona")) {
            altaPersona(request, response);
        } else if (userPath.equals("/EliminarPersona")) {
            eliminarPersona(request, response);
        } else if (userPath.equals("/ModificarPersona")) {
            modificarPersona(request, response);
        } else if (userPath.equals("/ListarActosPorPersona")) {
            listarActosPorPersona(request, response);
        }

    }

    private void altaPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1. Recuperamos los parametros
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        //Recuperamos los datos del instrumento
        String nombre_instrumento = request.getParameter("nombre_instrumento");
        String cuerda = request.getParameter("cuerda");
        String marca = request.getParameter("marca");

        //2. Creamos el objeto Persona
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setEmail(email);
        persona.setTelefono(telefono);

        //3. Creamos el objeto Instrumento
        Instrumento instrumento = new Instrumento();
        instrumento.setNombre(nombre_instrumento);
        instrumento.setCuerda(cuerda);
        instrumento.setMarca(marca);
        persona.setInstrumento(instrumento);

        try {
            //Si ya existe el email no deberia registrarse
            personaService.addPersona(persona);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }
        String[] actos = request.getParameterValues("actosPersona");
        if (actos != null) {
            Acto acto;
            for (String a : actos) {
                acto = new Acto();
                int id = Integer.parseInt(a);
                acto.setId(id);
                acto = actoService.findActoById(acto);

                acto.getPersonas().add(persona);
                persona.getActos().add(acto);
                try {
                    //Si ya existe el email no deberia registrarse
                    actoService.updateActo(acto);
                } catch (Exception e) {
                    //Informamos cualquier error
                    e.printStackTrace();
                }
            }

        }

        // Volvemos a cargar la lista de personas
        // Ejecutamos el metodo y obtenemos la lista
        listarPersonas(request,response);

    }

    private void eliminarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1. Recuperamos los parametros
        String idPersona = request.getParameter("id");

        //2. Creamos el objeto Persona
        int id = Integer.parseInt(idPersona);
        Persona persona = new Persona();
        persona.setId(id);
        persona = personaService.findPersonaById(persona);
        
        Set<Acto> listaActos = persona.getActos();
        ArrayList<Acto> actos = new ArrayList<>(listaActos);
        for (Acto acto : actos) {
            acto.getPersonas().remove(persona);            
            try {
                actoService.updateActo(acto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
            //3. Eliminamos a la persona
            personaService.deletePersona(persona);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        // Ejecutamos el metodo y obtenemos la lista
        // Ejecutamos el metodo y obtenemos la lista
        listarPersonas(request,response);
    }

    private void modificarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("editar")) {

            //1. Recuperamos el id de la persona seleccionada
            String idPersona = request.getParameter("id");
            if (idPersona != null) {
                //2. Creamos el objeto persona a recuperar
                int id = Integer.valueOf(idPersona);
                Persona persona = new Persona();
                persona.setId(id);
                try {
                    persona = this.personaService.findPersonaById(persona);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //3. Compartimos el objeto persona en alcance request
                request.setAttribute("persona", persona);

                //4. Redireccionamos a la pagina para editar el objeto Persona
                request.getRequestDispatcher("/modificarPersona.jsp").forward(request, response);
            }
        } else if (accion != null && accion.equals("modificar")) {

            //1. Recuperamos los parametros
            String idPersona = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");

            //Recuperamos los datos del instrumento
            String idinstrumento = request.getParameter("idinstrumento");
            String nombre_instrumento = request.getParameter("nombre_instrumento");
            String cuerda = request.getParameter("cuerda");
            String marca = request.getParameter("marca");

            //2. Creamos el objeto Persona
            Persona persona = new Persona();
            int id = Integer.valueOf(idPersona);
            persona.setId(id);
            persona.setNombre(nombre);
            persona.setEmail(email);
            persona.setTelefono(telefono);

            //3. Creamos el objeto Instrumento
            Instrumento instrumento = new Instrumento();
            instrumento.setId(Integer.valueOf(idinstrumento));
            instrumento.setNombre(nombre_instrumento);
            instrumento.setCuerda(cuerda);
            instrumento.setMarca(marca);
            persona.setInstrumento(instrumento);

            try {
                this.personaService.updatePersona(persona);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }

            // Volvemos a cargar la lista de personas
            // Ejecutamos el metodo y obtenemos la lista
            List lista = personaService.listPersonas();
            ArrayList<Persona> listaArray = new ArrayList<>(lista);
            // Asignamos al request el atributo lista
            request.getSession().setAttribute("personas", listaArray);

            request.getRequestDispatcher("/listarPersonas.jsp").forward(request, response);
        }
    }

    private void listarPersonas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Ejecutamos el metodo y obtenemos la lista
            List listaPersonas = personaService.listPersonas();
            ArrayList<Persona> listaArray = new ArrayList<>(listaPersonas);
            // Asignamos al request el atributo lista
            List lista = actoService.listActos();
            ArrayList listaActos = new ArrayList<>(lista);
            
            request.getSession().setAttribute("personas", listaArray);
            request.getSession().setAttribute("actos", listaActos);
            // Pasamos al RequestDispatcher la pagina a cargar

            RequestDispatcher rd = request.getRequestDispatcher("/listarPersonas.jsp");
            // Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarActosPorPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idPersona = request.getParameter("id");

            //2. Creamos el objeto Persona
            int id = Integer.parseInt(idPersona);
            Persona persona = new Persona();
            persona.setId(id);
            persona = personaService.findPersonaById(persona);

            ArrayList<Acto> listaActos = new ArrayList<>(persona.getActos());

            request.getSession().setAttribute("actos", listaActos);
            RequestDispatcher rd = request.getRequestDispatcher("/listarActos.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
