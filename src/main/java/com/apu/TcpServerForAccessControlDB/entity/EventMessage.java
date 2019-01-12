package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.apu.TcpServerForAccessControlDB.interfaces.AccessControlEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "event_message")
@XmlRootElement
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "EventMessage.findAll", query = "SELECT e FROM EventMessage e")
    , @NamedQuery(name = "EventMessage.findByEventMessId", query = "SELECT e FROM EventMessage e WHERE e.eventMessId = :eventMessId")
    , @NamedQuery(name = "EventMessage.findByDescription", query = "SELECT e FROM EventMessage e WHERE e.description = :description")
    , @NamedQuery(name = "EventMessage.findByDate", query = "SELECT e FROM EventMessage e WHERE e.date = :date")})
public class EventMessage implements Serializable, AccessControlEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "event_mess_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer eventMessId;

    @Column(name = "description", length = 255)
    @Getter @Setter
    private String description;
    
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date date;
    
    @JoinColumn(name = "base_access_mess_id", referencedColumnName = "access_mess_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private AccessMessage baseAccessMessId;
    
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private Device deviceId;
    
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private EventType eventId;
    
    @JoinColumn(name = "rule_id", referencedColumnName = "rule_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private Rule ruleId;

    
    public EventMessage(Device deviceId, EventType eventId, AccessMessage baseAccessMessId, 
                            Rule ruleId, Date date, String description) {
        super();
        this.deviceId = deviceId;
        this.eventId = eventId;
        this.baseAccessMessId = baseAccessMessId;
        this.ruleId = ruleId;
        this.date = date;
        this.description = description;               
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventMessId != null ? eventMessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventMessage)) {
            return false;
        }
        EventMessage other = (EventMessage) object;
        if ((this.eventMessId == null && other.eventMessId != null) || 
                (this.eventMessId != null && !this.eventMessId.equals(other.eventMessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.EventMessage[ eventMessId=" + eventMessId + " ]";
    }
    
}
