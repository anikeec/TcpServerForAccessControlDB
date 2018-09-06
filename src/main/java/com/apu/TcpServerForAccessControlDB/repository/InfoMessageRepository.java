/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.InfoMessage;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author apu
 */
public interface InfoMessageRepository extends CrudRepository<InfoMessage, Integer>{
    
}
