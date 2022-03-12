package com.starwarsresistence.projetofinal.dto;

import com.starwarsresistence.projetofinal.model.LocalizationModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class LocalizationDto {

    @NotNull(message = "Field longitude cannot be null")
    private Double longitude;
    @NotNull(message = "Field latitude cannot be null")
    private Double latitude;
    @NotNull(message = "Field galaxyName cannot be null")
    @Pattern(regexp = "[a-zA-Z]{3}\\d{3}", message = "Value not in required pattern. Example ABC123")
    private String galaxyName;

}
