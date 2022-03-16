package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    private final RebelService rebelService;

    public ItemService(@Lazy RebelService rebelService) {
        this.rebelService = rebelService;
    }

    public void itemsExists(List<String> items) throws NotFoundException {

        for (String item : items) {
            if (itemRepository.findByName(item).isEmpty()) {
                throw new NotFoundException("Item " + item + " not found");
            }
        }
    }

    public int itemValue(String name) {
        return itemRepository.getItemModelsByName(name).get(0).getValue();
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

    public int checkListValue(List<ItemModel> itemModelList) {
        int listValue = 0;

        for (ItemModel itemModel : itemModelList) {
            listValue += itemRepository.getItemModelsByName(itemModel.getName()).get(0).getValue();
        }

        return listValue;
    }

    public void checkInventory(List<String> itemList, String id) throws NotFoundException {

        int index = 0;
        RebelModel rebelModel = rebelService.getRebel(id);
        List<ItemModel> itemModelList = rebelModel.getItems();

        for (String item : itemList) {
            if (itemModelList.stream().noneMatch(itemModel -> itemModel.getName().equals(item))) {
                throw new NotFoundException("The rebel does not have the item or does not have the quantity informed: " + item);
            }

            Optional<ItemModel> optionalItemModel = itemModelList.stream().filter(itemModel -> itemModel.getName().equals(item)).findFirst();
            if (!optionalItemModel.isPresent()) {
                throw new NotFoundException("The rebel does not have the item or does not have the quantity informed: " + item);
            }
            itemModelList.remove(optionalItemModel.get());
        }

    }
}
