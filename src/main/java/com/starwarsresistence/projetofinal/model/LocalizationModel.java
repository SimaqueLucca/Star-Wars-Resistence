package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotNull;

@Getter @Setter
@Document("TB_LOCALIZATION")
public class LocalizationModel {
    @NotNull(message = "Field longitude cannot be null")
    private Double longitude;
    @NotNull (message = "Field latitude cannot be null")
    private Double latitude;
    @NotNull (message = "Field name cannot be null")
    private String galaxyName;
}
