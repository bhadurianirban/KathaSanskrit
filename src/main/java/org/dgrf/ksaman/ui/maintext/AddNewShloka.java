/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.maintext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.ksaman.core.DTO.MaintextDTO;
import org.ksaman.core.DTO.UbachaDTO;
import org.hedwig.cloud.response.HedwigResponseCode;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "addNewShloka")
@ViewScoped
public class AddNewShloka implements Serializable {

    /**
     * Creates a new instance of AddNewShloka
     */
    private MaintextDTO maintextDTO;
    private int parvaId;
    private int adhyayId;
    private int shlokaLine;
    private int shlokaNum;
    private String shlokaText;
    private String anubadText;
    private String endChar;
    private int selectedUbachaId;

    private Map<String, Integer> ubachaListMap;

    public void fillValues() {
        KSCoreService kSCoreService = new KSCoreService();
        List<UbachaDTO> ubachaDTOList = kSCoreService.getUbachaDTOList();

        ubachaListMap = new HashMap<>();

        for (int i = 0; i < ubachaDTOList.size(); i++) {
            ubachaListMap.put(ubachaDTOList.get(i).getUbachaName(), ubachaDTOList.get(i).getUbachaId());
        }
        
        maintextDTO = new MaintextDTO();
        maintextDTO.setParvaId(parvaId);
        maintextDTO.setAdhyayId(adhyayId);
        
        int maxShlokaNum = kSCoreService.getMaxShlokaNumber(maintextDTO);
        
        maintextDTO = new MaintextDTO();
        maintextDTO.setParvaId(parvaId);
        maintextDTO.setAdhyayId(adhyayId);
        maintextDTO.setMaxShlokaNum(maxShlokaNum);
        
        int maxShlokaLine = kSCoreService.getMaxShlokaLine(maintextDTO);
        if(maxShlokaLine > 1){
            shlokaNum = maxShlokaNum + 1;
            shlokaLine = 1;
            endChar = "|";
        } else {
            shlokaNum = maxShlokaNum;
            shlokaLine = maxShlokaLine + 1;
            endChar = "||";
        }
        if (shlokaNum == 0) {
            shlokaNum = 1;
            endChar = "|";
        }
        
    }

    public String addNewShloka() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;

        maintextDTO = new MaintextDTO();

        maintextDTO.setParvaId(parvaId);
        maintextDTO.setAdhyayId(adhyayId);
        maintextDTO.setShlokaLine(shlokaLine);
        maintextDTO.setShlokaNum(shlokaNum);
        maintextDTO.setUbachaId(selectedUbachaId);
        maintextDTO.setShlokaText(shlokaText);
        maintextDTO.setAnubadText(anubadText);
        maintextDTO.setEndChar(endChar);

        KSCoreService kSCoreService = new KSCoreService();
        responseCode = kSCoreService.addNewShloka(maintextDTO);

        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage("Shloka creation alert:", "Shloka added Successfully.");
            context.addMessage(null, fm);
            return "ShlokaListTabView?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId;
        } else if (responseCode == HedwigResponseCode.DB_DUPLICATE) {
            fm = new FacesMessage("Shloka creation alert:", "Shloka already exsists.");
            context.addMessage(null, fm);
            return "AddNewShloka?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId;
        } else {
            fm = new FacesMessage("Shloka creation alert:", "duh! Something went wrong :(");
            context.addMessage(null, fm);
            return "AddNewShloka?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId;
        }
    }

    public String previousPage() {
        return "ShlokaListTabView?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId;
    }

    public MaintextDTO getMaintextDTO() {
        return maintextDTO;
    }

    public void setMaintextDTO(MaintextDTO maintextDTO) {
        this.maintextDTO = maintextDTO;
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

    public String getShlokaText() {
        return shlokaText;
    }

    public void setShlokaText(String shlokaText) {
        this.shlokaText = shlokaText;
    }

    public String getAnubadText() {
        return anubadText;
    }

    public void setAnubadText(String anubadText) {
        this.anubadText = anubadText;
    }

    public int getSelectedUbachaId() {
        return selectedUbachaId;
    }

    public void setSelectedUbachaId(int selectedUbachaId) {
        this.selectedUbachaId = selectedUbachaId;
    }

    public Map<String, Integer> getUbachaListMap() {
        return ubachaListMap;
    }

    public void setUbachaListMap(Map<String, Integer> ubachaListMap) {
        this.ubachaListMap = ubachaListMap;
    }

    public String getEndChar() {
        return endChar;
    }

    public void setEndChar(String endChar) {
        this.endChar = endChar;
    }

}
