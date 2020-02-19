/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.jsf.pgs;

import com.autofactory.dao.AutofctrFacade;
import com.autofactory.dao.CrbdclrsFacade;
import com.autofactory.dao.CrbdtypeFacade;
import com.autofactory.dao.CrbodyFacade;
import com.autofactory.dao.MotorFacade;
import com.autofactory.dao.MtrtypeFacade;
import com.autofactory.dao.TrnsmsnFacade;
import com.autofactory.dao.TrnstypeFacade;
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

/**
 *
 * @author Eugene Sidorov
 */
@Named
@SessionScoped
public class ItemsFactoryView implements Serializable {

    @Inject
    private AutofctrFacade autofctrFacade;
    @Inject
    private CrbodyFacade crbodyFacade;
    @Inject
    private MotorFacade motorFacade;
    @Inject
    private TrnsmsnFacade trnsmsnFacade;
    @Inject
    private CrbdclrsFacade crbdclrsFacade;
    @Inject
    private CrbdtypeFacade crbdtypeFacade;
    @Inject
    private MtrtypeFacade mtrtypeFacade;
    @Inject
    private TrnstypeFacade trnstypeFacade;

    @Inject
    private HardwareStore hardwareStore;

    @Inject
    private AutoMblView autoMblView;

    @Inject
    private JsfUtils jsfUtils;

    private Crbody crbody;
    private List<Crbody> crbodyLst;
    private List<Crbody> crbodyLstFltr;
    private Crbdtype crbdtype;
    private List<Crbdtype> crbdtypeLst;
    private Map<String, Integer> crbdtypeSelMap;
    private Map<Integer, Crbdtype> crbdtypeFndMap;
    private Integer crbdtypeIdx;
    private Crbdclrs crbdclrs;
    private List<Crbdclrs> crbdclrsLst;
    private Map<String, Integer> crbdclrsSelMap;
    private Map<Integer, Crbdclrs> crbdclrsFndMap;
    private Integer crbdclrsIdx;
    private Crbody crbodyNe;

    private Motor motor;
    private List<Motor> motorLst;
    private List<Motor> motorLstFltr;
//    private Map<String, Integer> motorSelMap;
//    private Map<Integer, Motor> motorFndMap;
//    private Integer motorIdx;
    private Mtrtype mtrtype;
    private List<Mtrtype> mtrtypeLst;
    private Map<String, Integer> mtrtypeSelMap;
    private Map<Integer, Mtrtype> mtrtypeFndMap;
    private Integer mtrtypeIdx;
    private Motor motorNe;

    private Trnsmsn trnsmsn;
    private List<Trnsmsn> trnsmsnLst;
    private List<Trnsmsn> trnsmsnLstFltr;
//    private Integer trnsmsnIdx;
//    private Map<String, Integer> trnsmsnSelMap;
//    private Map<Integer, Trnsmsn> trnsmsnFndMap;
    private Trnstype trnstype;
    private List<Trnstype> trnstypeLst;
    private Integer trnstypeIdx;
    private Map<String, Integer> trnstypeSelMap;
    private Map<Integer, Trnstype> trnstypeFndMap;
    private Trnsmsn trnsmsnNe;

    private Map<Integer, Crbody> fromStoreCrbodyMap;
    private Map<Integer, Motor> fromStoreMotorMap;
    private Map<Integer, Trnsmsn> fromStoreTrnsmsnMap;

    private static final int CRBODY_STATUS_NEW = 1;
    private static final int CRBODY_STATUS_EDIT = 2;
    private static final int MOTOR_STATUS_NEW = 3;
    private static final int MOTOR_STATUS_EDIT = 4;
    private static final int TRNSMSN_STATUS_NEW = 5;
    private static final int TRNSMSN_STATUS_EDIT = 6;

    private static final String HDR_CRBODY_NEW = "Новый кузов";
    private static final String HDR_MOTOR_NEW = "Новый двигатель";
    private static final String HDR_TRNSMSN_NEW = "Новая трансмиссия";
    private static final String HDR_EDIT = "Редактирование";

    private int crbodyNeStatus = 0;
    private int motorNeStatus = 0;
    private int trnsmsnNeStatus = 0;

    private String crbodyNeHdr;
    private String motorNeHdr;
    private String trnsmsnNeHdr;

    public String getCrbodyNeHdr() {
        return crbodyNeHdr;
    }

    public String getMotorNeHdr() {
        return motorNeHdr;
    }

    public String getTrnsmsnNeHdr() {
        return trnsmsnNeHdr;
    }

    public Crbody getCrbody() {
        return crbody;
    }

    public void setCrbody(Crbody crbody) {
        this.crbody = crbody;
    }

    public List<Crbody> getCrbodyLst() {
        obtainCrbodyies();
        return crbodyLst;
    }

    public void setCrbodyLst(List<Crbody> crbodyLst) {
        this.crbodyLst = crbodyLst;
    }

    public Crbdtype getCrbdtype() {
        return crbdtype;
    }

    public void setCrbdtype(Crbdtype crbdtype) {
        this.crbdtype = crbdtype;
    }

    public Map<String, Integer> getCrbdtypeSelMap() {
        return crbdtypeSelMap;
    }

    public List<Crbdtype> getCrbdtypeLst() {
        return crbdtypeLst;
    }

    public void setCrbdtypeLst(List<Crbdtype> crbdtypeLst) {
        this.crbdtypeLst = crbdtypeLst;
    }

    public void setCrbdtypeSelMap(Map<String, Integer> crbdtypeSelMap) {
        this.crbdtypeSelMap = crbdtypeSelMap;
    }

    public Integer getCrbdtypeIdx() {
        return crbdtypeIdx;
    }

    public void setCrbdtypeIdx(Integer crbdtypeIdx) {
        this.crbdtypeIdx = crbdtypeIdx;
    }

    public Crbdclrs getCrbdclrs() {
        return crbdclrs;
    }

    public void setCrbdclrs(Crbdclrs crbdclrs) {
        this.crbdclrs = crbdclrs;
    }

    public List<Crbdclrs> getCrbdclrseLst() {
        return crbdclrsLst;
    }

    public void setCrbdclrseLst(List<Crbdclrs> crbdclrseLst) {
        this.crbdclrsLst = crbdclrseLst;
    }

    public Integer getCrbdclrsIdx() {
        return crbdclrsIdx;
    }

    public void setCrbdclrsIdx(Integer crbdclrsIdx) {
        this.crbdclrsIdx = crbdclrsIdx;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public List<Motor> getMotorLst() {
        obtainMotors();
        return motorLst;
    }

    public void setMotorLst(List<Motor> motorLst) {
        this.motorLst = motorLst;
    }

//    public Integer getMotorIdx() {
//        return motorIdx;
//    }
//
//    public void setMotorIdx(Integer motorIdx) {
//        this.motorIdx = motorIdx;
//    }
    public List<Mtrtype> getMtrtypeLst() {
        return mtrtypeLst;
    }

    public void setMtrtypeLst(List<Mtrtype> mtrtypeLst) {
        this.mtrtypeLst = mtrtypeLst;
    }

//    public Map<String, Integer> getMotorSelMap() {
//        return motorSelMap;
//    }
//
//    public void setMotorSelMap(Map<String, Integer> motorSelMap) {
//        this.motorSelMap = motorSelMap;
//    }
    public Map<String, Integer> getMtrtypeSelMap() {
        return mtrtypeSelMap;
    }

    public void setMtrtypeSelMap(Map<String, Integer> mtrtypeSelMap) {
        this.mtrtypeSelMap = mtrtypeSelMap;
    }

    public Integer getMtrtypeIdx() {
        return mtrtypeIdx;
    }

    public void setMtrtypeIdx(Integer mtrtypeIdx) {
        this.mtrtypeIdx = mtrtypeIdx;
    }

    public Trnsmsn getTrnsmsn() {
        return trnsmsn;
    }

    public void setTrnsmsn(Trnsmsn trnsmsn) {
        this.trnsmsn = trnsmsn;
    }

    public List<Trnsmsn> getTrnsmsnLst() {
        obtainTrnsmsns();
        return trnsmsnLst;
    }

    public void setTrnsmsnLst(List<Trnsmsn> trnsmsnLst) {
        this.trnsmsnLst = trnsmsnLst;
    }

//    public Integer getTrnsmsnIdx() {
//        return trnsmsnIdx;
//    }
//
//    public void setTrnsmsnIdx(Integer trnsmsnIdx) {
//        this.trnsmsnIdx = trnsmsnIdx;
//    }
    public Trnstype getTrnstype() {
        return trnstype;
    }

    public void setTrnstype(Trnstype trnstype) {
        this.trnstype = trnstype;
    }

    public List<Trnstype> getTrnstypeLst() {
        return trnstypeLst;
    }

    public void setTrnstypeLst(List<Trnstype> trnstypeLst) {
        this.trnstypeLst = trnstypeLst;
    }

    public Integer getTrnstypeIdx() {
        return trnstypeIdx;
    }

    public void setTrnstypeIdx(Integer trnstypeIdx) {
        this.trnstypeIdx = trnstypeIdx;
    }

    public List<Crbody> getCrbodyLstFltr() {
        return crbodyLstFltr;
    }

    public void setCrbodyLstFltr(List<Crbody> crbodyLstFltr) {
        this.crbodyLstFltr = crbodyLstFltr;
    }

    public List<Motor> getMotorLstFltr() {
        return motorLstFltr;
    }

    public void setMotorLstFltr(List<Motor> motorLstFltr) {
        this.motorLstFltr = motorLstFltr;
    }

    public List<Trnsmsn> getTrnsmsnLstFltr() {
        return trnsmsnLstFltr;
    }

    public void setTrnsmsnLstFltr(List<Trnsmsn> trnsmsnLstFltr) {
        this.trnsmsnLstFltr = trnsmsnLstFltr;
    }

    public Map<Integer, Crbody> getFromStoreCrbodyMap() {
        return fromStoreCrbodyMap;
    }

    public void setFromStoreCrbodyMap(Map<Integer, Crbody> fromStoreCrbodyMap) {
        this.fromStoreCrbodyMap = fromStoreCrbodyMap;
    }

    public Map<Integer, Motor> getFromStoreMotorMap() {
        return fromStoreMotorMap;
    }

    public void setFromStoreMotorMap(Map<Integer, Motor> fromStoreMotorMap) {
        this.fromStoreMotorMap = fromStoreMotorMap;
    }

    public Map<Integer, Trnsmsn> getFromStoreTrnsmsnMap() {
        return fromStoreTrnsmsnMap;
    }

    public void setFromStoreTrnsmsnMap(Map<Integer, Trnsmsn> fromStoreTrnsmsnMap) {
        this.fromStoreTrnsmsnMap = fromStoreTrnsmsnMap;
    }

    public AutoMblView getAutoMblView() {
        return autoMblView;
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

    public CrbdclrsFacade getCrbdclrsFacade() {
        return crbdclrsFacade;
    }

    public CrbdtypeFacade getCrbdtypeFacade() {
        return crbdtypeFacade;
    }

    public MtrtypeFacade getMtrtypeFacade() {
        return mtrtypeFacade;
    }

    public TrnstypeFacade getTrnstypeFacade() {
        return trnstypeFacade;
    }

    public void toBuildCarbody() {
        if (crbody != null) {
            if (fromStoreCrbodyMap.get(crbody.getCrbodyId()) == null) {
                autoMblView.obtainCrbodyFromFctr(crbody);
                //crbodyLst.remove(crbody);
            }
        }
    }

    public void toBuildMotor() {
        if (motor != null) {
            if (fromStoreMotorMap.get(motor.getMotorId()) == null) {
                autoMblView.obtainMotorFromFctr(motor);
                //motorLst.remove(motor);
            }
        }
    }

    public void toBuildTrnsmsn() {
        if (trnsmsn != null) {
            if (fromStoreTrnsmsnMap.get(trnsmsn.getTrnsmsnId()) == null) {
                autoMblView.obtainTrnsmsnFromFctr(trnsmsn);
                //motorLst.remove(motor);
            }
        }
    }

    private void obtainItemsFromStore() {
        fromStoreCrbodyMap = hardwareStore.getCrbodiesMap();
        fromStoreMotorMap = hardwareStore.getMotorsMap();
        fromStoreTrnsmsnMap = hardwareStore.getTrnsmsnsMap();
    }

    public void obtainCrbodyies() {
        fromStoreCrbodyMap = hardwareStore.getCrbodiesMap();
        crbodyLst = null;
        //crbodyLst = new ArrayList<>(fromStoreCrbodyMap.values());
        //crbodyLst = crbodyFacade.findUnUseCrbodies();
        refreshCrbodyLst();
    }

    public void refreshCrbodyLst() {
        crbodyLst = actulUnUseCrbodies();
        //crbodyLst = crbodyFacade.findUnUseCrbodies();
//        for (Crbody crbd : crbodyLst) {
//            //System.err.println("id " + crbd.getCrbodyId() + " type: " + crbd.getCrbdtypeFk().getBdtype() + " color " + crbd.getCrbdclrsFk().getColor());
//        }
    }

    public List<Crbody> actulUnUseCrbodies() {
        return crbodyFacade.findUnUseCrbodies();
    }

    public List<Motor> actulUnUseMotors() {
        return motorFacade.findUnUseMotors();
    }

    public List<Trnsmsn> actulUnUseTrnsmsns() {
        return trnsmsnFacade.findUnUseTrnsmsn();
    }

    public void obtainMotors() {
        fromStoreMotorMap = hardwareStore.getMotorsMap();
        motorLst = null;
        motorLst = actulUnUseMotors();
        //motorLst = new ArrayList<>(fromStoreMotorMap.values());
        //motorLst = motorFacade.findUnUseMotors();

    }

    public void refreshMotorLst() {
        motorLst = motorFacade.findUnUseMotors();
    }

    public void obtainTrnsmsns() {
        fromStoreTrnsmsnMap = hardwareStore.getTrnsmsnsMap();
        trnsmsnLst = null;
        trnsmsnLst = actulUnUseTrnsmsns();
        //trnsmsnLst = new ArrayList<>(fromStoreTrnsmsnMap.values());
        //trnsmsnLst = trnsmsnFacade.findUnUseTrnsmsn();
    }

    public void refreshTrnsmsnsLst() {
        trnsmsnLst = trnsmsnFacade.findUnUseTrnsmsn();
    }

    //Со склада на фабрику
    public void toFactoryCrbody() {
        if (crbody != null) {
            boolean crbodyfnd = false;
            Crbody crbodyToFactr = hardwareStore.obtainCrbody(new ArrayList<Integer>(crbody.getCrbodyId()));
            if (crbodyToFactr != null) {
                for (int i = 0; i < crbodyLst.size(); i++) {
                    if (crbodyLst.get(i).getCrbodyId().equals(crbodyToFactr.getCrbodyId())) {
                        crbodyLst.set(i, crbodyToFactr);
                        crbodyfnd = true;
                        break;
                    }
                }
                if (!crbodyfnd) {
                    crbodyLst.add(crbodyToFactr);
                }
                crbody = crbodyToFactr;
                fromStoreCrbodyMap.remove(crbody.getCrbodyId());
            }
        }
    }

    public void toFctrCrbody() {
        if (crbody != null) {
            Crbody crbodyToFactr = hardwareStore.obtainCrbody(new ArrayList<Integer>(crbody.getCrbodyId()));
            crbodyToFactr = null;
            fromStoreCrbodyMap.remove(crbody.getCrbodyId());
        }
    }

    public void toFactoryMotor() {
        if (motor != null) {
            boolean motorFnd = false;
            Motor motorToFactr = hardwareStore.obtainMotor(new ArrayList<Integer>(motor.getMotorId()));
            if (motorToFactr != null) {
                for (int i = 0; i < motorLst.size(); i++) {
                    if (motorLst.get(i).getMotorId().equals(motorToFactr.getMotorId())) {
                        motorLst.set(i, motorToFactr);
                        motorFnd = true;
                        break;
                    }
                }
                if (!motorFnd) {
                    motorLst.add(motorToFactr);
                }
                motor = motorToFactr;
                fromStoreMotorMap.remove(motor.getMotorId());
            }
        }
    }

    public void toFctrMotor() {
        if (motor != null) {
            Motor motorToFactr = hardwareStore.obtainMotor(new ArrayList<Integer>(motor.getMotorId()));
            motorToFactr = null;
            fromStoreMotorMap.remove(motor.getMotorId());
        }
    }

    public void toFactoryTrnsmsn() {
        if (trnsmsn != null) {
            boolean trnsmsnFnd = false;
            Trnsmsn trnsmsnToFactr = hardwareStore.obtainTrnsmsn(new ArrayList<Integer>(trnsmsn.getTrnsmsnId()));
            if (trnsmsnToFactr != null) {
                for (int i = 0; i < trnsmsnLst.size(); i++) {
                    if (trnsmsnLst.get(i).equals(trnsmsnToFactr)) {
                        trnsmsnLst.set(i, trnsmsnToFactr);
                        trnsmsnFnd = true;
                        break;
                    }
                }
                if (!trnsmsnFnd) {
                    trnsmsnLst.add(trnsmsnToFactr);
                }
                trnsmsn = trnsmsnToFactr;
                fromStoreTrnsmsnMap.remove(trnsmsn.getTrnsmsnId());
            }
        }
    }

    public void toFctrTrnsmsn() {
        if (trnsmsn != null) {
            Trnsmsn trnsmsnToFactr = hardwareStore.obtainTrnsmsn(new ArrayList<Integer>(trnsmsn.getTrnsmsnId()));
            trnsmsnToFactr = null;
            fromStoreTrnsmsnMap.remove(trnsmsn.getTrnsmsnId());
        }
    }

    //С фабрики на склад 
    public void toStoreCrbody() {
        if ((crbody != null) && (crbody.getCrbodyId() != null)) {
            if (hardwareStore.toStoreCrbody(crbody)) {
                fromStoreCrbodyMap.put(crbody.getCrbodyId(), crbody);
            }
        }
    }

    public void toStoreMotor() {
        if ((motor != null) && (motor.getMotorId() != null)) {
            if (hardwareStore.toStoreMotor(motor)) {
                fromStoreMotorMap.put(motor.getMotorId(), motor);
            }
        }
    }

    public void toStoreTrnsmsn() {
        if ((trnsmsn != null) && (trnsmsn.getTrnsmsnId() != null)) {
            if (hardwareStore.toStoreTrnsmsn(trnsmsn)) {
                fromStoreTrnsmsnMap.put(trnsmsn.getTrnsmsnId(), trnsmsn);
            }
        }
    }

    public void toStoreCrbody(Crbody crbody) {
        if ((crbody != null) && (crbody.getCrbodyId() != null)) {
            if (hardwareStore.toStoreCrbody(crbody)) {
                fromStoreCrbodyMap.put(crbody.getCrbodyId(), crbody);
            }
        }
    }

    public void toStoreMotor(Motor motor) {
        if ((motor != null) && (motor.getMotorId() != null)) {
            if (hardwareStore.toStoreMotor(motor)) {
                fromStoreMotorMap.put(motor.getMotorId(), motor);
            }
        }
    }

    public void toStoreTrnsmsn(Trnsmsn trnsmsn) {
        if ((trnsmsn != null) && (trnsmsn.getTrnsmsnId() != null)) {
            if (hardwareStore.toStoreTrnsmsn(trnsmsn)) {
                fromStoreTrnsmsnMap.put(trnsmsn.getTrnsmsnId(), trnsmsn);
            }
        }
    }

    private void clearCrbodyDetails() {
        crbdtypeIdx = null;
        crbdclrsIdx = null;
        crbdclrs = null;
        crbdtype = null;
//        clearCrbodyTypes();
//        clearCrbodyColors();
    }

    private void clearCrbdtypeSelMap() {
        if (crbdtypeSelMap == null) {
            crbdtypeSelMap = new HashMap<>();
        } else {
            crbdtypeSelMap.clear();
        }
    }

    private void clearCrbdtypeFndMap() {
        if (crbdtypeFndMap == null) {
            crbdtypeFndMap = new HashMap<>();
        } else {
            crbdtypeFndMap.clear();
        }
    }

    private void fillCrbdtypeSelFndMaps() {
        clearCrbdtypeSelMap();
        clearCrbdtypeFndMap();
        if ((crbdtypeLst == null) || (crbdtypeLst.isEmpty())) {
            crbdtypeLst = crbdtypeFacade.findAll();
        }
        if ((crbdtypeLst != null) && (!crbdtypeLst.isEmpty())) {
            for (Crbdtype crbdtp : crbdtypeLst) {
                crbdtypeSelMap.put(crbdtp.getBdtype(), crbdtp.getCrbdtypeId());
                crbdtypeFndMap.put(crbdtp.getCrbdtypeId(), crbdtp);
            }
        }
    }

    private void clearCrbdclrsSelMap() {
        if (crbdclrsSelMap == null) {
            crbdclrsSelMap = new HashMap<>();
        } else {
            crbdclrsSelMap.clear();
        }
    }

    private void clearCrbdclrsFndMap() {
        if (crbdclrsFndMap == null) {
            crbdclrsFndMap = new HashMap<>();
        } else {
            crbdclrsFndMap.clear();
        }
    }

    private void fillCrbdclrsSelFndMap() {
        clearCrbdclrsSelMap();
        clearCrbdclrsFndMap();
        if ((crbdclrsLst == null) || (crbdclrsLst.isEmpty())) {
            crbdclrsLst = crbdclrsFacade.findAll();
        }
        if ((crbdclrsLst != null) && (!crbdclrsLst.isEmpty())) {
            for (Crbdclrs clr : crbdclrsLst) {
                crbdclrsSelMap.put(clr.getColor(), clr.getCrbdclrsId());
                crbdclrsFndMap.put(clr.getCrbdclrsId(), clr);
            }
        }
    }

    private void prepareCrbodyElements() {
        if ((crbdtypeSelMap == null) || (crbdtypeSelMap.isEmpty()) || (crbdtypeFndMap == null) || (crbdtypeFndMap.isEmpty())) {
            fillCrbdtypeSelFndMaps();
        }
        if ((crbdclrsSelMap == null) || (crbdclrsSelMap.isEmpty()) || (crbdclrsFndMap == null) || (crbdclrsFndMap.isEmpty())) {
            fillCrbdclrsSelFndMap();
        }
    }

    //Создать
    public void createCrbody() {
        crbodyNeStatus = CRBODY_STATUS_NEW;
        crbodyNeHdr = HDR_CRBODY_NEW;
        //crbodyNe = new Crbody();
        crbody = new Crbody();
        crbody.setStactive(Boolean.TRUE);
        clearCrbodyDetails();
        prepareCrbodyElements();
        showCrbodyDlg();
    }

    public void createMotor() {
        motorNeStatus = MOTOR_STATUS_NEW;
        motorNeHdr = HDR_MOTOR_NEW;
        //motorNe = new Motor();
        motor = new Motor();
        motor.setStactive(Boolean.TRUE);
        clearMotorDetails();
        prepareMotorElements();
        showMotorDlg();
    }

    public void createTrnsmsn() {
        trnsmsnNeStatus = TRNSMSN_STATUS_NEW;
        trnsmsnNeHdr = HDR_TRNSMSN_NEW;
        //trnsmsnNe = new Trnsmsn();
        trnsmsn = new Trnsmsn();
        trnsmsn.setStactive(Boolean.TRUE);
        clearTrnsmsnDetails();
        prepareTrnsmsnElements();
        showTrnsmsnDlg();
    }

    //редактировать
    public void editCrbody() {
        if (crbody != null) {
            crbodyNeStatus = CRBODY_STATUS_EDIT;
            crbodyNeHdr = HDR_EDIT + " " + crbody.getCrbddsc();
            //crbodyNe = crbody;
            clearCrbodyDetails();
            prepareCrbodyDetails();
            prepareCrbodyElements();
            showCrbodyDlg();
        }
    }

    public void editMotor() {
        if (motor != null) {
            motorNeStatus = MOTOR_STATUS_EDIT;
            motorNeHdr = HDR_EDIT + " " + motor.getMtdsc();
            //motorNe = motor;
            clearMotorDetails();
            prepareMotorDetails();
            prepareMotorElements();
            showMotorDlg();
        }
    }

    public void editTrnsmsn() {
        if (trnsmsn != null) {
            trnsmsnNeStatus = TRNSMSN_STATUS_EDIT;
            trnsmsnNeHdr = HDR_EDIT + " " + trnsmsn.getTrnsmsnsn();
            //trnsmsnNe = trnsmsn;
            clearTrnsmsnDetails();
            prepareTrnsmsnDetails();
            prepareTrnsmsnElements();
            showTrnsmsnDlg();
        }
    }

    public void deleteCrbody() {
        if (crbody != null) {
            crbody = crbodyFacade.find(crbody.getCrbodyId());
            crbody.setStactive(Boolean.FALSE);
            crbodyFacade.edit(crbody);
            Crbody inStoreCrbody = hardwareStore.obtainCrbody(new ArrayList<Integer>(crbody.getCrbodyId()));
            inStoreCrbody = null;
            //crbodyLst.remove(crbody);
            fromStoreCrbodyMap.remove(crbody.getCrbodyId());
            crbody = null;
            refreshCrbodyLst();
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Кузов не выбран.",
                            "Ошибка. Кузов не выбран."));
        }
    }

    public void deleteMotor() {
        if (motor != null) {
            motor = motorFacade.find(motor.getMotorId());
            motor.setStactive(Boolean.FALSE);
            motorFacade.edit(motor);
            Motor inStoreMotor = hardwareStore.obtainMotor(new ArrayList<Integer>(motor.getMotorId()));
            inStoreMotor = null;
            //motorLst.remove(motor);
            fromStoreMotorMap.remove(motor.getMotorId());
//            motorFndMap.remove(motor.getMotorId());
//            motorSelMap.remove(motor.getMtdsc());
            motor = null;
            refreshMotorLst();
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Двигатель не выбран.",
                            "Ошибка. Двигатель не выбран."));
        }
    }

    public void deleteTrnsmsn() {
        if (trnsmsn != null) {
            trnsmsn = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
            trnsmsn.setStactive(Boolean.FALSE);
            trnsmsnFacade.edit(trnsmsn);
            Trnsmsn inStoreTrnsmsn = hardwareStore.obtainTrnsmsn(new ArrayList<Integer>(trnsmsn.getTrnsmsnId()));
            inStoreTrnsmsn = null;
            //trnsmsnLst.remove(trnsmsn);
            fromStoreTrnsmsnMap.remove(trnsmsn.getTrnsmsnId());
//            trnsmsnFndMap.remove(trnsmsn.getTrnsmsnId());
//            trnsmsnSelMap.remove(trnsmsn.getTrnsmsnsn());
            trnsmsn = null;
            refreshTrnsmsnsLst();
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Трансмиссия не выбрана.",
                            "Ошибка. Трансмиссия не выбрана."));
        }
    }

    public void saveCrbody() {
        if (crbody != null) {
            if (crbodyNeStatus == CRBODY_STATUS_NEW) {
                if (chkCrbodyIdx()) {
                    crbody.setCrbdtypeFk(crbdtypeFndMap.get(crbdtypeIdx));
                    crbody.setCrbdclrsFk(crbdclrsFndMap.get(crbdclrsIdx));
                    crbody.setStactive(Boolean.TRUE);
                    if (chkCrbodyFields(crbody)) {
                        crbodyFacade.create(crbody);
                        hardwareStore.toStoreCrbody(crbody);
                        //crbody = crbodyNe;
                        //crbodyLst.add(crbody);
                        //refreshCrbodyLst();
                        dlgClose();
                    }
                } else {
                    jsfUtils.getFacesContext().addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Тип или цвет кузова не выбран.",
                                    "Ошибка. Тип или цвет кузова не выбран."));
                }
            } else {
                if (crbodyNeStatus == CRBODY_STATUS_EDIT) {
                    crbodyNe = crbodyFacade.find(crbody.getCrbodyId());
                    if (chkCrbodyIdx()) {
                        crbodyNe.setCrbdUse(crbody.getCrbdUse());
                        //crbodyNe.setCrbdclrsFk(crbody.getCrbdclrsFk());
                        crbodyNe.setCrbdclrsFk(crbdclrsFndMap.get(crbdclrsIdx) != null ? crbdclrsFndMap.get(crbdclrsIdx) : crbody.getCrbdclrsFk());
                        crbodyNe.setCrbddsc(crbody.getCrbddsc());
                        //crbodyNe.setCrbdtypeFk(crbody.getCrbdtypeFk());
                        crbodyNe.setCrbdtypeFk(crbdtypeFndMap.get(crbdtypeIdx) != null ? crbdtypeFndMap.get(crbdtypeIdx) : crbody.getCrbdtypeFk());
                        crbodyNe.setDorscnt(crbody.getDorscnt());
                        crbodyNe.setStactive(crbody.getStactive());
                        crbodyNe.setVincode(crbody.getVincode());
                        if (chkCrbodyFields(crbody)) {
                            crbodyFacade.edit(crbodyNe);
                            crbody = crbodyNe;
                            //fromStoreCrbodyMap.remove(crbody.getCrbodyId());
//                            int idx = -1;
//                            idx = crbodyLst.indexOf(crbody);
//                            if (idx > -1) {
//                                crbodyLst.set(idx, crbody);
//                            }
                            hardwareStore.toStoreCrbody(crbody);
                            //refreshCrbodyLst();
                            dlgClose();
                        }
                    } else {
                        jsfUtils.getFacesContext().addMessage("",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Тип или цвет кузова не выбран.",
                                        "Ошибка. Тип или цвет кузова не выбран."));
                    }
                }
            }
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Кузов не выбран.",
                            "Ошибка. Кузов не выбран."));
        }
    }

    public void saveMotor() {
        if (motor != null) {
            if (motorNeStatus == MOTOR_STATUS_NEW) {
                if (chkMotorIdx()) {
                    motor.setMtrtypeFk(mtrtypeFndMap.get(mtrtypeIdx));
                    motor.setStactive(Boolean.TRUE);
                    if (chkMotorFields(motor)) {
                        motorFacade.create(motor);
                        motorLst.add(motor);
                        //motor = motorNe;
                        hardwareStore.toStoreMotor(motor);
                        //refreshMotorLst();
                        dlgClose();
                    }
                } else {
                    jsfUtils.getFacesContext().addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Двигатель не выбран.",
                                    "Ошибка. Двигатель не выбран."));
                }
            } else {
                if (motorNeStatus == MOTOR_STATUS_EDIT) {
                    motorNe = motorFacade.find(motor.getMotorId());
                    if (chkMotorIdx()) {
                        motorNe.setMotorsn(motor.getMotorsn());
                        motorNe.setMtdsc(motor.getMtdsc());
                        motorNe.setMtrUse(motor.getMtrUse());
                        motorNe.setMtrpower(motor.getMtrpower());
                        motorNe.setMtrtypeFk(motor.getMtrtypeFk());
                        motorNe.setStactive(motor.getStactive());
                        motorNe.setVolume(motor.getVolume());
                        if (chkMotorFields(motor)) {
                            motorFacade.edit(motorNe);
                            motor = motorNe;
                            //fromStoreMotorMap.remove(motor.getMotorId());
//                        int idx = -1;
//                        idx = motorLst.indexOf(motor);
//                        if (idx > -1) {
//                            motorLst.set(idx, motor);
//                        }
                            hardwareStore.toStoreMotor(motor);
                            //refreshMotorLst();
                            dlgClose();
                        }
                    }
                }
            }
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Двигатель не выбран.",
                            "Ошибка. Двигатель не выбран."));
        }
    }

    public void saveTrnsmsn() {
        if (trnsmsn != null) {
            if (trnsmsnNeStatus == TRNSMSN_STATUS_NEW) {
                if (chkTrnsmsnIdx()) {
                    trnsmsn.setStactive(Boolean.TRUE);
                    trnsmsn.setTrnstypeFk(trnstypeFndMap.get(trnstypeIdx));
                    if (chkTrnsmsnFields(trnsmsn)) {
                        trnsmsnFacade.create(trnsmsn);
                        //trnsmsnLst.add(trnsmsn);
                        hardwareStore.toStoreTrnsmsn(trnsmsn);
                        //refreshTrnsmsnsLst();
                        dlgClose();
                    }
                } else {
                    jsfUtils.getFacesContext().addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Трансмиссия не выбрана.",
                                    "Ошибка. Трансмиссия не выбрана."));
                }
            } else {
                if (trnsmsnNeStatus == TRNSMSN_STATUS_EDIT) {
                    trnsmsnNe = trnsmsnFacade.find(trnsmsn.getTrnsmsnId());
                    if (chkTrnsmsnIdx()) {
                        trnsmsnNe.setStactive(trnsmsn.getStactive());
                        trnsmsnNe.setTrnsUse(trnsmsn.getTrnsUse());
                        trnsmsnNe.setTrnsdsc(trnsmsn.getTrnsdsc());
                        trnsmsnNe.setTrnsmsnsn(trnsmsn.getTrnsmsnsn());
                        trnsmsnNe.setTrnstypeFk(trnsmsn.getTrnstypeFk());
                        if (chkTrnsmsnFields(trnsmsn)) {
                            trnsmsnFacade.edit(trnsmsnNe);
                            trnsmsn = trnsmsnNe;
                            //fromStoreTrnsmsnMap.remove(trnsmsn.getTrnsmsnId());
//                        int idx = -1;
//                        idx = trnsmsnLst.indexOf(trnsmsn);
//                        if (idx > -1) {
//                            trnsmsnLst.set(idx, trnsmsn);
//                        }
                            hardwareStore.toStoreTrnsmsn(trnsmsn);
                            //refreshTrnsmsnsLst();
                            dlgClose();
                        }
                    }
                }
            }
        } else {
            jsfUtils.getFacesContext().addMessage("",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка. Трансмиссия не выбрана.",
                            "Ошибка. Трансмиссия не выбрана."));
        }
    }

    private void prepareCrbodyDetails() {
        crbdtypeIdx = crbody.getCrbdtypeFk().getCrbdtypeId();
        crbdclrsIdx = crbody.getCrbdclrsFk().getCrbdclrsId();
        crbdtype = crbody.getCrbdtypeFk();
        crbdclrs = crbody.getCrbdclrsFk();
    }

    private void clearMotorDetails() {
//        motorIdx = null;
        mtrtypeIdx = null;
        mtrtype = null;
    }

    private void prepareMotorDetails() {
//        motorIdx = motor.getMotorId();
        mtrtypeIdx = motor.getMtrtypeFk().getMtrtypeId();
        mtrtype = motor.getMtrtypeFk();
    }

    private void prepareMotorElements() {
        if ((mtrtypeSelMap == null) || (mtrtypeSelMap.isEmpty()) || (mtrtypeFndMap == null) || (mtrtypeFndMap.isEmpty())) {
            fillMtrtypeSelFndMaps();
        }
//        if ((motorSelMap == null) || (motorSelMap.isEmpty()) || (motorFndMap == null) || (motorFndMap.isEmpty())) {
//            fillMotorSelFndMap();
//        }
    }

//    private void fillMotorSelFndMap() {
//        clearMotorSelMap();
//        clearMotorFndMap();
//        if ((motorLst == null) || (motorLst.isEmpty())) {
//            motorLst = motorFacade.findActiveMotors();
//        }
//        if ((motorLst != null) && (!motorLst.isEmpty())) {
//            for (Motor mtr : motorLst) {
//                motorSelMap.put(mtr.getMtdsc(), mtr.getMotorId());
//                motorFndMap.put(mtr.getMotorId(), mtr);
//            }
//        }
//    }
//    private void clearMotorSelMap() {
//        if (motorSelMap == null) {
//            motorSelMap = new HashMap<>();
//        } else {
//            motorSelMap.clear();
//        }
//    }
//    private void clearMotorFndMap() {
//        if (motorFndMap == null) {
//            motorFndMap = new HashMap<>();
//        } else {
//            motorFndMap.clear();
//        }
//    }
    private void fillMtrtypeSelFndMaps() {
        clearMtrtypeSelMap();
        clearMtrtypeFndMap();
        if ((mtrtypeLst == null) || (mtrtypeLst.isEmpty())) {
            mtrtypeLst = mtrtypeFacade.findAll();
        }
        if ((mtrtypeLst != null) && (!mtrtypeLst.isEmpty())) {
            for (Mtrtype mtrtp : mtrtypeLst) {
                mtrtypeSelMap.put(mtrtp.getTypemtr(), mtrtp.getMtrtypeId());
                mtrtypeFndMap.put(mtrtp.getMtrtypeId(), mtrtp);
            }
        }
    }

    private void clearMtrtypeSelMap() {
        if (mtrtypeSelMap == null) {
            mtrtypeSelMap = new HashMap<>();
        } else {
            mtrtypeSelMap.clear();
        }
    }

    private void clearMtrtypeFndMap() {
        if (mtrtypeFndMap == null) {
            mtrtypeFndMap = new HashMap<>();
        } else {
            mtrtypeFndMap.clear();
        }
    }

    private void clearTrnsmsnDetails() {
//        trnsmsnIdx = null;
        trnstypeIdx = null;
        trnstype = null;
    }

    private void prepareTrnsmsnElements() {
        if ((trnstypeSelMap == null) || (trnstypeSelMap.isEmpty()) || (trnstypeFndMap == null) || (trnstypeFndMap.isEmpty())) {
            fillTrnstypeSelFndMaps();
        }
//        if ((trnsmsnSelMap == null) || (trnsmsnSelMap.isEmpty()) || (trnsmsnFndMap == null) || (trnsmsnFndMap.isEmpty())) {
//            fillTrnsmsnSelFndMap();
//        }
    }

    private void fillTrnstypeSelFndMaps() {
        clearTrnstypeSelMap();
        clearTrnstypeFndMap();
        if ((trnstypeLst == null) || (trnstypeLst.isEmpty())) {
            trnstypeLst = trnstypeFacade.findAll();
        }
        if ((trnstypeLst != null) && (!trnstypeLst.isEmpty())) {
            for (Trnstype trntp : trnstypeLst) {
                trnstypeSelMap.put(trntp.getTypetrns(), trntp.getTrnstypeId());
                trnstypeFndMap.put(trntp.getTrnstypeId(), trntp);
            }
        }
    }

    private void clearTrnstypeSelMap() {
        if (trnstypeSelMap == null) {
            trnstypeSelMap = new HashMap<>();
        } else {
            trnstypeSelMap.clear();
        }
    }

    private void clearTrnstypeFndMap() {
        if (trnstypeFndMap == null) {
            trnstypeFndMap = new HashMap<>();
        } else {
            trnstypeFndMap.clear();
        }
    }

    private void prepareTrnsmsnDetails() {
//        trnsmsnIdx = trnsmsn.getTrnsmsnId();
        trnstypeIdx = trnsmsn.getTrnstypeFk().getTrnstypeId();
        trnstype = trnsmsn.getTrnstypeFk();
    }

//    private void fillTrnsmsnSelFndMap() {
//        clearTrnsmsnSelMap();
//        clearTrnsmsnFndMap();
//        if ((trnsmsnLst == null) || (trnsmsnLst.isEmpty())) {
//            trnsmsnLst = trnsmsnFacade.findAll();
//        }
//        if ((trnstypeLst != null) && (!trnstypeLst.isEmpty())) {
//            for (Trnstype trntp : trnstypeLst) {
//                trnstypeSelMap.put(trntp.getTypetrns(), trntp.getTrnstypeId());
//                trnstypeFndMap.put(trntp.getTrnstypeId(), trntp);
//            }
//        }
//    }
    private boolean chkTrnsmsnFields(Trnsmsn trnsmsnNe) {
        boolean chkFields = false;
        if (trnsmsnNe != null) {
            if ((trnsmsnNe.getTrnsmsnsn() != null) && (!"".equals(trnsmsnNe.getTrnsmsnsn()))) {
                if (trnsmsnNe.getTrnstypeFk() != null) {
                    chkFields = true;
                }
            }
        }

        return chkFields;
    }

    private boolean chkMotorFields(Motor motorNe) {
        boolean chkFields = false;
        if (motorNe != null) {
            if ((motorNe.getMotorsn() != null) && (!"".equals(motorNe.getMotorsn()))) {
                if ((motorNe.getMtdsc() != null) && (!"".equals(motorNe.getMtdsc()))) {
                    if ((motorNe.getMtrpower() != null) && (motorNe.getMtrpower().doubleValue() > 0.00D)) {
                        if (motorNe.getMtrtypeFk() != null) {
                            if ((motorNe.getVolume() != null) && (!"".equals(motorNe.getVolume().doubleValue() > 0.00D))) {
                                chkFields = true;
                            }
                        }
                    }
                }
            }
        }
        return chkFields;
    }

    private boolean chkCrbodyFields(Crbody crbodyNe) {
        boolean chkFields = false;
        if (crbodyNe != null) {
            if ((crbodyNe.getCrbdtypeFk() != null)) {
                if ((crbodyNe.getCrbdclrsFk() != null)) {
                    if ((crbodyNe.getVincode() != null) && (!"".equals(crbodyNe.getVincode()))) {
                        if ((crbodyNe.getDorscnt() != null)) {
                            if ((crbodyNe.getCrbddsc() != null) && (!"".equals(crbodyNe.getCrbddsc()))) {
                                chkFields = true;
                            }
                        }
                    }
                }
            }
        }
        return chkFields;
    }

    private boolean chkTrnsmsnIdx() {
        return trnstypeIdx != null;
    }

    private boolean chkMotorIdx() {
        return mtrtypeIdx != null;

    }

    private boolean chkCrbodyIdx() {
        boolean chkCrbdIdx = false;
        if (crbdtypeIdx != null) {
            if (crbdclrsIdx != null) {
                chkCrbdIdx = true;
            }
        }
        return chkCrbdIdx;
    }

    public String statusCrbody(Integer crbdIndx) {
        String crbdStatus = "Не известно";
        if (crbdIndx != null) {
            if (fromStoreCrbodyMap.containsKey(crbdIndx)) {
                crbdStatus = "На складе";
            } else {
                if (autoMblView.getFromStoreCrbodyMap().containsKey(crbdIndx)) {
                    crbdStatus = "На сборке";
                } else {
                    crbdStatus = "На фабрике";
                }
            }
        }
        return crbdStatus;
    }

    public String statusCrbody(Crbody crbody) {
        String crbdStatus = "Не известно";
        if (crbody != null) {
            if (crbody.getCrbodyId() != null) {
                int crbdIndx = crbody.getCrbodyId();
                //if (crbdIndx != null) {
                if (fromStoreCrbodyMap.containsKey(crbdIndx)) {
                    crbdStatus = "На складе";
                } else {
                    if (autoMblView.getFromStoreCrbodyMap().containsKey(crbdIndx)) {
                        crbdStatus = "На сборке";
                    } else {
                        crbdStatus = "На фабрике";
                    }
                }
                //}
            }
        }
        return crbdStatus;
    }

    public String statusMotor(Integer motorIndx) {
        String motorStatus = "Не известно";
        if (motorIndx != null) {
            if (fromStoreMotorMap.containsKey(motorIndx)) {
                motorStatus = "На складе";
            } else {
                if (autoMblView.getFromStoreMotorMap().containsKey(motorIndx)) {
                    motorStatus = "На сборке";
                } else {
                    motorStatus = "На фабрике";
                }
            }
        }
        return motorStatus;
    }

    public String statusMotor(Motor motor) {
        String motorStatus = "Не известно";
        if (motor != null) {
            if (motor.getMotorId() != null) {
                int motorIndx = motor.getMotorId();
                if (fromStoreMotorMap.containsKey(motorIndx)) {
                    motorStatus = "На складе";
                } else {
                    if (autoMblView.getFromStoreMotorMap().containsKey(motorIndx)) {
                        motorStatus = "На сборке";
                    } else {
                        motorStatus = "На фабрике";
                    }
                }
            }
        }
        return motorStatus;
    }

    public String statusTrnsmsn(Integer trnsmsnIndx) {
        String trnsmsnStatus = "Не известно";
        if (trnsmsnIndx != null) {
            if (fromStoreTrnsmsnMap.containsKey(trnsmsnIndx)) {
                trnsmsnStatus = "На складе";
            } else {
                if (autoMblView.getFromStoreTrnsmsnMap().containsKey(trnsmsnIndx)) {
                    trnsmsnStatus = "На сборке";
                } else {
                    trnsmsnStatus = "На фабрике";
                }
            }
        }
        return trnsmsnStatus;
    }

    public String statusTrnsmsn(Trnsmsn trnsmsn) {
        String trnsmsnStatus = "Не известно";
        if (trnsmsn != null) {
            if (trnsmsn.getTrnsmsnId() != null) {
                int trnsmsnIndx = trnsmsn.getTrnsmsnId();
                if (fromStoreTrnsmsnMap.containsKey(trnsmsnIndx)) {
                    trnsmsnStatus = "На складе";
                } else {
                    if (autoMblView.getFromStoreTrnsmsnMap().containsKey(trnsmsnIndx)) {
                        trnsmsnStatus = "На сборке";
                    } else {
                        trnsmsnStatus = "На фабрике";
                    }
                }
            }
        }
        return trnsmsnStatus;
    }

    public void showCrbodyDlg() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 600);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("/dlgs/necrbody", options, null);
    }

    public void showMotorDlg() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 600);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("/dlgs/nemotor", options, null);
    }

    public void showTrnsmsnDlg() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("width", 600);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("/dlgs/netrnsmsn", options, null);
    }

    //Сделать метод закрытия отправляющий все неиспользованные запчасти на склад
    private void toStoreAllDetails() {
        if ((crbodyLst != null) && (!crbodyLst.isEmpty())) {
            for (Crbody crbd : crbodyLst) {
                if (crbd.getCrbdUse() == null) {
                    hardwareStore.toStoreCrbody(crbd);
                }
            }
        }
        if ((motorLst != null) && (!motorLst.isEmpty())) {
            for (Motor mtr : motorLst) {
                if (mtr.getMtrUse() == null) {
                    hardwareStore.toStoreMotor(mtr);
                }
            }
        }
        if ((trnsmsnLst != null) && (!trnsmsnLst.isEmpty())) {
            for (Trnsmsn trns : trnsmsnLst) {
                if (trns.getTrnsUse() == null) {
                    hardwareStore.toStoreTrnsmsn(trns);
                }
            }
        }
    }

    public void defCarBodyStatusNew() {
        crbodyNeStatus = CRBODY_STATUS_NEW;
    }

    public void defCarBodyStatusEdit() {
        crbodyNeStatus = CRBODY_STATUS_EDIT;
    }
    
    public void defMotorStatusNew() {
        motorNeStatus = MOTOR_STATUS_NEW;
    }

    public void defMotorStatusEdit() {
        motorNeStatus = MOTOR_STATUS_EDIT;
    }
    
    public void defTrnsmsStatusNew() {
        trnsmsnNeStatus = TRNSMSN_STATUS_NEW;
    }

    public void defTrnsmsnStatusEdit() {
        trnsmsnNeStatus = TRNSMSN_STATUS_EDIT;
    }
    
    public Boolean deleteRESTCrbody(Integer crbodyId) {
        boolean crbodyFinded = false;
        if (crbodyId != null) {
            Crbody crbody = crbodyFacade.find(crbodyId);
            if (crbody != null) {
                this.crbody = crbody;
                deleteCrbody();
                crbodyFinded = true;
            }
        }
        return crbodyFinded;
    }
    
    public Boolean deleteRESTMotor(Integer motorId) {
        boolean motorFinded = false;
        if (motorId != null) {
            Motor motor = motorFacade.find(motorId);
            if (motor != null) {
                this.motor = motor;
                deleteMotor();
                motorFinded = true;
            }
        }
        return motorFinded;
    }
    
    public Boolean deleteRESTTrnsmsn(Integer trnsmsnId) {
        boolean trnsmsnFinded = false;
        if (trnsmsnId != null) {
            Trnsmsn trnsmsn = trnsmsnFacade.find(trnsmsnId);
            if (trnsmsn != null) {
                this.trnsmsn = trnsmsn;
                deleteTrnsmsn();
                trnsmsnFinded = true;
            }
        }
        return trnsmsnFinded;
    }

    public void cancelCrbodyDlg() {
        if (crbody != null) {
            if (crbody.getCrbodyId() == null) {
                crbody = null;
            }
            clearCrbodyDetails();
            dlgClose();
        }
    }

    public void cancelMotorDlg() {
        if (motor != null) {
            if (motor.getMotorId() == null) {
                motor = null;
            }
            clearMotorDetails();
            dlgClose();
        }
    }

    public void cancelTrnsmsnDlg() {
        if (trnsmsn != null) {
            if (trnsmsn.getTrnsmsnId() == null) {
                trnsmsn = null;
            }
            clearTrnsmsnDetails();
            dlgClose();
        }
    }

    public void dlgClose() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    @PostConstruct
    private void init() {
        //obtainItemsFromStore();
        crbodyNeHdr = "";
        motorNeHdr = "";
        trnsmsnNeHdr = "";
    }

    @PreDestroy
    private void close() {
        toStoreAllDetails();
    }

}
