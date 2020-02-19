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
    @NamedQuery(name = "Crbdclrs.findAll", query = "SELECT c FROM Crbdclrs c")
    , @NamedQuery(name = "Crbdclrs.findByCrbdclrsId", query = "SELECT c FROM Crbdclrs c WHERE c.crbdclrsId = :crbdclrsId")
    , @NamedQuery(name = "Crbdclrs.findByVersnum", query = "SELECT c FROM Crbdclrs c WHERE c.versnum = :versnum")
    , @NamedQuery(name = "Crbdclrs.findByColor", query = "SELECT c FROM Crbdclrs c WHERE c.color = :color")
    , @NamedQuery(name = "Crbdclrs.findByClrdsc", query = "SELECT c FROM Crbdclrs c WHERE c.clrdsc = :clrdsc")})
public class Crbdclrs implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "bdclrsGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "bdclrs_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "bdclrsGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CRBDCLRS_ID", nullable = false, precision = 10, scale = 0)
    private Integer crbdclrsId;
    @Version
    private Integer versnum;
    @Size(max = 256)
    @Column(length = 256)
    private String color;
    @Size(max = 1024)
    @Column(length = 1024)
    private String clrdsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crbdclrsFk")
    private Collection<Crbody> crbodyCollection;

    public Crbdclrs() {
    }

    public Crbdclrs(Integer crbdclrsId) {
        this.crbdclrsId = crbdclrsId;
    }

    public Integer getCrbdclrsId() {
        return crbdclrsId;
    }

    public void setCrbdclrsId(Integer crbdclrsId) {
        this.crbdclrsId = crbdclrsId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClrdsc() {
        return clrdsc;
    }

    public void setClrdsc(String clrdsc) {
        this.clrdsc = clrdsc;
    }

    @XmlTransient
    public Collection<Crbody> getCrbodyCollection() {
        return crbodyCollection;
    }

    public void setCrbodyCollection(Collection<Crbody> crbodyCollection) {
        this.crbodyCollection = crbodyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crbdclrsId != null ? crbdclrsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crbdclrs)) {
            return false;
        }
        Crbdclrs other = (Crbdclrs) object;
        if ((this.crbdclrsId == null && other.crbdclrsId != null) || (this.crbdclrsId != null && !this.crbdclrsId.equals(other.crbdclrsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Crbdclrs[ crbdclrsId=" + crbdclrsId + " ]";
    }
    
}
