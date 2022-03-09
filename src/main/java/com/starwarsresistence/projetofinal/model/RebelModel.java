package com.starwarsresistence.projetofinal.model;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
@Document("TB_REBEL")
public class RebelModel {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @NotNull (message = "Field name cannot be null")
    private String name;
    @NotNull (message = "Field age cannot be null")
    private Integer age;
    @NotNull (message = "Field gender cannot be null")
    private Character gender;
    @NotNull (message = "Field localization cannot be null")
    private LocalizationModel localization;
    private List<ItemModel> items;
    private Integer traitorCount;
}
