package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.DepartmentDAO;
import com.aimprosoft.model.Department;
import com.aimprosoft.model.Employee;
import com.aimprosoft.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@Repository
/*@Qualifier("departmentDAO")*/
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
        for (Employee emp: employees) {
            HibernateUtil.executeDAO(emp,"delete");
        }
        HibernateUtil.executeDAO(department,"delete");
    }
    @Override
    public void update(Department department) throws SQLException {
        HibernateUtil.executeDAO(department, "update");
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

        department = (Department) session.get(Department.class,lDepID);
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
}
//-------------
/*    @Override
    public List<Department> getAll() throws SQLException{
  Session session = currentSession();
  session.beginTransaction();
        List<Department> departments = (List<Department>) session.createQuery("from Department").list();
        return departments;
    }*/