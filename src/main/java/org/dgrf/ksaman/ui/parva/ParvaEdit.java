/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.parva;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.ksaman.core.DTO.ParvaDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "parvaEdit")
@ViewScoped
public class ParvaEdit implements Serializable {

    /**
     * Creates a new instance of ParvaEdit
     */
    private ParvaDTO parvaDTO;
    private int parvaId;
    private String parvaName;
    
    public void fillValues() {
        KSCoreService ksCoreService = new KSCoreService();
        parvaDTO = new ParvaDTO();
        
        parvaDTO.setParvaId(parvaId);
        
        parvaDTO = ksCoreService.getParvaDTO(parvaDTO);
        
        parvaId = parvaDTO.getParvaId();
        parvaName = parvaDTO.getParvaName();
    }
    
    public String updateSelectedParva() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        KSCoreService ksCoreService = new KSCoreService();
        parvaDTO = new ParvaDTO();
        parvaDTO.setParvaId(parvaId);
        parvaDTO.setParvaName(parvaName);
        
        responseCode = ksCoreService.updateParva(parvaDTO);
        
        if (responseCode == 0) {
            fm = new FacesMessage("Parva changes alert:", "Parva data changed Successfully.");
            context.addMessage(null, fm);
            return "/edit/parva/ParvaList?faces-redirect=true";
        } else {
            fm = new FacesMessage("Parva changes alert:", "Either Parva not found or something went wrong.");
            context.addMessage(null, fm);
            return "/edit/parva/ParvaEdit?faces-redirect=true&parvaId=" + parvaId;
        }
    }

    public ParvaDTO getParvaDTO() {
        return parvaDTO;
    }

    public void setParvaDTO(ParvaDTO parvaDTO) {
        this.parvaDTO = parvaDTO;
    }

    public int getParvaId() {
        return parvaId;
    }

    public void setParvaId(int parvaId) {
        this.parvaId = parvaId;
    }

    public String getParvaName() {
        return parvaName;
    }

    public void setParvaName(String parvaName) {
        this.parvaName = parvaName;
    }
    
    
}
