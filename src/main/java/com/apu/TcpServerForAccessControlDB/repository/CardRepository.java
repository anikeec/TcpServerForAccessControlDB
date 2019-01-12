package com.apu.TcpServerForAccessControlDB.repository;

import com.apu.TcpServerForAccessControlDB.entity.Card;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author apu
 */
public interface CardRepository extends PagingAndSortingRepository<Card, Integer>{
    
    @Cacheable("card")
    @Query(value = "SELECT c FROM Card c INNER JOIN FETCH c.userId u", nativeQuery = false)
    public List<Card> findAll();
    
    public Page<Card> findAll(Pageable pageable);
    
    @Cacheable("card")
    public List<Card> findByCardId(@Param("cardId") Integer cardId);
    
    @Cacheable("card")
    public List<Card> findByCardNumber(@Param("cardNumber") String cardNumber);  
    
    @Cacheable("card")
    public List<Card> findByActive(@Param("active") Boolean active);

    @CacheEvict(value="card", allEntries=true)
    <S extends Card> S save(S entity);

    @CacheEvict(value="card", allEntries=true)
    void delete(Card entity);
    
}
