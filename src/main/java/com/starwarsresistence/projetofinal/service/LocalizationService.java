package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.dto.LocalizationDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.LocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocalizationService {
    @Autowired
    private LocalizationRepository localizationRepository;
    @Autowired
    private RebelService rebelService;

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

    public LocalizationModel updateLocalization(LocalizationDto localizationDto, String id) throws NotFoundException {
        rebelService.rebelExists(id);
        RebelModel rebelModel = rebelService.getRebel(id);

        planetExists(localizationDto.getName());
        coordinateIsNull(localizationDto.getLatitude());
        coordinateIsNull(localizationDto.getLongitude());

        LocalizationModel localizationModel = new LocalizationModel();
        localizationModel.setName(localizationDto.getName());
        localizationModel.setLongitude(localizationDto.getLongitude());
        localizationModel.setLatitude(localizationDto.getLatitude());
        rebelModel.setLocalization(localizationModel);

        rebelService.save(rebelModel);

        return localizationModel;

    }
}
