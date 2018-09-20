/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.EventType;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface EventTypeRepository extends CrudRepository<EventType, Integer>{
    
    @Cacheable("eventtype")
//    @CacheEvict(value="eventtype", allEntries=true)
    List<EventType> findByEventId(@Param("eventId") Integer eventId);
    
    @Cacheable("eventtype")
//    @CacheEvict(value="eventtype", allEntries=true)
    List<EventType> findByDescription(@Param("description") String description);
    List<EventType> findAll();
}
