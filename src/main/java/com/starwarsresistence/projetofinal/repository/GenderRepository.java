package com.starwarsresistence.projetofinal.repository;

import com.starwarsresistence.projetofinal.model.GenderModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenderRepository extends MongoRepository<GenderModel, String> {
    @Query("{ $or : [{'name': ?0}, {'identifier': ?0}]}")
    List<GenderModel> findByName(String name);

    @Query("{ $or : [{'name': ?0}, {'identifier': ?0}]}")
    List<GenderModel> getGenderData(String value);
}
