package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//import org.springframework.data.redis.core.RedisHash;

import com.apu.TcpServerForAccessControlDB.interfaces.ActivatableEntity;
import com.apu.TcpServerForAccessControlDB.interfaces.VisualizableEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "card")
@XmlRootElement
//@RedisHash("card")
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c")
    , @NamedQuery(name = "Card.findByCardId", query = "SELECT c FROM Card c WHERE c.cardId = :cardId")
    , @NamedQuery(name = "Card.findByCardNumber", query = "SELECT c FROM Card c WHERE c.cardNumber = :cardNumber")
    , @NamedQuery(name = "Card.findByActive", query = "SELECT c FROM Card c JOIN SystemUser u ON c.userId = u.userId WHERE c.active = :active")})
public class Card implements Serializable, ActivatableEntity, VisualizableEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "card_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer cardId;

    @Column(name = "card_number", length = 255)
    @Getter @Setter
    private String cardNumber;
    
    @Column(name = "active")
    @Getter @Setter
    private Boolean active = false;
    
    @OneToMany(mappedBy = "cardId", fetch = FetchType.LAZY)
    private List<AccessMessage> accessMessageCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "cardId", fetch = FetchType.LAZY)
    private List<Rule> ruleCollection = new ArrayList<>();
    
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private SystemUser userId;


    @XmlTransient
    public List<AccessMessage> getAccessMessageCollection() {
        return accessMessageCollection;
    }

    public void setAccessMessageCollection(List<AccessMessage> accessMessageCollection) {
        this.accessMessageCollection = accessMessageCollection;
    }

    @XmlTransient
    public List<Rule> getRuleCollection() {
        return ruleCollection;
    }

    public void setRuleCollection(List<Rule> ruleCollection) {
        this.ruleCollection = ruleCollection;
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
        if ((this.cardId == null && other.cardId != null) || 
                (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.Card[ cardId=" + cardId + " ]";
    }

    public Integer getId() {
        return this.getCardId();
    }

    public String getDescription() {
        return this.getCardId() + " - " + this.getCardNumber();
    }
    
}
