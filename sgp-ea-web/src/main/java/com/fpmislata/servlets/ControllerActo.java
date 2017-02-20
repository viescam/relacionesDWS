/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Acto;
import com.fpmislata.domain.Persona;
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
 * @author alumno
 */
@WebServlet(name = "ControllerActo",
        loadOnStartup = 1,
        urlPatterns = {"/AltaActo", "/ListarActos", "/ListarPersonasPorActo"
        })
public class ControllerActo extends HttpServlet {

    @EJB
    private PersonaServiceLocal personaService;

    @EJB
    private ActoServiceLocal actoService;

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

        if (userPath.equals("/ListarActos")) {
            listarActos(request, response);
        } else if (userPath.equals("/AltaActo")) {
            altaActo(request, response);
        } else if (userPath.equals("/EliminarActo")) {
            eliminarActo(request, response);
            // Si la operacion es Modificar Categoria
        } /* else if (userPath.equals("/UpdateCategoria")) {
            modificarActo(request, response);
        } */ else if (userPath.equals("/ListarPersonasPorActo")) {
            listarPersonasPorActo(request, response);
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

    private void listarActos(HttpServletRequest request, HttpServletResponse response) {
        try {
            List lista = actoService.listActos();
            ArrayList listaActos = new ArrayList<>(lista);

            List listaPersonasList = personaService.listPersonas();
            ArrayList<Persona> listaPersonas = new ArrayList<>(listaPersonasList);

            request.getSession().setAttribute("personas", listaPersonas);
            request.getSession().setAttribute("actos", listaActos);
            RequestDispatcher rd = request.getRequestDispatcher("/listarActos.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listarPersonasPorActo(HttpServletRequest request, HttpServletResponse response) {
        try {
            String idActo = request.getParameter("id");
            Acto a = new Acto();
            a.setId(Integer.parseInt(idActo));
            a = actoService.findActoById(a);

            ArrayList<Persona> listaPersonas = new ArrayList<>(a.getPersonas());

            request.getSession().setAttribute("personas", listaPersonas);
            RequestDispatcher rd = request.getRequestDispatcher("/listarPersonas.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void altaActo(HttpServletRequest request, HttpServletResponse response) {

        String tipo = request.getParameter("tipo");
        Float presupuesto = Float.parseFloat(request.getParameter("presupuesto"));
        String lugar = request.getParameter("lugar");

        String[] personas = request.getParameterValues("personasActo");

        Acto acto = new Acto();
        acto.setTipo(tipo);
        acto.setPresupuesto(presupuesto);
        acto.setLugar(lugar);
        try {
            actoService.addActo(acto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (personas != null) {
            Persona persona;
            for (String p : personas) {
                persona = new Persona();
                int id = Integer.parseInt(p);
                persona.setId(id);
                persona = personaService.findPersonaById(persona);

                persona.getActos().add(acto);
                acto.getPersonas().add(persona);
                try {
                    personaService.updatePersona(persona);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        listarActos(request,response);
    }

    private void eliminarActo(HttpServletRequest request, HttpServletResponse response) {
        //1. Recuperamos los parametros
        String idActo = request.getParameter("id");

        //2. Creamos el objeto Persona
        int id = Integer.parseInt(idActo);
        Acto acto = new Acto();
        acto.setId(id);
        acto = actoService.findActoById(acto);
        
        Set<Persona> listaPersonas = acto.getPersonas();
        ArrayList<Persona> personas = new ArrayList<>(listaPersonas);        
        for (Persona persona : personas) {
             persona.getActos().remove(persona);
           try {
               personaService.updatePersona(persona);               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
            //3. Eliminamos a la persona
            actoService.deleteActo(acto);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        // Ejecutamos el metodo y obtenemos la lista
        // Ejecutamos el metodo y obtenemos la lista
        listarActos(request,response);
    }

}
