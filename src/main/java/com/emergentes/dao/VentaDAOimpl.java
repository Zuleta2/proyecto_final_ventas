package com.emergentes.dao;

import com.emergentes.modelo.Venta;
import com.emergentes.utiles.ConexionDB;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VentaDAOimpl extends ConexionDB implements VentaDAO{
    
    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();
            
            String fec = venta.getFecha().toString();
            
            PreparedStatement ps = this.conn.prepareStatement("INSERT into ventas (maquina_id, cliente_id, fecha) values (?, ?, ?)");
            ps.setString(1, venta.getMaquina_id());
            ps.setString(2, venta.getCliente_id());
            ps.setString(3, fec);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }        
    }

    @Override
    public void update(Venta venta) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE ventas set maquina_id = ?, cliente_id = ?, fecha = ? where id = ?");
            ps.setString(1, venta.getMaquina_id());
            ps.setString(2, venta.getCliente_id());            
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }

    @Override
    public void delete(int id) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM ventas WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }

    @Override
    public Venta getById(int id) throws Exception {
        Venta venta = new Venta();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM ventas WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                venta.setId(rs.getInt("id"));
                venta.setMaquina_id(rs.getString("maquina_id"));
                venta.setCliente_id(rs.getString("cliente_id"));
                venta.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return venta;        
    }

    @Override
    public List<Venta> getAll() throws Exception {
        List<Venta> lista = null;
        try{
            this.conectar();
            String sql = "SELECT v.*,p.nombre as maquina, c.nombre as cliente FROM ventas v ";
                    sql += "LEFT JOIN maquinas p on v.maquina_id = p.id ";
                    sql += "LEFT JOIN clientes c on v.cliente_id = c.id ";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Venta>();
            while(rs.next()){
                Venta venta = new Venta();
                venta.setId(rs.getInt("id"));
                venta.setMaquina_id(rs.getString("maquina_id"));
                venta.setCliente_id(rs.getString("cliente_id"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setMaquina(rs.getString("maquina"));
                venta.setCliente(rs.getString("cliente"));                
                System.out.println("Fila: "+ venta.toString());                
                lista.add(venta);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }  
        return lista;        
    }
}
