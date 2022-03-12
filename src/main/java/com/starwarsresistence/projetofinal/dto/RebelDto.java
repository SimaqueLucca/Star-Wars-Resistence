package com.starwarsresistence.projetofinal.dto;

import com.starwarsresistence.projetofinal.model.ItemEnum;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RebelDto {

    @NotBlank(message = "Field name cannot be null")
    @Size(min = 2)
    private String name;

    @NotNull(message = "Field age cannot be null")
    private Integer age;

    @NotNull(message = "Field gender cannot be null")
    @Size(max = 1)
    private String gender;

    @NotEmpty(message = "items cannot be null")
    private List<ItemEnum> items;

    private LocalizationModel localization;
}
