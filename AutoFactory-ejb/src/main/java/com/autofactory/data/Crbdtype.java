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
    @NamedQuery(name = "Crbdtype.findAll", query = "SELECT c FROM Crbdtype c")
    , @NamedQuery(name = "Crbdtype.findByCrbdtypeId", query = "SELECT c FROM Crbdtype c WHERE c.crbdtypeId = :crbdtypeId")
    , @NamedQuery(name = "Crbdtype.findByVersnum", query = "SELECT c FROM Crbdtype c WHERE c.versnum = :versnum")
    , @NamedQuery(name = "Crbdtype.findByBdtype", query = "SELECT c FROM Crbdtype c WHERE c.bdtype = :bdtype")
    , @NamedQuery(name = "Crbdtype.findByTypedsc", query = "SELECT c FROM Crbdtype c WHERE c.typedsc = :typedsc")})
public class Crbdtype implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "bdtypeGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "bdtype_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "bdtypeGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CRBDTYPE_ID", nullable = false, precision = 10, scale = 0)
    private Integer crbdtypeId;
    @Version
    private Integer versnum;
    @Size(max = 256)
    @Column(length = 256)
    private String bdtype;
    @Size(max = 1024)
    @Column(length = 1024)
    private String typedsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "crbdtypeFk")
    private Collection<Crbody> crbodyCollection;

    public Crbdtype() {
    }

    public Crbdtype(Integer crbdtypeId) {
        this.crbdtypeId = crbdtypeId;
    }

    public Integer getCrbdtypeId() {
        return crbdtypeId;
    }

    public void setCrbdtypeId(Integer crbdtypeId) {
        this.crbdtypeId = crbdtypeId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getBdtype() {
        return bdtype;
    }

    public void setBdtype(String bdtype) {
        this.bdtype = bdtype;
    }

    public String getTypedsc() {
        return typedsc;
    }

    public void setTypedsc(String typedsc) {
        this.typedsc = typedsc;
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
        hash += (crbdtypeId != null ? crbdtypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crbdtype)) {
            return false;
        }
        Crbdtype other = (Crbdtype) object;
        if ((this.crbdtypeId == null && other.crbdtypeId != null) || (this.crbdtypeId != null && !this.crbdtypeId.equals(other.crbdtypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Crbdtype[ crbdtypeId=" + crbdtypeId + " ]";
    }
    
}
