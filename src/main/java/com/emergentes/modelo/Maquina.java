package com.emergentes.modelo;

public class Maquina {
    private int id;
    private String motor_id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String motor;

    public Maquina() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotor_id() {
        return motor_id;
    }

    public void setMotor_id(String motor_id) {
        this.motor_id = motor_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return "Maquina{" + "id=" + id + ", motor_id=" + motor_id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", motor=" + motor + '}';
    }
  
}

