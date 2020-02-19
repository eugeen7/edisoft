/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.data;

import java.io.Serializable;
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
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Eugene Sidorov
 */
@Entity
//@Table(catalog = "", schema = "AFACTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autombl.findAll", query = "SELECT a FROM Autombl a")
    , @NamedQuery(name = "Autombl.findByAutomblId", query = "SELECT a FROM Autombl a WHERE a.automblId = :automblId")
    , @NamedQuery(name = "Autombl.findByVersnum", query = "SELECT a FROM Autombl a WHERE a.versnum = :versnum")
    , @NamedQuery(name = "Autombl.findByAtmodel", query = "SELECT a FROM Autombl a WHERE a.atmodel = :atmodel")
    , @NamedQuery(name = "Autombl.findByAtdsc", query = "SELECT a FROM Autombl a WHERE a.atdsc = :atdsc")})
public class Autombl implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @TableGenerator(name = "automblGen", table = "IDGEN", pkColumnName = "IDNAME",
            valueColumnName = "IDVAL", pkColumnValue = "atmbl_pk", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "automblGen")
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUTOMBL_ID", nullable = false, precision = 10, scale = 0)
    private Integer automblId;
    @Version
    private Integer versnum;
    @Size(max = 256)
    @Column(length = 256)
    private String atmodel;
    @Size(max = 1024)
    @Column(length = 1024)
    private String atdsc;
    @Column(name = "STACTIVE")
    private Boolean stactive;
    @JoinColumn(name = "AUTOFCTR_FK", referencedColumnName = "AUTOFCTR_ID", nullable = false)
    @ManyToOne(optional = false)
    private Autofctr autofctrFk;
    @JoinColumn(name = "CRBODY_FK", referencedColumnName = "CRBODY_ID")
    @ManyToOne
    private Crbody crbodyFk;
    @JoinColumn(name = "MOTOR_FK", referencedColumnName = "MOTOR_ID")
    @ManyToOne
    private Motor motorFk;
    @JoinColumn(name = "TRNSMSN_FK", referencedColumnName = "TRNSMSN_ID")
    @ManyToOne
    private Trnsmsn trnsmsnFk;

    public Autombl() {
    }

    public Autombl(Integer automblId) {
        this.automblId = automblId;
    }

    public Integer getAutomblId() {
        return automblId;
    }

    public void setAutomblId(Integer automblId) {
        this.automblId = automblId;
    }

    public Integer getVersnum() {
        return versnum;
    }

    public void setVersnum(Integer versnum) {
        this.versnum = versnum;
    }

    public String getAtmodel() {
        return atmodel;
    }

    public void setAtmodel(String atmodel) {
        this.atmodel = atmodel;
    }

    public String getAtdsc() {
        return atdsc;
    }

    public void setAtdsc(String atdsc) {
        this.atdsc = atdsc;
    }

    public Boolean getStactive() {
        return stactive;
    }

    public void setStactive(Boolean stactive) {
        this.stactive = stactive;
    }

    public Autofctr getAutofctrFk() {
        return autofctrFk;
    }

    public void setAutofctrFk(Autofctr autofctrFk) {
        this.autofctrFk = autofctrFk;
    }

    public Crbody getCrbodyFk() {
        return crbodyFk;
    }

    public void setCrbodyFk(Crbody crbodyFk) {
        this.crbodyFk = crbodyFk;
    }

    public Motor getMotorFk() {
        return motorFk;
    }

    public void setMotorFk(Motor motorFk) {
        this.motorFk = motorFk;
    }

    public Trnsmsn getTrnsmsnFk() {
        return trnsmsnFk;
    }

    public void setTrnsmsnFk(Trnsmsn trnsmsnFk) {
        this.trnsmsnFk = trnsmsnFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (automblId != null ? automblId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autombl)) {
            return false;
        }
        Autombl other = (Autombl) object;
        if ((this.automblId == null && other.automblId != null) || (this.automblId != null && !this.automblId.equals(other.automblId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Autombl[ automblId=" + automblId + " ]";
    }
    
}
