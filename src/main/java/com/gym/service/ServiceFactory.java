package com.gym.service;

import com.gym.dao.custom.impl.MemberDAOImpl;
import com.gym.service.custom.impl.EmployeeServiceImpl;
import com.gym.service.custom.impl.EquipmentServiceImpl;
import com.gym.service.custom.impl.MemberServicImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case EMPLOYEE:
                return (T)new EmployeeServiceImpl();
            case EQUIPMENT:
                return (T) new EquipmentServiceImpl();

            case MEMBER:
                return (T) new MemberServicImpl();

            default:
                return null;
        }
    }
}
