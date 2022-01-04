package dao.custom;

import dao.SuperDAO;
import dto.ProgramDTO;
import entity.Program;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProgramDAO extends SuperDAO<Program,String> {
    //public ArrayList<ProgramDTO> getAllProgramIds() throws SQLException,ClassNotFoundException;
    public String getProgramIds() throws SQLException,ClassNotFoundException;
}
