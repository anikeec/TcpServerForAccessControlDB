/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

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
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

//import org.springframework.data.redis.core.RedisHash;

import com.apu.TcpServerForAccessControlDB.interfaces.ActivatableEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "system_user")
@XmlRootElement
//@RedisHash("systemUser")
@JsonIgnoreProperties("cardCollection")
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
    private Integer userId;
//    @Size(max = 255)
    @Column(name = "first_name", length = 255)
    private String firstName;
//    @Size(max = 255)
    @Column(name = "second_name", length = 255)
    private String secondName;
//    @Size(max = 255)
    @Column(name = "phone_number", length = 255)
    private String phoneNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
//    @Size(max = 255)
    @Column(name = "email", length = 255)
    private String email;
    @Column(name = "password", length = 255)
    private String password;
    @Column(name = "active")
    private Boolean active = false;
    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private Collection<Card> cardCollection;
    @ManyToMany(mappedBy = "userCollection")
    private Set<UserRole> userRoleCollection;

    public SystemUser() {
    }

    public SystemUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
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
