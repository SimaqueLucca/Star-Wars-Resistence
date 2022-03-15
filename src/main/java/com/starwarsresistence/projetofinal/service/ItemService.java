package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public void itemsExists(List<String> items) throws NotFoundException {

        for (String item : items) {
            if (itemRepository.findByName(item).isEmpty()) {
                throw new NotFoundException("Item " + item + " not found");
            }
        }
    }

    public int itemValue(String name) {
        ItemModel itemModel = itemRepository.getItemValue(name).get(0);
        return itemModel.getValue();
    }

    public List<ItemModel> listOFItemModel(List<String> itemDtoList) {
        List<ItemModel> itemModelList = new ArrayList<>();

        for (String item : itemDtoList) {
            ItemModel itemModel = new ItemModel();
            itemModel.setName(item);
            itemModel.setValue(itemValue(item));
            itemModelList.add(itemModel);
        }

        return itemModelList;
    }
}
