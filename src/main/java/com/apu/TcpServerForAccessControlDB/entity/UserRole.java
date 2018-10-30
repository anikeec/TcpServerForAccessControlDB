package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "user_role", catalog = "accesscontroldb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u")
    , @NamedQuery(name = "UserRole.findByUserRoleId", query = "SELECT u FROM UserRole u WHERE u.userRoleId = :userRoleId")
    , @NamedQuery(name = "UserRole.findByDescription", query = "SELECT u FROM UserRole u WHERE u.description = :description")})
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_role_id")
    private Integer userRoleId;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "UserroleUser", 
            joinColumns = { @JoinColumn(name = "user_role_id") }, 
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
        )
    private Collection<SystemUser> userCollection;

    public UserRole() {
    }

    public UserRole(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserRole(Integer userRoleId, String description) {
        this.userRoleId = userRoleId;
        this.description = description;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<SystemUser> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<SystemUser> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRoleId != null ? userRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRole)) {
            return false;
        }
        UserRole other = (UserRole) object;
        if ((this.userRoleId == null && other.userRoleId != null) || (this.userRoleId != null && !this.userRoleId.equals(other.userRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.packet.UserRole[ userRoleId=" + userRoleId + " ]";
    }
    
}

