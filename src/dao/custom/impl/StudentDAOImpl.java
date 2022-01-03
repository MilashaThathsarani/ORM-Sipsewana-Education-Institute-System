package dao.custom.impl;

import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import util.FactoryConfiguration;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean add(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String studentId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, studentId);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String studentId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = null;
        String hql = "FROM Student S WHERE S.studentId = :studentId";
        Query query = session.createQuery(hql).setString("studentId", studentId);
        list = query.getResultList();

        transaction.commit();
        session.close();
        return list.get(0);
    }

    @Override
    public ArrayList <Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Student> list = null;

        Query students = session.createQuery("from Student");
        list = students.list();

        transaction.commit();

        session.close();
        return (ArrayList<Student>) list;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudentIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    /*@Override
    public String getStudentIds() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();

        ResultSet rst = null;
        NativeQuery sqlQuery = session.createSQLQuery("SELECT custId FROM Customer ORDER BY custId DESC LIMIT 1");
        if (rst.next()) {
            int tempId = Integer.
                    parseInt(rst.getString("custId").split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "C-00" + tempId;
            } else if (tempId <= 99) {
                return "C-0" + tempId;
            } else {
                return "C-" + tempId;
            }

        } else {
            return "C-001";
        }
    }*/
}
