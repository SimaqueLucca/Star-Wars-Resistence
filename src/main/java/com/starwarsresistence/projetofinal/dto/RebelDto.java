package com.starwarsresistence.projetofinal.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class RebelDto {
    @NotBlank(message = "Name cannot be null")
    @Size(min = 2)
    private String name;
    @NotNull(message = "Age cannot be null")
    @Min(1) @Max(999)
    private Integer age;
    @NotNull(message = "Gender cannot be null")
    @Size(max = 1)
    private String gender;
    @NotNull(message = "Items cannot be null")
    private List<ItemDto> items;
    @NotNull (message = "Localization cannot be null")
    private LocalizationDto localization;
}
