package com.aimprosoft.controller.department;

import com.aimprosoft.controller.InternalController;
import com.aimprosoft.exeption.ValidateExp;
import com.aimprosoft.model.Department;
import com.aimprosoft.service.DepartmentService;
import com.aimprosoft.util.FormatUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class DepSave implements InternalController {
    @Autowired
    private DepartmentService departmentService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String strDepId = req.getParameter("DepID");
        String depName = req.getParameter("DepName");
        Long depId = FormatUtils.getLongFromStr(strDepId);
        Department department = new Department();
        department.setName(depName);
        if (depId != null) {
            department.setId(depId);
        }//edit

        try {
            departmentService.saveOrUpdateDepartment(department);
        } catch (ValidateExp ex) {
            req.setAttribute("department", department);
            req.setAttribute("depId", depId);
            req.setAttribute("errorMap", ex.getErrorMap());
            req.getRequestDispatcher("WEB-INF/jsp/editDep.jsp").forward(req, resp);
        } catch (ConstraintViolationException errorMessage) {
            req.setAttribute("department", department);
            req.setAttribute("depId", depId);
            req.setAttribute("errorMap.name", errorMessage.getMessage());
            req.getRequestDispatcher("WEB-INF/jsp/editDep.jsp").forward(req, resp);
        }
        resp.sendRedirect("/DepartmentsList");
    }
}