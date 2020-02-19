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
    @NamedQuery(name = "Autofctr.findAll", query = "SELECT a FROM Autofctr a")
    , @NamedQuery(name = "Autofctr.findByAutofctrId", query = "SELECT a FROM Autofctr a WHERE a.autofctrId = :autofctrId")
    , @NamedQuery(name = "Autofctr.findByVersnum", query = "SELECT a FROM Autofctr a WHERE a.versnum = :versnum")
    , @NamedQuery(name = "Autofctr.findByFctrNm", query = "SELECT a FROM Autofctr a WHERE a.fctrNm = :fctrNm")
    , @NamedQuery(name = "Autofctr.findByAdrs", query = "SELECT a FROM Autofctr a WHERE a.adrs = :adrs")
    , @NamedQuery(name = "Autofctr.findByAutodsc", query = "SELECT a FROM Autofctr a WHERE a.autodsc = :autodsc")})
public class Autofctr implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "autofctrGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "atfctr_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "autofctrGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTOFCTR_ID", nullable = false, precision = 10, scale = 0)
    private Integer autofctrId;
    @Version
    private Integer versnum;
    @Size(max = 512)
    @Column(name = "FCTR_NM", length = 512)
    private String fctrNm;
    @Size(max = 512)
    @Column(length = 512)
    private String adrs;
    @Size(max = 512)
    @Column(length = 512)
    private String autodsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autofctrFk")
    private Collection<Autombl> automblCollection;

    public Autofctr() {
    }

    public Autofctr(Integer autofctrId) {
        this.autofctrId = autofctrId;
    }

    public Integer getAutofctrId() {
        return autofctrId;
    }

    public void setAutofctrId(Integer autofctrId) {
        this.autofctrId = autofctrId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getFctrNm() {
        return fctrNm;
    }

    public void setFctrNm(String fctrNm) {
        this.fctrNm = fctrNm;
    }

    public String getAdrs() {
        return adrs;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public String getAutodsc() {
        return autodsc;
    }

    public void setAutodsc(String autodsc) {
        this.autodsc = autodsc;
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
        hash += (autofctrId != null ? autofctrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autofctr)) {
            return false;
        }
        Autofctr other = (Autofctr) object;
        if ((this.autofctrId == null && other.autofctrId != null) || (this.autofctrId != null && !this.autofctrId.equals(other.autofctrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Autofctr[ autofctrId=" + autofctrId + " ]";
    }
    
}
