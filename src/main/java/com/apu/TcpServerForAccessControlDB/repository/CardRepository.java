/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.Card;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface CardRepository extends CrudRepository<Card, Integer>{
    
//    @Cacheable("card")
    public List<Card> findAll();
    
//    @Cacheable("card")
    public List<Card> findByCardId(@Param("cardId") Integer cardId);
    
//    @Cacheable("card")
    public List<Card> findByCardNumber(@Param("cardNumber") String cardNumber);  
    
//    @Cacheable("card")
    public List<Card> findByActive(@Param("active") Boolean active);

//    @CacheEvict(value="card", allEntries=true)
    <S extends Card> S save(S entity);

//    @CacheEvict(value="card", allEntries=true)
    void delete(Card entity);
    
}
