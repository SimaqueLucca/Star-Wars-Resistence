package com.starwarsresistence.projetofinal.dto;

import com.starwarsresistence.projetofinal.model.ItemTradeModel;
import com.starwarsresistence.projetofinal.validation.NotTraitor.NotTraitor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class TradeDto {

    @NotTraitor()
    private String firstRebelID;
    @NotNull
    private List<ItemTradeModel> firstRebelItems;
    @NotNull
    @NotTraitor()
    private String secondRebelID;
    @NotNull
    private List<ItemTradeModel> secondRebelItems;

}
