package com.starwarsresistence.projetofinal.model;

import lombok.Getter;

import java.util.*;

@Getter
public enum LocalizationEnum {
    TATOOINE, ALDERAAN, YAVIN, HOTH, DAGOBAH, BESPIN, ENDOR, NABOO, CORUSCANT, KAMINO;

    private static final List<LocalizationEnum> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = values.size();
    private static final Random random = new Random();

    public static LocalizationEnum randomLocalization() {
        return values.get(random.nextInt(size));
    }

    public static boolean PlanetExist(String planetRebel) {
        return Arrays.stream(LocalizationEnum.values()).anyMatch(planet -> planet.name().equalsIgnoreCase(planetRebel));
    }

    public static LocalizationEnum ConvertEnum(String planetRebel) {
        return LocalizationEnum.valueOf(planetRebel);
    }

}
