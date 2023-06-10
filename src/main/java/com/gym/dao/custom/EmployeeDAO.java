package com.gym.dao.custom;

import com.gym.dao.CrudDAO;
import com.gym.entity.Employee;
import com.gym.entity.Equipment;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    public List<Employee> searchByText(String text);

}
