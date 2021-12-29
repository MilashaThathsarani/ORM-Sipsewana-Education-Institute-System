package bo.custom;

import bo.SuperBO;
import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    //String getCustomerIds() throws SQLException, ClassNotFoundException;

    //CustomerDTO searchCustomer(String custId) throws SQLException, ClassNotFoundException;

    boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String studentId);

    //boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    //boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

   // ArrayList<CustomerTm> getAll() throws SQLException, ClassNotFoundException;

    //int customers() throws SQLException, ClassNotFoundException;
}
