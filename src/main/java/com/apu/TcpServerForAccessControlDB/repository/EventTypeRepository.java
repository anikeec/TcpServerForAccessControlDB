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
    List<EventType> findAll();
    
    @Cacheable("eventtype")
    List<EventType> findByEventId(@Param("eventId") Integer eventId);
    
    @Cacheable("eventtype")
    List<EventType> findByDescription(@Param("description") String description);

    @CacheEvict(value="eventtype", allEntries=true)
    <S extends EventType> S save(S entity);

    @CacheEvict(value="eventtype", allEntries=true)
    void delete(EventType entity);
    
}
