package com.starwarsresistence.projetofinal.dto;

import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
public class RebelDto {

    @NotBlank
    private String name;
    @NotBlank
    private Integer age;
    @NotBlank @Size(max = 1)
    private Character gender;
    @NotBlank
    private LocalizationModel localization;
    private List<ItemModel> items;
}


