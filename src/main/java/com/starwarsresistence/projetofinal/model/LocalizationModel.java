package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("TB_LOCALIZATION")
public class LocalizationModel {
    private Double longitude;
    private Double latitude;
    private String name;
}
