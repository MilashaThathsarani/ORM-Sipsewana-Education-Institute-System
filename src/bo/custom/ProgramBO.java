package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.StudentDTO;
import view.tm.ProgramTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProgramBO extends SuperBO {
    String getCustomerIds() throws SQLException, ClassNotFoundException;

    ProgramDTO searchProgram(String programId) throws SQLException, ClassNotFoundException;

    boolean add(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

    //boolean ifStudentExist(String studentId);

    boolean update(ProgramDTO programDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String programId) throws SQLException, ClassNotFoundException;

    ArrayList<ProgramTM> getAll() throws SQLException, ClassNotFoundException;

    //int customers() throws SQLException, ClassNotFoundException;
}
