package com.autofactory.jsf.pgs;

import com.autofactory.dao.AutofctrFacade;
import com.autofactory.dao.AutomblFacade;
import com.autofactory.dao.CrbdclrsFacade;
import com.autofactory.dao.CrbdtypeFacade;
import com.autofactory.dao.CrbodyFacade;
import com.autofactory.dao.MotorFacade;
import com.autofactory.dao.MtrtypeFacade;
import com.autofactory.dao.TrnsmsnFacade;
import com.autofactory.dao.TrnstypeFacade;
import com.autofactory.data.Autofctr;
import com.autofactory.data.Autombl;
import com.autofactory.data.Crbdclrs;
import com.autofactory.data.Crbdtype;
import com.autofactory.data.Crbody;
import com.autofactory.data.Motor;
import com.autofactory.data.Mtrtype;
import com.autofactory.data.Trnsmsn;
import com.autofactory.data.Trnstype;
import com.autofactory.datacache.HardwareStore;
import com.autofactory.jsf.utils.JsfUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eugene Sidorov
 */
@Named
@SessionScoped
public class AutoMblView implements Serializable {

    @Inject
    private AutomblFacade automblFacade;
    @Inject
    private AutofctrFacade autofctrFacade;
    @Inject
    private CrbodyFacade crbodyFacade;
    @Inject
    private MotorFacade motorFacade;
    @Inject
    private MtrtypeFacade mtrtypeFacade;
    @Inject
    private TrnsmsnFacade trnsmsnFacade;
    @Inject
    private TrnstypeFacade trnstypeFacade;
    @Inject
    private CrbdtypeFacade crbdtypeFacade;
    @Inject
    private CrbdclrsFacade crbdclrsFacade;

    @Inject
    private HardwareStore hardwareStore;

    @Inject
    private JsfUtils jsfUtils;

    private Autombl autombl; //Автомобиль выбранный в таблице
    private List<Autombl> automblLst; //Список автомобилей
    private List<Autombl> fltrAutomblLst; //Список автомобилей фильтр таблици
    private Autombl automblNe; //Автомобиль новый редактируемый

    private Crbody crbody;
    private Motor motor;
    private Trnsmsn trnsmsn;
//    private Crbdclrs crbdclrs;
//    private Crbdtype crbdtype;
//    private Mtrtype mtrtype;
//    private Trnstype trnstype;

    private Integer crbdclrIdx;
    private Integer crbdtypeIdx;
    private Integer mtrtypeIdx;
    private Integer trnstypeIdx;
    private Integer motorIdx;
    private Integer trnsmsnIdx;
    //Списки с ID деталей для получения их со склада
    private List<Integer> crbodyIdLst;
    private List<Integer> motorIdLst;
    private List<Integer> trnsmsnIdLst;

    private Map<Integer, Crbody> fromStoreCrbodyMap;
    private Map<Integer, Motor> fromStoreMotorMap;
    private Map<Integer, Trnsmsn> fromStoreTrnsmsnMap;

    private Map<String, Integer> selCarBodyTypeMap;
    private Map<String, Integer> selCarBodyColorMap;
    private Map<String, Integer> selMotorTypeMap;
    private Map<String, Integer> selMotorDscMap;
    private Map<String, Integer> selTrnsmsnTypeMap;
    private Map<String, Integer> selTrnsmsnDscMap;

    private List<Crbody> crbodyLst;
    private List<Crbdtype> crbdtypeLst;
    private List<Crbdclrs> crbdclrsLst;
    private List<Motor> motorLst;
    private List<Trnsmsn> trnsmsnLst;
    private Integer crbodyCount;

    private Autofctr autofctr;

    private static final int AUTOMBL_STATUS_NEW = 1;
    private static final int AUTOMBL_STATUS_EDIT = 2;
    private static final int AUTOMBL_STATUS_DELETE = 3;

    private int automblStatus = 0;

    private String automblNeHdr;

    public Autombl getAutombl() {
        return autombl;
    }

    public void setAutombl(Autombl autombl) {
//        defineSelIdx();
        this.autombl = autombl;
    }

    public List<Autombl> getAutomblLst() {
        if ((automblLst == null) || (automblLst.isEmpty())) {
            refreshAutomblLst();
        }
        return automblLst;
    }

    public void setAutomblLst(List<Autombl> automblLst) {
        this.automblLst = automblLst;
    }

    public List<Autombl> getFltrAutomblLst() {
        return fltrAutomblLst;
    }

    public void setFltrAutomblLst(List<Autombl> fltrAutomblLst) {
        this.fltrAutomblLst = fltrAutomblLst;
    }

    public Autofctr getAutofctr() {
        return autofctr;
    }

    public Autombl getAutomblNe() {
        return automblNe;
    }

    public void setAutomblNe(Autombl automblNe) {
        this.automblNe = automblNe;
    }

    public Integer getCrbdclrIdx() {
        return crbdclrIdx;
    }

    public void setCrbdclrIdx(Integer crbdclrIdx) {
        crbodyCount = 0;
        if (crbdclrIdx != null) {
            if (crbdtypeIdx != null) {
                for (Crbody crbd : fromStoreCrbodyMap.values()) {
                    if ((crbd.getCrbdclrsFk().getCrbdclrsId().equals(crbdclrIdx)) && (crbd.getCrbdtypeFk().getCrbdtypeId().equals(crbdtypeIdx))) {
                        crbodyCount++;
                    }
                }
            } else {
                for (Crbody crbd : fromStoreCrbodyMap.values()) {
                    if (crbd.getCrbdclrsFk().getCrbdclrsId().equals(crbdclrIdx)) {
                        crbodyCount++;
                    }
                }
            }
        }
        this.crbdclrIdx = crbdclrIdx;
    }

    public Integer getCrbdtypeIdx() {
        return crbdtypeIdx;
    }

    public void setCrbdtypeIdx(Integer crbdtypeIdx) {
        crbodyCount = 0;
        if (crbdtypeIdx != null) {
            if (crbdclrIdx != null) {
                for (Crbody crbd : fromStoreCrbodyMap.values()) {
                    if ((crbd.getCrbdtypeFk().getCrbdtypeId().equals(crbdtypeIdx)) && (crbd.getCrbdclrsFk().getCrbdclrsId().equals(crbdclrIdx))) {
                        crbodyCount++;
                    }
                }
            } else {
                for (Crbody crbd : fromStoreCrbodyMap.values()) {
                    if (crbd.getCrbdtypeFk().getCrbdtypeId().equals(crbdtypeIdx)) {
                        crbodyCount++;
                    }
                }
            }
        }
        this.crbdtypeIdx = crbdtypeIdx;
    }

    public Integer getMtrtypeIdx() {
        return mtrtypeIdx;
    }

    public void setMtrtypeIdx(Integer mtrtypeIdx) {
        this.mtrtypeIdx = mtrtypeIdx;
    }

    public Integer getTrnstypeIdx() {
        return trnstypeIdx;
    }

    public void setTrnstypeIdx(Integer trnstypeIdx) {
        this.trnstypeIdx = trnstypeIdx;
    }

    public Integer getMotorIdx() {
        return motorIdx;
    }

    public void setMotorIdx(Integer motorIdx) {
        this.motorIdx = motorIdx;
    }

    public Integer getTrnsmsnIdx() {
        return trnsmsnIdx;
    }

    public void setTrnsmsnIdx(Integer trnsmsnIdx) {
        this.trnsmsnIdx = trnsmsnIdx;
    }

    public Map<String, Integer> getSelCarBodyTypeMap() {
        return selCarBodyTypeMap;
    }

    public void setSelCarBodyTypeMap(Map<String, Integer> selCarBodyTypeMap) {
        this.selCarBodyTypeMap = selCarBodyTypeMap;
    }

    public Map<String, Integer> getSelCarBodyColorMap() {
        return selCarBodyColorMap;
    }

    public void setSelCarBodyColorMap(Map<String, Integer> selCarBodyColorMap) {
        this.selCarBodyColorMap = selCarBodyColorMap;
    }

    public Map<String, Integer> getSelMotorTypeMap() {
        return selMotorTypeMap;
    }

    public void setSelMotorTypeMap(Map<String, Integer> selMotorTypeMap) {
        this.selMotorTypeMap = selMotorTypeMap;
    }

    public Map<String, Integer> getSelMotorDscMap() {
        return selMotorDscMap;
    }

    public void setSelMotorDscMap(Map<String, Integer> selMotorDscMap) {
        this.selMotorDscMap = selMotorDscMap;
    }

    public Map<String, Integer> getSelTrnsmsnTypeMap() {
        return selTrnsmsnTypeMap;
    }

    public void setSelTrnsmsnTypeMap(Map<String, Integer> selTrnsmsnTypeMap) {
        this.selTrnsmsnTypeMap = selTrnsmsnTypeMap;
    }

    public Map<String, Integer> getSelTrnsmsnDscMap() {
        return selTrnsmsnDscMap;
    }

    public void setSelTrnsmsnDscMap(Map<String, Integer> selTrnsmsnDscMap) {
        this.selTrnsmsnDscMap = selTrnsmsnDscMap;
    }

    public Integer getCrbodyCount() {
        return crbodyCount;
    }

    public String getAutomblNeHdr() {
        return automblNeHdr;
    }

    public Map<Integer, Crbody> getFromStoreCrbodyMap() {
        return fromStoreCrbodyMap;
    }

    public Map<Integer, Motor> getFromStoreMotorMap() {
        return fromStoreMotorMap;
    }

    public Map<Integer, Trnsmsn> getFromStoreTrnsmsnMap() {
        return fromStoreTrnsmsnMap;
    }

    public List<Crbdtype> getCrbdtypeLst() {
        return crbdtypeLst;
    }

    public void setCrbdtypeLst(List<Crbdtype> crbdtypeLst) {
        this.crbdtypeLst = crbdtypeLst;
    }

    public List<Crbdclrs> getCrbdclrsLst() {
        return crbdclrsLst;
    }

    public void setCrbdclrsLst(List<Crbdclrs> crbdclrsLst) {
        this.crbdclrsLst = crbdclrsLst;
    }

    public List<Motor> getMotorLst() {
        return motorLst;
    }

    public void setMotorLst(List<Motor> motorLst) {
        this.motorLst = motorLst;
    }

    public List<Trnsmsn> getTrnsmsnLst() {
        return trnsmsnLst;
    }

    public void setTrnsmsnLst(List<Trnsmsn> trnsmsnLst) {
        this.trnsmsnLst = trnsmsnLst;
    }

    public JsfUtils getJsfUtils() {
        return jsfUtils;
    }

    public AutomblFacade getAutomblFacade() {
        return automblFacade;
    }

    public AutofctrFacade getAutofctrFacade() {
        return autofctrFacade;
    }

    public CrbodyFacade getCrbodyFacade() {
        return crbodyFacade;
    }

    public MotorFacade getMotorFacade() {
        return motorFacade;
    }

    public TrnsmsnFacade getTrnsmsnFacade() {
        return trnsmsnFacade;
    }

    public CrbdtypeFacade getCrbdtypeFacade() {
        return crbdtypeFacade;
    }

    public CrbdclrsFacade getCrbdclrsFacade() {
        return crbdclrsFacade;
    }

    public MtrtypeFacade getMtrtypeFacade() {
        return mtrtypeFacade;
    }

    public TrnstypeFacade getTrnstypeFacade() {
        return trnstypeFacade;
    }

    public int getAutomblStatus() {
        return automblStatus;
    }

    public void setAutomblStatus(int automblStatus) {
        this.automblStatus = automblStatus;
    }

    public static int getAUTOMBL_STATUS_NEW() {
        return AUTOMBL_STATUS_NEW;
    }

    public Integer getAtomblStatusNew() {
        return AUTOMBL_STATUS_NEW;
    }

    public static int getAUTOMBL_STATUS_EDIT() {
        return AUTOMBL_STATUS_EDIT;
    }

    public Integer getAtomblStatusEdit() {
        return AUTOMBL_STATUS_EDIT;
    }

    public static int getAUTOMBL_STATUS_DELETE() {
        return AUTOMBL_STATUS_DELETE;
    }

    public void refreshAutomblLst() {
        //automblLst = automblFacade.findAllAutombl();
        automblLst = automblFacade.findAll();
    }

    public List<Autombl> freshAutomblLst() {
        return automblLst = automblFacade.findAll();
    }

    public void createAutombl() {
        automblStatus = AUTOMBL_STATUS_NEW;
        autombl = new Autombl();
        autombl.setStactive(Boolean.TRUE);
        clearDetails();
        obtainCarElements();
        prepareElements();
        automblNeHdr = "Новый автомобиль";
        crbodyCount = 0;
        showAutombDlg();
    }

    public void editAutombl() {
        if (autombl != null) {
            automblStatus = AUTOMBL_STATUS_EDIT;
            automblNeHdr = autombl.getAtmodel() + " редактирование";
            clearDetails();
            obtainCarElements();
            prepareDetails(autombl);
            prepareElements();
            //automblNe = autombl;
            crbodyCount = 0;
            showAutombDlg();
        }
    }

    public void deleteAutombl() {
        //int stepDelete = 0;
        if (autombl != null) {
            if (autombl.getAutomblId() != null) {
                if (autombl.getCrbodyFk() != null) {
                    crbody = crbodyFacade.find(autombl.getCrbodyFk().getCrbodyId());
                    crbody.setCrbdUse(null);
                    //crbody.getAutomblCollection().remove(autombl);
                    autombl.setCrbodyFk(null);
                    crbodyFacade.edit(crbody);
                    crbody = crbodyFacade.find(crbody.getCrbodyId());
                    hardwareStore.toStoreCrbody(crbody);
                    //stepDelete++;
                }
                if (autombl.getMotorFk() != null) {
                    motor = motorFacade.find(autombl.getMotorFk().getMotorId());
                    motor.setMtrUse(null);
                    //motor.getAutomblCollection().remove(autombl);
                    autombl.setMotorFk(null);
                    motorFacade.edit(motor);
                    motor = motorFacade.find(motor.getMotorId());
                    hardwareStore.toStoreMotor(motor);
                    //stepDelete++;
                }
                if (autombl.getTrnsmsnFk() != null) {
                    trnsmsn = trnsmsnFacade.find(autombl.getTrnsmsnFk().getTrnsmsnId());
                    trnsmsn.setTrnsUse(null);
                    //trnsmsn.getAutomblCollection().remove(autombl);
                    autombl.setTrnsmsnFk(null);
                    trnsmsnFacade.edit(trnsmsn);
                    trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                    hardwareStore.toStoreTrnsmsn(trnsmsn);
                    //stepDelete++;
                }
                automblNe = automblFacade.find(autombl.getAutomblId());
                if (automblNe != null) {
                    automblNe.setStactive(false);
                    automblFacade.edit(automblNe);
//                    autombl = null;
//                    automblNe = null;
                }
            } else {
                autombl = null;
            }
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Автомобиль не выбран.",
                            "Ошибка. Автомобиль не выбран."));
        }
        autombl = null;
        automblNe = null;
        refreshAutomblLst();
    }

    public void saveAutombl() {
        if (autombl != null) {
            crbody = null;
            motor = null;
            trnsmsn = null;
            if (automblStatus == AUTOMBL_STATUS_NEW) {
                clearCrbodyIdLst();
                clearMotorIdLst();
                clearTrnsmsnIdLst();
                autombl.setAutofctrFk(autofctr);
                if (crbdtypeIdx != null) {
                    if (fillCrbodyIdLst()) {
                        crbody = hardwareStore.obtainCrbody(crbodyIdLst);
                        if (crbody != null) {
                            autombl.setCrbodyFk(crbody);
                        }
                    }
                }
                if (motorIdx != null) {
                    if (fillMotorIdLst()) {
                        motor = hardwareStore.obtainMotor(motorIdLst);
                        if (motor != null) {
                            autombl.setMotorFk(motor);
                        }
                    }
                }
                if (trnsmsnIdx != null) {
                    if (fillTrnsmsnIdLst()) {
                        trnsmsn = hardwareStore.obtainTrnsmsn(trnsmsnIdLst);
                        if (trnsmsn != null) {
                            autombl.setTrnsmsnFk(trnsmsn);
                        }
                    }
                }
                autombl.setStactive(Boolean.TRUE);
                automblFacade.create(autombl);
                //autombl = automblNe;
                if (autombl.getAutomblId() != null) {
                    if (crbody != null) {
                        crbody = crbodyFacade.find(crbody.getCrbodyId());
                        //crbody.getAutomblCollection().add(autombl);
                        crbody.setCrbdUse(autombl.getAutomblId());
                        crbodyFacade.edit(crbody);
                    }
                    if (motor != null) {
                        motor = motorFacade.find(motor.getMotorId());
                        //motor.getAutomblCollection().add(autombl);
                        motor.setMtrUse(autombl.getAutomblId());
                        motorFacade.edit(motor);
                    }
                    if (trnsmsn != null) {
                        trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                        //trnsmsn.getAutomblCollection().add(autombl);
                        trnsmsn.setTrnsUse(autombl.getAutomblId());
                        trnsmsnFacade.edit(trnsmsn);
                    }
                }
                refreshAutomblLst();
                dlgClose();
            }
            if (automblStatus == AUTOMBL_STATUS_EDIT) {
                automblNe = automblFacade.find(autombl.getAutomblId());
                automblNe.setAtdsc(autombl.getAtdsc());
                automblNe.setAtmodel(autombl.getAtmodel());
                automblNe.setAutofctrFk(autombl.getAutofctrFk());
                automblNe.setCrbodyFk(autombl.getCrbodyFk());
                automblNe.setMotorFk(autombl.getMotorFk());
                automblNe.setStactive(autombl.getStactive());
                automblNe.setTrnsmsnFk(autombl.getTrnsmsnFk());
                if (crbdtypeIdx != null) {
                    if (automblNe.getCrbodyFk() != null) {
                        if (!automblNe.getCrbodyFk().getCrbodyId().equals(crbdtypeIdx)) {
                            crbody = crbodyFacade.find(automblNe.getCrbodyFk().getCrbodyId());
                            //crbody.getAutomblCollection().remove(automblNe);
                            if (crbody != null) {
                                crbody.setCrbdUse(null);
                                crbodyFacade.edit(crbody);
                                crbody = crbodyFacade.find(crbody.getCrbodyId());
                                hardwareStore.toStoreCrbody(crbody);
                            }
                            crbody = null;
                            automblNe.setCrbodyFk(null);
                            if (fillCrbodyIdLst()) {
                                crbody = hardwareStore.obtainCrbody(crbodyIdLst);
                                if (crbody != null) {
                                    crbody = crbodyFacade.find(crbody.getCrbodyId());
                                    if (crbody != null) {
                                        automblNe.setCrbodyFk(crbody);
                                        crbody.setCrbdUse(automblNe.getAutomblId());
                                        crbodyFacade.edit(crbody);
                                    }
                                }
                            }
                        }
                    } else {
                        if (fillCrbodyIdLst()) {
                            crbody = hardwareStore.obtainCrbody(crbodyIdLst);
                            if (crbody != null) {
                                crbody = crbodyFacade.find(crbody.getCrbodyId());
                                if (crbody != null) {
                                    //crbody.getAutomblCollection().add(automblNe);
                                    crbody.setCrbdUse(automblNe.getAutomblId());
                                    crbodyFacade.edit(crbody);
                                    crbody = crbodyFacade.find(crbody.getCrbodyId());
                                    automblNe.setCrbodyFk(crbody);
                                }
                            }
                        }
                    }
                } else {
                    if (automblNe.getCrbodyFk() != null) {
                        crbody = crbodyFacade.find(automblNe.getCrbodyFk().getCrbodyId());
                        if (crbody != null) {
                            crbody.setCrbdUse(null);
                            //crbody.getAutomblCollection().remove(automblNe);
                            crbodyFacade.edit(crbody);
                            automblNe.setCrbodyFk(null);
                            crbody = crbodyFacade.find(crbody.getCrbodyId());
                            hardwareStore.toStoreCrbody(crbody);
                        }
                    }
                }
                if (motorIdx != null) {
                    if (automblNe.getMotorFk() != null) {
                        if (!automblNe.getMotorFk().getMotorId().equals(motorIdx)) {
                            motor = motorFacade.find(automblNe.getMotorFk().getMotorId());
                            //motor.getAutomblCollection().remove(automblNe);
                            if (motor != null) {
                                motor.setMtrUse(null);
                                motorFacade.edit(motor);
                                motor = motorFacade.find(motor.getMotorId());
                                hardwareStore.toStoreMotor(motor);
                            }
                            motor = null;
                            automblNe.setMotorFk(null);
                            if (fillMotorIdLst()) {
                                motor = hardwareStore.obtainMotor(motorIdLst);
                                if (motor != null) {
                                    motor = motorFacade.find(motor.getMotorId());
                                    if (motor != null) {
                                        automblNe.setMotorFk(motor);
                                        motor.setMtrUse(automblNe.getAutomblId());
                                        motorFacade.edit(motor);
                                    }
                                }
                            }
                        }
                    } else {
                        if (fillMotorIdLst()) {
                            motor = hardwareStore.obtainMotor(motorIdLst);
                            if (motor != null) {
                                motor = motorFacade.find(motor.getMotorId());
                                if (motor != null) {
                                    //motor.getAutomblCollection().add(automblNe);
                                    motor.setMtrUse(automblNe.getAutomblId());
                                    motorFacade.edit(motor);
                                    motor = motorFacade.find(motor.getMotorId());
                                    automblNe.setMotorFk(motor);
                                }
                            }
                        }
                    }
                } else {
                    if (automblNe.getMotorFk() != null) {
                        motor = motorFacade.find(automblNe.getMotorFk().getMotorId());
                        if (motor != null) {
                            motor.setMtrUse(null);
                            //motor.getAutomblCollection().remove(automblNe);
                            motorFacade.edit(motor);
                            automblNe.setMotorFk(null);
                            motor = motorFacade.find(motor.getMotorId());
                            hardwareStore.toStoreMotor(motor);
                        }
                    }
                }
                if (trnsmsnIdx != null) {
                    if (automblNe.getTrnsmsnFk() != null) {
                        if (!automblNe.getTrnsmsnFk().getTrnsmsnId().equals(trnsmsnIdx)) {
                            trnsmsn = trnsmsnFacade.find(automblNe.getTrnsmsnFk().getTrnsmsnId());
                            //trnsmsn.getAutomblCollection().remove(automblNe);
                            if (trnsmsn != null) {
                                trnsmsn.setTrnsUse(null);
                                trnsmsnFacade.edit(trnsmsn);
                                trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                                hardwareStore.toStoreTrnsmsn(trnsmsn);
                            }
                            trnsmsn = null;
                            automblNe.setTrnsmsnFk(null);
                            if (fillTrnsmsnIdLst()) {
                                trnsmsn = hardwareStore.obtainTrnsmsn(trnsmsnIdLst);
                                if (trnsmsn != null) {
                                    trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                                    if (trnsmsn != null) {
                                        automblNe.setTrnsmsnFk(trnsmsn);
                                        trnsmsn.setTrnsUse(automblNe.getAutomblId());
                                        trnsmsnFacade.edit(trnsmsn);
                                    }
                                }
                            }
                        }
                    } else {
                        if (fillTrnsmsnIdLst()) {
                            trnsmsn = hardwareStore.obtainTrnsmsn(trnsmsnIdLst);
                            if (trnsmsn != null) {
                                trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                                if (trnsmsn != null) {
                                    //trnsmsn.getAutomblCollection().add(automblNe);
                                    trnsmsn.setTrnsUse(automblNe.getAutomblId());
                                    trnsmsnFacade.edit(trnsmsn);
                                    trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                                    automblNe.setTrnsmsnFk(trnsmsn);
                                }
                            }
                        }
                    }
                } else {
                    if (automblNe.getTrnsmsnFk() != null) {
                        trnsmsn = trnsmsnFacade.find(automblNe.getTrnsmsnFk().getTrnsmsnId());
                        if (trnsmsn != null) {
                            trnsmsn.setTrnsUse(null);
                            //trnsmsn.getAutomblCollection().remove(automblNe);
                            trnsmsnFacade.edit(trnsmsn);
                            automblNe.setTrnsmsnFk(null);
                            trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                            hardwareStore.toStoreTrnsmsn(trnsmsn);
                        }
                    }
                }
                automblFacade.edit(automblNe);
                refreshAutomblLst();
                autombl = automblFacade.find(automblNe.getAutomblId());
                dlgClose();
            }
            crbody = null;
            motor = null;
            trnsmsn = null;
            //refreshAutomblLst();
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Автомобиль не выбран.",
                            "Ошибка. Автомобиль не выбран."));
        }
    }

    private void defineSelIdx() {
        if (autombl != null) {
            if (autombl.getCrbodyFk() != null) {
                if (autombl.getCrbodyFk().getCrbdtypeFk() != null) {
                    crbdtypeIdx = autombl.getCrbodyFk().getCrbdtypeFk().getCrbdtypeId();
                }
                if (autombl.getCrbodyFk().getCrbdclrsFk() != null) {
                    crbdclrIdx = autombl.getCrbodyFk().getCrbdclrsFk().getCrbdclrsId();
                }
            }
            if (autombl.getMotorFk() != null) {
                motorIdx = autombl.getMotorFk().getMotorId();
                if (autombl.getMotorFk().getMtrtypeFk() != null) {
                    mtrtypeIdx = autombl.getMotorFk().getMtrtypeFk().getMtrtypeId();
                }
            }
            if (autombl.getTrnsmsnFk() != null) {
                trnsmsnIdx = autombl.getTrnsmsnFk().getTrnsmsnId();
                if (autombl.getTrnsmsnFk().getTrnstypeFk() != null) {
                    trnstypeIdx = autombl.getTrnsmsnFk().getTrnstypeFk().getTrnstypeId();
                }
            }
        }
    }

    private boolean fillTrnsmsnIdLst() {
        boolean trnsmsnIdLstFilled = false;
        for (Trnsmsn selTrns : fromStoreTrnsmsnMap.values()) {
            if (selTrns.getTrnsmsnId().equals(trnsmsnIdx)) {
                trnsmsnIdLst.add(selTrns.getTrnsmsnId());
                trnsmsnIdLstFilled = true;
            }
        }
        return trnsmsnIdLstFilled;
    }

    private boolean fillMotorIdLst() {
        boolean motorIdLstFilled = false;
        for (Motor selMotor : fromStoreMotorMap.values()) {
            if (selMotor.getMotorId().equals(motorIdx)) {
                motorIdLst.add(selMotor.getMotorId());
                motorIdLstFilled = true;
            }
        }
        return motorIdLstFilled;
    }

    private boolean fillCrbodyIdLst() {
        boolean crbodyIdLstFilled = false;
        if (crbdclrIdx != null) {
            for (Crbody selCrbd : fromStoreCrbodyMap.values()) {
                if ((selCrbd.getCrbdtypeFk().getCrbdtypeId().equals(crbdtypeIdx)) && (selCrbd.getCrbdclrsFk().getCrbdclrsId().equals(crbdclrIdx))) {
                    crbodyIdLst.add(selCrbd.getCrbodyId());
                }
            }
            if ((crbodyIdLst != null) && (!crbodyIdLst.isEmpty())) {
                crbodyIdLstFilled = true;
            }
        } else {
            for (Crbody selCrbd : fromStoreCrbodyMap.values()) {
                if ((selCrbd.getCrbdtypeFk().getCrbdtypeId().equals(crbdtypeIdx))) {
                    crbodyIdLst.add(selCrbd.getCrbodyId());
                }
            }
            if ((crbodyIdLst != null) && (!crbodyIdLst.isEmpty())) {
                crbodyIdLstFilled = true;
            }
        }
        return crbodyIdLstFilled;
    }

    private void prepareDetails(Autombl autombl) {
        if (autombl != null) {
            if ((autombl.getCrbodyFk() != null) && (autombl.getCrbodyFk().getCrbdclrsFk() != null) && (autombl.getCrbodyFk().getCrbdclrsFk().getCrbdclrsId() != null)) {
                setCrbdclrIdx(autombl.getCrbodyFk().getCrbdclrsFk().getCrbdclrsId());
                //System.err.println("setCrbdclrIdx " + getCrbdclrIdx());
            }
            if ((autombl.getCrbodyFk() != null) && (autombl.getCrbodyFk().getCrbdtypeFk() != null) && (autombl.getCrbodyFk().getCrbdtypeFk().getCrbdtypeId() != null)) {
                setCrbdtypeIdx(autombl.getCrbodyFk().getCrbdtypeFk().getCrbdtypeId());
                //System.err.println("setCrbdtypeIdx " + getCrbdtypeIdx());
            }
            if ((autombl.getMotorFk() != null) && (autombl.getMotorFk().getMtrtypeFk() != null) && (autombl.getMotorFk().getMtrtypeFk().getMtrtypeId() != null)) {
                setMtrtypeIdx(autombl.getMotorFk().getMtrtypeFk().getMtrtypeId());
                //System.err.println("setMtrtypeIdx " + getMtrtypeIdx());
            }
            if ((autombl.getTrnsmsnFk() != null) && (autombl.getTrnsmsnFk().getTrnstypeFk() != null) && (autombl.getTrnsmsnFk().getTrnstypeFk().getTrnstypeId() != null)) {
                setTrnstypeIdx(autombl.getTrnsmsnFk().getTrnstypeFk().getTrnstypeId());
                //System.err.println("setTrnstypeIdx " + getTrnstypeIdx());
            }
            if ((autombl.getMotorFk() != null) && (autombl.getMotorFk().getMotorId() != null)) {
                setMotorIdx(autombl.getMotorFk().getMotorId());
                //System.err.println("setMotorIdx " + getMotorIdx());
            }
            if ((autombl.getTrnsmsnFk() != null) && (autombl.getTrnsmsnFk().getTrnsmsnId() != null)) {
                setTrnsmsnIdx(autombl.getTrnsmsnFk().getTrnsmsnId());
                //System.err.println("setTrnsmsnIdx " + getTrnsmsnIdx());
            }
            if ((autombl.getCrbodyFk() != null) && (autombl.getCrbodyFk().getCrbodyId() != null)) {
                crbodyIdLst.add(autombl.getCrbodyFk().getCrbodyId());
                //System.err.println("crbodyIdLst " + autombl.getCrbodyFk().getCrbodyId());
            }
            if (motorIdx != null) {
                motorIdLst.add(motorIdx);
                //System.err.println("motorIdx " + motorIdx);
            }
            if (trnsmsnIdx != null) {
                trnsmsnIdLst.add(trnsmsnIdx);
                //System.err.println("trnsmsnIdx " + trnsmsnIdx);
            }
        }
    }

    private void prepareElements() {
        prepareSelElements();
        if (chkObtainedElements()) {
            fillCrbodies();
            fillMotors();
            fillTrnsmsn();
        }
        //crbdtypeLst = crbdtypeFacade.findAll();
        crbdtypeLst = actualCrbdtypeLst();
        //crbdclrsLst = crbdclrsFacade.findAll();
        crbdclrsLst = actualCrbdclrsLst();
        motorLst = motorFacade.findAll();
        motorLst = actualMotorLst();
        //trnsmsnLst = trnsmsnFacade.findAll();
        trnsmsnLst = actualTrnsmsnLst();
    }

    public List<Crbody> actualCrbodyLst() {
        return crbodyFacade.findAll();
    }

    public List<Crbdtype> actualCrbdtypeLst() {
        return crbdtypeFacade.findAll();
    }

    public List<Crbdclrs> actualCrbdclrsLst() {
        return crbdclrsFacade.findAll();
    }

    public List<Motor> actualMotorLst() {
        return motorFacade.findAll();
    }

    public List<Mtrtype> actualMtrtypesLst() {
        return mtrtypeFacade.findAll();
    }

    public List<Trnsmsn> actualTrnsmsnLst() {
        return trnsmsnFacade.findAll();
    }

    public List<Trnstype> actualTrnstypesLst() {
        return trnstypeFacade.findAll();
    }

    private boolean chkCrbodyLst() {
        return (crbodyLst != null && !crbodyLst.isEmpty());
    }

    private void prepareSelElements() {
        clearSelCarBodyTypeMap();
        clearSelCarBodyColorMap();
        clearSelMotorTypeMap();
        clearSelMotorDscMap();
        clearSelTrnsmsnTypeMap();
        clearSelTrnsmsnDscMap();

    }

    private void fillCrbodies() {
        for (Crbody tmpCrbd : fromStoreCrbodyMap.values()) {
            selCarBodyTypeMap.put(tmpCrbd.getCrbdtypeFk().getBdtype(), tmpCrbd.getCrbdtypeFk().getCrbdtypeId());
            selCarBodyColorMap.put(tmpCrbd.getCrbdclrsFk().getColor(), tmpCrbd.getCrbodyId());
        }
    }

    private void fillMotors() {
        for (Motor tmpMotor : fromStoreMotorMap.values()) {
            selMotorTypeMap.put(tmpMotor.getMtrtypeFk().getTypemtr(), tmpMotor.getMtrtypeFk().getMtrtypeId());
            selMotorDscMap.put(tmpMotor.getMtdsc(), tmpMotor.getMotorId());
        }
    }

    private void fillTrnsmsn() {
        for (Trnsmsn tmpTrns : fromStoreTrnsmsnMap.values()) {
            selTrnsmsnTypeMap.put(tmpTrns.getTrnstypeFk().getTypetrns(), tmpTrns.getTrnstypeFk().getTrnstypeId());
            selTrnsmsnDscMap.put(tmpTrns.getTrnsdsc(), tmpTrns.getTrnsmsnId());
        }
    }

    private void clearSelTrnsmsnDscMap() {
        if (selTrnsmsnDscMap == null) {
            selTrnsmsnDscMap = new HashMap<>();
        } else {
            selTrnsmsnDscMap.clear();
        }
    }

    private void clearSelTrnsmsnTypeMap() {
        if (selTrnsmsnTypeMap == null) {
            selTrnsmsnTypeMap = new HashMap<>();
        } else {
            selTrnsmsnTypeMap.clear();
        }
    }

    private void clearSelMotorDscMap() {
        if (selMotorDscMap == null) {
            selMotorDscMap = new HashMap<>();
        } else {
            selMotorDscMap.clear();
        }
    }

    private void clearSelMotorTypeMap() {
        if (selMotorTypeMap == null) {
            selMotorTypeMap = new HashMap<>();
        } else {
            selMotorTypeMap.clear();
        }
    }

    private void clearSelCarBodyColorMap() {
        if (selCarBodyColorMap == null) {
            //System.err.println("clearSelCarBodyColorMap selCarBodyColorMap = null? " + selCarBodyColorMap == null);
            selCarBodyColorMap = new HashMap<>();
            //System.err.println("clearSelCarBodyColorMap selCarBodyColorMap = null? " + selCarBodyColorMap == null);
        } else {
            selCarBodyColorMap.clear();
        }
    }

    private void clearSelCarBodyTypeMap() {
        if (selCarBodyTypeMap == null) {
            //System.err.println("clearSelCarBodyTypeMap selCarBodyTypeMap = null? " + selCarBodyTypeMap == null);
            selCarBodyTypeMap = new HashMap<>();
            //System.err.println("clearSelCarBodyTypeMap selCarBodyTypeMap = null? " + selCarBodyTypeMap == null);
        } else {
            selCarBodyTypeMap.clear();
        }
    }

    private boolean chkObtainedElements() {
        boolean elementsChecked = false;
        if ((fromStoreCrbodyMap != null) && (!fromStoreCrbodyMap.isEmpty())
                && (fromStoreMotorMap != null) && (!fromStoreMotorMap.isEmpty())
                && (fromStoreTrnsmsnMap != null) && (!fromStoreTrnsmsnMap.isEmpty())) {
            elementsChecked = true;
        }
        return elementsChecked;
    }

    private void obtainCarElements() {
        fromStoreCrbodyMap = hardwareStore.getCrbodiesMap();
        fromStoreMotorMap = hardwareStore.getMotorsMap();
        fromStoreTrnsmsnMap = hardwareStore.getTrnsmsnsMap();
//        System.err.println("obtainCarElements");
//        System.err.println("fromStoreCrbodyMap != null " + fromStoreCrbodyMap != null + " " + fromStoreCrbodyMap.size());
//        System.err.println("fromStoreMotorMap != null " + fromStoreMotorMap != null + " " + fromStoreMotorMap.size());
//        System.err.println("fromStoreTrnsmsnMap != null " + fromStoreTrnsmsnMap != null +  " " + fromStoreTrnsmsnMap.size());
    }

    protected void obtainCrbodyFromFctr(Crbody crbody) {
        if (crbody != null) {
            if (crbody.getCrbodyId() != null) {
                fromStoreCrbodyMap.put(crbody.getCrbodyId(), crbody);
                fillCrbodies();
            }
        }
    }

    protected void obtainMotorFromFctr(Motor motor) {
        if (motor != null) {
            if (motor.getMotorId() != null) {
                fromStoreMotorMap.put(motor.getMotorId(), motor);
                fillMotors();
            }
        }
    }

    protected void obtainTrnsmsnFromFctr(Trnsmsn trnsmsn) {
        if (trnsmsn != null) {
            if (trnsmsn.getTrnsmsnId() != null) {
                fromStoreTrnsmsnMap.put(trnsmsn.getTrnsmsnId(), trnsmsn);
                fillTrnsmsn();
            }
        }
    }

    private void clearDetails() {
        crbdclrIdx = null;
        crbdtypeIdx = null;
        mtrtypeIdx = null;
        trnstypeIdx = null;
        motorIdx = null;
        trnsmsnIdx = null;
        clearTrnsmsnIdLst();
        clearMotorIdLst();
        clearCrbodyIdLst();
    }

    private void clearTrnsmsnIdLst() {
        if (trnsmsnIdLst == null) {
            trnsmsnIdLst = new ArrayList<>();
        } else {
            trnsmsnIdLst.clear();
        }
    }

    private void clearMotorIdLst() {
        if (motorIdLst == null) {
            motorIdLst = new ArrayList<>();
        } else {
            motorIdLst.clear();
        }
    }

    private void clearCrbodyIdLst() {
        if (crbodyIdLst == null) {
            crbodyIdLst = new ArrayList<>();
        } else {
            crbodyIdLst.clear();
        }
    }

    //Сделать метод закрытия отправляющий все неиспользованные запчасти на склад //Загрузить данные по свободным деталям из БД
    private void toStoreAllDetails() {
        if ((fromStoreCrbodyMap != null) && (!fromStoreCrbodyMap.isEmpty())) {
            for (Crbody crbd : fromStoreCrbodyMap.values()) {
                if (crbd.getCrbdUse() == null) {
                    hardwareStore.toStoreCrbody(crbd);
                }
            }
        }
        if ((fromStoreMotorMap != null) && (!fromStoreMotorMap.isEmpty())) {
            for (Motor mtr : fromStoreMotorMap.values()) {
                if (mtr.getMtrUse() == null) {
                    hardwareStore.toStoreMotor(mtr);
                }
            }
        }
        if ((fromStoreTrnsmsnMap != null) && (!fromStoreTrnsmsnMap.isEmpty())) {
            for (Trnsmsn trns : fromStoreTrnsmsnMap.values()) {
                if (trns.getTrnsUse() == null) {
                    hardwareStore.toStoreTrnsmsn(trns);
                }
            }
        }
    }

    public void defAutomblStatusNew() {
        automblStatus = AUTOMBL_STATUS_NEW;
    }

    public void defAutomblStatusEdit() {
        automblStatus = AUTOMBL_STATUS_EDIT;
    }

    public Boolean deleteRESTAutoMbl(Integer automblId) {
        boolean automblFinded = false;
        if (automblId != null) {
            Autombl autombl = automblFacade.find(automblId);
            if (autombl != null) {
                this.autombl = autombl;
                deleteAutombl();
                automblFinded = true;
            }
        }
        return automblFinded;
    }

    public void showAutombDlg() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 700);
        options.put("height", 570);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("/dlgs/neautombl", options, null);
    }

    public void cancelAutomblDlg() {
        if (autombl != null) {
            if (autombl.getAutomblId() == null) {
                autombl = null;
            }
            clearDetails();
            dlgClose();
        }
    }

    public void dlgClose() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    @PostConstruct
    private void init() {
        crbodyCount = 0;
        automblNeHdr = "";
        clearDetails();
        autofctr = autofctrFacade.findAutofctr().get(0);
    }

    @PreDestroy
    private void close() {
        toStoreAllDetails();
    }

}
