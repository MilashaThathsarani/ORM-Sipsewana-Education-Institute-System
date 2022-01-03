package dao;

import dao.custom.impl.RegisterDAOImpl;
import dao.custom.impl.ProgramDAOImpl;
import dao.custom.impl.RegisterDetailDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case REGISTER:
                return new RegisterDAOImpl();
            case REGISTERDETAIL:
                return new RegisterDetailDAOImpl();
            default:
                return null;
        }
    }

    public enum DAOTypes {
        STUDENT,PROGRAM , REGISTER , REGISTERDETAIL
    }

}
