package com.gym.dao.custom;

import com.gym.dao.CrudDAO;
import com.gym.dao.exception.ConstraintViolationException;
import com.gym.dao.util.DBUtil;
import com.gym.db.DBConnection;
import com.gym.entity.Member;
import com.gym.entity.Payment;

import java.sql.SQLException;
import java.util.List;

public interface MemberDAO extends CrudDAO<Member,String> {
    public List<Member> searchByText(String text);
}



