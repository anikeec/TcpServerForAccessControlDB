/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.EventMessage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author apu
 */
public interface EventMessageRepository extends CrudRepository<EventMessage, Integer>{
    
    public List<EventMessage> findAll();
    
}
