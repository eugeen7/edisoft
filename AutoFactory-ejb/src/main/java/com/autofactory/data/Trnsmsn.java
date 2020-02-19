/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Trnsmsn.findAll", query = "SELECT t FROM Trnsmsn t")
    , @NamedQuery(name = "Trnsmsn.findByTrnsmsnId", query = "SELECT t FROM Trnsmsn t WHERE t.trnsmsnId = :trnsmsnId")
    , @NamedQuery(name = "Trnsmsn.findByVersnum", query = "SELECT t FROM Trnsmsn t WHERE t.versnum = :versnum")
    , @NamedQuery(name = "Trnsmsn.findByTrnsmsnsn", query = "SELECT t FROM Trnsmsn t WHERE t.trnsmsnsn = :trnsmsnsn")
    , @NamedQuery(name = "Trnsmsn.findByTrnsdsc", query = "SELECT t FROM Trnsmsn t WHERE t.trnsdsc = :trnsdsc")
    , @NamedQuery(name = "Trnsmsn.findByTrnsUse", query = "SELECT t FROM Trnsmsn t WHERE t.trnsUse = :trnsUse")})
public class Trnsmsn implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "trnsmsnGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "trnsmsn_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "trnsmsnGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRNSMSN_ID", nullable = false, precision = 38, scale = 0)
    private Integer trnsmsnId;
    @Version
    private Integer versnum;
    @Size(max = 50)
    @Column(length = 50)
    private String trnsmsnsn;
    @Size(max = 1024)
    @Column(length = 1024)
    private String trnsdsc;
    @Column(name = "TRNS_USE")
    private Integer trnsUse;
    @Column(name = "STACTIVE")
    private Boolean stactive;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "trnsmsnFk")
    @OneToMany(mappedBy = "trnsmsnFk")
    private Collection<Autombl> automblCollection;
    @JoinColumn(name = "TRNSTYPE_FK", referencedColumnName = "TRNSTYPE_ID", nullable = false)
    @ManyToOne(optional = false)
    private Trnstype trnstypeFk;

    public Trnsmsn() {
    }

    public Trnsmsn(Integer trnsmsnId) {
        this.trnsmsnId = trnsmsnId;
    }

    public Trnsmsn(Integer trnsmsnId, Integer versnum, String trnsmsnsn, String trnsdsc, Integer trnsUse, Boolean stactive, Collection<Autombl> automblCollection, Trnstype trnstypeFk) {
        this.trnsmsnId = trnsmsnId;
        this.versnum = versnum;
        this.trnsmsnsn = trnsmsnsn;
        this.trnsdsc = trnsdsc;
        this.trnsUse = trnsUse;
        this.stactive = stactive;
        this.automblCollection = automblCollection;
        this.trnstypeFk = trnstypeFk;
    }

    public Integer getTrnsmsnId() {
        return trnsmsnId;
    }

    public void setTrnsmsnId(Integer trnsmsnId) {
        this.trnsmsnId = trnsmsnId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getTrnsmsnsn() {
        return trnsmsnsn;
    }

    public void setTrnsmsnsn(String trnsmsnsn) {
        this.trnsmsnsn = trnsmsnsn;
    }

    public String getTrnsdsc() {
        return trnsdsc;
    }

    public void setTrnsdsc(String trnsdsc) {
        this.trnsdsc = trnsdsc;
    }

    public Integer getTrnsUse() {
        return trnsUse;
    }

    public void setTrnsUse(Integer trnsUse) {
        this.trnsUse = trnsUse;
    }

    public Boolean getStactive() {
        return stactive;
    }

    public void setStactive(Boolean stactive) {
        this.stactive = stactive;
    }

    @XmlTransient
    public Collection<Autombl> getAutomblCollection() {
        return automblCollection;
    }

    public void setAutomblCollection(Collection<Autombl> automblCollection) {
        this.automblCollection = automblCollection;
    }

    public Trnstype getTrnstypeFk() {
        return trnstypeFk;
    }

    public void setTrnstypeFk(Trnstype trnstypeFk) {
        this.trnstypeFk = trnstypeFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trnsmsnId != null ? trnsmsnId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trnsmsn)) {
            return false;
        }
        Trnsmsn other = (Trnsmsn) object;
        if ((this.trnsmsnId == null && other.trnsmsnId != null) || (this.trnsmsnId != null && !this.trnsmsnId.equals(other.trnsmsnId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Trnsmsn[ trnsmsnId=" + trnsmsnId + " ]";
    }
    
    public Trnsmsn clone() {
        return new Trnsmsn(trnsmsnId, versnum, trnsmsnsn, trnsdsc, trnsUse, stactive, automblCollection, trnstypeFk);
    }
    
}
