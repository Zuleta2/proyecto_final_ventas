
package com.emergentes.dao;

import com.emergentes.modelo.Motor;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MotorDAOimpl extends ConexionDB implements MotorDAO{

    @Override
    public void insert(Motor motor) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into motores (nombre, descripcion, precio) values (?, ?, ?)");
            ps.setString(1, motor.getNombre());
            ps.setString(2, motor.getDescripcion());
            ps.setDouble(3, motor.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }        
    }

    @Override
    public void update(Motor motor) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE motores set nombre = ?, descripcion = ?, precio = ? where id = ?");
            ps.setString(1, motor.getNombre());
            ps.setString(2, motor.getDescripcion());            
            ps.setDouble(3, motor.getPrecio());            
            ps.setInt(4,motor.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM motores WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }

    @Override
    public Motor getById(int id) throws Exception {
        Motor cli = new Motor();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM motores WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setDescripcion(rs.getString("descripcion"));
                cli.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;        
    }

    @Override
    public List<Motor> getAll() throws Exception {
        List<Motor> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM motores");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Motor>();
            while(rs.next()){
                Motor cli = new Motor();
                System.out.println("Fila ...");
                cli.setId(rs.getInt("id"));
                cli.setNombre(rs.getString("nombre"));
                cli.setDescripcion(rs.getString("descripcion"));
                cli.setPrecio(rs.getDouble("precio"));
                lista.add(cli);
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
