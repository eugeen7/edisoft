/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.jsf.rest;

import com.autofactory.data.Crbody;
import com.autofactory.data.Motor;
import com.autofactory.data.Trnsmsn;
import com.autofactory.datacache.HardwareStore;
import com.autofactory.jsf.pgs.AutoMblView;
import com.autofactory.jsf.pgs.ItemsFactoryView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Eugene Sidorov
 */
@Path("/store")
@RequestScoped
public class StoreRESRSrv {

    @Inject
    private AutoMblView autoMblView;

    @Inject
    private ItemsFactoryView itemsFactoryView;

    @Inject
    private HardwareStore hardwareStore;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutofactoryResource
     */
    public StoreRESRSrv() {
    }

    /**
     * Retrieves representation of an instance of
     * com.autofactory.jsf.rest.AutofactoryResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getFctName() {
        return "Склад АфтоФорд";
    }

    //Получить данные со склада
    @GET
    @Path("/crbdlst")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Crbody> crbodiesLstFromStore() {
        return hardwareStore.getCrbodiesAvlbl();
    }

    @GET
    @Path("/mtrlst")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Motor> motorsLstFromStore() {
        return hardwareStore.getMotorsAvlbl();
    }

    @GET
    @Path("/trnslst")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trnsmsn> trnsLstFromStore() {
        return hardwareStore.getTrnsmsnsAvlbl();
    }

    //
    @GET
    @Path("/crbdmap")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map<Integer, Crbody> crbodiesMapFromStore() {
        return hardwareStore.getCrbodiesMap();
    }

    @GET
    @Path("/mtrmap")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map<Integer, Motor> motorsMapFromStore() {
        return hardwareStore.getMotorsMap();
    }

    @GET
    @Path("/trnsmap")
    //@Produces(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Map<Integer, Trnsmsn> trnsMapFromStore() {
        return hardwareStore.getTrnsmsnsMap();
    }

    //Отправка запчастей на склад
    //Кузов на склад
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response carBodyToStore(Crbody crbody) {
        Response.ResponseBuilder builder = null;
        if (validCrBody(crbody)) {
            if (hardwareStore.toStoreCrbody(crbody)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Кузов не сохранён на складе");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Не правильные параметры объекта Кузов");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        //
//        URI crBodyUri = context.getAbsolutePathBuilder().path(crbody.getCrbodyId().toString()).build();
//        return Response.created(crBodyUri).build();
        return builder.build();
    }

    //Двигатель на склад
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response motorToStore(Motor motor) {
        Response.ResponseBuilder builder = null;
        if (validMotor(motor)) {
            if (hardwareStore.toStoreMotor(motor)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Двигатель не сохранён на складе");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Не правильные параметры объекта Двигатель");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Трансмиссия на склад
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response trnsmsnToStore(Trnsmsn trnsmsn) {
        Response.ResponseBuilder builder = null;
        if (validTrnsmsn(trnsmsn)) {
            if (hardwareStore.toStoreTrnsmsn(trnsmsn)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Трансмиссия не сохранёна на складе");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Не правильные параметры объекта Трансмиссия");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Проверки
    //Кузов
    private boolean validCrBody(Crbody crbody) {
        boolean crbodyToStoreValid = false;
        if (crbody != null) {
            if (crbody.getCrbdclrsFk() != null && crbody.getCrbdtypeFk() != null) {
                if (crbody.getCrbodyId() == null) {
                    itemsFactoryView.getCrbodyFacade().create(crbody);
                }
                if (crbody.getCrbodyId() != null) {
                    crbodyToStoreValid = true;
                }
            }
        }
        return crbodyToStoreValid;
    }

    //Кузов
    private boolean validMotor(Motor motor) {
        boolean motorToStoreValid = false;
        if (motor != null) {
            if (motor.getMtrtypeFk() != null) {
                if (motor.getMotorId() == null) {
                    itemsFactoryView.getMotorFacade().create(motor);
                }
                if (motor.getMotorId() != null) {
                    motorToStoreValid = true;
                }
            }
        }
        return motorToStoreValid;
    }

    //Трансмиссия
    private boolean validTrnsmsn(Trnsmsn trnsmsn) {
        boolean trnsmsnToStoreValid = false;
        if (trnsmsn != null) {
            if (trnsmsn.getTrnstypeFk() != null) {
                if (trnsmsn.getTrnsmsnId() == null) {
                    itemsFactoryView.getTrnsmsnFacade().create(trnsmsn);
                }
                if (trnsmsn.getTrnsmsnId() != null) {
                    trnsmsnToStoreValid = true;
                }
            }
        }
        return trnsmsnToStoreValid;
    }

    /**
     * PUT method for updating or creating an instance of AutofactoryResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
