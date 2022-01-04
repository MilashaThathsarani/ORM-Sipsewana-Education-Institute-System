package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String getStudentIds() throws SQLException, ClassNotFoundException {
        return studentDAO.getStudentIds();
    }

    @Override
    public StudentDTO searchStudent(String studentId) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(
                student.getStudentId(),
                student.getStudentName(),
                student.getAddress(),
                student.getBirthday(),
                student.getAge(),
                student.getGender(),
                student.getPhoneNumber(),
                student.getEducation());

    }

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

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException {
        return studentDAO.update(new Student(
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
    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException {
        return studentDAO.delete(studentId);
    }

    @Override
    public ArrayList<StudentTM> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentTM> allStudents = new ArrayList<>();
        for (Student student : all) {
            allStudents.add(new StudentTM(student.getStudentId(),student.getStudentName(),student.getAddress(),student.getBirthday(),student.getAge(),student.getGender(),student.getPhoneNumber(),student.getEducation()));
        }
        return allStudents;
    }
}

