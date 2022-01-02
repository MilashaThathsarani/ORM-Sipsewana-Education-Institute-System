package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    public ArrayList<StudentDTO> getStudentIds() throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String studentId) throws SQLException, ClassNotFoundException;

    boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String studentId);

    boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException;

    ArrayList<StudentTM> getAll() throws SQLException, ClassNotFoundException;

    //int customers() throws SQLException, ClassNotFoundException;
}
