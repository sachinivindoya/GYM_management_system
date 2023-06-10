package com.gym.dao.custom;

import com.gym.dao.CrudDAO;
import com.gym.entity.Equipment;

import java.util.List;

public interface EquipmentDAO extends CrudDAO<Equipment,String> {
    public List<Equipment> searchByText(String text);
}
