package com.starwarsresistence.projetofinal.controller;


import com.starwarsresistence.projetofinal.dto.TradeDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<List<RebelModel>> tradeItem(@RequestBody @Valid TradeDto tradeDto) throws NotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(tradeService.tradeItem(tradeDto));
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
