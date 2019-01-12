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

import com.apu.TcpServerForAccessControlDB.interfaces.AccessControlEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "access_message")
@XmlRootElement
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "AccessMessage.findAll", query = "SELECT a FROM AccessMessage a")
    , @NamedQuery(name = "AccessMessage.findByAccessMessId", query = "SELECT a FROM AccessMessage a WHERE a.accessMessId = :accessMessId")
    , @NamedQuery(name = "AccessMessage.findByDescription", query = "SELECT a FROM AccessMessage a WHERE a.description = :description")
    , @NamedQuery(name = "AccessMessage.findByDate", query = "SELECT a FROM AccessMessage a WHERE a.date = :date")})
public class AccessMessage implements Serializable, AccessControlEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "access_mess_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer accessMessId;
    
    @Column(name = "description", length = 255)
    @Getter @Setter
    private String description;
    
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date date;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", referencedColumnName = "card_id") 
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
    
    @OneToMany(mappedBy = "baseAccessMessId", fetch = FetchType.LAZY)
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
        hash += (accessMessId != null ? accessMessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessMessage)) {
            return false;
        }
        AccessMessage other = (AccessMessage) object;
        if ((this.accessMessId == null && other.accessMessId != null) || 
                (this.accessMessId != null && !this.accessMessId.equals(other.accessMessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.AccessMessage[ accessMessId=" + accessMessId + " ]";
    }
    
}
