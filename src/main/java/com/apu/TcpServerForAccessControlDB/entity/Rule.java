package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;

import com.apu.TcpServerForAccessControlDB.interfaces.ActivatableEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "rule")
@XmlRootElement
//@RedisHash("rule")
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Rule.findAll", query = "SELECT r FROM Rule r")
    , @NamedQuery(name = "Rule.findByRuleId", query = "SELECT r FROM Rule r WHERE r.ruleId = :ruleId")
    , @NamedQuery(name = "Rule.findByDeviceIdAndCardId", query = "SELECT r FROM Rule r WHERE r.deviceId = :deviceId AND r.cardId = :cardId")
    , @NamedQuery(name = "Rule.findByDeviceIdCardIdEventTypeIdRuleTypeId", 
        query = "SELECT r FROM Rule r WHERE r.deviceId = :deviceId AND r.cardId = :cardId AND r.eventId = :eventId AND r.ruleTypeId = :ruleTypeId")
    , @NamedQuery(name = "Rule.findByDateBegin", query = "SELECT r FROM Rule r WHERE r.dateBegin = :dateBegin")
    , @NamedQuery(name = "Rule.findByDateEnd", query = "SELECT r FROM Rule r WHERE r.dateEnd = :dateEnd")
    , @NamedQuery(name = "Rule.findByCardDeviceEvent", query = "SELECT count(r.ruleId) "
            + " FROM Rule r, Card c, Device d, EventType e WHERE r.cardId=c.cardId AND r.deviceId=d.deviceId AND r.eventId=e.eventId" 
            + " AND d.deviceNumber=:deviceNumber AND e.eventId=:eventId AND c.cardNumber LIKE :cardNumber")
    , @NamedQuery(name = "Rule.findByActive", query = "SELECT r FROM Rule r WHERE r.active = :active")})
public class Rule implements Serializable, ActivatableEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rule_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer ruleId;
    
    @Column(name = "date_begin")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Getter @Setter
    private Date dateBegin;
    
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Getter @Setter
    private Date dateEnd;
    
    @Column(name = "active")
    @Getter @Setter
    private Boolean active = false;
    
    @JoinColumn(name = "card_id", referencedColumnName = "card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private Card cardId;
    
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private Device deviceId;
    
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private EventType eventId;
    
    @JoinColumn(name = "rule_type_id", referencedColumnName = "rule_type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private RuleType ruleTypeId;
    
    @OneToMany(mappedBy = "ruleId", fetch = FetchType.LAZY)
    private List<EventMessage> eventMessageCollection = new ArrayList<>();


    @XmlTransient
    public List<EventMessage> getEventMessageCollection() {
        return eventMessageCollection;
    }

    public void setEventMessageCollection(List<EventMessage> eventMessageCollection) {
        this.eventMessageCollection = eventMessageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleId != null ? ruleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rule)) {
            return false;
        }
        Rule other = (Rule) object;
        if ((this.ruleId == null && other.ruleId != null) || 
                (this.ruleId != null && !this.ruleId.equals(other.ruleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.Rule[ ruleId=" + ruleId + " ]";
    }
    
}
