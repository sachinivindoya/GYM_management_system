package com.gym.dao.custom.impl;

import com.gym.dao.custom.EquipmentDAO;
import com.gym.dao.exception.ConstraintViolationException;
import com.gym.dao.util.DBUtil;
import com.gym.entity.Equipment;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EquipmentDAOImpl implements EquipmentDAO {
    private final Connection connection;

    public EquipmentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Equipment save(Equipment equipment) throws ConstraintViolationException {
        try {
            if (DBUtil.executeUpdate("INSERT INTO equipment (Equip_Id,Name,Date,Price,Status) VALUES (?,?,?,?,?)",
                    equipment.getEquip_Id(), equipment.getName(), equipment.getDate(), equipment.getPrice(), equipment.getStatus())) {
                return equipment;
            }
            throw new SQLException("Failed to save the Equipment");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Equipment update(Equipment equipment) throws ConstraintViolationException {
        try {
            if (DBUtil.executeUpdate("UPDATE equipment  SET Name=? ,Date=? ,Price=? ,Status=? WHERE Equip_Id=?", equipment.getName(), equipment.getDate(), equipment.getPrice(), equipment.getStatus(),equipment.getEquip_Id())) {
                return equipment;
            }
            throw new SQLException("Failed to update the equipment");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String Equip_Id) throws ConstraintViolationException {
        try {
            if (!DBUtil.executeUpdate("DELETE FROM equipment WHERE Equip_Id=?", Equip_Id))
                throw new SQLException("Failed to delete the equipment");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Equipment> findAll() {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM equipment");
            return getEquipmentList(rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the equipment");
        }
    }

    @Override
    public Optional<Equipment> findByPk(String pk) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM equipment WHERE Equip_Id=?", pk);
            if (rst.next()) {
                return Optional.of(new Equipment(rst.getString("Equip_Id"), rst.getString("Name"), rst.getString("Date"), rst.getDouble("Price"), rst.getString("Status")));

            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the equipment details");
        }
    }

    @Override
    public boolean existByPk(String Equip_Id) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM equipment WHERE Equip_Id=?", Equip_Id);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT COUNT(Equip_Id) AS count FROM equipment");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Equipment> searchByText(String text) {
        try{
            text="%"+text+"%";
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM equipment WHERE Equip_Id LIKE ? OR Name LIKE ?  ", text, text);
            return getEquipmentList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Equipment> getEquipmentList(ResultSet rst) {
        try {
            List<Equipment> equipmentList = new ArrayList<>();
            while (rst.next()) {
                Equipment equipment = new Equipment(rst.getString("Equip_Id"), rst.getString("Name"), rst.getString("Date"), rst.getDouble("Price"), rst.getString("Status"));
                equipmentList.add(equipment);
            }
            return equipmentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
