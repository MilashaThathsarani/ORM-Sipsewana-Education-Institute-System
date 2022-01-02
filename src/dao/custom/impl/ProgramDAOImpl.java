package dao.custom.impl;

import dao.SuperDAO;
import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public boolean delete(String programId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Program program = session.get(Program.class, programId);

        session.delete(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Program program) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(program);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Program search(String programId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Program> list = null;
        String hql = "FROM Program P WHERE P.programId = :programId";
        Query query = session.createQuery(hql).setString("programId", programId);
        list = query.getResultList();

        transaction.commit();
        return list.get(0);
    }

    @Override
    public ArrayList<Program> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Program> list = null;

        Query programs = session.createQuery("from Program ");
        list = programs.list();

        transaction.commit();

        session.close();
        return (ArrayList<Program>) list;
    }

    @Override
    public ArrayList<ProgramDTO> getAllProgramIds() throws SQLException, ClassNotFoundException {
       return null;
    }

   /* @Override
    public List getAllProgramIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString("custId")
            );
        }
        return ids;
    }*/
}
