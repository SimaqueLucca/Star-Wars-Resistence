package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.LocalizationDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.service.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/localization")
public class LocalizationController {

    @Autowired
    private LocalizationService localizationService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatelocalization(@RequestBody @Valid LocalizationDto localizationDto, @PathVariable String id) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(localizationService.updateLocalization(localizationDto, id));
    }
}
