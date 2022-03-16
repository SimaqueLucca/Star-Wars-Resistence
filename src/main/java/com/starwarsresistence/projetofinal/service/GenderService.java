package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.GenderModel;
import com.starwarsresistence.projetofinal.repository.GenderRepository;
import org.springframework.stereotype.Service;


@Service
public class GenderService {
    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public void genderExists(String gender) throws NotFoundException {

        if (genderRepository.findByName(gender).isEmpty()) {
            throw new NotFoundException("Gender " + gender + " not found");
        }
    }

    public GenderModel getGenderData(String value) {
        return genderRepository.getGenderData(value).get(0);
    }
}

