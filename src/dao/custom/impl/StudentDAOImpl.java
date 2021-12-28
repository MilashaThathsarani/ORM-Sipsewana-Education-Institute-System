package dao.custom.impl;

import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import entity.SuperEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student studentDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean add(SuperEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Object o) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SuperEntity entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public SuperEntity search(Object o) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
