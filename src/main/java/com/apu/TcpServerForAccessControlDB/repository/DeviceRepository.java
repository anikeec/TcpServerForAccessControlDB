package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.Device;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author apu
 */
@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer>{
    
    @Cacheable("device")
    List<Device> findAll();
    
    @Cacheable(value = "device")
    List<Device> findByActive(@Param("active") Boolean active);
    
    @Cacheable(value = "device")
    List<Device> findByDeviceNumber(@Param("deviceNumber") Integer deviceNumber);
    
    @Cacheable(value = "device")
    List<Device> findByDeviceId(@Param("deviceId") Integer deviceId);    
    
    @CacheEvict(value="device", allEntries=true)
    <S extends Device> S save(S entity);

    @CacheEvict(value="device", allEntries=true)
    void delete(Device entity);    
    
}
