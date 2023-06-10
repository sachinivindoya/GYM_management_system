package com.gym.service.custom;

import com.gym.dto.EmployeeDTO;
import com.gym.service.SuperService;
import com.gym.service.exception.DuplicateException;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SuperService {
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException, SQLException;
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO)  throws NotFoundException;

    public void deleteEmployee(String Equip_Id) throws NotFoundException, InUseException;

    List<EmployeeDTO> findAllEmployee();

    public EmployeeDTO findEmployeeByEmp_Id(String Emp_Id) throws NotFoundException;

    public List<EmployeeDTO> searchEmployeeByText(String text) ;
}
