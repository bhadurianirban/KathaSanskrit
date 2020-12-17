/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.maintext;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.hedwig.cloud.response.HedwigResponseCode;
import org.ksaman.core.DTO.MaintextDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "translatedTextTabView")
@ViewScoped
public class TranslatedTextTabView implements Serializable {

    /**
     * Creates a new instance of TranslatedTextTabView
     */
    private List<MaintextDTO> translationDTOList;
    private int parvaId;
    private int adhyayId;
    private int ubachaId;
    private int shlokaLine;
    private int shlokaNum;
    private String ubachaName;
    private String parvaName;
    private String ubachaBachan;
    private String shlokaText;
    private String shlokaAnubad;
    private MaintextDTO maintextDTO;
    private Boolean anubadNotIncluded;

    
    public void loadTranslatedText() {
        KSCoreService ksCoreService = new KSCoreService();
        translationDTOList = ksCoreService.getMaintextTranslation(parvaId, adhyayId, shlokaNum);

        if (translationDTOList.size() != 0) {
            ubachaName = translationDTOList.get(0).getUbachaName();
            ubachaBachan = translationDTOList.get(0).getUbachaBachan();
            parvaName = translationDTOList.get(0).getParvaName();
            ubachaId = translationDTOList.get(0).getUbachaId();
            parvaId = translationDTOList.get(0).getParvaId();
            shlokaNum = translationDTOList.get(0).getShlokaNum();
            adhyayId = translationDTOList.get(0).getAdhyayId();
            shlokaAnubad = translationDTOList.get(0).getAnubadText();
            
            anubadNotIncluded = true;
            
            if (shlokaAnubad == null) {
                anubadNotIncluded = false;
                shlokaAnubad = "অনুবাদ করা হয় নি";
            }
        }

        for (int i = 0; i < translationDTOList.size(); i++) {

            shlokaText = translationDTOList.get(i).getShlokaText();
            shlokaLine = translationDTOList.get(i).getShlokaLine();

        }
    }
    
    public String referencedtextView() {
        parvaId = translationDTOList.get(0).getParvaId();
        adhyayId = translationDTOList.get(0).getAdhyayId();
        shlokaNum = translationDTOList.get(0).getShlokaNum();
        shlokaLine = translationDTOList.get(0).getShlokaLine();
        return "ReferencedTextView";
    }
    
    public String updateShlokaAnubad() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;

        maintextDTO = new MaintextDTO();

        maintextDTO.setParvaId(parvaId);
        maintextDTO.setAdhyayId(adhyayId);
        maintextDTO.setShlokaNum(shlokaNum);
        
        if(shlokaAnubad.trim().equals("অনুবাদ করা হয় নি")) {
            maintextDTO.setAnubadText(null);
        } else {
            maintextDTO.setAnubadText(shlokaAnubad);
        }
        
        KSCoreService kSCoreService = new KSCoreService();
        responseCode = kSCoreService.updateMaintextTranslation(maintextDTO);

        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"Shloka Anubad update alert:", "Shloka Anubad updated Successfully.");
            context.addMessage(null, fm);
            return "TranslatedTextTabView?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId + "&shlokaNum=" + shlokaNum;
        } else if (responseCode == HedwigResponseCode.DB_NON_EXISTING) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Shloka Anubad update alert:", "Shloka Anubad not exsists.");
            context.addMessage(null, fm);
            return "TranslatedTextTabView?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId + "&shlokaNum=" + shlokaNum;
        } else {
            fm = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Shloka Anubad update alert:", "duh! Something went wrong :(");
            context.addMessage(null, fm);
            return "TranslatedTextTabView?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId + "&shlokaNum=" + shlokaNum;
        }
    }

    public List<MaintextDTO> getTranslationDTOList() {
        return translationDTOList;
    }

    public void setTranslationDTOList(List<MaintextDTO> translationDTOList) {
        this.translationDTOList = translationDTOList;
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

    public int getUbachaId() {
        return ubachaId;
    }

    public void setUbachaId(int ubachaId) {
        this.ubachaId = ubachaId;
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

    public String getShlokaAnubad() {
        return shlokaAnubad;
    }

    public void setShlokaAnubad(String shlokaAnubad) {
        this.shlokaAnubad = shlokaAnubad;
    }

    public String getParvaName() {
        return parvaName;
    }

    public void setParvaName(String parvaName) {
        this.parvaName = parvaName;
    }

    public MaintextDTO getMaintextDTO() {
        return maintextDTO;
    }

    public void setMaintextDTO(MaintextDTO maintextDTO) {
        this.maintextDTO = maintextDTO;
    }

    public Boolean getAnubadNotIncluded() {
        return anubadNotIncluded;
    }

    public void setAnubadNotIncluded(Boolean anubadNotIncluded) {
        this.anubadNotIncluded = anubadNotIncluded;
    }
    
}
