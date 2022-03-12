package com.starwarsresistence.projetofinal.model;

import lombok.Getter;

@Getter
public enum ItemEnum {
    ARMA(4), MUNICAO(3), AGUA(2), COMIDA(1);

    private Integer value;

    ItemEnum(Integer value){
        this.value = value;
    }

    public static boolean itemExist(String itemName){
        for (ItemEnum value : ItemEnum.values()){
            if (itemName == value.name()){
                return true;
            }
        }
        return false;
    }

}
