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
    @NamedQuery(name = "Crbody.findAll", query = "SELECT c FROM Crbody c")
    , @NamedQuery(name = "Crbody.findByCrbodyId", query = "SELECT c FROM Crbody c WHERE c.crbodyId = :crbodyId")
    , @NamedQuery(name = "Crbody.findByVersnum", query = "SELECT c FROM Crbody c WHERE c.versnum = :versnum")
    , @NamedQuery(name = "Crbody.findByDorscnt", query = "SELECT c FROM Crbody c WHERE c.dorscnt = :dorscnt")
    , @NamedQuery(name = "Crbody.findByVincode", query = "SELECT c FROM Crbody c WHERE c.vincode = :vincode")
    , @NamedQuery(name = "Crbody.findByCrbddsc", query = "SELECT c FROM Crbody c WHERE c.crbddsc = :crbddsc")
    , @NamedQuery(name = "Crbody.findByCrbdUse", query = "SELECT c FROM Crbody c WHERE c.crbdUse = :crbdUse")})
public class Crbody implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "crbodyGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "crbody_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "crbodyGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CRBODY_ID", nullable = false, precision = 10, scale = 0)
    private Integer crbodyId;
    @Version
    private Integer versnum;
    private Integer dorscnt;
    @Size(max = 30)
    @Column(length = 30)
    private String vincode;
    @Size(max = 1024)
    @Column(length = 1024)
    private String crbddsc;
    @Column(name = "CRBD_USE")
    private Integer crbdUse;
    @Column(name = "STACTIVE")
    private Boolean stactive;
    @JoinColumn(name = "CRBDCLRS_FK", referencedColumnName = "CRBDCLRS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Crbdclrs crbdclrsFk;
    @JoinColumn(name = "CRBDTYPE_FK", referencedColumnName = "CRBDTYPE_ID", nullable = false)
    @ManyToOne(optional = false)
    private Crbdtype crbdtypeFk;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "crbodyFk")
    @OneToMany(mappedBy = "crbodyFk")
    private Collection<Autombl> automblCollection;

    public Crbody() {
    }

    public Crbody(Integer crbodyId) {
        this.crbodyId = crbodyId;
    }

    public Crbody(Integer crbodyId, Integer versnum, Integer dorscnt, String vincode, String crbddsc, Integer crbdUse, Boolean stactive, Crbdclrs crbdclrsFk, Crbdtype crbdtypeFk, Collection<Autombl> automblCollection) {
        this.crbodyId = crbodyId;
        this.versnum = versnum;
        this.dorscnt = dorscnt;
        this.vincode = vincode;
        this.crbddsc = crbddsc;
        this.crbdUse = crbdUse;
        this.stactive = stactive;
        this.crbdclrsFk = crbdclrsFk;
        this.crbdtypeFk = crbdtypeFk;
        this.automblCollection = automblCollection;
    }

    public Integer getCrbodyId() {
        return crbodyId;
    }

    public void setCrbodyId(Integer crbodyId) {
        this.crbodyId = crbodyId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public Integer getDorscnt() {
        return dorscnt;
    }

    public void setDorscnt(Integer dorscnt) {
        this.dorscnt = dorscnt;
    }

    public String getVincode() {
        return vincode;
    }

    public void setVincode(String vincode) {
        this.vincode = vincode;
    }

    public String getCrbddsc() {
        return crbddsc;
    }

    public void setCrbddsc(String crbddsc) {
        this.crbddsc = crbddsc;
    }

    public Integer getCrbdUse() {
        return crbdUse;
    }

    public void setCrbdUse(Integer crbdUse) {
        this.crbdUse = crbdUse;
    }

    public Boolean getStactive() {
        return stactive;
    }

    public void setStactive(Boolean stactive) {
        this.stactive = stactive;
    }

    public Crbdclrs getCrbdclrsFk() {
        return crbdclrsFk;
    }

    public void setCrbdclrsFk(Crbdclrs crbdclrsFk) {
        this.crbdclrsFk = crbdclrsFk;
    }

    public Crbdtype getCrbdtypeFk() {
        return crbdtypeFk;
    }

    public void setCrbdtypeFk(Crbdtype crbdtypeFk) {
        this.crbdtypeFk = crbdtypeFk;
    }

    @XmlTransient
    public Collection<Autombl> getAutomblCollection() {
        return automblCollection;
    }

    public void setAutomblCollection(Collection<Autombl> automblCollection) {
        this.automblCollection = automblCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crbodyId != null ? crbodyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Crbody)) {
            return false;
        }
        Crbody other = (Crbody) object;
        if ((this.crbodyId == null && other.crbodyId != null) || (this.crbodyId != null && !this.crbodyId.equals(other.crbodyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Crbody[ crbodyId=" + crbodyId + " ]";
    }
    
    @Override
    public Crbody clone() {
        return new Crbody(crbodyId, versnum, dorscnt, vincode, crbddsc, crbdUse, stactive, crbdclrsFk, crbdtypeFk, automblCollection);
    }
    
}
