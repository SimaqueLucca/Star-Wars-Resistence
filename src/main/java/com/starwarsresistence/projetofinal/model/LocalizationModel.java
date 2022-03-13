package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Random;

import static com.starwarsresistence.projetofinal.model.LocalizationEnum.randomLocalization;

@Getter
@Setter
public class LocalizationModel {
    private Double longitude;
    private Double latitude;
    private LocalizationEnum planetName;


    public static LocalizationModel generateLocalization() {
        Double latitude = Math.floor(Math.random() * (99 - 1) + 1);
        Double longitude = Math.floor(Math.random() * (99 - 1) + 1);

        LocalizationModel localizationModel = new LocalizationModel();

        localizationModel.setLatitude(latitude);
        localizationModel.setLongitude(longitude);
        localizationModel.setPlanetName(randomLocalization());

        return localizationModel;
    }

}
