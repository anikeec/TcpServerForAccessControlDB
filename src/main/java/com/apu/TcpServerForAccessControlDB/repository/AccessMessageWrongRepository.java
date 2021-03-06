package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.AccessMessageWrong;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author apu
 */
public interface AccessMessageWrongRepository extends PagingAndSortingRepository<AccessMessageWrong, Integer>{
    
    public List<AccessMessageWrong> findAll();
    
    public Page<AccessMessageWrong> findAll(Pageable pageable);
    
}
