package com.apu.TcpServerForAccessControlDB.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.apu.TcpServerForAccessControlDB.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Cacheable("userrole")    
    List<UserRole> findByUserRoleId(@Param("userRoleId") Integer userRoleId);
    
    @Cacheable("userrole")
    List<UserRole> findByDescription(@Param("description") String description);
    
    @Cacheable("userrole")
    List<UserRole> findAll();
    
    @CacheEvict(value="userrole", allEntries=true)
    public <S extends UserRole> S save(S entity);
    
    @CacheEvict(value="userrole", allEntries=true)
    public void delete(UserRole entity);
    
}
