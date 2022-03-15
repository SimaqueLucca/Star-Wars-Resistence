package com.starwarsresistence.projetofinal.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RebelDto {
    @NotBlank(message = "Name cannot be null")
    @Size(min = 2)
    private String name;
    @NotNull(message = "Age cannot be null")
    private Integer age;
    @NotNull(message = "Gender cannot be null")
    @Size(max = 1)
    private String gender;
    @NotEmpty(message = "Items cannot be null")
    private List<String> items;
    @NotNull (message = "Localization cannot be null")
    private LocalizationDto localization;
}
