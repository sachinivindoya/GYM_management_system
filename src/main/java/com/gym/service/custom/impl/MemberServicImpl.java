package com.gym.service.custom.impl;


import com.gym.dao.DaoFactory;
import com.gym.dao.DaoTypes;
import com.gym.dao.custom.MemberDAO;
import com.gym.db.DBConnection;
import com.gym.dto.MemberDTO;
import com.gym.service.custom.MemberService;
import com.gym.service.exception.DuplicateException;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;
import com.gym.service.util.Convertor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class MemberServicImpl implements MemberService {
    private final MemberDAO memberDAO;
    private final Convertor convertor;
    private final Connection connection;

    public MemberServicImpl() {
        connection= DBConnection.getDbConnection().getConnection();
        memberDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.MEMBER );
        convertor=new Convertor();
    }

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) throws DuplicateException, SQLException {

        if (memberDAO.existByPk(memberDTO.getID())) throw new DuplicateException("Member already saved");

        memberDAO.save(convertor.toMember(memberDTO));

        return memberDTO;

    }

    @Override
    public MemberDTO updateMember(MemberDTO memberDTO) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteMember(int ID) throws NotFoundException, InUseException {

    }

    @Override
    public List<MemberDTO> findAllMembers() {
        return null;
    }

    @Override
    public MemberDTO findMemberByID(int ID) throws NotFoundException {
        return null;
    }

    @Override
    public List<MemberDTO> searchMemberByText(String text) {
        return null;
    }
}
