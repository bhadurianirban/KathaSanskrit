/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.browse;

import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.ksaman.core.DTO.MaintextDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "shlokapaadSuchiList")
@ViewScoped
public class ShlokapaadSuchiList implements Serializable {

    /*
     * Creates a new instance of ShlokapaadSuchiList
     */
    private List<MaintextDTO> shlokapaadDTOList;
    private MaintextDTO selectedShlokapaad;
    private String firstChar;

    public void loadAllShlokapaadList() {
        KSCoreService ksCoreService = new KSCoreService();
        shlokapaadDTOList = ksCoreService.getShlokaUniqueFirstCharList();

        for (int i = 0; i < shlokapaadDTOList.size(); i++) {
            firstChar = shlokapaadDTOList.get(i).getFirstChar();
        }
    }
    
    public String shlokaListView() {
        firstChar = selectedShlokapaad.getFirstChar();
        return "ShlokaListByPaad";
    }

    public List<MaintextDTO> getShlokapaadDTOList() {
        return shlokapaadDTOList;
    }

    public void setShlokapaadDTOList(List<MaintextDTO> shlokapaadDTOList) {
        this.shlokapaadDTOList = shlokapaadDTOList;
    }

    public MaintextDTO getSelectedShlokapaad() {
        return selectedShlokapaad;
    }

    public void setSelectedShlokapaad(MaintextDTO selectedShlokapaad) {
        this.selectedShlokapaad = selectedShlokapaad;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }
}
