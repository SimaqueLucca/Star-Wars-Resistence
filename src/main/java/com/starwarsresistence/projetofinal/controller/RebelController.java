package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.RebelDto;
import com.starwarsresistence.projetofinal.model.ItemEnum;
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

import java.util.*;

import javax.validation.Valid;

import static com.starwarsresistence.projetofinal.model.ItemEnum.*;
import static com.starwarsresistence.projetofinal.model.LocalizationEnum.PlanetExist;

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
    public ResponseEntity<Object> createRebel(@RequestBody @Valid RebelDto rebelDto) {

        Boolean itemExistResult = ItemExist(rebelDto.getItems());

        if (!itemExistResult) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reported items do not exist. Available items: ARMA, MUNICAO, AGUA, COMIDA ");
        }

        Boolean planetExistResult = PlanetExist(rebelDto.getLocalization().getPlanetName().toString());

        if (!planetExistResult) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reported items do not exist. Available items: TATOOINE, ALDERAAN, YAVIN, HOTH, DAGOBAH, BESPIN, ENDOR, NABOO, CORUSCANT, KAMINO ");
        }

        List<ItemEnum> newRebelItems = new ArrayList<>();
        newRebelItems = ConvertEnum(rebelDto.getItems());

        RebelModel rebelModel = new RebelModel();
        BeanUtils.copyProperties(rebelDto, rebelModel);
        rebelModel.setItems(newRebelItems);

        return ResponseEntity.status(HttpStatus.CREATED).body(rebelService.save(rebelModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRebelById(@PathVariable ObjectId id) {
        Optional<RebelModel> rebelModelOptional = rebelService.findById(id);
        if (!rebelModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rebel not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(rebelModelOptional);
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
