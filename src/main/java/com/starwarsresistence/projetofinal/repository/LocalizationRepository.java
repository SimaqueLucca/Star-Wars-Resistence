package com.starwarsresistence.projetofinal.repository;

import com.starwarsresistence.projetofinal.model.LocalizationModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizationRepository extends MongoRepository<LocalizationModel, String> {
    List<LocalizationModel> findByName(String planetName);
    List<LocalizationModel> getByNameNotNull();
}
