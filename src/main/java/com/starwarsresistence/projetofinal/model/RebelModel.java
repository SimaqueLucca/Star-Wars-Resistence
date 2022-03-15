package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Document("TB_REBEL")
public class RebelModel {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String name;
    private Integer age;
    private GenderModel gender;
    private LocalizationModel localization;
    private List<ItemModel> items;
    private Integer traitorCount;
    private Boolean traitor;
}
