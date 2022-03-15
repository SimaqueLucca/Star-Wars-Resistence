package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.LocalizationDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.service.LocalizationService;
import com.starwarsresistence.projetofinal.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/localization")
public class LocalizationController {
    @Autowired
    RebelService rebelService;

    @Autowired
    LocalizationService localizationService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatelocalization(@RequestBody @Valid LocalizationDto localizationDto, @PathVariable String id) throws NotFoundException {
        rebelService.rebelExists(id);
        RebelModel rebelModel = rebelService.getRebel(id);

        localizationService.planetExists(localizationDto.getName());
        localizationService.coordinateIsNull(localizationDto.getLatitude());
        localizationService.coordinateIsNull(localizationDto.getLongitude());

        LocalizationModel localizationModel = new LocalizationModel();
        localizationModel.setName(localizationDto.getName());
        localizationModel.setLongitude(localizationDto.getLongitude());
        localizationModel.setLatitude(localizationDto.getLatitude());
        rebelModel.setLocalization(localizationModel);

        rebelService.save(rebelModel);

        return ResponseEntity.status(HttpStatus.OK).body(localizationModel);
    }
}
