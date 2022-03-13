package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum ItemEnum {
    ARMA(4), MUNICAO(3), AGUA(2), COMIDA(1);

    private Integer value;

    ItemEnum(Integer value) {
        this.value = value;
    }

    public static boolean ItemExist(List<String> rebelItems) {

        List<String> listEnum = new ArrayList<>();
        for (ItemEnum itemEnum : values()) {
            listEnum.add(itemEnum.name());
        }

        for (String rebelItem : rebelItems) {
            if (!listEnum.contains(rebelItem)) {
                return false;
            }
        }
        return true;
    }

    public static List<ItemEnum> ConvertEnum(List<String> rebelItems) {
        List<ItemEnum> listEnum = new ArrayList<>();

        for (String rebelItem : rebelItems) {
            listEnum.add(ItemEnum.valueOf(rebelItem));
        }

        return listEnum;
    }

}
