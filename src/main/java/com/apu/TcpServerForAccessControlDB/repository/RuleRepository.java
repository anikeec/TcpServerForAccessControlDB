/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.Card;
import com.apu.TcpServerForAccessControlDB.entity.Device;
import com.apu.TcpServerForAccessControlDB.entity.EventType;
import com.apu.TcpServerForAccessControlDB.entity.Rule;
import com.apu.TcpServerForAccessControlDB.entity.RuleType;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface RuleRepository extends CrudRepository<Rule, Integer>{
    
    @Cacheable("rule")
//    @CacheEvict(value="rule", allEntries=true)
    List<Rule> findByDeviceIdAndCardId(@Param("deviceId") Device deviceId, @Param("cardId") Card cardId);
    
    Integer findByCardDeviceEvent(@Param("cardNumber") String cardNumber,
            @Param("deviceNumber") Integer deviceNumber,
            @Param("eventId") Integer eventId);
    List<Rule> findAll();
    List<Rule> findByRuleId(@Param("ruleId") Integer ruleId);
    List<Rule> findByDeviceIdCardIdEventTypeIdRuleTypeId(@Param("deviceId") Device deviceId,
                                                        @Param("cardId") Card cardId,
                                                        @Param("eventId") EventType eventTypeId,
                                                        @Param("ruleTypeId") RuleType ruleTypeId);    
    List<Rule> findByActive(@Param("active") Boolean active);
}
