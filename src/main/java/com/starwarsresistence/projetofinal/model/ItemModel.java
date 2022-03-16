package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("TB_ITEM")
public class ItemModel {
    private String name;
    private int value;
}
