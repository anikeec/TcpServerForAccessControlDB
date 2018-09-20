/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.Device;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface DeviceRepository extends CrudRepository<Device, Integer>{
    
    @Cacheable("device")
//    @CacheEvict(value="device", allEntries=true)
    List<Device> findByDeviceNumber(@Param("deviceNumber") Integer deviceNumber);
    List<Device> findByDeviceId(@Param("deviceId") Integer deviceId);
    List<Device> findAll();
    List<Device> findByActive(@Param("active") Boolean active);
    
}
