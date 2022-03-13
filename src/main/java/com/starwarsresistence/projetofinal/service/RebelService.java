package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.RebelRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RebelService {

    @Autowired
    private RebelRepository rebelRepository;

    public List<RebelModel> findAll() {
        return rebelRepository.findAll();
    }

    @Transactional
    public RebelModel save(RebelModel rebelModel) {

        if (rebelModel.getLocalization() == null) {
            rebelModel.setLocalization(LocalizationModel.generateLocalization());
        }

        rebelModel.setTraitorCount(0);
        return rebelRepository.save(rebelModel);
    }

    public Optional<RebelModel> findById(ObjectId id) {
        return rebelRepository.findById(id);
    }
}
