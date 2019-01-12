package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "access_message_wrong")
@XmlRootElement
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "AccessMessageWrong.findAll", query = "SELECT a FROM AccessMessageWrong a")
    , @NamedQuery(name = "AccessMessageWrong.findByAccessMessId", query = "SELECT a FROM AccessMessageWrong a WHERE a.accessMessId = :accessMessId")
    , @NamedQuery(name = "AccessMessageWrong.findByCardNumber", query = "SELECT a FROM AccessMessageWrong a WHERE a.cardNumber = :cardNumber")
    , @NamedQuery(name = "AccessMessageWrong.findByDeviceNumber", query = "SELECT a FROM AccessMessageWrong a WHERE a.deviceNumber = :deviceNumber")
    , @NamedQuery(name = "AccessMessageWrong.findByEventId", query = "SELECT a FROM AccessMessageWrong a WHERE a.eventId = :eventId")
    , @NamedQuery(name = "AccessMessageWrong.findByDescription", query = "SELECT a FROM AccessMessageWrong a WHERE a.description = :description")
    , @NamedQuery(name = "AccessMessageWrong.findByDate", query = "SELECT a FROM AccessMessageWrong a WHERE a.date = :date")})
public class AccessMessageWrong implements Serializable, AccessControlEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "access_mess_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer accessMessId;
    
    @Basic(optional = false)
    @Column(name = "card_number", length = 255)
    @Getter @Setter
    private String cardNumber;
    
    @Basic(optional = false)
    @Column(name = "device_number")
    @Getter @Setter
    private Integer deviceNumber;
    
    @Basic(optional = false)
    @Column(name = "event_id")
    @Getter @Setter
    private Integer eventId;
    
    @Basic(optional = false)
    @Column(name = "description", length = 255)
    @Getter @Setter
    private String description;
    
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date date;


    public AccessMessageWrong(String cardNumber, Integer deviceNumber, Integer eventId, Date date, String description) {
        this.cardNumber = cardNumber;
        this.deviceNumber = deviceNumber;
        this.eventId = eventId;
        this.description = description;
        this.date = date;
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
        if (!(object instanceof AccessMessageWrong)) {
            return false;
        }
        AccessMessageWrong other = (AccessMessageWrong) object;
        if ((this.accessMessId == null && other.accessMessId != null) || 
                (this.accessMessId != null && !this.accessMessId.equals(other.accessMessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.AccessMessageWrong[ accessMessId=" + accessMessId + " ]";
    }
    
}
