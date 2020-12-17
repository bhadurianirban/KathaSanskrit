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
import org.ksaman.core.DTO.WordsDTO;
import org.ksaman.core.bl.service.KSCoreService;

/**
 *
 * @author dgrfiv
 */
@Named(value = "wordspaadSuchiList")
@ViewScoped
public class WordspaadSuchiList implements Serializable {

    /**
     * Creates a new instance of WordspaadSuchiList
     */
    private List<WordsDTO> wordspaadDTOList;
    private WordsDTO selectedWordspaad;
    private String firstChar;

    public void loadAllWordspaadList() {
        KSCoreService ksCoreService = new KSCoreService();
        wordspaadDTOList = ksCoreService.getShlokaWordUniqueFirstCharList();

//        for (int i = 0; i < wordspaadDTOList.size(); i++) {
//            firstChar = wordspaadDTOList.get(i).getWordFirstChar();
//        }
    }
    
    public String wordsListView() {
        firstChar = selectedWordspaad.getWordFirstChar();
        return "WordsListByPaad";
    }

    public List<WordsDTO> getWordspaadDTOList() {
        return wordspaadDTOList;
    }

    public void setWordspaadDTOList(List<WordsDTO> wordspaadDTOList) {
        this.wordspaadDTOList = wordspaadDTOList;
    }

    public WordsDTO getSelectedWordspaad() {
        return selectedWordspaad;
    }

    public void setSelectedWordspaad(WordsDTO selectedWordspaad) {
        this.selectedWordspaad = selectedWordspaad;
    }
    
    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }
    
}
