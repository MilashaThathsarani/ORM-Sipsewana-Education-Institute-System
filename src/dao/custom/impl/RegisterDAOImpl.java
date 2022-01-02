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
        return null;
    }
}
