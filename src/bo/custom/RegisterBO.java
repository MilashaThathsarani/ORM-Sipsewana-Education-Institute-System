package bo.custom;

import bo.SuperBO;
import dto.ProgramDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.RegisterDetail;

import java.sql.SQLException;
import java.util.List;

public interface RegisterBO extends SuperBO {
    boolean purchaseRegister(RegistrationDTO dto) throws SQLException, ClassNotFoundException;

    String getRegisterIds()throws SQLException, ClassNotFoundException;

    StudentDTO searchStudents(String studentId)throws SQLException, ClassNotFoundException;

    ProgramDTO searchPrograms(String programId) throws SQLException, ClassNotFoundException;

    List <String>getAllStudentIds()throws SQLException, ClassNotFoundException;

    List<String> getAllProgramIds()throws SQLException, ClassNotFoundException;

    //int orders() throws SQLException, ClassNotFoundException;
}
