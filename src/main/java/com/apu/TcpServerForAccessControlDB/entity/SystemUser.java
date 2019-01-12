package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//import org.springframework.data.redis.core.RedisHash;

import com.apu.TcpServerForAccessControlDB.interfaces.ActivatableEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "system_user")
@XmlRootElement
//@RedisHash("systemUser")
@JsonIgnoreProperties("cardCollection")
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "SystemUser.findAll", query = "SELECT u FROM SystemUser u")
    , @NamedQuery(name = "SystemUser.findByUserId", query = "SELECT u FROM SystemUser u WHERE u.userId = :userId")
    , @NamedQuery(name = "SystemUser.findByFirstName", query = "SELECT u FROM SystemUser u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "SystemUser.findBySecondName", query = "SELECT u FROM SystemUser u WHERE u.secondName = :secondName")
    , @NamedQuery(name = "SystemUser.findByPhoneNumber", query = "SELECT u FROM SystemUser u WHERE u.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "SystemUser.findByEmail", query = "SELECT u FROM SystemUser u WHERE u.email = :email")
    , @NamedQuery(name = "SystemUser.findByActive", query = "SELECT u FROM SystemUser u WHERE u.active = :active")})
public class SystemUser implements Serializable, ActivatableEntity {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false, unique = true)
    @Getter @Setter
    private Integer userId;
    
    @Column(name = "first_name", length = 255)
    @Getter @Setter
    private String firstName;
    
    @Column(name = "second_name", length = 255)
    @Getter @Setter
    private String secondName;
    
    @Column(name = "phone_number", length = 255)
    @Getter @Setter
    private String phoneNumber;
    
    @Column(name = "email", length = 255)
    @Getter @Setter
    private String email;
    
    @Column(name = "password", length = 255)
    @Getter @Setter
    private String password;
    
    @Column(name = "active")
    @Getter @Setter
    private Boolean active = false;
    
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY, 
                cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Card> cardCollection = new ArrayList<>();
    
    @ManyToMany(mappedBy = "userCollection")
    private Set<UserRole> userRoleCollection = new HashSet<>();
    
    

    @XmlTransient
    public List<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(List<Card> cardCollection) {
        this.cardCollection = cardCollection;
    }
    
    @XmlTransient
    public Set<UserRole> getUserRoleCollection() {
        return userRoleCollection;
    }

    public void setUserRoleCollection(Set<UserRole> userRoleCollection) {
        this.userRoleCollection = userRoleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.SystemUser[ userId=" + userId + " ]";
    }
    
}
