/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.ubacha;

import java.io.Serializable;
import java.util.List;
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
@Named(value = "ubachaList")
@ViewScoped
public class UbachaList implements Serializable {

    /**
     * Creates a new instance of UbachaList
     */
    
    private List<UbachaDTO> ubachaDTOList;
    private UbachaDTO selectedUbacha;
    
    public void loadAllUbachaList() {
        KSCoreService ksCoreService = new KSCoreService();
        ubachaDTOList = ksCoreService.getUbachaDTOList();
    }
    
    public String editUbacha() {
        return "/edit/ubacha/UbachaEdit?faces-redirect=true&ubachaId=" + selectedUbacha.getUbachaId();
    }
    
    public String deleteUbacha() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);
        FacesMessage fm;
        int responseCode;
        int ubachaId = selectedUbacha.getUbachaId();
        
        UbachaDTO ubachaDTO = new UbachaDTO();
        ubachaDTO.setUbachaId(ubachaId);
        
        KSCoreService ksCoreService = new KSCoreService();
        responseCode = ksCoreService.removeUbacha(ubachaDTO);
        
        if (responseCode == HedwigResponseCode.SUCCESS) {
            fm = new FacesMessage("Ubacha delete alert:", "Ubacha data deleted Successfully.");
            context.addMessage(null, fm);
            return "/edit/ubacha/UbachaList?faces-redirect=true";
        } else if (responseCode == HedwigResponseCode.DB_NON_EXISTING) {
            fm = new FacesMessage("Ubacha delete alert:", "Selected Ubacha not found.");
            context.addMessage(null, fm);
            return "/edit/ubacha/UbachaList?faces-redirect=true";
        } else {
            fm = new FacesMessage("Ubacha delete alert:", "Something went wrong, please contact admin.");
            context.addMessage(null, fm);
            return "/edit/ubacha/UbachaList?faces-redirect=true";
        }
    }

    public List<UbachaDTO> getUbachaDTOList() {
        return ubachaDTOList;
    }

    public void setUbachaDTOList(List<UbachaDTO> ubachaDTOList) {
        this.ubachaDTOList = ubachaDTOList;
    }

    public UbachaDTO getSelectedUbacha() {
        return selectedUbacha;
    }

    public void setSelectedUbacha(UbachaDTO selectedUbacha) {
        this.selectedUbacha = selectedUbacha;
    }
}
