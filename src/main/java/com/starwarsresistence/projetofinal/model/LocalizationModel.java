package com.starwarsresistence.projetofinal.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class LocalizationModel {
    private Double longitude;
    private Double latitude;
    private String galaxyName;


    public static LocalizationModel generateLocalization(){
        Random random = new Random();

        Double latitude = 1.0 + (99.0 - 1.0) * random.nextDouble();
        Double longitude = 1.0 + (99.0 - 1.0) * random.nextDouble();

        String galaxyName = Character.toString((char) ('a' + random.nextInt(26)))
                + Character.toString((char) ('A' + random.nextInt(26)))
                + Character.toString((char) ('A' + random.nextInt(26)))
                + Integer.toString(random.nextInt(999 - 001) + 001);

        LocalizationModel localizationModel = new LocalizationModel();

        localizationModel.setLatitude(latitude);
        localizationModel.setLongitude(longitude);
        localizationModel.setGalaxyName(galaxyName);

        return localizationModel;
    }

}
