package com.starwarsresistence.projetofinal.controller;

import com.starwarsresistence.projetofinal.dto.RebelDto;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.service.RebelService;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<RebelModel> createRebel(@RequestBody RebelDto rebelDto) {
        RebelModel rebelModel = new RebelModel();
        BeanUtils.copyProperties(rebelDto, rebelModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(rebelService.save(rebelModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RebelModel> getRebelById(@PathVariable ObjectId id) {
        return ResponseEntity.status(HttpStatus.OK).body(rebelService.findById(id));
    }
}
