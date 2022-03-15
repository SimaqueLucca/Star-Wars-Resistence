package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocalizationService {
    @Autowired
    LocalizationRepository localizationRepository;

    public void planetExists(String planet) throws NotFoundException {

        if (localizationRepository.findByName(planet).isEmpty()) {
           String planets = "";
            List<LocalizationModel> localizationModelList = localizationRepository.getByNameNotNull();

            for (LocalizationModel localizationModel : localizationModelList) {
                planets = planets + "\n" + localizationModel.getName();
            }

            if (planet == null) throw new NotFoundException("Planet cannot be null. All planets. Planets: " + planets);


            throw new NotFoundException("Planet " + planet + " not found. All planets. Planets: " + planets);
        }
    }

    public void coordinateIsNull(Double coordinate) throws NotFoundException {
        if (coordinate == null)
            throw new NotFoundException("Localization parameters cannot be null. example: latitude : 49.65, longitude : 28.00 , planet : YAVIN");
    }
}
