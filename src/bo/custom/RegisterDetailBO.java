package bo.custom;

import bo.SuperBO;
import view.tm.RegisterDetailTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterDetailBO extends SuperBO {

    ArrayList<RegisterDetailTM> getAllRegisterDetails(String registerId) throws SQLException, ClassNotFoundException;
}
