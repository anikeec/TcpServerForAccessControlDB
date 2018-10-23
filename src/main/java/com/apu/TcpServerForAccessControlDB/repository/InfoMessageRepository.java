/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.InfoMessage;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author apu
 */
public interface InfoMessageRepository extends PagingAndSortingRepository<InfoMessage, Integer>{
    
    public List<InfoMessage> findAll();
    
    public Page<InfoMessage> findAll(Pageable pageable);
    
}
