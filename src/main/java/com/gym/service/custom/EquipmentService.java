package com.gym.service.custom;

import com.gym.dto.EquipmentDTO;
import com.gym.service.SuperService;
import com.gym.service.exception.DuplicateException;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface EquipmentService extends SuperService {
    public EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO) throws DuplicateException, SQLException;
    public EquipmentDTO updateEquipment(EquipmentDTO equipmentDTO)  throws NotFoundException;

    public void deleteEquipment(String Equip_Id) throws NotFoundException, InUseException;

    List<EquipmentDTO> findAllEquipment();

    public EquipmentDTO findEquipmentByEquip_Id(String Equip_Id) throws NotFoundException;

    public List<EquipmentDTO> searchEquipmentByText(String text) ;
}


