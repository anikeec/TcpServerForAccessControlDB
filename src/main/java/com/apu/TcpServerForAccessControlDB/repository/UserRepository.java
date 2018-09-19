/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    
    List<User> findAll();
    List<User> findByEmail(@Param("email") String email);
    List<User> findByUserId(@Param("userId") Integer userId);
    
}
