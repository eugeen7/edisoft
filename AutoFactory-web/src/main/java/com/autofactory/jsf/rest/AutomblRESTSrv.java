/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.jsf.rest;

import com.autofactory.data.Autombl;
import com.autofactory.data.Crbdclrs;
import com.autofactory.data.Crbdtype;
import com.autofactory.data.Crbody;
import com.autofactory.data.Motor;
import com.autofactory.data.Trnsmsn;
import com.autofactory.jsf.pgs.AutoMblView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Eugene Sidorov
 */
@Path("/atmbl")
@RequestScoped
public class AutomblRESTSrv {

    @Inject
    private AutoMblView autoMblView;


    //Список автомобилей
    @GET
    @Path("/atmbllst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Autombl> automblLst() {
        return autoMblView.freshAutomblLst();
    }

    //Автомобиль
    @GET
    @Path("{atmblid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Autombl findAutomblById(@PathParam("atmblid") Integer atmblid) {
        Autombl autombl = null;
        if (atmblid != null) {
            autombl = autoMblView.getAutomblFacade().find(atmblid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (autombl == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return autombl;
    }

    //Список всех кузовов
    @GET
    @Path("/crdlst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Crbody> crbodyLst() {
        return autoMblView.actualCrbodyLst();
    }

    //Кузов
    @GET
    @Path("{crbdid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Crbody findCrbodyById(@PathParam("crbdid") Integer crbdid) {
        Crbody crbody = null;
        if (crbdid != null) {
            crbody = autoMblView.getCrbodyFacade().find(crbdid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (crbody == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return crbody;
    }

    //Список всех типов кузовов
    @GET
    @Path("/crdtpslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Crbdtype> crbodyTypesLst() {
        return autoMblView.actualCrbdtypeLst();
    }

    //Тип кузова
    @GET
    @Path("{crbdtpid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Crbdtype findCrbdTypeById(@PathParam("crbdtpid") Integer crbdtpid) {
        Crbdtype crbdtype = null;
        if (crbdtpid != null) {
            crbdtype = autoMblView.getCrbdtypeFacade().find(crbdtpid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (crbdtype == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return crbdtype;
    }

    //Тип кузова количество доступно
    @GET
    @Path("{bdtpcntid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Integer countCrbdTypeById(@PathParam("bdtpcntid") Integer bdtpcntid) {
        int countCrbdType = 0;
        if (bdtpcntid != null) {
            autoMblView.setCrbdtypeIdx(bdtpcntid);
            countCrbdType = autoMblView.getCrbodyCount();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return countCrbdType;
    }

    //Список всех цветов кузовов
    @GET
    @Path("/crdclrslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Crbdclrs> crbodyColorsLst() {
        return autoMblView.actualCrbdclrsLst();
    }

    //Цвет кузова
    @GET
    @Path("{crbdclrid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Crbdclrs findCrbdColorById(@PathParam("crbdclrid") Integer crbdclrid) {
        Crbdclrs crbdclrs = null;
        if (crbdclrid != null) {
            crbdclrs = autoMblView.getCrbdclrsFacade().find(crbdclrid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (crbdclrs == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return crbdclrs;
    }
    
    //Цвет кузова количество доступно
    @GET
    @Path("{bdclrcntid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Integer countCrbdColorById(@PathParam("bdclrcntid") Integer bdclrcntid) {
        int countCrbdType = 0;
        if (bdclrcntid != null) {
            autoMblView.setCrbdclrIdx(bdclrcntid);
            countCrbdType = autoMblView.getCrbodyCount();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return countCrbdType;
    }

    //Список всех двигателей
    @GET
    @Path("/motorslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Motor> motorsLst() {
        return autoMblView.actualMotorLst();
    }

    //Двигатель
    @GET
    @Path("{mtrid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Motor findMotorById(@PathParam("mtrid") Integer mtrid) {
        Motor motor = null;
        if (mtrid != null) {
            motor = autoMblView.getMotorFacade().find(mtrid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (motor == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return motor;
    }

//    //Список всех типов двигателей
//    @GET
//    @Path("/mtrtpslst")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Mtrtype> mtrtypesLst() {
//        return autoMblView.actualMtrtypesLst();
//    }
//    //Тип двигателя
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Mtrtype findMtrtypeById(@PathParam("id") Integer id) {
//        Mtrtype mtrtype = null;
//        if (id != null) {
//            mtrtype = autoMblView.getMtrtypeFacade().find(id);
//        } else {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        if (mtrtype == null) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        return mtrtype;
//    }
    //Список всех трансмиссий
    @GET
    @Path("/trnslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trnsmsn> trnsmsnLst() {
        return autoMblView.actualTrnsmsnLst();
    }

    //Трансмиссия
    @GET
    @Path("{trnsid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trnsmsn findTrnsmsnById(@PathParam("trnsid") Integer trnsid) {
        Trnsmsn trnsmsn = null;
        if (trnsid != null) {
            trnsmsn = autoMblView.getTrnsmsnFacade().find(trnsid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (trnsmsn == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trnsmsn;
    }

//    //Список всех типов трансмиссий
//    @GET
//    @Path("/trntpslst")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Trnstype> trnstypeLst() {
//        return autoMblView.actualTrnstypesLst();
//    }
//
//    //Тип трансмиссии
//    @GET
//    @Path("{id}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Trnstype findTrnstypeById(@PathParam("id") Integer id) {
//        Trnstype trnstype = null;
//        if (id != null) {
//            trnstype = autoMblView.getTrnstypeFacade().find(id);
//        } else {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        if (trnstype == null) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        return trnstype;
//    }
    
    //Сохранить автомобиль
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response saveAutombl(Autombl autombl) {
        Response.ResponseBuilder builder = null;
        if (autombl != null) {
            if (autombl.getAutomblId() == null) {
                autoMblView.defAutomblStatusNew();
            } else {
                autoMblView.defAutomblStatusEdit();
            }
            autoMblView.setAutombl(autombl);
            autoMblView.saveAutombl();
            builder = Response.ok();
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Объект Автомобиль = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Удалить автомобиль
    @DELETE
    @Path("{delatmblid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteAutombl(@PathParam("delatmblid") Integer delatmblid) {
        Response.ResponseBuilder builder = null;
        if (delatmblid != null) {
            if (autoMblView.deleteRESTAutoMbl(delatmblid)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Ошибка удаления объекта Автомобиль");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }

        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "ID объекта Автомобиль = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

}
