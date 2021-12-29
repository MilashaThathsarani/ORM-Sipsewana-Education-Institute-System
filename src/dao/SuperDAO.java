package dao;


import entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperDAO <T, ID> extends SuperEntity {
    boolean add(T t) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(T t) throws SQLException, ClassNotFoundException;

    T search(ID id) throws SQLException, ClassNotFoundException;

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
}
