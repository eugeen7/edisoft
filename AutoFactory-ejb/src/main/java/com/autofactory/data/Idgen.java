/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.data;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
    @NamedQuery(name = "Idgen.findAll", query = "SELECT i FROM Idgen i")
    , @NamedQuery(name = "Idgen.findByIdname", query = "SELECT i FROM Idgen i WHERE i.idname = :idname")
    , @NamedQuery(name = "Idgen.findByIdval", query = "SELECT i FROM Idgen i WHERE i.idval = :idval")})
public class Idgen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String idname;
    private BigInteger idval;

    public Idgen() {
    }

    public Idgen(String idname) {
        this.idname = idname;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public BigInteger getIdval() {
        return idval;
    }

    public void setIdval(BigInteger idval) {
        this.idval = idval;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idname != null ? idname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idgen)) {
            return false;
        }
        Idgen other = (Idgen) object;
        if ((this.idname == null && other.idname != null) || (this.idname != null && !this.idname.equals(other.idname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.autofactorydb.data.Idgen[ idname=" + idname + " ]";
    }
    
}
