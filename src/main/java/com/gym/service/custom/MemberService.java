package com.gym.service.custom;


import com.gym.dto.MemberDTO;
import com.gym.service.SuperService;
import com.gym.service.exception.DuplicateException;
import com.gym.service.exception.InUseException;
import com.gym.service.exception.NotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface MemberService extends SuperService {
    public MemberDTO saveMember(MemberDTO memberDTO) throws DuplicateException, SQLException;
    public MemberDTO updateMember(MemberDTO memberDTO)  throws NotFoundException;

    public void deleteMember(int ID) throws NotFoundException, InUseException;

    List<MemberDTO> findAllMembers();

    public MemberDTO findMemberByID(int ID) throws NotFoundException;

    public List<MemberDTO> searchMemberByText(String text) ;
}
