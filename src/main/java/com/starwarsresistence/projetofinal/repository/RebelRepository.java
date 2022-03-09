package com.starwarsresistence.projetofinal.repository;

import com.starwarsresistence.projetofinal.model.RebelModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelRepository extends MongoRepository<RebelModel, ObjectId> {
}
