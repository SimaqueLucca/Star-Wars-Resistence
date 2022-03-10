package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.RebelDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<RebelModel> createRebel(@RequestBody @Valid RebelDto rebelDto) {
        RebelModel rebelModel = new RebelModel();
        BeanUtils.copyProperties(rebelDto, rebelModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(rebelService.save(rebelModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RebelModel> getRebelById(@PathVariable ObjectId id) {
        return ResponseEntity.status(HttpStatus.OK).body(rebelService.findById(id));
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
