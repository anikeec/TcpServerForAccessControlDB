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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import org.springframework.data.redis.core.RedisHash;

/**
 *
 * @author apu
 */
@Entity
@Table(name = "rule_type")
@XmlRootElement
//@RedisHash("ruletype")
@NoArgsConstructor
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
    @Getter @Setter
    private Integer ruleTypeId;
    
    @Column(name = "description", length = 255)
    @Getter @Setter
    private String description;
    
    @OneToMany(mappedBy = "ruleTypeId", fetch = FetchType.LAZY)
    private List<Rule> ruleCollection = new ArrayList<>();


    @XmlTransient
    public List<Rule> getRuleCollection() {
        return ruleCollection;
    }

    public void setRuleCollection(List<Rule> ruleCollection) {
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
        if ((this.ruleTypeId == null && other.ruleTypeId != null) || 
                (this.ruleTypeId != null && !this.ruleTypeId.equals(other.ruleTypeId))) {
            return false;
        }
        if ((this.description == null && other.description != null) || 
                (this.description != null && !this.description.equals(other.description))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apu.TcpServerForAccessControl.entity.RuleType[ ruleTypeId=" + ruleTypeId + " ]";
    }
    
}
