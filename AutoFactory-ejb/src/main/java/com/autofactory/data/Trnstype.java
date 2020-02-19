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
    @NamedQuery(name = "Trnstype.findAll", query = "SELECT t FROM Trnstype t")
    , @NamedQuery(name = "Trnstype.findByTrnstypeId", query = "SELECT t FROM Trnstype t WHERE t.trnstypeId = :trnstypeId")
    , @NamedQuery(name = "Trnstype.findByVersnum", query = "SELECT t FROM Trnstype t WHERE t.versnum = :versnum")
    , @NamedQuery(name = "Trnstype.findByTypetrns", query = "SELECT t FROM Trnstype t WHERE t.typetrns = :typetrns")
    , @NamedQuery(name = "Trnstype.findByTrndsc", query = "SELECT t FROM Trnstype t WHERE t.trndsc = :trndsc")})
public class Trnstype implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "trnstypeGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "trnstype_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "trnstypeGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRNSTYPE_ID", nullable = false, precision = 38, scale = 0)
    private Integer trnstypeId;
    @Version
    private Integer versnum;
    @Size(max = 256)
    @Column(length = 256)
    private String typetrns;
    @Size(max = 1024)
    @Column(length = 1024)
    private String trndsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trnstypeFk")
    private Collection<Trnsmsn> trnsmsnCollection;

    public Trnstype() {
    }

    public Trnstype(Integer trnstypeId) {
        this.trnstypeId = trnstypeId;
    }

    public Integer getTrnstypeId() {
        return trnstypeId;
    }

    public void setTrnstypeId(Integer trnstypeId) {
        this.trnstypeId = trnstypeId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getTypetrns() {
        return typetrns;
    }

    public void setTypetrns(String typetrns) {
        this.typetrns = typetrns;
    }

    public String getTrndsc() {
        return trndsc;
    }

    public void setTrndsc(String trndsc) {
        this.trndsc = trndsc;
    }

    @XmlTransient
    public Collection<Trnsmsn> getTrnsmsnCollection() {
        return trnsmsnCollection;
    }

    public void setTrnsmsnCollection(Collection<Trnsmsn> trnsmsnCollection) {
        this.trnsmsnCollection = trnsmsnCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trnstypeId != null ? trnstypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trnstype)) {
            return false;
        }
        Trnstype other = (Trnstype) object;
        if ((this.trnstypeId == null && other.trnstypeId != null) || (this.trnstypeId != null && !this.trnstypeId.equals(other.trnstypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Trnstype[ trnstypeId=" + trnstypeId + " ]";
    }
    
}
