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
    @NamedQuery(name = "Motor.findAll", query = "SELECT m FROM Motor m")
    , @NamedQuery(name = "Motor.findByMotorId", query = "SELECT m FROM Motor m WHERE m.motorId = :motorId")
    , @NamedQuery(name = "Motor.findByVersnum", query = "SELECT m FROM Motor m WHERE m.versnum = :versnum")
    , @NamedQuery(name = "Motor.findByVolume", query = "SELECT m FROM Motor m WHERE m.volume = :volume")
    , @NamedQuery(name = "Motor.findByMtrpower", query = "SELECT m FROM Motor m WHERE m.mtrpower = :mtrpower")
    , @NamedQuery(name = "Motor.findByMotorsn", query = "SELECT m FROM Motor m WHERE m.motorsn = :motorsn")
    , @NamedQuery(name = "Motor.findByMtdsc", query = "SELECT m FROM Motor m WHERE m.mtdsc = :mtdsc")
    , @NamedQuery(name = "Motor.findByMtrUse", query = "SELECT m FROM Motor m WHERE m.mtrUse = :mtrUse")})
public class Motor implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "motorGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "motor_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "motorGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOTOR_ID", nullable = false, precision = 10, scale = 0)
    private Integer motorId;
    @Version
    private Integer versnum;
    @Column(precision = 5)
    private Double volume;
    @Column(precision = 7)
    private Double mtrpower;
    @Size(max = 50)
    @Column(length = 50)
    private String motorsn;
    @Size(max = 1024)
    @Column(length = 1024)
    private String mtdsc;
    @Column(name = "MTR_USE")
    private Integer mtrUse;
    @Column(name = "STACTIVE")
    private Boolean stactive;
    @JoinColumn(name = "MTRTYPE_FK", referencedColumnName = "MTRTYPE_ID", nullable = false)
    @ManyToOne(optional = false)
    private Mtrtype mtrtypeFk;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "motorFk")
    @OneToMany(mappedBy = "motorFk")
    private Collection<Autombl> automblCollection;

    public Motor() {
    }

    public Motor(Integer motorId) {
        this.motorId = motorId;
    }

    public Motor(Integer motorId, Integer versnum, Double volume, Double mtrpower, String motorsn, String mtdsc, Integer mtrUse, Boolean stactive, Mtrtype mtrtypeFk, Collection<Autombl> automblCollection) {
        this.motorId = motorId;
        this.versnum = versnum;
        this.volume = volume;
        this.mtrpower = mtrpower;
        this.motorsn = motorsn;
        this.mtdsc = mtdsc;
        this.mtrUse = mtrUse;
        this.stactive = stactive;
        this.mtrtypeFk = mtrtypeFk;
        this.automblCollection = automblCollection;
    }

    public Integer getMotorId() {
        return motorId;
    }

    public void setMotorId(Integer motorId) {
        this.motorId = motorId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getMtrpower() {
        return mtrpower;
    }

    public void setMtrpower(Double mtrpower) {
        this.mtrpower = mtrpower;
    }

    public String getMotorsn() {
        return motorsn;
    }

    public void setMotorsn(String motorsn) {
        this.motorsn = motorsn;
    }

    public String getMtdsc() {
        return mtdsc;
    }

    public void setMtdsc(String mtdsc) {
        this.mtdsc = mtdsc;
    }

    public Integer getMtrUse() {
        return mtrUse;
    }

    public void setMtrUse(Integer mtrUse) {
        this.mtrUse = mtrUse;
    }

    public Boolean getStactive() {
        return stactive;
    }

    public void setStactive(Boolean stactive) {
        this.stactive = stactive;
    }

    public Mtrtype getMtrtypeFk() {
        return mtrtypeFk;
    }

    public void setMtrtypeFk(Mtrtype mtrtypeFk) {
        this.mtrtypeFk = mtrtypeFk;
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
        hash += (motorId != null ? motorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motor)) {
            return false;
        }
        Motor other = (Motor) object;
        if ((this.motorId == null && other.motorId != null) || (this.motorId != null && !this.motorId.equals(other.motorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Motor[ motorId=" + motorId + " ]";
    }
    
    @Override
    public Motor clone() {
      return new Motor(motorId, versnum, volume, mtrpower, motorsn, mtdsc, mtrUse, stactive, mtrtypeFk, automblCollection);
    }
    
}
