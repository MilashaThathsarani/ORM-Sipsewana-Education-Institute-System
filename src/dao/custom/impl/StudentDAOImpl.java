package dao.custom.impl;

import dao.custom.StudentDAO;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Student search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
