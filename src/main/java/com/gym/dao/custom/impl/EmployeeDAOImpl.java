package com.gym.dao.custom.impl;

import com.gym.dao.custom.EmployeeDAO;
import com.gym.dao.exception.ConstraintViolationException;
import com.gym.dao.util.DBUtil;
import com.gym.entity.Employee;
import com.gym.entity.Equipment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee save(Employee employee) throws ConstraintViolationException, SQLException {
        try {
            if (DBUtil.executeUpdate("INSERT INTO employee (Emp_Id,Name,Address,NIC,Contact,Joined_Date,Occupation) VALUES (?,?,?,?,?,?,?)",
                    employee.getEmp_Id(), employee.getName(), employee.getAddress(), employee.getNIC(), employee.getContact(),employee.getJoined_Date(),employee.getOccupation())) {
                return employee;
            }
            throw new SQLException("Failed to save the Employee");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Employee update(Employee employee) throws ConstraintViolationException {
        try {
            if (DBUtil.executeUpdate("UPDATE employee  SET Name=? ,Address=? ,NIC=? ,Contact=? Joined_Date=? , Occupation=? WHERE Emp_Id=?", employee.getName(), employee.getAddress(), employee.getNIC(), employee.getContact(),employee.getJoined_Date(),employee.getOccupation())) {
                return employee;
            }
            throw new SQLException("Failed to update the employee");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String Emp_Id) throws ConstraintViolationException {
        try {
            if (!DBUtil.executeUpdate("DELETE FROM employee WHERE Emp_Id=?", Emp_Id))
                throw new SQLException("Failed to delete the employee");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM employee");
            return getEmployeeList(rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the employee");
        }
    }

    @Override
    public Optional<Employee> findByPk(String pk) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM employee WHERE Emp_Id=?", pk);
            if (rst.next()) {
                return Optional.of(new Employee(rst.getString("Emp_Id"), rst.getString("Name"), rst.getString("Address"), rst.getString("NIC"), rst.getString("Contact"),rst.getString("Joined_-Date"),rst.getString("Occupation")));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the equipment details");
        }
    }

    @Override
    public boolean existByPk(String Emp_Id) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM employee WHERE Emp_Id=?", Emp_Id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {

         /* try {
            ResultSet rst = DBUtil.executeQuery("SELECT COUNT(Emp_Id) AS count FROM equipment");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        return 0;
    }

    @Override
    public List<Employee> searchByText(String text) {
        try{
            text="%"+text+"%";
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM employee WHERE Emp_Id LIKE ? OR Name LIKE ?  ", text, text);
            return getEmployeeList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Employee> getEmployeeList(ResultSet rst) {
        try {
            List<Employee> employeeList = new ArrayList<>();
            while (rst.next()) {
                Employee employee = new Employee(rst.getString("Emp_Id"), rst.getString("Name"), rst.getString("Address"), rst.getString("NIC"), rst.getString("Contact"),rst.getString("Joined_Date"),rst.getString("Occupation"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
