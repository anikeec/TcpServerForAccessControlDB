package com.apu.TcpServerForAccessControlDB.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apu.TcpServerForAccessControlDB.entity.UserroleUser;

public interface UserroleUserRepository extends CrudRepository<UserroleUser, Integer> {

    @Cacheable("userroleuser")    
    List<UserroleUser> findByUruId(@Param("uruId") Integer uruId);
    
    @Cacheable("userroleuser")
    List<UserroleUser> findAll();
    
    @CacheEvict(value="userroleuser", allEntries=true)
    public <S extends UserroleUser> S save(S entity);
    
    @CacheEvict(value="userroleuser", allEntries=true)
    public void delete(UserroleUser entity);
    
}
