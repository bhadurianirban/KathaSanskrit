/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.browse;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.ksaman.core.DTO.MaintextDTO;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author dgrfiv
 */
@Named(value = "shlokaListByPaad")
@ViewScoped
public class ShlokaListByPaad implements Serializable {

    /**
     * Creates a new instance of ShlokaListByPaad
     */
    private LazyDataModel<MaintextDTO> shlokaDTOList;
    private MaintextDTO selectedShloka;
    private int parvaId;
    private int adhyayId;
    private int shlokaLine;
    private int shlokaNum;
    private String ubachaName;
    private String ubachaBachan;
    private String shlokaText;
    private String firstChar;
    private String parvaName;

    public void loadAllShlokaList() {
        //KSCoreService ksCoreService = new KSCoreService();
        shlokaDTOList = new ShlokaListByPaadLazyDataModel(firstChar);
        
        for (int i = 0; i < shlokaDTOList.getRowCount(); i++) {

//            parvaId = shlokaDTOList.get(i).getParvaId();
//            adhyayId = shlokaDTOList.get(i).getAdhyayId();
//            shlokaNum = shlokaDTOList.get(i).getShlokaNum();
//            shlokaLine = shlokaDTOList.get(i).getShlokaLine();
//            parvaName = shlokaDTOList.get(i).getParvaName();
//            ubachaName = shlokaDTOList.get(i).getUbachaName();
//            ubachaBachan = shlokaDTOList.get(i).getUbachaBachan();

            // shlokaText = shlokaDTOList.getRowData().getShlokaText();
            
            
        }
    }
    
    public String translationView() {
        parvaId = selectedShloka.getParvaId();
        adhyayId = selectedShloka.getAdhyayId();
        shlokaNum = selectedShloka.getShlokaNum();
        return "TranslatedTextView";
    }

    public LazyDataModel<MaintextDTO> getShlokaDTOList() {
        return shlokaDTOList;
    }

    public void setShlokaDTOList(LazyDataModel<MaintextDTO> shlokaDTOList) {
        this.shlokaDTOList = shlokaDTOList;
    }


    public MaintextDTO getSelectedShloka() {
        return selectedShloka;
    }

    public void setSelectedShloka(MaintextDTO selectedShloka) {
        this.selectedShloka = selectedShloka;
    }

    public int getParvaId() {
        return parvaId;
    }

    public void setParvaId(int parvaId) {
        this.parvaId = parvaId;
    }

    public int getAdhyayId() {
        return adhyayId;
    }

    public void setAdhyayId(int adhyayId) {
        this.adhyayId = adhyayId;
    }

    public int getShlokaLine() {
        return shlokaLine;
    }

    public void setShlokaLine(int shlokaLine) {
        this.shlokaLine = shlokaLine;
    }

    public int getShlokaNum() {
        return shlokaNum;
    }

    public void setShlokaNum(int shlokaNum) {
        this.shlokaNum = shlokaNum;
    }

    public String getUbachaName() {
        return ubachaName;
    }

    public void setUbachaName(String ubachaName) {
        this.ubachaName = ubachaName;
    }

    public String getUbachaBachan() {
        return ubachaBachan;
    }

    public void setUbachaBachan(String ubachaBachan) {
        this.ubachaBachan = ubachaBachan;
    }

    public String getShlokaText() {
        return shlokaText;
    }

    public void setShlokaText(String shlokaText) {
        this.shlokaText = shlokaText;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public String getParvaName() {
        return parvaName;
    }

    public void setParvaName(String parvaName) {
        this.parvaName = parvaName;
    }
    
    
    
}
