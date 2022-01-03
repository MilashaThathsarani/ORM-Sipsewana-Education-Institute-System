package dao.custom;

import dao.SuperDAO;
import entity.Registration;

import java.sql.SQLException;

public interface RegisterDAO extends SuperDAO<Registration,String> {
    boolean ifRegisterExist(String registerId);
    public String getRegisterIds() throws SQLException,ClassNotFoundException;
}
