package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.MaquinaDAO;
import com.emergentes.dao.MaquinaDAOimpl;
import com.emergentes.dao.VentaDAO;
import com.emergentes.dao.VentaDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Maquina;
import com.emergentes.modelo.Venta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="VentaControlador", urlPatterns={"/VentaControlador"})
public class VentaControlador extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException {
        
        try {
            VentaDAO dao = new VentaDAOimpl();
            MaquinaDAO daoMaquina = new MaquinaDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
         
            int id;
            List<Maquina> lista_maquinas = null;
            List<Cliente> lista_clientes = null;
            
            
            Venta venta = new Venta();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    lista_maquinas = daoMaquina.getAll();
                    lista_clientes = daoCliente.getAll();
                   
                    request.setAttribute("lista_maquinas", lista_maquinas);
                    request.setAttribute("lista_clientes", lista_clientes);
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    venta = dao.getById(id);
                    System.out.println(venta);
                    lista_maquinas = daoMaquina.getAll();
                    lista_clientes = daoCliente.getAll();
                    request.setAttribute("lista_maquinas", lista_maquinas);
                    request.setAttribute("lista_clientes", lista_clientes);
                    request.setAttribute("venta", venta);
                    request.getRequestDispatcher("frmventa.jsp").forward(request, response);
                    break;     
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/VentaControlador");
                    break;
                case "view":
                    List<Venta> lista = dao.getAll();
                    request.setAttribute("ventas", lista);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
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
        String maquina_id = request.getParameter("maquina_id");
        String cliente_id = request.getParameter("cliente_id");
        String fecha = request.getParameter("fecha");
        System.out.println("Fecha: ====> " + fecha);
        
        Venta venta = new Venta();
        
         venta.setId(id);
        venta.setMaquina_id(maquina_id);
        venta.setCliente_id(cliente_id);
        venta.setFecha(convierteFecha(fecha));
        System.out.println(venta.toString());
        
        if (id == 0){
            try {
                VentaDAO dao = new VentaDAOimpl();
                dao.insert(venta);
                response.sendRedirect(request.getContextPath()+"/VentaControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else{
            try {
                VentaDAO dao = new VentaDAOimpl();
                System.out.println("Datos" + venta.toString());
                dao.update(venta);
                response.sendRedirect(request.getContextPath()+"/VentaControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
    
    public static Date convierteFecha(String fecha)
    {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaBD = null;
        
        try {
            java.util.Date fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaBD;
    }

}