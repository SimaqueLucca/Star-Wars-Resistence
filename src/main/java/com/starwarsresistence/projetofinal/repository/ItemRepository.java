package com.starwarsresistence.projetofinal.repository;

import com.starwarsresistence.projetofinal.model.ItemModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<ItemModel, String> {
    List<ItemModel> findByName(String itemName);

    List<ItemModel> getItemValue(String itemName);
}
