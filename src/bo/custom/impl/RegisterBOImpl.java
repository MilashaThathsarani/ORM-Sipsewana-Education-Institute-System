package bo.custom.impl;

import bo.custom.RegisterBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dao.custom.RegisterDAO;
import dao.custom.RegisterDetailDAO;
import dao.custom.StudentDAO;
import dto.ProgramDTO;
import dto.RegisterDetailDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import entity.Program;
import entity.RegisterDetail;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);
    private final RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTER);
    private final RegisterDetailDAO registerDetailDAO = (RegisterDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTERDETAIL);

    @Override
    public RegistrationDTO getRegister(String registerId) throws SQLException, ClassNotFoundException {
        List<RegistrationDTO> all = getAll();
        for (RegistrationDTO p:all){
            if (p.getRegisterId().equals(registerId)){
                return new RegistrationDTO(p.getRegisterId(),
                        p.getStudentId(),
                        p.getProgramId(),
                        p.getRegisterDate(),
                        p.getTime(),
                        p.getPayment(),
                        p.getRegisterDetail());
            }
        }
        return null;
    }

    @Override
    public List<RegistrationDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Registration> all = registerDAO.getAll();
        ArrayList<RegistrationDTO> dtos = new ArrayList<>();

        for (Registration registration : all){
            dtos.add(new RegistrationDTO(registration.getRegisterId(),
                    registration.getStudent().getStudentId(),
                    getPid(registration.getStudent().getStudentId()),
                    registration.getRegisterDate(),
                    registration.getTime(),
                    registration.getPayment()));
        }
        return null;
    }

    @Override
    public List<Registration> getAllForAll() throws SQLException, ClassNotFoundException {
        return   registerDAO.getAll();
    }

    public String getPid(String studentId) throws SQLException, ClassNotFoundException {
        List<RegisterDetail> all = registerDetailDAO.getAll();
        for (RegisterDetail registerDetail : all){
            if (studentId.equals(registerDetail.getSid().getStudentId())){
                return registerDetail.getProgramId().getProgramId();
            }
        }
        return null;
    }

    @Override
    public boolean purchaseRegister(RegistrationDTO dto) throws SQLException, ClassNotFoundException {
        try {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        boolean registerAvailable = registerDAO.ifRegisterExist(dto.getRegisterId());
        if (registerAvailable) {
            return false;
        }

        Student student = studentDAO.search(dto.getStudentId());
        Registration registration = new Registration(dto.getRegisterId(), dto.getRegisterDate(), dto.getTime(), dto.getPayment(), student);
        boolean registerAdded = registerDAO.add(registration);
        if (!registerAdded) {
            transaction.commit();
            session.close();
            return false;
        }
        Student student1 = studentDAO.search(dto.getStudentId());
        Program program = programDAO.search(dto.getProgramId());

        for (RegisterDetailDTO detailDTO : dto.getRegisterDetail()) {
            RegisterDetail registerDetail = new RegisterDetail(dto.getRegisterId(), program, student1);
            boolean registerDetailAdded = registerDetailDAO.add(registerDetail);
            if (!registerDetailAdded) {
                transaction.commit();
                session.close();
                return false;
            }
        }
        transaction.commit();
        session.close();
    }catch (Exception e) {
        e.printStackTrace();
    }
        return true;
    }


    @Override
    public String getRegisterIds() throws SQLException, ClassNotFoundException {
        return registerDAO.getRegisterIds();
    }

    @Override
    public StudentDTO searchStudents(String studentId) throws SQLException, ClassNotFoundException {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(student.getStudentId(),
                student.getStudentName(),
                student.getAddress(),
                student.getBirthday(),
                student.getAge(),
                student.getGender(),
                student.getPhoneNumber(),
                student.getEducation());
    }

    @Override
    public ProgramDTO searchPrograms(String programId) throws SQLException, ClassNotFoundException {
        Program program = programDAO.search(programId);
        return new ProgramDTO(program.getProgramId(),
                program.getProgramName(),
                program.getDuration(),
                program.getFee());
    }

    @Override
    public List<String> getAllStudentIds() throws SQLException, ClassNotFoundException {
        List<String> studentIds = new ArrayList<>();
        List<Student> allStudents = studentDAO.getAll();
        for (Student student : allStudents) {
            studentIds.add(student.getStudentId());
        }
        return studentIds;
    }

    @Override
    public List<String> getAllProgramIds() throws SQLException, ClassNotFoundException {
        List<String> programIds = new ArrayList<>();
        List<Program> allProgram = programDAO.getAll();
        for (Program program : allProgram) {
            programIds.add(program.getProgramId());
        }
        return programIds;
    }
}
