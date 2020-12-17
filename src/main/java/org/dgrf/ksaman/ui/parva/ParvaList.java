/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.parva;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.hedwig.cloud.response.HedwigResponseCode;
import org.ksaman.core.DTO.ParvaDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author bhaduri
 */
@Named(value = "parvaList")
@ViewScoped
public class ParvaList implements Serializable{
    
    /**
     * Creates a new instance of ParvaList
     */
    
    private List<ParvaDTO> parvaDTOList;
    private ParvaDTO selectedParva;
    
    public ParvaList() {
    }
    public void loadAllParvaList() {
        KSCoreService ksCoreService = new KSCoreService();
        parvaDTOList = ksCoreService.getParvaDTOList();
    }

    public List<ParvaDTO> getParvaDTOList() {
        return parvaDTOList;
    }

    public void setParvaDTOList(List<ParvaDTO> parvaDTOList) {
        this.parvaDTOList = parvaDTOList;
    }
    
    public String editParva() {
        return "/edit/parva/ParvaEdit?faces-redirect=true&parvaId=" + selectedParva.getParvaId();
    }
    
    public String deleteParva() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        int parvaId = selectedParva.getParvaId();
        
        ParvaDTO parvaDTO = new ParvaDTO();
        parvaDTO.setParvaId(parvaId);
        
        KSCoreService ksCoreService = new KSCoreService();
        responseCode = ksCoreService.removeParva(parvaDTO);
        
        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage("Parva delete alert:", "Parva data deleted Successfully.");
            context.addMessage(null, fm);
            return "/edit/parva/ParvaList?faces-redirect=true";
        } else if (responseCode == HedwigResponseCode.DB_NON_EXISTING) {
            fm = new FacesMessage("Parva delete alert:", "Selected Parva not found.");
            context.addMessage(null, fm);
            return "/edit/parva/ParvaList?faces-redirect=true";
        } else {
            fm = new FacesMessage("Parva delete alert:", "Something went wrong, please contact admin.");
            context.addMessage(null, fm);
            return "/edit/parva/ParvaList?faces-redirect=true";
        }
    }

    public ParvaDTO getSelectedParva() {
        return selectedParva;
    }

    public void setSelectedParva(ParvaDTO selectedParva) {
        this.selectedParva = selectedParva;
    }
    
    
}
