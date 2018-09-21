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
    
//    @Cacheable("rule")
    List<Rule> findAll();
    
//    @Cacheable("rule")
    List<Rule> findByRuleId(@Param("ruleId") Integer ruleId);
    
//    @Cacheable("rule")
    List<Rule> findByActive(@Param("active") Boolean active);
    
//    @Cacheable("rule")
    List<Rule> findByDeviceIdAndCardId(@Param("deviceId") Device deviceId, @Param("cardId") Card cardId);
    
//    @Cacheable("rule")
    Integer findByCardDeviceEvent(@Param("cardNumber") String cardNumber,
            @Param("deviceNumber") Integer deviceNumber,
            @Param("eventId") Integer eventId);
        
//    @Cacheable("rule")
    List<Rule> findByDeviceIdCardIdEventTypeIdRuleTypeId(@Param("deviceId") Device deviceId,
                                                        @Param("cardId") Card cardId,
                                                        @Param("eventId") EventType eventTypeId,
                                                        @Param("ruleTypeId") RuleType ruleTypeId); 
    
//    @CacheEvict(value="rule", allEntries=true)
    <S extends Rule> S save(S entity);
    
//    @CacheEvict(value="rule", allEntries=true)
    void delete(Rule entity); 
    
}
