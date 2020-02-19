/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Eugene Sidorov
 */
@Entity
//@Table(catalog = "", schema = "AFACTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mtrtype.findAll", query = "SELECT m FROM Mtrtype m")
    , @NamedQuery(name = "Mtrtype.findByMtrtypeId", query = "SELECT m FROM Mtrtype m WHERE m.mtrtypeId = :mtrtypeId")
    , @NamedQuery(name = "Mtrtype.findByVersnum", query = "SELECT m FROM Mtrtype m WHERE m.versnum = :versnum")
    , @NamedQuery(name = "Mtrtype.findByTypemtr", query = "SELECT m FROM Mtrtype m WHERE m.typemtr = :typemtr")
    , @NamedQuery(name = "Mtrtype.findByTpmtdsc", query = "SELECT m FROM Mtrtype m WHERE m.tpmtdsc = :tpmtdsc")})
public class Mtrtype implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "mtrtypeGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "mtrtype_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "mtrtypeGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "MTRTYPE_ID", nullable = false, precision = 38, scale = 0)
    private Integer mtrtypeId;
    @Version
    private Integer versnum;
    @Size(max = 256)
    @Column(length = 256)
    private String typemtr;
    @Size(max = 1024)
    @Column(length = 1024)
    private String tpmtdsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mtrtypeFk")
    private Collection<Motor> motorCollection;

    public Mtrtype() {
    }

    public Mtrtype(Integer mtrtypeId) {
        this.mtrtypeId = mtrtypeId;
    }

    public Integer getMtrtypeId() {
        return mtrtypeId;
    }

    public void setMtrtypeId(Integer mtrtypeId) {
        this.mtrtypeId = mtrtypeId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getTypemtr() {
        return typemtr;
    }

    public void setTypemtr(String typemtr) {
        this.typemtr = typemtr;
    }

    public String getTpmtdsc() {
        return tpmtdsc;
    }

    public void setTpmtdsc(String tpmtdsc) {
        this.tpmtdsc = tpmtdsc;
    }

    @XmlTransient
    public Collection<Motor> getMotorCollection() {
        return motorCollection;
    }

    public void setMotorCollection(Collection<Motor> motorCollection) {
        this.motorCollection = motorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mtrtypeId != null ? mtrtypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mtrtype)) {
            return false;
        }
        Mtrtype other = (Mtrtype) object;
        if ((this.mtrtypeId == null && other.mtrtypeId != null) || (this.mtrtypeId != null && !this.mtrtypeId.equals(other.mtrtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Mtrtype[ mtrtypeId=" + mtrtypeId + " ]";
    }
    
}
