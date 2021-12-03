package com.emergentes.dao;

import com.emergentes.modelo.Maquina;
import java.util.List;

public interface MaquinaDAO {
    public void insert(Maquina maquina) throws Exception;
    public void update(Maquina maquina) throws Exception;
    public void delete(int id) throws Exception;
    public Maquina getById(int id) throws Exception;
    public List<Maquina> getAll() throws Exception;    
}
