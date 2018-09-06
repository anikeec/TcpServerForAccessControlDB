/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.AccessMessageWrong;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author apu
 */
public interface AccessMessageWrongRepository extends CrudRepository<AccessMessageWrong, Integer>{
    
}
