/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.redis.core.RedisHash;

import com.apu.TcpServerForAccessControlDB.ActivatableEntity;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "card")
@XmlRootElement
@RedisHash("card")
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c")
    , @NamedQuery(name = "Card.findByCardId", query = "SELECT c FROM Card c WHERE c.cardId = :cardId")
    , @NamedQuery(name = "Card.findByCardNumber", query = "SELECT c FROM Card c WHERE c.cardNumber = :cardNumber")
    , @NamedQuery(name = "Card.findByActive", query = "SELECT c FROM Card c JOIN SystemUser u ON c.userId = u.userId WHERE c.active = :active")})
public class Card implements Serializable, ActivatableEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "card_id", nullable = false, unique = true)
    private Integer cardId;
//    @Size(max = 255)
    @Column(name = "card_number", length = 255)
    private String cardNumber;
    @Column(name = "active")
    private Boolean active = false;
    @OneToMany(mappedBy = "cardId", fetch = FetchType.LAZY)
    private Collection<AccessMessage> accessMessageCollection;
    @OneToMany(mappedBy = "cardId", fetch = FetchType.LAZY)
    private Collection<Rule> ruleCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SystemUser userId;

    public Card() {
    }

    public Card(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @XmlTransient
    public Collection<AccessMessage> getAccessMessageCollection() {
        return accessMessageCollection;
    }

    public void setAccessMessageCollection(Collection<AccessMessage> accessMessageCollection) {
        this.accessMessageCollection = accessMessageCollection;
    }

    @XmlTransient
    public Collection<Rule> getRuleCollection() {
        return ruleCollection;
    }

    public void setRuleCollection(Collection<Rule> ruleCollection) {
        this.ruleCollection = ruleCollection;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SystemUser getUserId() {
        return userId;
    }

    public void setUserId(SystemUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Card)) {
            return false;
        }
        Card other = (Card) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.Card[ cardId=" + cardId + " ]";
    }
    
}
