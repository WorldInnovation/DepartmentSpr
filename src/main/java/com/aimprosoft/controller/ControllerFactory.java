package com.aimprosoft.controller;

import com.aimprosoft.controller.department.DepDelete;
import com.aimprosoft.controller.department.DepSave;
import com.aimprosoft.controller.department.DepartmentsList;
import com.aimprosoft.controller.department.EditDepartment;
import com.aimprosoft.controller.employee.EmpDelete;
import com.aimprosoft.controller.employee.EmpEdit;
import com.aimprosoft.controller.employee.EmpSave;
import com.aimprosoft.controller.employee.EmployeesList;

import java.util.HashMap;
import java.util.Map;

class ControllerFactory {
    private static ControllerFactory INSTANCE = null;
    private volatile static boolean instanceCreated = false;
    private Map<String, InternalController> controllerMap = new HashMap<String, InternalController>();
    private InternalController defaultController = new DepartmentsList();


    {
        controllerMap.put("/", defaultController);
        controllerMap.put("/DepDelete", new DepDelete());
        controllerMap.put("/EditDepartment", new EditDepartment());
        controllerMap.put("/EmpDelete", new EmpDelete());
        controllerMap.put("/EmployeesList", new EmployeesList());
        controllerMap.put("/EmpEdit", new EmpEdit());
        controllerMap.put("/DepSave", new DepSave());
        controllerMap.put("/EmpSave", new EmpSave());
    }

    InternalController getConUrl(String url) {

        return controllerMap.get(url);
    }

    InternalController getDefaultController() {
        return defaultController;
    }

    static ControllerFactory getInstance() {
        try {
            if (!instanceCreated) {
                INSTANCE = new ControllerFactory();
                instanceCreated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return INSTANCE;
    }


}

/*
    if (!instanceCreated) {
            synchronized (ControllerFactory.class) {
                try {
                    if (!instanceCreated) {
                        INSTANCE = new ControllerFactory();
                        instanceCreated = true;
                    }
                } catch (Exception ignored) {

                }
            }
        }
        return INSTANCE;

   */