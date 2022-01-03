package dao.custom.impl;

import dao.custom.RegisterDetailDAO;
import entity.RegisterDetail;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDetailDAOImpl implements RegisterDetailDAO {
    @Override
    public boolean add(RegisterDetail registerDetail) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(registerDetail);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(RegisterDetail t) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public RegisterDetail search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<RegisterDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
