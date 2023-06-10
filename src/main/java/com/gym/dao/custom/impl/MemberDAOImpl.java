package com.gym.dao.custom.impl;

import com.gym.dao.custom.MemberDAO;
import com.gym.dao.exception.ConstraintViolationException;
import com.gym.dao.util.DBUtil;
import com.gym.entity.Member;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDAOImpl implements MemberDAO {
    private final Connection connection;

    public MemberDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Member save(Member member) throws ConstraintViolationException, SQLException {
            try {
               if (DBUtil.executeUpdate("INSERT INTO members (ID, FullName , Address,Age ,Membership ,NIC  ,Contact ,JoinedDate ) VALUES (? ,?, ?, ?, ?, ?, ?, ?)",
                        member.getID(), member.getFullName(), member.getAddress(), member.getAge(), member.getMembership(), member.getNIC(),  member.getContact(), member.getJoinedDate()));
            throw new SQLException("Failed to save the member");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }


    @Override
    public Member update(Member member) throws ConstraintViolationException {
        try {
            if (DBUtil.executeUpdate("UPDATE members  SET FullName=? ,Address=? ,Age=? ,Membership=? ,NIC=? ,Contact=? ,JoinedDate=? ,  WHERE ID=?", member.getID(), member.getFullName(), member.getAddress(), member.getAge(), member.getMembership(), member.getNIC(),  member.getContact(), member.getJoinedDate())) {
                return member;
            }
            throw new SQLException("Failed to update the member");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }



    @Override
    public List<Member> findAll() {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM members");
            return getMemberList(rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load the member");
        }
    }

    @Override
    public Optional<Member> findByPk(String pk) {
        return Optional.empty();
    }



    @Override
    public boolean existByPk(String ID) {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM members WHERE ID=?", ID);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = DBUtil.executeQuery("SELECT COUNT(ID) AS count FROM members");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> searchByText(String text) {
        try {
            text = "%" + text + "%";
            ResultSet rst = DBUtil.executeQuery("SELECT * FROM members WHERE ID LIKE ? OR FullName LIKE ?  ", text, text);
            return getMemberList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Member> getMemberList(ResultSet rst) {
        try {
            List<Member> memberList = new ArrayList<>();
            while (rst.next()) {
                Member member = new Member(rst.getInt("ID"), rst.getString("FullName"), rst.getString("Address"), rst.getString("Age"), rst.getString("Membership"), rst.getString("NIC"),  rst.getString("Contact"), rst.getString("JoinedDate"));
                memberList.add(member);
            }
            return memberList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
