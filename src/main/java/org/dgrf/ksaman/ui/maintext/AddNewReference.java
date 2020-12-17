/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.maintext;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.hedwig.cloud.response.HedwigResponseCode;
import org.ksaman.core.DTO.ReferencetextDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "addNewReference")
@ViewScoped
public class AddNewReference implements Serializable {

    /**
     * Creates a new instance of AddNewReference
     */
    private ReferencetextDTO referencetextDTO;
    private int parvaId;
    private int adhyayId;
    private int shlokaNum;
    private int shlokaLine;
    private int refTextId;
    private String refText;
    private int refTextPosition;
    
    public String addNewReference() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;

        referencetextDTO = new ReferencetextDTO();

        referencetextDTO.setParvaId(parvaId);
        referencetextDTO.setAdhyayId(adhyayId);
        referencetextDTO.setShlokaLine(shlokaLine);
        referencetextDTO.setShlokaNum(shlokaNum);
        referencetextDTO.setRefTextId(refTextId);
        referencetextDTO.setRefText(refText);
        referencetextDTO.setRefTextPosition(refTextPosition);
        

        KSCoreService kSCoreService = new KSCoreService();
        responseCode = kSCoreService.addNewReference(referencetextDTO);

        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage("Reference creation alert:", "Reference added Successfully.");
            context.addMessage(null, fm);
            return "ReferencedTextTabView?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId + "&shlokaNum=" + shlokaNum + "&shlokaLine=" + shlokaLine;
        } else if (responseCode == HedwigResponseCode.DB_DUPLICATE) {
            fm = new FacesMessage("Reference creation alert:", "Reference already exsists.");
            context.addMessage(null, fm);
            return "AddNewReference?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId + "&shlokaNum=" + shlokaNum + "&shlokaLine=" + shlokaLine;
        } else {
            fm = new FacesMessage("Reference creation alert:", "duh! Something went wrong :(");
            context.addMessage(null, fm);
            return "AddNewReference?faces-redirect=true&parvaId=" + parvaId + "&adhyayId=" + adhyayId + "&shlokaNum=" + shlokaNum + "&shlokaLine=" + shlokaLine;
        }
    }

    public String previousPage() {
        return "ReferencedTextTabView";
    }

    public ReferencetextDTO getReferencetextDTO() {
        return referencetextDTO;
    }

    public void setReferencetextDTO(ReferencetextDTO referencetextDTO) {
        this.referencetextDTO = referencetextDTO;
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

    public int getShlokaNum() {
        return shlokaNum;
    }

    public void setShlokaNum(int shlokaNum) {
        this.shlokaNum = shlokaNum;
    }

    public int getShlokaLine() {
        return shlokaLine;
    }

    public void setShlokaLine(int shlokaLine) {
        this.shlokaLine = shlokaLine;
    }

    public int getRefTextId() {
        return refTextId;
    }

    public void setRefTextId(int refTextId) {
        this.refTextId = refTextId;
    }

    public String getRefText() {
        return refText;
    }

    public void setRefText(String refText) {
        this.refText = refText;
    }

    public int getRefTextPosition() {
        return refTextPosition;
    }

    public void setRefTextPosition(int refTextPosition) {
        this.refTextPosition = refTextPosition;
    }
    
    
    
}
