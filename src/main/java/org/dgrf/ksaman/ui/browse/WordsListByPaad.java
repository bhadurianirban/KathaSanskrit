/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.browse;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.ksaman.core.DTO.WordsDTO;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author dgrfiv
 */
@Named(value = "wordsListByPaad")
@ViewScoped
public class WordsListByPaad implements Serializable {

    /**
     * Creates a new instance of WordsListByPaad
     */
    private LazyDataModel<WordsDTO> wordDTOList;
    private WordsDTO selectedWord;
    private int parvaId;
    private int adhyayId;
    private int shlokaLine;
    private int shlokaNum;
    private String ubachaName;
    private String ubachaBachan;
    private String firstChar;
    private String parvaName;
    private String wordText;
    private String shlokaText;

    public void loadAllShlokaList() {
        wordDTOList = new WordsListByPaadLazyDataModel(firstChar);
    }
    
    public String translationView() {
        parvaId = selectedWord.getParvaId();
        adhyayId = selectedWord.getAdhyayId();
        shlokaNum = selectedWord.getShlokaNum();
        return "TranslatedTextView";
    }

    public LazyDataModel<WordsDTO> getWordDTOList() {
        return wordDTOList;
    }

    public void setWordDTOList(LazyDataModel<WordsDTO> wordDTOList) {
        this.wordDTOList = wordDTOList;
    }

    public WordsDTO getSelectedWord() {
        return selectedWord;
    }

    public void setSelectedWord(WordsDTO selectedWord) {
        this.selectedWord = selectedWord;
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

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public String getParvaName() {
        return parvaName;
    }

    public void setParvaName(String parvaName) {
        this.parvaName = parvaName;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public String getShlokaText() {
        return shlokaText;
    }

    public void setShlokaText(String shlokaText) {
        this.shlokaText = shlokaText;
    }
    
}
