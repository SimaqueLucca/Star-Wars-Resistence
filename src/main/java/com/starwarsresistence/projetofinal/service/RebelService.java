package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.RebelRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class RebelService {

    @Autowired
    private RebelRepository rebelRepository;

    public List<RebelModel> findAll() {
        return rebelRepository.findAll();
    }

    @Transactional
    public RebelModel save(RebelModel rebelModel) {
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

        rebelModel.setLocalization(localizationModel);
        rebelModel.setTraitorCount(0);

        return rebelRepository.save(rebelModel);
    }

    public RebelModel findById(ObjectId id) {
        return rebelRepository.findById(id).get();
    }
}
