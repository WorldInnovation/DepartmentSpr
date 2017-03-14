package com.aimprosoft.controller;

import com.aimprosoft.controller.department.DepartmentsList;

import java.util.Map;

class ControllerFactory {
    private static ControllerFactory INSTANCE = null;
    private volatile static boolean instanceCreated = false;
    private Map<String, InternalController> controllerMap;
    private InternalController defaultController = new DepartmentsList();

    public void setControllerMap(Map<String, InternalController> controllerMap) {
        this.controllerMap = controllerMap;
    }

    public void setDefaultController(InternalController defaultController) {
        this.defaultController = defaultController;
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