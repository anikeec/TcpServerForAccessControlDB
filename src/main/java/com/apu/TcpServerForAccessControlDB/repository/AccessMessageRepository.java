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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apu
 */
public interface AccessMessageRepository extends PagingAndSortingRepository<AccessMessage, Integer>{
    
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
    
//     @Query(value = "SELECT * FROM Access_message a JOIN Device d ON a.device_id = d.device_id", nativeQuery = true)
//    @Query(value = "SELECT a FROM AccessMessage a INNER JOIN FETCH a.deviceId d INNER JOIN FETCH a.cardId c", nativeQuery = false)
    public Page<AccessMessage> findAll(Pageable pageable);
    
//    @Query(value = "SELECT * FROM "
//            + "(SELECT * FROM access_message AS am "
//            + "INNER JOIN device d ON am.device_id=d.device_id "
//            + "INNER JOIN card c ON am.card_id=c.card_id  "
//            + "WHERE am.access_mess_id > 0 ORDER BY am.access_mess_id LIMIT 10 OFFSET :page * 10) a", nativeQuery = true)
    @Query(value = "SELECT am.access_mess_id, am.date, am.description, am.card_id, am.device_id, am.event_id,"
            + "d.device_id, d.device_number, last_packet_id, d.active, "
            + "c.card_id, c.card_number, c.user_id, c.active "
            + "FROM access_message AS am "
            + "JOIN device d ON am.device_id=d.device_id "
            + "JOIN card c ON am.card_id=c.card_id  "
            + "WHERE am.access_mess_id > 0 ORDER BY am.access_mess_id LIMIT 10 OFFSET :page * 10", nativeQuery = true)
    public List<AccessMessage> findAllByPage(@Param("page") Integer page);
    
}
