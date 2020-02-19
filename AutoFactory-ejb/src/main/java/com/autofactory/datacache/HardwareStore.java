/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autofactory.datacache;

import com.autofactory.dao.CrbodyFacade;
import com.autofactory.dao.MotorFacade;
import com.autofactory.dao.TrnsmsnFacade;
import com.autofactory.data.Crbody;
import com.autofactory.data.Motor;
import com.autofactory.data.Trnsmsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import static javax.ejb.ConcurrencyManagementType.CONTAINER;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Eugene Sidorov
 */
@Singleton
@ConcurrencyManagement(CONTAINER)
@Startup
public class HardwareStore {

    @EJB
    private CrbodyFacade crbodyFacade;
    @EJB
    private MotorFacade motorFacade;
    @EJB
    private TrnsmsnFacade trnsmsnFacade;

    //Доступные корпуса
    private List<Crbody> crbodiesAvlbl;
    //Доступные двигатели
    private List<Motor> motorsAvlbl;
    //Доступные трансмиссии
    private List<Trnsmsn> trnsmsnsAvlbl;
    //Доступные кузова по их id
    private Map<Integer, Crbody> crbodiesMap;
    private Map<Integer, Motor> motorsMap;
    private Map<Integer, Trnsmsn> trnsmsnsMap;
    //Выше правильно НИЖЕ без пометки доделать (исправить)

    //Получить копию текущего Map корпусов
    public Map<Integer, Crbody> getCrbodiesMap() {
        Map<Integer, Crbody> cloneCurrCrbodiesMap = new HashMap<>();
        Crbody tmpClone = null;
        for (Crbody tmpCrbd : crbodiesMap.values()) {
            tmpClone = tmpCrbd.clone();
            cloneCurrCrbodiesMap.put(tmpClone.getCrbodyId(), tmpClone);
        }
        return cloneCurrCrbodiesMap; //crbodiesMap;

    }

    //Получить копию текущего Map двигателей
    public Map<Integer, Motor> getMotorsMap() {
        Map<Integer, Motor> cloneCurrMotorMap = new HashMap<>();
        Motor tmpClone = null;
        for (Motor tmp : motorsMap.values()) {
            tmpClone = tmp.clone();
            cloneCurrMotorMap.put(tmpClone.getMotorId(), tmpClone);
        }
        return cloneCurrMotorMap; //motorsMap;
    }

    //Получить копию текущего Map трансмиссий
    public Map<Integer, Trnsmsn> getTrnsmsnsMap() {
        Map<Integer, Trnsmsn> cloneCurrTrnsmsnMap = new HashMap<>();
        Trnsmsn tmpClone = null;
        for (Trnsmsn tmp : trnsmsnsMap.values()) {
            tmpClone = tmp.clone();
            cloneCurrTrnsmsnMap.put(tmpClone.getTrnsmsnId(), tmpClone);
        }
        return cloneCurrTrnsmsnMap; //trnsmsnsMap;
    }

    //Получить копию текущего списка корпусов
    public List<Crbody> getCrbodiesAvlbl() {
        List<Crbody> currentCarbodies = new ArrayList<>();
        for (Crbody tmpCrbd : crbodiesAvlbl) {
            currentCarbodies.add(tmpCrbd.clone());
        }
        return currentCarbodies;//crbodiesAvlbl;
    }

    //Получить копию текущего списка двигателей
    public List<Motor> getMotorsAvlbl() {
        List<Motor> currentMotors = new ArrayList<>();
        for (Motor tmpMotor : motorsAvlbl) {
            currentMotors.add(tmpMotor.clone());
        }
        return currentMotors; //motorsAvlbl;
    }

    //Получить копию текущего списка трансмиссий
    public List<Trnsmsn> getTrnsmsnsAvlbl() {
        List<Trnsmsn> currentTrnsmsns = new ArrayList<>();
        for (Trnsmsn tmpTrns : trnsmsnsAvlbl) {
            currentTrnsmsns.add(tmpTrns.clone());
        }
        return currentTrnsmsns; //trnsmsnsAvlbl;
    }

    //Получение деталей со клада
    //Получить корпус
    public Crbody obtainCrbody(List<Integer> crbodyIdLst) {
        Crbody obtainingCrbody = null;
        if ((crbodyIdLst != null) && (!crbodyIdLst.isEmpty())) {
            if ((crbodiesMap != null) && (!crbodiesMap.isEmpty())) {
                for (Integer crbdId : crbodyIdLst) {
                    obtainingCrbody = crbodiesMap.get(crbdId);
                    if (obtainingCrbody != null) {
                        crbodiesMap.remove(crbdId);
                        crbodiesAvlbl.remove(obtainingCrbody);
                        //crbodiesAvlbl.clear();
                        //crbodiesAvlbl.addAll(crbodiesMap.values());
                        break;
                    }
                }
            }
        }
        return obtainingCrbody;
    }

    //Получить двигатель
    public Motor obtainMotor(List<Integer> motorIdLst) {
        Motor obtainingMotor = null;
        if ((motorIdLst != null) && (!motorIdLst.isEmpty())) {
            if ((motorsMap != null) && (!motorsMap.isEmpty())) {
                for (Integer mtrId : motorIdLst) {
                    obtainingMotor = motorsMap.get(mtrId);
                    if (obtainingMotor != null) {
                        motorsMap.remove(mtrId);
                        motorsAvlbl.remove(obtainingMotor);
                        break;
                    }
                }
            }
        }
        return obtainingMotor;
    }

    //Получить трансмиссию
    public Trnsmsn obtainTrnsmsn(List<Integer> trnsmsnIdLst) {
        Trnsmsn obtainingTrnsmsn = null;
        if ((trnsmsnIdLst != null) && (!trnsmsnIdLst.isEmpty())) {
            if ((trnsmsnsMap != null) && (!trnsmsnsMap.isEmpty())) {
                for (Integer trnsId : trnsmsnIdLst) {
                    obtainingTrnsmsn = trnsmsnsMap.get(trnsId);
                    if (obtainingTrnsmsn != null) {
                        trnsmsnsMap.remove(trnsId);
                        trnsmsnsAvlbl.remove(obtainingTrnsmsn);
                        break;
                    }
                }
            }
        }
        return obtainingTrnsmsn;
    }

//Вернуть детали на клад
    //Вернуть корпус
    public Boolean toStoreCrbody(Crbody crbdToStore) {
        Boolean crbdStored = false;
        if (crbodiesMap != null) {
            if ((crbdToStore != null) && (crbdToStore.getCrbodyId() != null)) {
                //System.err.println("toStoreCrbody id = " + crbdToStore.getCrbodyId());
                //if ((!crbodiesMap.containsKey(crbdToStore.getCrbodyId())) && (!crbodiesMap.containsValue(crbdToStore))) {
                crbodiesMap.put(crbdToStore.getCrbodyId(), crbdToStore);
                crbdStored = true;
                if (!crbodiesAvlbl.contains(crbdToStore)) {
                    crbodiesAvlbl.add(crbdToStore);
                } else {
                    int idx = -1;
                    idx = crbodiesAvlbl.indexOf(crbdToStore);
                    if (idx > -1) {
                        crbodiesAvlbl.set(idx, crbdToStore);
                    }
                }
            }
        }
        return crbdStored;
    }

    //Вернуть двигатель
    public Boolean toStoreMotor(Motor motorToStore) {
        Boolean motorStored = false;
        if (motorsMap != null) {
            if ((motorToStore != null) && (motorToStore.getMotorId() != null)) {
                //if ((!motorsMap.containsKey(motorToStore.getMotorId())) && (!motorsMap.containsValue(motorToStore))) {
                motorsMap.put(motorToStore.getMotorId(), motorToStore);
                motorStored = true;
                if (!motorsAvlbl.contains(motorToStore)) {
                    motorsAvlbl.add(motorToStore);
                } else {
                    int idx = -1;
                    idx = motorsAvlbl.indexOf(motorToStore);
                    if (idx > -1) {
                        motorsAvlbl.set(idx, motorToStore);
                    }
                }
            }
        }
        return motorStored;
    }

    //Вернуть трансмиссию
    public Boolean toStoreTrnsmsn(Trnsmsn trnsmsnToStore) {
        Boolean trnsmsnStored = false;
        if (trnsmsnsMap != null) {
            if ((trnsmsnToStore != null) && (trnsmsnToStore.getTrnsmsnId() != null)) {
                //if ((!trnsmsnsMap.containsKey(trnsmsnToStore.getTrnsmsnId())) && (!trnsmsnsMap.containsValue(trnsmsnToStore))) {
                trnsmsnsMap.put(trnsmsnToStore.getTrnsmsnId(), trnsmsnToStore);
                trnsmsnStored = true;
                if (!trnsmsnsAvlbl.contains(trnsmsnToStore)) {
                    trnsmsnsAvlbl.add(trnsmsnToStore);
                } else {
                    int idx = -1;
                    idx = trnsmsnsAvlbl.indexOf(trnsmsnToStore);
                    if (idx > -1) {
                        trnsmsnsAvlbl.set(idx, trnsmsnToStore);
                    }
                }
            }
        }
        return trnsmsnStored;
    }

    @Schedule(minute = "*/10", hour = "*", persistent = false)  
    private void loadHardwares() {
        crbodiesAvlbl = crbodyFacade.findUnUseCrbodies();
        motorsAvlbl = motorFacade.findUnUseMotors();
        trnsmsnsAvlbl = trnsmsnFacade.findUnUseTrnsmsn();
        refresheDetails();
    }

//Заполнение Map
    private void refresheDetails() {

        if ((crbodiesAvlbl != null) && (!crbodiesAvlbl.isEmpty())) {
            clearCrbodiesMap();
            for (Crbody crbd : crbodiesAvlbl) {
                crbodiesMap.put(crbd.getCrbodyId(), crbd);
            }
        }
        if ((motorsAvlbl != null) && (!motorsAvlbl.isEmpty())) {
            clearMotorsMap();
            for (Motor mtr : motorsAvlbl) {
                motorsMap.put(mtr.getMotorId(), mtr);
            }
        }
        if ((trnsmsnsAvlbl != null) && (!trnsmsnsAvlbl.isEmpty())) {
            clearTrnsmsnsMap();
            for (Trnsmsn trns : trnsmsnsAvlbl) {
                trnsmsnsMap.put(trns.getTrnsmsnId(), trns);
            }
        }
    }

    //Очистка создание Map
    private void clearCrbodiesMap() {
        crbodiesMap = null;
        crbodiesMap = new HashMap<>();
    }

    private void clearMotorsMap() {
        motorsMap = null;
        motorsMap = new HashMap<>();
    }

    private void clearTrnsmsnsMap() {
        trnsmsnsMap = null;
        trnsmsnsMap = new HashMap<>();
    }

    @PostConstruct
    private void init() {
        loadHardwares();
    }

}
