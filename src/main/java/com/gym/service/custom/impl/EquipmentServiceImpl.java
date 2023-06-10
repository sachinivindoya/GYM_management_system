package com.gym.service.custom.impl;


import com.gym.dao.DaoFactory;
import com.gym.dao.DaoTypes;
import com.gym.dao.custom.EquipmentDAO;
import com.gym.dao.exception.ConstraintViolationException;
import com.gym.db.DBConnection;
import com.gym.dto.EquipmentDTO;
import com.gym.entity.Equipment;
import com.gym.service.custom.EquipmentService;
import com.gym.service.exception.DuplicateException;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;
import com.gym.service.util.Convertor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentDAO equipmentDAO;
    private final Convertor convertor;
    private final Connection connection;

    public EquipmentServiceImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        equipmentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.EQUIPMENT );
        convertor=new Convertor();
    }

    @Override
    public EquipmentDTO saveEquipment(EquipmentDTO equipmentDTO) throws DuplicateException, SQLException {

        if (equipmentDAO.existByPk(equipmentDTO.getEquip_Id())) throw new DuplicateException("Equipment already saved");

        equipmentDAO.save(convertor.toEquipment(equipmentDTO));

        return equipmentDTO;

    }

    @Override
    public EquipmentDTO updateEquipment(EquipmentDTO equipmentDTO) throws NotFoundException {
        if (!equipmentDAO.existByPk(equipmentDTO.getEquip_Id())) throw new NotFoundException("Equipment not found");

        equipmentDAO.update(convertor.toEquipment(equipmentDTO));

        return equipmentDTO;    }

    @Override
    public void deleteEquipment(String Equip_Id) throws NotFoundException, InUseException {
        if (!equipmentDAO.existByPk(Equip_Id)) throw new NotFoundException("Equipment not found");

        try {
            equipmentDAO.deleteByPk(Equip_Id);
        }catch (ConstraintViolationException e){
            throw new InUseException("Equipment already deleted.");
        }
    }

    @Override
    public List<EquipmentDTO> findAllEquipment() {
        return equipmentDAO.findAll().stream().map(equipment -> convertor.fromEquipment(equipment)).collect(Collectors.toList());

    }

    @Override
    public EquipmentDTO findEquipmentByEquip_Id(String Equip_Id) throws NotFoundException {
        Optional<Equipment> optionalEquipment = equipmentDAO.findByPk(Equip_Id);
        if (!optionalEquipment.isPresent()) throw new NotFoundException("Equipment not found");

        return convertor.fromEquipment(optionalEquipment.get());
    }

    @Override
    public List<EquipmentDTO> searchEquipmentByText(String text) {
        return equipmentDAO.searchByText(text).stream().map(equipment -> convertor.fromEquipment(equipment)).collect(Collectors.toList());

    }
}
