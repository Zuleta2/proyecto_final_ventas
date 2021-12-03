

package com.emergentes.controlador;

import com.emergentes.dao.MotorDAO;
import com.emergentes.dao.MotorDAOimpl;
import com.emergentes.modelo.Motor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="MotorControlador", urlPatterns={"/MotorControlador"})
public class MotorControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException {
        
        try {
            MotorDAO dao = new MotorDAOimpl();
            int id;
            Motor mot = new Motor();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("motor", mot);
                    request.getRequestDispatcher("frmmotor.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    mot = dao.getById(id);
                    System.out.println(mot);
                    
                    request.setAttribute("motor", mot);
                    request.getRequestDispatcher("frmmotor.jsp").forward(request, response);
                    break;     
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/MotorControlador");
                    break;
                case "view":
                    List<Motor> lista = dao.getAll();
                    request.setAttribute("motores", lista);
                    request.getRequestDispatcher("motores.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        
        Motor mot = new Motor();
        
        mot.setId(id);
        mot.setNombre(nombre);
        mot.setDescripcion(descripcion);
        mot.setPrecio(Double.parseDouble(precio));
        
        if (id == 0){
            try {
                MotorDAO dao = new MotorDAOimpl();
                dao.insert(mot);
                response.sendRedirect(request.getContextPath()+"/MotorControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else{
            try {
                MotorDAO dao = new MotorDAOimpl();
                System.out.println("Datos" + mot.toString());
                dao.update(mot);
                response.sendRedirect(request.getContextPath()+"/MotorControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}