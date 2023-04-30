package org.example.DAO;

import org.example.model.Superclass;

import java.util.List;

public abstract class AbstractDAO {
    public abstract void create() ;

    public abstract List<Superclass> findAll();
}
