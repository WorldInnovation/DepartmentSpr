package com.aimprosoft.controller.employee;

import com.aimprosoft.controller.InternalController;
import com.aimprosoft.model.Employee;
import com.aimprosoft.service.EmployeeService;
import com.aimprosoft.util.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class EmpDelete implements InternalController {
    @Autowired
    private EmployeeService employeeService;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {
        String depID = req.getParameter("DepID");
        String empID = req.getParameter("EmpID");
        Long lEmpID = FormatUtils.getLongFromStr(empID);
        Employee employee = new Employee();
        if (lEmpID != null) {
            employee.setId(lEmpID);
            employee = employeeService.getEmpByID(employee);
            employeeService.deleteEmployee(employee);
        }
        String sendParam = "?DepID=".concat(depID);
        resp.sendRedirect("/EmployeesList".concat(sendParam));
    }
}