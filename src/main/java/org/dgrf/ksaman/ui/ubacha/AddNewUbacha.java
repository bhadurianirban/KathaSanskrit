/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.ubacha;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.hedwig.cloud.response.HedwigResponseCode;
import org.ksaman.core.DTO.UbachaDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "addNewUbacha")
@ViewScoped
public class AddNewUbacha implements Serializable {

    /**
     * Creates a new instance of AddNewUbacha
     */
    private int ubachaId;
    private String ubachaName;
    private String ubachaBachan;
    
    public String addNewUbacha() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        
        UbachaDTO ubachaDTO = new UbachaDTO();
        
        ubachaDTO.setUbachaId(ubachaId);
        ubachaDTO.setUbachaName(ubachaName);
        ubachaDTO.setUbachaBachan(ubachaBachan);
        
        KSCoreService ksCoreService = new KSCoreService();
        responseCode = ksCoreService.addUbacha(ubachaDTO);
        
        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage("Ubacha creation alert:", "Ubacha added Successfully.");
            context.addMessage(null, fm);
            return "/edit/ubacha/UbachaList?faces-redirect=true";
        } else if (responseCode == HedwigResponseCode.DB_DUPLICATE) {
            fm = new FacesMessage("Ubacha creation alert:", "Ubacha already exsists.");
            context.addMessage(null, fm);
            return "/edit/ubacha/AddNewUbacha?faces-redirect=true";
        } else {
            fm = new FacesMessage("Ubacha creation alert:", "duh! Something went wrong :(");
            context.addMessage(null, fm);
            return "/edit/ubacha/AddNewUbacha?faces-redirect=true";
        }
    }

    public int getUbachaId() {
        return ubachaId;
    }

    public void setUbachaId(int ubachaId) {
        this.ubachaId = ubachaId;
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
    
    
}
