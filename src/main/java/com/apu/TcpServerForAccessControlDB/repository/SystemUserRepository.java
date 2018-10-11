/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.SystemUser;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface SystemUserRepository extends CrudRepository<SystemUser, Integer>{
    
    @Cacheable("user")
    List<SystemUser> findAll();
    
    @Cacheable("user")
    List<SystemUser> findByEmail(@Param("email") String email);
    
    @Cacheable("user")
    List<SystemUser> findByUserId(@Param("userId") Integer userId);
    
    @Cacheable("user")
    List<SystemUser> findByActive(@Param("active") Boolean active);
    
    @CacheEvict(value="user", allEntries=true)
    <S extends SystemUser> S save(S entity);
    
    @CacheEvict(value="user", allEntries=true)
    void delete(SystemUser entity);
    
}