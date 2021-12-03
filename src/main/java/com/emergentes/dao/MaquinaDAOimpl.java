
package com.emergentes.dao;

import com.emergentes.modelo.Maquina;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MaquinaDAOimpl extends ConexionDB implements MaquinaDAO{

    @Override
    public void insert(Maquina maquina) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into maquinas (motor_id, nombre, descripcion, precio) values (?, ?, ?, ?)");
            ps.setString(1, maquina.getMotor_id());
            ps.setString(2, maquina.getNombre());
            ps.setString(3, maquina.getDescripcion());
            ps.setDouble(4, maquina.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }        
    }

    @Override
    public void update(Maquina maquina) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE maquinas set motor_id = ?,nombre =?, descripcion = ?, precio = ? where id = ?");
            ps.setString(1, maquina.getMotor_id());
            ps.setString(2, maquina.getNombre());
            ps.setString(3, maquina.getDescripcion());            
            ps.setDouble(4, maquina.getPrecio());            
            ps.setInt(5,maquina.getId());
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
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM maquinas WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }        
    }

    @Override
    public Maquina getById(int id) throws Exception {
        Maquina maq = new Maquina();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM maquinas WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maq.setId(rs.getInt("id"));
                maq.setMotor_id(rs.getString("motor_id"));
                maq.setNombre(rs.getString("nombre"));
                maq.setDescripcion(rs.getString("descripcion"));
                maq.setPrecio(rs.getDouble("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return maq;        
    }

    @Override
    public List<Maquina> getAll() throws Exception {
        List<Maquina> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT mm.id, m.nombre, mm.nombre as nombremaquina, mm.descripcion, mm.precio from motores as m, maquinas as mm where m.id=mm.motor_id");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Maquina>();
            while(rs.next()){
                Maquina maq = new Maquina();
                System.out.println("Fila ...");
                maq.setId(rs.getInt("id"));
                maq.setMotor_id(rs.getString("nombre"));
                maq.setNombre(rs.getString("nombremaquina"));
                maq.setDescripcion(rs.getString("descripcion"));
                maq.setPrecio(rs.getDouble("precio"));
                lista.add(maq);
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
