package com.aimprosoft.controller;

import com.aimprosoft.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public   class Controller extends HttpServlet  {
    private ControllerFactory factory = ControllerFactory.getInstance();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        InternalController internalController = factory.getConUrl(requestURI);
        if(internalController==null){
            internalController = factory.getDefaultController();
        }

        try {

            internalController.execute(req, resp);
        } catch (SQLException | HibernateException e) {
            if(e.getMessage()!=null){

                Session session = HibernateUtil.getSessionFactory().
                        getCurrentSession();
                session.getTransaction().
                        rollback();

                req.setAttribute("sqlError",e.getMessage());
                resp.sendRedirect("WEB-INF/jsp/sqlException.jsp");
            }
        }
    }
}
