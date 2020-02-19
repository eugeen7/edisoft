/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.jsf.utils;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eugene Sidorov
 */
@SessionScoped
public class JsfUtils implements Serializable {

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

}
