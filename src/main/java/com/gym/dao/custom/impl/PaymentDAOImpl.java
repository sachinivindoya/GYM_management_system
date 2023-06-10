package com.gym.dao.custom.impl;

import java.sql.Connection;

public class PaymentDAOImpl {
    private final Connection connection;
    public PaymentDAOImpl(Connection connection){
        this.connection=connection;
    }
}
