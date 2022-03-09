package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

public class LocalizationModel {
    @NotNull(message = "Field longitude cannot be null")
    private Double longitude;
    @NotNull(message = "Field latitude cannot be null")
    private Double latitude;
    @NotNull(message = "Field name cannot be null")
    private String galaxyName;
}
