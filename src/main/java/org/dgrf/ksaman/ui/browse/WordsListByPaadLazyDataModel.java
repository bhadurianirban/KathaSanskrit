/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.browse;

import java.util.List;
import java.util.Map;
import org.ksaman.core.DTO.WordsDTO;
import org.ksaman.core.bl.service.KSCoreService;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author dgrfiv
 */
public class WordsListByPaadLazyDataModel extends LazyDataModel<WordsDTO> {
    public String firstChar;
    
    public WordsListByPaadLazyDataModel(String firstChar) {
        
        this.firstChar = firstChar;
        KSCoreService kSCoreService = new KSCoreService();
        
        this.setRowCount(kSCoreService.getWordsCountByFirstChar(firstChar));
    }
    
    @Override
    public List<WordsDTO> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, FilterMeta> filters) {
        KSCoreService kSCoreService = new KSCoreService();
        List<WordsDTO> listWordsByPaad = kSCoreService.getWordsListByFirstChar(firstChar, first, pageSize);
        return listWordsByPaad;
    }
}
