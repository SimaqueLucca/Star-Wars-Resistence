package com.starwarsresistence.projetofinal.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LocalizationDto {

    @NotNull(message = "Field longitude cannot be null")
    private Double longitude;
    @NotNull(message = "Field latitude cannot be null")
    private Double latitude;
    @NotNull(message = "Field planetName cannot be null")
    private String planetName;
}
