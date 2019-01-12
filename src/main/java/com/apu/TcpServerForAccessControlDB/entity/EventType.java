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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import org.springframework.data.redis.core.RedisHash;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "event_type")
@XmlRootElement
//@RedisHash("eventtype")
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "EventType.findAll", query = "SELECT e FROM EventType e")
    , @NamedQuery(name = "EventType.findByEventId", query = "SELECT e FROM EventType e WHERE e.eventId = :eventId")
    , @NamedQuery(name = "EventType.findByDescription", query = "SELECT e FROM EventType e WHERE e.description = :description")})
public class EventType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "event_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer eventId;

    @Column(name = "description", length = 255)
    @Getter @Setter
    private String description;
    
    @OneToMany(mappedBy = "eventId", fetch = FetchType.LAZY)
    private List<Rule> ruleCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "eventId", fetch = FetchType.LAZY)
    private List<AccessMessage> accessMessageCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "eventId", fetch = FetchType.LAZY)
    private List<EventMessage> eventMessageCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "eventId", fetch = FetchType.LAZY)
    private List<InfoMessage> infoMessageCollection = new ArrayList<>();

    
    @XmlTransient
    public List<Rule> getRuleCollection() {
        return ruleCollection;
    }

    public void setRuleCollection(List<Rule> ruleCollection) {
        this.ruleCollection = ruleCollection;
    }

    @XmlTransient
    public List<AccessMessage> getAccessMessageCollection() {
        return accessMessageCollection;
    }

    public void setAccessMessageCollection(List<AccessMessage> accessMessageCollection) {
        this.accessMessageCollection = accessMessageCollection;
    }

    @XmlTransient
    public List<EventMessage> getEventMessageCollection() {
        return eventMessageCollection;
    }

    public void setEventMessageCollection(List<EventMessage> eventMessageCollection) {
        this.eventMessageCollection = eventMessageCollection;
    }

    @XmlTransient
    public List<InfoMessage> getInfoMessageCollection() {
        return infoMessageCollection;
    }

    public void setInfoMessageCollection(List<InfoMessage> infoMessageCollection) {
        this.infoMessageCollection = infoMessageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventId != null ? eventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventType)) {
            return false;
        }
        EventType other = (EventType) object;
        if ((this.eventId == null && other.eventId != null) || 
                (this.eventId != null && !this.eventId.equals(other.eventId))) {
            return false;
        }
        if ((this.description == null && other.description != null) || 
                (this.description != null && !this.description.equals(other.description))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.EventType[ eventId=" + eventId + " ]";
    }
    
}
