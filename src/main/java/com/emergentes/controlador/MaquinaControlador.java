

package com.emergentes.controlador;

import com.emergentes.dao.MotorDAO;
import com.emergentes.dao.MotorDAOimpl;
import com.emergentes.dao.MaquinaDAO;
import com.emergentes.dao.MaquinaDAOimpl;
import com.emergentes.modelo.Maquina;
import com.emergentes.modelo.Motor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="MaquinaControlador", urlPatterns={"/MaquinaControlador"})
public class MaquinaControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException {
        
        try {
            MaquinaDAO dao = new MaquinaDAOimpl();
            MotorDAO daoMotor = new MotorDAOimpl();
            
            int id;
            List<Motor> lista_motores = null;
            
            Maquina maq = new Maquina();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    lista_motores = daoMotor.getAll();
                    
                    request.setAttribute("lista_motores", lista_motores);
                    request.setAttribute("maquina", maq);
                    request.getRequestDispatcher("frmmaquina.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    maq = dao.getById(id);
                    System.out.println(maq);
                    lista_motores = daoMotor.getAll();
                    
                    request.setAttribute("lista_motores", lista_motores);
                    request.setAttribute("maquina", maq);
                    request.getRequestDispatcher("frmmaquina.jsp").forward(request, response);
                    break;     
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/MaquinaControlador");
                    break;
                case "view":
                    List<Maquina> lista = dao.getAll();
                    request.setAttribute("maquinas", lista);
                    request.getRequestDispatcher("maquinas.jsp").forward(request, response);
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
        String motor_id = request.getParameter("motor_id");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        
        Maquina maq = new Maquina();
        
        maq.setId(id);
        maq.setMotor_id(motor_id);
        maq.setNombre(nombre);
        maq.setDescripcion(descripcion);
        maq.setPrecio(Double.parseDouble(precio));
        
        if (id == 0){
            try {
                MaquinaDAO dao = new MaquinaDAOimpl();
                dao.insert(maq);
                response.sendRedirect(request.getContextPath()+"/MaquinaControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else{
            try {
                MaquinaDAO dao = new MaquinaDAOimpl();
                System.out.println("Datos" + maq.toString());
                dao.update(maq);
                response.sendRedirect(request.getContextPath()+"/MaquinaControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}