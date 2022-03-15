package com.starwarsresistence.projetofinal.repository;

import com.starwarsresistence.projetofinal.model.ItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, String> {
    @Query("{'name': ?0 }")
    List<ItemModel> findByName(String itemName);

    @Query("{'name': ?0 }")
    List<ItemModel> getItemValue(String itemName);
}
