package dao.custom.impl;

import dao.custom.RegisterDAO;
import entity.Program;
import entity.Registration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public boolean add(Registration registration) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(registration);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Registration t) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Registration search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    /*@Override
    public Registration search(String registerId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Registration> list = null;
        String hql = "FROM Registration R WHERE R.registerId = :registerId";
        Query query = session.createQuery(hql).setString("registerId", registerId);
        list = query.getResultList();

        transaction.commit();
        return  list.get(0);
    }*/

    @Override
    public ArrayList<Registration> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();



        Query programs = session.createQuery("from Registration ");
        List<Registration> list = programs.list();

        transaction.commit();

        session.close();
        return (ArrayList<Registration>) list;
    }

    @Override
    public boolean ifRegisterExist(String registerId) {
        return false;
    }

    @Override
    public String getRegisterIds() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Registration r ORDER BY r.registerId desc ";
        Query query = session.createQuery(hql);
        List resultList = query.getResultList();
        transaction.commit();
        session.close();
        if (resultList.size() > 0){
            int tempId = Integer.
                    parseInt(((Registration) resultList.get(0)).getRegisterId().split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "R-00" + tempId;
            } else if (tempId <= 99) {
                return "R-0" + tempId;
            } else if (tempId <= 999) {
                return "R-0" + tempId;
            } else {
                return "R-" + tempId;
            }
        }else {
            return "R-001";
        }
    }
}
