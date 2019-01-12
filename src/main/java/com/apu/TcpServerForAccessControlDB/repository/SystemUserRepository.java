package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.SystemUser;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface SystemUserRepository extends PagingAndSortingRepository<SystemUser, Integer>{
    
    @Cacheable("user")
    List<SystemUser> findAll();
    
    public Page<SystemUser> findAll(Pageable pageable);
    
    @Cacheable("user")
    List<SystemUser> findByEmail(@Param("email") String email);
    
    @Cacheable("user")
    List<SystemUser> findByUserId(@Param("userId") Integer userId);
    
    @Cacheable("user")
    List<SystemUser> findByActive(@Param("active") Boolean active);
    
    @CacheEvict(value="user", allEntries=true)
    <S extends SystemUser> S save(S entity);
    
    @CacheEvict(value="user", allEntries=true)
    void delete(SystemUser entity);
    
}
