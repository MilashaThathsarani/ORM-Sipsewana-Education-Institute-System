package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;

import java.sql.SQLException;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.add(new Student(
                studentDTO.getStudentId(),
                studentDTO.getStudentName(),
                studentDTO.getAddress(),
                studentDTO.getBirthday(),
                studentDTO.getAge(),
                studentDTO.getGender(),
                studentDTO.getPhoneNumber(),
                studentDTO.getEducation()));
    }


    @Override
    public boolean ifStudentExist(String studentId) {
        return false;
    }
}

