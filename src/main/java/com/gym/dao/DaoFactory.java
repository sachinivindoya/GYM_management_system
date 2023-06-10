package com.gym.dao;

import com.gym.dao.custom.impl.*;

import java.sql.Connection;

public class DaoFactory {

    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection, DaoTypes daoType) {
        switch (daoType){
            case EMPLOYEE:
                return (T)new EmployeeDAOImpl(connection);
            case EQUIPMENT:
                return (T) new EquipmentDAOImpl(connection);
            case MEMBER:
                return (T) new MemberDAOImpl(connection);
            case PAYMENT:
                return (T) new PaymentDAOImpl(connection);

            default:
                return null;

        }

    }
}
