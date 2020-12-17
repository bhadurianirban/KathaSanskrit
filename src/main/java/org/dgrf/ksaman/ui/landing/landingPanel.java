/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dgrf.ksaman.ui.landing;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.ksaman.core.DTO.MaintextDTO;
import org.ksaman.core.bl.service.KSCoreService;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author dgrfiv
 */
@Named(value = "landingPanel")
@ViewScoped
public class landingPanel implements Serializable {

    /**
     * Creates a new instance of landingPanel
     */

    private PieChartModel model;

    @PostConstruct
    public void init() {
        KSCoreService kSCoreService = new KSCoreService();
        MaintextDTO maintextDTO = kSCoreService.getTranslationPercentage();
        
        model = new PieChartModel();
        model.set("Shloka", maintextDTO.getShlokaNumCount());
        model.set("Translations", maintextDTO.getShlokaNumTranslatedCount());

        //followings are some optional customizations:
        //set title
        model.setTitle("2019 Mahabharat Shloka Chart");
        //set legend position to 'e' (east), other values are 'w', 's' and 'n'
        model.setLegendPosition("e");
        //enable tooltips
        model.setShowDatatip(true);
        //show labels inside pie chart
        model.setShowDataLabels(true);
        //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
        model.setDataFormat("percent");
        //format: %d for 'value', %s for 'label', %d%% for 'percent'
        model.setDataLabelFormatString("%d%%");
        //pie sector colors
        model.setSeriesColors("aaf,afa");
    }

    public PieChartModel getModel() {
        return model;
    }

}
