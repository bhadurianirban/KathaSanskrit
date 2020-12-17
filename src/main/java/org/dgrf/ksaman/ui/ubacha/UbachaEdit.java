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
import org.ksaman.core.DTO.UbachaDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "ubachaEdit")
@ViewScoped
public class UbachaEdit implements Serializable {

    /**
     * Creates a new instance of UbachaEdit
     */
    private UbachaDTO ubachaDTO;
    private int ubachaId;
    private String ubachaName;
    private String ubachaBachan;
    
    public void fillValues() {
        KSCoreService ksCoreService = new KSCoreService();
        ubachaDTO = new UbachaDTO();
        
        ubachaDTO.setUbachaId(ubachaId);
        
        ubachaDTO = ksCoreService.getUbachaDTO(ubachaDTO);
        
        ubachaId = ubachaDTO.getUbachaId();
        ubachaName = ubachaDTO.getUbachaName();
        ubachaBachan = ubachaDTO.getUbachaBachan();
    }
    
    public String updateSelectedUbacha() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        KSCoreService ksCoreService = new KSCoreService();
        ubachaDTO = new UbachaDTO();
        ubachaDTO.setUbachaId(ubachaId);
        ubachaDTO.setUbachaName(ubachaName);
        ubachaDTO.setUbachaBachan(ubachaBachan);
        
        responseCode = ksCoreService.updateUbacha(ubachaDTO);
        
        if (responseCode == 0) {
            fm = new FacesMessage("Ubacha changes alert:", "Ubacha data changed Successfully.");
            context.addMessage(null, fm);
            return "/edit/ubacha/UbachaList?faces-redirect=true";
        } else {
            fm = new FacesMessage("Ubacha changes alert:", "Either Ubacha not found or something went wrong.");
            context.addMessage(null, fm);
            return "/edit/ubacha/UbachaEdit?faces-redirect=true&ubachaId=" + ubachaId;
        }
    }

    public UbachaDTO getUbachaDTO() {
        return ubachaDTO;
    }

    public void setUbachaDTO(UbachaDTO ubachaDTO) {
        this.ubachaDTO = ubachaDTO;
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
