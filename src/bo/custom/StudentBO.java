package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    //String getCustomerIds() throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String studentId) throws SQLException, ClassNotFoundException;

    boolean add(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String studentId);

    boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    //boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    ArrayList<StudentTM> getAll() throws SQLException, ClassNotFoundException;

    //int customers() throws SQLException, ClassNotFoundException;
}
