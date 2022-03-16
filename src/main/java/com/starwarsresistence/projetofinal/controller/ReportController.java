package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.model.PercentageReportModel;
import com.starwarsresistence.projetofinal.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private RebelService rebelService;

    @GetMapping
    public PercentageReportModel percentageReport() {
        return rebelService.getPercentageReport();
    }


}
