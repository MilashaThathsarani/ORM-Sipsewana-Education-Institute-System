package dao;


import entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SuperDAO <Entity extends SuperEntity, Id> {
    boolean add(Entity t) throws SQLException, ClassNotFoundException;

    boolean delete(Id id) throws SQLException, ClassNotFoundException;

    boolean update(Entity t) throws SQLException, ClassNotFoundException;

    Entity search(Id id) throws SQLException, ClassNotFoundException;

    ArrayList<Entity> getAll() throws SQLException, ClassNotFoundException;
}
