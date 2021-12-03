package com.emergentes.dao;

import com.emergentes.modelo.Motor;
import java.util.List;

public interface MotorDAO {
    public void insert(Motor motor) throws Exception;
    public void update(Motor motor) throws Exception;
    public void delete(int id) throws Exception;
    public Motor getById(int id) throws Exception;
    public List<Motor> getAll() throws Exception;    
}
