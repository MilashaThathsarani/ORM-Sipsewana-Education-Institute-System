package dao.custom;

import bo.SuperBO;
import dao.SuperDAO;
import dto.StudentDTO;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends SuperDAO<Student,String> {
    public String getStudentIds() throws SQLException,ClassNotFoundException;

}
