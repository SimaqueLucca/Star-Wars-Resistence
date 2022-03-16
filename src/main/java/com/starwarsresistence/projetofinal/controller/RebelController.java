package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.RebelDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.service.RebelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/rebel")
public class RebelController {
    @Autowired
    private RebelService rebelService;

    @GetMapping
    public ResponseEntity<List<RebelModel>> getAllRebels() {
        return ResponseEntity.status(HttpStatus.OK).body(rebelService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> createRebel(@RequestBody @Valid RebelDto rebelDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(rebelService.save(rebelDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RebelModel> getRebelById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(rebelService.getRebel(id));
    }

    @PostMapping("/report-traitor/{id}")
    public String reportTraitor(@PathVariable String id) {
        return rebelService.saveReport(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
