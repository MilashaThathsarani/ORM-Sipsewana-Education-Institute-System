package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.StudentDTO;

import java.sql.SQLException;

public interface ProgramBO extends SuperBO {
    //String getCustomerIds() throws SQLException, ClassNotFoundException;

    //ProgramDTO searchProgram(String programId) throws SQLException, ClassNotFoundException;

    boolean add(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

    //boolean ifStudentExist(String studentId);

    //boolean update(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    //boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    // ArrayList<CustomerTm> getAll() throws SQLException, ClassNotFoundException;

    //int customers() throws SQLException, ClassNotFoundException;
}
