package com.gym.dao;

import com.gym.dao.exception.ConstraintViolationException;
import com.gym.entity.SuperEntity;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO <T extends SuperEntity,ID extends Serializable> extends SuperDAO{
    T save(T entity) throws ConstraintViolationException, SQLException;

    T update(T entity) throws ConstraintViolationException;

    void deleteByPk(ID pk) throws ConstraintViolationException;

    List<T> findAll() ;

    Optional<T> findByPk(ID pk) ;

    boolean existByPk(String pk) ;

    long count() ;

}
