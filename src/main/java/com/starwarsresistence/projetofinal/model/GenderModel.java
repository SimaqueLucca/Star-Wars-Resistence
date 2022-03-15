package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("TB_GENDER")
public class GenderModel {
    String name;
    String identifier;
}
