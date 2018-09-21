/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.User;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    
//    @Cacheable("user")
    List<User> findAll();
    
//    @Cacheable("user")
    List<User> findByEmail(@Param("email") String email);
    
//    @Cacheable("user")
    List<User> findByUserId(@Param("userId") Integer userId);
    
//    @Cacheable("user")
    List<User> findByActive(@Param("active") Boolean active);
    
//    @CacheEvict(value="user", allEntries=true)
    <S extends User> S save(S entity);
    
//    @CacheEvict(value="user", allEntries=true)
    void delete(User entity);
    
}
