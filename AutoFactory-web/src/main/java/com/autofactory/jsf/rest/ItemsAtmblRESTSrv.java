/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.jsf.rest;

import com.autofactory.data.Crbdclrs;
import com.autofactory.data.Crbdtype;
import com.autofactory.data.Crbody;
import com.autofactory.data.Motor;
import com.autofactory.data.Mtrtype;
import com.autofactory.data.Trnsmsn;
import com.autofactory.data.Trnstype;
import com.autofactory.jsf.pgs.ItemsFactoryView;
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
@Path("/itmsatmbl")
@RequestScoped
public class ItemsAtmblRESTSrv {

    @Inject
    private ItemsFactoryView itemsFactoryView;

    //Список всех кузовов
    @GET
    @Path("/crdlst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Crbody> crbodyLst() {
        return itemsFactoryView.actulUnUseCrbodies();
    }

    //Кузов
    @GET
    @Path("{crbdid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Crbody findCrbodyById(@PathParam("crbdid") Integer crbdid) {
        Crbody crbody = null;
        if (crbdid != null) {
            crbody = itemsFactoryView.getCrbodyFacade().find(crbdid);
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
        return itemsFactoryView.getCrbdtypeFacade().findAll();
    }

    //Тип кузова
    @GET
    @Path("{crdtpid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Crbdtype findCrbdTypeById(@PathParam("crdtpid") Integer crdtpid) {
        Crbdtype crbdtype = null;
        if (crdtpid != null) {
            crbdtype = itemsFactoryView.getCrbdtypeFacade().find(crdtpid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (crbdtype == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return crbdtype;
    }

    //Список всех цветов кузовов
    @GET
    @Path("/crdclrslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Crbdclrs> crbodyColorsLst() {
        return itemsFactoryView.getCrbdclrsFacade().findAll();
    }

    //Цвет кузова
    @GET
    @Path("{crdclrid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Crbdclrs findCrbdColorById(@PathParam("crdclrid") Integer crdclrid) {
        Crbdclrs crbdclrs = null;
        if (crdclrid != null) {
            crbdclrs = itemsFactoryView.getCrbdclrsFacade().find(crdclrid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (crbdclrs == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return crbdclrs;
    }

    //Список всех двигателей
    @GET
    @Path("/motorslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Motor> motorsLst() {
        return itemsFactoryView.actulUnUseMotors();
    }

    //Двигатель
    @GET
    @Path("{motorid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Motor findMotorById(@PathParam("motorid") Integer motorid) {
        Motor motor = null;
        if (motorid != null) {
            motor = itemsFactoryView.getMotorFacade().find(motorid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (motor == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return motor;
    }

    //Список всех типов двигателей
    @GET
    @Path("/mtrtpslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mtrtype> mtrtypesLst() {
        return itemsFactoryView.getMtrtypeFacade().findAll();
    }

    //Тип двигателя
    @GET
    @Path("{mtrtpid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Mtrtype findMtrtypeById(@PathParam("mtrtpid") Integer mtrtpid) {
        Mtrtype mtrtype = null;
        if (mtrtpid != null) {
            mtrtype = itemsFactoryView.getMtrtypeFacade().find(mtrtpid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (mtrtype == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return mtrtype;
    }

    //Список всех трансмиссий
    @GET
    @Path("/trnslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trnsmsn> trnsmsnLst() {
        return itemsFactoryView.actulUnUseTrnsmsns();
    }

    //Трансмиссия
    @GET
    @Path("{trnsid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trnsmsn findTrnsmsnById(@PathParam("trnsid") Integer trnsid) {
        Trnsmsn trnsmsn = null;
        if (trnsid != null) {
            trnsmsn = itemsFactoryView.getTrnsmsnFacade().find(trnsid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (trnsmsn == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trnsmsn;
    }

    //Список всех типов трансмиссий
    @GET
    @Path("/trntpslst")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Trnstype> trnstypeLst() {
        return itemsFactoryView.getTrnstypeFacade().findAll();
    }

    //Тип трансмиссии
    @GET
    @Path("{trntpid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Trnstype findTrnstypeById(@PathParam("trntpid") Integer trntpid) {
        Trnstype trnstype = null;
        if (trntpid != null) {
            trnstype = itemsFactoryView.getTrnstypeFacade().find(trntpid);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (trnstype == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return trnstype;
    }

    //Сохранить корпус
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response saveCarBody(Crbody crbody) {
        Response.ResponseBuilder builder = null;
        if (crbody != null) {
            if (crbody.getCrbodyId() == null) {
                itemsFactoryView.defCarBodyStatusNew();
            } else {
                itemsFactoryView.defCarBodyStatusEdit();
            }
            itemsFactoryView.setCrbody(crbody);
            itemsFactoryView.saveCrbody();
            builder = Response.ok();
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Объект Автомобиль = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Удалить корпус 
    @DELETE
    @Path("{delcrbodyid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteAutombl(@PathParam("delcrbodyid") Integer delcrbodyid) {
        Response.ResponseBuilder builder = null;
        if (delcrbodyid != null) {
            if (itemsFactoryView.deleteRESTCrbody(delcrbodyid)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Ошибка удаления объекта корпус");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }

        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "ID объекта корпус = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Сохранить двигатель
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response saveMotor(Motor motor) {
        Response.ResponseBuilder builder = null;
        if (motor != null) {
            if (motor.getMotorId() == null) {
                itemsFactoryView.defMotorStatusNew();
            } else {
                itemsFactoryView.defMotorStatusEdit();
            }
            itemsFactoryView.setMotor(motor);
            itemsFactoryView.saveMotor();
            builder = Response.ok();
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Объект двигатель = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Удалить двигатель 
    @DELETE
    @Path("{delmotorid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteMotor(@PathParam("delmotorid") Integer delmotorid) {
        Response.ResponseBuilder builder = null;
        if (delmotorid != null) {
            if (itemsFactoryView.deleteRESTMotor(delmotorid)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Ошибка удаления объекта двигатель");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }

        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "ID объекта двигатель = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //
    //Сохранить трансмиссию
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response saveTrnsmsn(Trnsmsn trnsmsn) {
        Response.ResponseBuilder builder = null;
        if (trnsmsn != null) {
            if (trnsmsn.getTrnsmsnId() == null) {
                itemsFactoryView.defTrnsmsStatusNew();
            } else {
                itemsFactoryView.defTrnsmsnStatusEdit();
            }
            itemsFactoryView.setTrnsmsn(trnsmsn);
            itemsFactoryView.saveTrnsmsn();
            builder = Response.ok();
        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "Объект трансмиссия = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

    //Удалить трансмиссию 
    @DELETE
    @Path("{deltrnsid}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response deleteTrnsmsn(@PathParam("deltrnsid") Integer deltrnsid) {
        Response.ResponseBuilder builder = null;
        if (deltrnsid != null) {
            if (itemsFactoryView.deleteRESTTrnsmsn(deltrnsid)) {
                builder = Response.ok();
            } else {
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "Ошибка удаления объекта трансмиссия");
                builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
            }

        } else {
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "ID объекта трансмиссия = null");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        return builder.build();
    }

}
