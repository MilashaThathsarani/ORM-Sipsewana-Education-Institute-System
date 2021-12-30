package bo.custom.impl;


import bo.custom.ProgramBO;
import dao.DAOFactory;
import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;

import java.sql.SQLException;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PROGRAM);


    @Override
    public boolean add(ProgramDTO programDTO) throws SQLException, ClassNotFoundException {
        return programDAO.add(new Program(
                programDTO.getProgramId(),
                programDTO.getProgramName(),
                programDTO.getDuration(),
                programDTO.getFee()));
    }
}

