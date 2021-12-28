package dao;


import entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperDAO<Entity extends SuperEntity, ID> {
    boolean add(Entity entity) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(Entity entity) throws SQLException, ClassNotFoundException;

    Entity search(ID id) throws SQLException, ClassNotFoundException;

    ArrayList<Entity> getAll() throws SQLException, ClassNotFoundException;
}
