package bo.custom.impl;


import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;
import entity.Student;
import view.tm.ProgramTM;
import view.tm.StudentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);


    @Override
    public String getCustomerIds() throws SQLException, ClassNotFoundException {
        return programDAO.getProgramIds();
    }

    @Override
    public ProgramDTO searchProgram(String programId) throws SQLException, ClassNotFoundException {
        Program program = programDAO.search(programId);
        return new ProgramDTO(program.getProgramId(),
                program.getProgramName(),
                program.getDuration(),
                program.getFee());
    }

    @Override
    public boolean add(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.add(new Program(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()));
    }

    @Override
    public boolean update(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.update(new Program(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()));
    }

    @Override
    public boolean delete(String programId) throws SQLException, ClassNotFoundException {
        return programDAO.delete(programId);
    }

    @Override
    public ArrayList<ProgramTM> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Program> all = programDAO.getAll();
        ArrayList<ProgramTM> allPrograms = new ArrayList<>();
        for (Program program : all) {
            allPrograms.add(new ProgramTM(program.getProgramId(),program.getProgramName(),program.getDuration(),program.getFee()));
        }
        return allPrograms;
    }
}

