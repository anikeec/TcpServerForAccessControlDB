/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.EventType;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface EventTypeRepository extends CrudRepository<EventType, Integer>{
    
    List<EventType> findByEventId(@Param("eventId") Integer eventId);
    List<EventType> findByDescription(@Param("description") String description);
    
}