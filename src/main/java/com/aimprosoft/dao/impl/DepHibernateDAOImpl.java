package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import com.aimprosoft.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class DepHibernateDAOImpl implements DepartmentDAO {
    @Autowired
    SessionFactory sessionFactory;

    public void delete(Department department) throws SQLException {
        Long depID = department.getId();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Employee> employees = (List<Employee>) session.
                createQuery("from Employee e where e.depId=:depID").setParameter("depID", depID).
                list();
        session.close();
        for (Employee emp : employees) {
            HibernateUtil.executeDAO(emp, "delete");
        }

        HibernateUtil.executeDAO(department, "delete");
 /*
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(department);
        session.getTransaction().commit();
        session.close();*/
    }

    @Override
    public void update(Department department) throws SQLException {
        HibernateUtil.executeDAO(department, "update");
/*        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(department);
        session.getTransaction().commit();
        session.close();*/
    }

    @Override
    public List<Department> getAll() throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Department> departments = (List<Department>) session.
                createQuery("from Department").list();
        session.close();
        return departments;
    }

    @Override
    public Department getDepByID(Department department) throws SQLException {
        Long lDepID = department.getId();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        department = (Department) session.get(Department.class, lDepID);
        session.close();

        return department;
    }

    @Override
    public Department existNameInDB(Department department) throws SQLException {
        String depName = department.getName();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.
                createQuery("from Department where name=:name");
        query.setParameter("name", depName);
        session.close();
        Department dep = (Department) query.uniqueResult();
        return dep;
    }
    //-------------

//
//    public Session openCurrentSession() {
//
//        currentSession = getSessionFactory().openSession();
//
//        return currentSession;
//
//    }
//    private static SessionFactory getSessionFactory() {
//
//        Configuration configuration = new Configuration().configure();
//
//        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
//
//                .applySettings(configuration.getProperties());
//
//        SessionFactory sessionFactory = configuration.
//                buildSessionFactory(builder.build());
//
//        return sessionFactory;
//
//    }
//    public Session getCurrentSession() {
//        return currentSession;
//    }
//    public void setCurrentSession(Session currentSession) {
//
//        this.currentSession = currentSession;
//
//    }
//    public void closeCurrentSession() {
//        currentSession.close();
//    }

}