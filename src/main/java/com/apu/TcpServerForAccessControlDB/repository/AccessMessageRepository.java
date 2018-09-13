/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.AccessMessage;
import com.apu.TcpServerForAccessControlDB.entity.Card;
import com.apu.TcpServerForAccessControlDB.entity.Device;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apu
 */
public interface AccessMessageRepository extends CrudRepository<AccessMessage, Integer>{
    
    @Modifying
    @Query(value = "INSERT INTO Access_message(card_id,device_id,event_id,description,date) "
            + "VALUES ( "
            + " (SELECT card_id FROM Card WHERE card_number LIKE :cardNumber),"
            + " (SELECT device_id FROM Device WHERE device_number=:deviceNumber),"
            + " (SELECT event_id FROM Event_type WHERE event_id=:eventId),"
            + " :description,"
            + " :date"
            + " )", nativeQuery = true)
    @Transactional
    void insertMessage(@Param("cardNumber") String cardNumber,
            @Param("deviceNumber") Integer deviceNumber,
            @Param("eventId") Integer eventId,
            @Param("description") String description,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @Param("date") Date date);
    
    public List<AccessMessage> findAll();
    
}
