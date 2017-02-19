/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.servlets;

import com.fpmislata.domain.Instrumento;
import com.fpmislata.domain.Partitura;
import com.fpmislata.domain.Persona;
import com.fpmislata.service.PartituraServiceLocal;
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
@WebServlet(name = "ControllerPartitura",
        loadOnStartup = 1,
        urlPatterns = {"/ListarPartituras",
            "/AltaPartitura",
            "/ModificarPartitura",
            "/ListarPartiturasPorPersona",
            "/EliminarPartitura",
            "/ControllerPartitura"})
public class ControllerPartitura extends HttpServlet {

    @EJB
    private PartituraServiceLocal partituraService;

    @EJB
    private PersonaServiceLocal personaService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
// Si la operacion es listar partituras
        if (userPath.equals("/ListarPartituras")) {
            listarPartituras(request, response);
// Si la operacion es Alta Partitura
        } else if (userPath.equals("/AltaPartitura")) {
            altaPartitura(request, response);
// Si la operacion es Listar partituras por persona
        } else if (userPath.equals("/ListarPartiturasPorPersona")) {
            listarPartituraPorPersona(request, response);
        } else if (userPath.equals("/EliminarPartitura")) {
            eliminarPartitura(request, response);
        }  else if (userPath.equals("/ModificarPartitura")) {
            modificarPartitura(request, response);
        } else if (userPath.equals("/ControllerPartitura")) {
            listarPartituras(request, response);
        }
    }

    private void listarPartituras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
// Ejecutamos el metodo y obtenemos la lista
            List listaPartituras = partituraService.listPartituras();

// Asignamos al request el atributo lista
            ArrayList<Partitura> listaArrayPartitura = new ArrayList<>(listaPartituras);
            request.getSession().setAttribute("partituras", listaArrayPartitura);

// Como podemos agregar partituras necesitamos cargar las personas
            List listaPersonas = personaService.listPersonas();
            ArrayList<Persona> listaArrayPersonas = new ArrayList<>(listaPersonas);
            request.getSession().setAttribute("personas", listaArrayPersonas);

// Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listarPartituras.jsp");
// Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void altaPartitura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//1. Recuperamos los parametros del partitura
        String tipo = request.getParameter("tipo");
        String compositor = request.getParameter("compositor");
        String nombre = request.getParameter("nombre");        
        int id_persona = Integer.parseInt(request.getParameter("propietario"));
//2. Creamos el objeto Partitura
        Partitura partitura = new Partitura(tipo, compositor, nombre);

//3. Recuperamos el objeto Persona y asignamos el partitura
        Persona persona = new Persona();
        persona.setId(id_persona);
        persona = personaService.findPersonaById(persona);

//4. Asignamos los valores
        partitura.setPersona(persona);
        persona.getPartituras().add(partitura);
        try {
            partituraService.addPartitura(partitura);
            personaService.updatePersona(persona);
        } catch (Exception e) {
//Informamos cualquier error
            e.printStackTrace();
        }
        listarPartituras(request, response);
    }

    private void listarPartituraPorPersona(HttpServletRequest request, HttpServletResponse response) {
        try {
//1. Recuperamos los parametros de la persona
            String id = request.getParameter("id");

            Persona p = new Persona();
            p.setId(Integer.valueOf(id));
            p = personaService.findPersonaById(p);

// Asignamos al request el atributo lista
            ArrayList<Partitura> listaArrayPartitura = new ArrayList<>(p.getPartituras());
            request.getSession().setAttribute("partituras", listaArrayPartitura);

// Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listarPartituras.jsp");
// Cargamos la pagina
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void eliminarPartitura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1. Recuperamos los parametros
        String idPartitura = request.getParameter("id");

        //2. Creamos el objeto Persona
        int id = Integer.parseInt(idPartitura);
        Partitura partitura = new Partitura();
        partitura.setId(id);
        partitura = partituraService.findPartituraById(partitura);
        
        Persona persona = partitura.getPersona();
        partitura.getPersona().getPartituras().remove(partitura);
        try {
            //3. Eliminamos a la persona
            partituraService.deletePartitura(partitura);
            personaService.updatePersona(persona);
        } catch (Exception e) {
            //Informamos cualquier error
            e.printStackTrace();
        }

        // Ejecutamos el metodo y obtenemos la lista
        // Ejecutamos el metodo y obtenemos la lista
        List lista = partituraService.listPartituras();
        ArrayList<Persona> listaArray = new ArrayList<>(lista);
        // Asignamos al request el atributo lista
        request.getSession().setAttribute("partituras", listaArray);
        // Pasamos al RequestDispatcher la pagina a cargar
        RequestDispatcher rd = request.getRequestDispatcher("/listarPartituras.jsp");
        // Cargamos la pagina
        rd.forward(request, response);
    }
     
     private void modificarPartitura(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("editar")) {

            //1. Recuperamos el id de la persona seleccionada
            String idPartitura = request.getParameter("id");
            if (idPartitura != null) {
                //2. Creamos el objeto persona a recuperar
                int id = Integer.valueOf(idPartitura);
                Partitura partitura = new Partitura();
                partitura.setId(id);
                try {
                    partitura = this.partituraService.findPartituraById(partitura);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //3. Compartimos el objeto persona en alcance request
                request.setAttribute("partitura", partitura);

                //4. Redireccionamos a la pagina para editar el objeto Persona
                request.getRequestDispatcher("/modificarPartitura.jsp").forward(request, response);
            }
        } else if (accion != null && accion.equals("modificar")) {
            
            String idinstrumento = request.getParameter("id");
            String tipo = request.getParameter("tipo");
            String compositor = request.getParameter("compositor");
            String nombre = request.getParameter("nombre");        
            int id_persona = Integer.parseInt(request.getParameter("propietario"));
            //2. Creamos el objeto Partitura
            Partitura partitura = new Partitura();
            partitura.setId(Integer.parseInt(idinstrumento));
            
            partitura = partituraService.findPartituraById(partitura);
            
            partitura.setNombre(nombre);
            partitura.setCompositor(compositor);
            partitura.setTipo(tipo);

            //3. Recuperamos el objeto Persona y asignamos el partitura
            Persona persona = new Persona();
            persona.setId(id_persona);
            persona = personaService.findPersonaById(persona);
            Persona personaAntigua = partitura.getPersona();
            //4. Asignamos los valores
            partitura.getPersona().getPartituras().remove(partitura);
            partitura.setPersona(persona);
            persona.getPartituras().add(partitura);            

            try {
                partituraService.updatePartitura(partitura);
                personaService.updatePersona(persona);
                personaService.updatePersona(personaAntigua);
            } catch (Exception e) {
                //Informamos cualquier error
                e.printStackTrace();
            }
            
            listarPartituras(request, response);
            /*List lista = partituraService.listPartituras();
            ArrayList<Persona> listaArray = new ArrayList<>(lista);
            // Asignamos al request el atributo lista
            request.getSession().setAttribute("partituras", listaArray);
            // Pasamos al RequestDispatcher la pagina a cargar
            RequestDispatcher rd = request.getRequestDispatcher("/listarPartituras.jsp");
            // Cargamos la pagina
            rd.forward(request, response);*/
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
