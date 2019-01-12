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
@Table(name = "info_message")
@XmlRootElement
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "InfoMessage.findAll", query = "SELECT i FROM InfoMessage i")
    , @NamedQuery(name = "InfoMessage.findByInfoMessId", query = "SELECT i FROM InfoMessage i WHERE i.infoMessId = :infoMessId")
    , @NamedQuery(name = "InfoMessage.findByDescription", query = "SELECT i FROM InfoMessage i WHERE i.description = :description")
    , @NamedQuery(name = "InfoMessage.findByDate", query = "SELECT i FROM InfoMessage i WHERE i.date = :date")})
public class InfoMessage implements Serializable, AccessControlEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "info_mess_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer infoMessId;

    @Column(name = "description", length = 255)
    @Getter @Setter
    private String description;
    
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter
    private Date date;
    
    @JoinColumn(name = "device_id", referencedColumnName = "device_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter
    private Device deviceId;
    
    @JoinColumn(name = "event_id", referencedColumnName = "event_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private EventType eventId;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (infoMessId != null ? infoMessId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfoMessage)) {
            return false;
        }
        InfoMessage other = (InfoMessage) object;
        if ((this.infoMessId == null && other.infoMessId != null) || 
                (this.infoMessId != null && !this.infoMessId.equals(other.infoMessId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.InfoMessage[ infoMessId=" + infoMessId + " ]";
    }
    
}
