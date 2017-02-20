/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Persona;
import com.fpmislata.service.ActoServiceLocal;
import com.fpmislata.service.PersonaServiceLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        urlPatterns = {"/ListarActos"
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
        } /*else if (userPath.equals("/AddActo")) {
            altaActo(request, response);
        } else if (userPath.equals("/DeleteCategoria")) {
            eliminarActo(request, response);
            // Si la operacion es Modificar Categoria
        } else if (userPath.equals("/UpdateCategoria")) {
            modificarActo(request, response);
        } else if (userPath.equals("/ListarClientesPorActo")) {
            listarClientesPorActo(request, response);
        }*/
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

            request.getSession().setAttribute("listaPersonas", listaPersonas);
            request.getSession().setAttribute("listaActos", listaActos);
            RequestDispatcher rd = request.getRequestDispatcher("/listarActos.jsp");

            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
