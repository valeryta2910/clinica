/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import umariana.clinica.GestionarPaciente;

/**
 *
 * @author Tatiana Acosta
 */
@WebServlet(name = "recibePaciente", urlPatterns = {"/recibePaciente"}, loadOnStartup=1)
public class recibePaciente extends HttpServlet {
    GestionarPaciente gestionar = new GestionarPaciente();

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet recibePaciente</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet recibePaciente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    public void init() throws ServletException{
        gestionar.cargarArchivo();
        getServletContext().setAttribute("gestionar", gestionar);
    }
    
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
        
        String accion = request.getParameter("accion");
        
        try{
            switch (accion){
                case "guardar":
                    int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
                    String nombre  = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    String diagnostico = request.getParameter("diagnostico");
                    gestionar.agregarPaciente(idPaciente, nombre, apellido, diagnostico);
                    gestionar.guardarArchivo();
                    request.getSession().setAttribute("mensaje", "Paciente guardado con éxito");
                    break;
                case "editar":
                    idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
                    nombre = request.getParameter("nombre");
                    apellido = request.getParameter("apellido");
                    diagnostico = request.getParameter("diagnostico");
                    boolean resultado = gestionar.editarPaciente(idPaciente, nombre, apellido, diagnostico);
                    if(resultado==true){
                        gestionar.guardarArchivo();
                        request.getSession().setAttribute("mensaje", "Paciente editado con éxito");
                    }
                    else{
                        request.getSession().setAttribute("mensaje", "No se pudo editar: Paciente no editado");
                    }
                    break;
                case "eliminar":
                    idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
                    boolean respuesta = gestionar.eliminarPaciente(idPaciente);
                    if(respuesta == true){
                        gestionar.guardarArchivo();
                        request.getSession().setAttribute("mensaje", "Paciente eliminado con éxito");
                    }
                    else{
                        request.getSession().setAttribute("mensaje", "No se pudo eliminar: Paciente no encontrado");
                    }
                    break;
            }
        }catch (NumberFormatException e){
            request.getSession().setAttribute("mensaje", "El id ingresado no es válido");
        }
            response.sendRedirect("index.jsp");
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
