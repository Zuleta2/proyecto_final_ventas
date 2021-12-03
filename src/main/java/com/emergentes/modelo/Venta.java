package com.emergentes.modelo;

import java.sql.Date;

public class Venta {
    private int id;
    private String maquina_id;
    private String cliente_id;
    private Date fecha;
    private String cliente;
    private String maquina;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getMaquina_id() {
        return maquina_id;
    }

    public void setMaquina_id(String maquina_id) {
        this.maquina_id = maquina_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }
    
    

    @Override
    public String toString() {
        return "Venta{" + "id=" + id + ", cliente_id=" + cliente_id + ", maquina_id=" + maquina_id + ", fecha=" + fecha + '}';
    }   
}
