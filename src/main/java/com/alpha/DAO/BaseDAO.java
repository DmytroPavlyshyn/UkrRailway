package com.alpha.DAO;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    public T getById(int id) throws SQLException;
    public boolean add(T t)throws SQLException;
    public boolean delete(int id)throws SQLException;
    public List<T> getAll() throws SQLException;
}
