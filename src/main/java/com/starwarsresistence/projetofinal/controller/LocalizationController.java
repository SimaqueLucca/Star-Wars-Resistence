package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.LocalizationDto;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.service.RebelService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/localization")
public class LocalizationController {

    @Autowired
    private RebelService rebelService;

    @PutMapping("/{id}")
    public ResponseEntity<Object> changeLocalization(@RequestBody @Valid LocalizationDto localizationDto, @PathVariable ObjectId id){
        Optional<RebelModel> rebelModelOptional = rebelService.findById(id);

        if (!rebelModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rebel not found");
        }
        RebelModel rebelModel = new RebelModel();
        BeanUtils.copyProperties(rebelModelOptional.get(), rebelModel);

        LocalizationModel localizationModel = new LocalizationModel();
        BeanUtils.copyProperties(localizationDto, localizationModel);

        rebelModel.setLocalization(localizationModel);

        rebelService.save(rebelModel);

        return ResponseEntity.status(HttpStatus.OK).body(localizationModel);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return errors;
    }
}
