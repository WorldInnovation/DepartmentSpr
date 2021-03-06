package com.aimprosoft.dao.impl;

import com.aimprosoft.dao.EmployeeDAO;
import com.aimprosoft.model.Employee;
import com.aimprosoft.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
@Qualifier("employeeDAO")
public class EmpHibernateDAOImpl implements EmployeeDAO {
@Autowired
    private SessionFactory sessionFactory;
    @Override
    public void delete(Employee employee) throws SQLException {
        HibernateUtil.executeDAO(employee,"delete");
    }

    @Override
    public void update(Employee employee) throws SQLException {
        HibernateUtil.executeDAO(employee,"update");
    }

    @Override
    public List<Employee> getAll(Long depID) throws SQLException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Employee> employees =
                (List<Employee>) session.
                        createQuery("from Employee e where e.depId=:depID").setParameter("depID", depID).
                        list();

        session.close();
        return employees;
    }

    @Override
    public Employee getEmpByID(Employee employee) throws SQLException {
        Long lEmpID = employee.getId();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        employee = (Employee) session.get(Employee.class, lEmpID) ;

        session.close();
        return employee;

    }
}