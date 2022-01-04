package dao.custom.impl;

import dao.SuperDAO;
import dao.custom.ProgramDAO;
import dto.ProgramDTO;
import entity.Program;
import entity.Registration;
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
        session.close();
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
    public String getProgramIds() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Program p ORDER BY p.programId desc ";
        Query query = session.createQuery(hql);
        List resultList = query.getResultList();
        transaction.commit();
        session.close();
        if (resultList.size() > 0){
            int tempId = Integer.
                    parseInt(((Program) resultList.get(0)).getProgramId().split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "P-00" + tempId;
            } else if (tempId <= 99) {
                return "P-0" + tempId;
            } else if (tempId <= 999) {
                return "P-0" + tempId;
            } else {
                return "P-" + tempId;
            }
        }else {
            return "P-001";
        }
    }
}