package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "userrole_user", catalog = "accesscontroldb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserroleUser.findAll", query = "SELECT u FROM UserroleUser u")
    , @NamedQuery(name = "UserroleUser.findByUruId", query = "SELECT u FROM UserroleUser u WHERE u.uruId = :uruId")})
public class UserroleUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "uru_id", nullable = false, unique = true)
    private Integer uruId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SystemUser userId;
    @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserRole userRoleId;

    public UserroleUser() {
    }

    public UserroleUser(Integer uruId) {
        this.uruId = uruId;
    }

    public Integer getUruId() {
        return uruId;
    }

    public void setUruId(Integer uruId) {
        this.uruId = uruId;
    }

    public SystemUser getUserId() {
        return userId;
    }

    public void setUserId(SystemUser userId) {
        this.userId = userId;
    }

    public UserRole getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(UserRole userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uruId != null ? uruId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserroleUser)) {
            return false;
        }
        UserroleUser other = (UserroleUser) object;
        if ((this.uruId == null && other.uruId != null) || (this.uruId != null && !this.uruId.equals(other.uruId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.packet.UserroleUser[ uruId=" + uruId + " ]";
    }
    
}
