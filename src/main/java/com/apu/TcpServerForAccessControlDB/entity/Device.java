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
@Table(name = "device")
@XmlRootElement
//@RedisHash("device")
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
    , @NamedQuery(name = "Device.findByDeviceId", query = "SELECT d FROM Device d WHERE d.deviceId = :deviceId")
    , @NamedQuery(name = "Device.findByDeviceNumber", query = "SELECT d FROM Device d WHERE d.deviceNumber = :deviceNumber")
    , @NamedQuery(name = "Device.findByLastPacketId", query = "SELECT d FROM Device d WHERE d.lastPacketId = :lastPacketId")
    , @NamedQuery(name = "Device.findByActive", query = "SELECT d FROM Device d WHERE d.active = :active")})
public class Device implements Serializable, ActivatableEntity, VisualizableEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "device_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer deviceId;
    
    @Column(name = "device_number")
    @Getter @Setter
    private Integer deviceNumber;
    
    @Column(name = "last_packet_id")
    @Getter @Setter
    private Integer lastPacketId = 0;
    
    @Column(name = "active")
    @Getter @Setter
    private Boolean active = false;
    
    @OneToMany(mappedBy = "deviceId", fetch = FetchType.LAZY)
    private List<AccessMessage> accessMessageCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "deviceId", fetch = FetchType.LAZY)
    private List<Rule> ruleCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "deviceId", fetch = FetchType.LAZY)
    private List<EventMessage> eventMessageCollection = new ArrayList<>();
    
    @OneToMany(mappedBy = "deviceId", fetch = FetchType.LAZY)
    private List<InfoMessage> infoMessageCollection = new ArrayList<>();


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
        hash += (deviceId != null ? deviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.deviceId == null && other.deviceId != null) || 
                (this.deviceId != null && !this.deviceId.equals(other.deviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.Device[ deviceId=" + deviceId + " ]";
    }

    public Integer getId() {
        return this.getDeviceId();
    }

    public String getDescription() {
        return this.getDeviceId() + " - " + this.getDeviceNumber();
    }
    
}
