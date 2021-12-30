package dao.custom.impl;

import dao.SuperDAO;
import dao.custom.ProgramDAO;
import entity.Program;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramDAOImpl implements ProgramDAO {
    @Override
    public boolean add(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Program t) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Program search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Program> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
