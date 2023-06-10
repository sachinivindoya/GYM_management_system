package com.gym.service.custom.impl;

import com.gym.dao.DaoFactory;
import com.gym.dao.DaoTypes;
import com.gym.dao.custom.EmployeeDAO;
import com.gym.dao.exception.ConstraintViolationException;
import com.gym.db.DBConnection;
import com.gym.dto.EmployeeDTO;
import com.gym.entity.Employee;
import com.gym.entity.Equipment;
import com.gym.service.custom.EmployeeService;
import com.gym.service.exception.DuplicateException;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;
import com.gym.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final Convertor convertor;
    private final Connection connection;

    public EmployeeServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        employeeDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYEE );
        convertor=new Convertor();
    }


    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException, SQLException {
        if (employeeDAO.existByPk(employeeDTO.getEmp_Id())) throw new DuplicateException("Employee already saved");

        employeeDAO.save(convertor.toEmployee(employeeDTO));

        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws NotFoundException {
        if (!employeeDAO.existByPk(employeeDTO.getEmp_Id())) throw new NotFoundException("Employee not found");

        employeeDAO.update(convertor.toEmployee(employeeDTO));

        return employeeDTO;
    }

    @Override
    public void deleteEmployee(String Emp_Id) throws NotFoundException, InUseException {
        if (!employeeDAO.existByPk(Emp_Id)) throw new NotFoundException("Employee not found");

        try {
            employeeDAO.deleteByPk(Emp_Id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Employee already deleted.");
        }
    }

    @Override
    public List<EmployeeDTO> findAllEmployee() {
        return employeeDAO.findAll().stream().map(employee -> convertor.fromEmployees(employee)).collect(Collectors.toList());

    }

    @Override
    public EmployeeDTO findEmployeeByEmp_Id(String Emp_Id) throws NotFoundException {
        Optional<Employee> optionalEmployee = employeeDAO.findByPk(Emp_Id);
        if (!optionalEmployee.isPresent()) throw new NotFoundException("Employee not found");

        return convertor.fromEmployees(optionalEmployee.get());
    }

    @Override
    public List<EmployeeDTO> searchEmployeeByText(String text) {
        return employeeDAO.searchByText(text).stream().map(employee -> convertor.fromEmployees(employee)).collect(Collectors.toList());

    }
}
