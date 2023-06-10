package com.gym.service.util;

import com.gym.dto.EmployeeDTO;
import com.gym.dto.EquipmentDTO;
import com.gym.dto.MemberDTO;
import com.gym.entity.Employee;
import com.gym.entity.Equipment;
import com.gym.entity.Member;

public class Convertor {

    public Employee toEmployee(EmployeeDTO employeeDTO){
        return new Employee(employeeDTO.getEmp_Id(),employeeDTO.getName(),employeeDTO.getAddress(),employeeDTO.getNIC(),employeeDTO.getContact(), employeeDTO.getJoined_Date(),employeeDTO.getOccupation());
    }

    public EmployeeDTO fromEmployees(Employee employee){
        return new EmployeeDTO(employee.getEmp_Id(),employee.getName(),employee.getAddress(),employee.getNIC(),employee.getContact(),employee.getJoined_Date(),employee.getOccupation());
    }


    public Equipment toEquipment(EquipmentDTO equipmentDTO) {
        return new Equipment(equipmentDTO.getEquip_Id(),equipmentDTO.getName(),equipmentDTO.getDate(),equipmentDTO.getPrice(),equipmentDTO.getStatus());
    }
    public EquipmentDTO fromEquipment(Equipment equipment){
        return new EquipmentDTO(equipment.getEquip_Id(),equipment.getName(),equipment.getDate(),equipment.getPrice(),equipment.getStatus());
    }

    public Member toMember(MemberDTO memberDTO) {
        return new Member(Integer.parseInt(memberDTO.getID()),memberDTO.getFullName(),memberDTO.getAddress(),memberDTO.getAge(),memberDTO.getMembership(), memberDTO.getNIC(),memberDTO.getContact(),memberDTO.getJoinedDate());

    }
}
