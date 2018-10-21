/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.TcpServerForAccessControlDB.entity;

import java.io.Serializable;
import java.util.Collection;
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
//import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.redis.core.RedisHash;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "rule_type")
@XmlRootElement
@RedisHash("ruletype")
@NamedQueries({
    @NamedQuery(name = "RuleType.findAll", query = "SELECT r FROM RuleType r")
    , @NamedQuery(name = "RuleType.findByRuleTypeId", query = "SELECT r FROM RuleType r WHERE r.ruleTypeId = :ruleTypeId")
    , @NamedQuery(name = "RuleType.findByDescription", query = "SELECT r FROM RuleType r WHERE r.description = :description")})
public class RuleType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rule_type_id", nullable = false, unique = true)
    private Integer ruleTypeId;
//    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;
    @OneToMany(mappedBy = "ruleTypeId", fetch = FetchType.LAZY)
    private Collection<Rule> ruleCollection;

    public RuleType() {
    }

    public RuleType(Integer ruleTypeId) {
        this.ruleTypeId = ruleTypeId;
    }

    public Integer getRuleTypeId() {
        return ruleTypeId;
    }

    public void setRuleTypeId(Integer ruleTypeId) {
        this.ruleTypeId = ruleTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Rule> getRuleCollection() {
        return ruleCollection;
    }

    public void setRuleCollection(Collection<Rule> ruleCollection) {
        this.ruleCollection = ruleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruleTypeId != null ? ruleTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RuleType)) {
            return false;
        }
        RuleType other = (RuleType) object;
        if ((this.ruleTypeId == null && other.ruleTypeId != null) || (this.ruleTypeId != null && !this.ruleTypeId.equals(other.ruleTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.RuleType[ ruleTypeId=" + ruleTypeId + " ]";
    }
    
}
