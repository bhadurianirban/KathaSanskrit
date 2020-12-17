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
import org.hedwig.cloud.response.HedwigResponseCode;
import org.ksaman.core.DTO.ParvaDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "addNewParva")
@ViewScoped
public class AddNewParva implements Serializable {

    /**
     * Creates a new instance of AddNewParva
     */
    
    private int parvaId;
    private String parvaName;
    
    public String addNewParva() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        
        ParvaDTO parvaDTO = new ParvaDTO();
        
        parvaDTO.setParvaId(parvaId);
        parvaDTO.setParvaName(parvaName);
        
        KSCoreService ksCoreService = new KSCoreService();
        responseCode = ksCoreService.addParva(parvaDTO);
        
        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage("Parva creation alert:", "Parva added Successfully.");
            context.addMessage(null, fm);
            return "/edit/parva/ParvaList?faces-redirect=true";
        } else if (responseCode == HedwigResponseCode.DB_DUPLICATE) {
            fm = new FacesMessage("Parva creation alert:", "Parva already exsists.");
            context.addMessage(null, fm);
            return "/edit/parva/AddNewParva?faces-redirect=true";
        } else {
            fm = new FacesMessage("Parva creation alert:", "duh! Something went wrong :(");
            context.addMessage(null, fm);
            return "/edit/parva/AddNewParva?faces-redirect=true";
        }
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
