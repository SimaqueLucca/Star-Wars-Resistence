package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.ItemTradeModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.ItemRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final RebelService rebelService;

    public ItemService(@Lazy RebelService rebelService, ItemRepository itemRepository) {
        this.rebelService = rebelService;
        this.itemRepository = itemRepository;
    }

    public int itemValue(String name) {
        return itemRepository.getItemModelsByName(name).get(0).getValue();
    }

    public void itemsExists(List<String> items) throws NotFoundException {

        for (String item : items) {
            if (itemRepository.findByName(item).isEmpty()) {
                throw new NotFoundException("Item " + item + " not found");
            }
        }
    }


    public int checkListValue(List<ItemTradeModel> itemTradeModelList) {
        int listValue = 0;

        for (ItemTradeModel item : itemTradeModelList) {
            for (int i = 0; i < item.getQuantity(); i++) {
                listValue += itemRepository.getItemModelsByName(item.getItemName()).get(0).getValue();
            }
        }

        System.out.print(listValue);

        return listValue;
    }

    public void checkInventory(List<ItemTradeModel> itemTradeModelList, String id) throws NotFoundException {

        for (ItemTradeModel itemTradeModel : itemTradeModelList) {

            RebelModel rebelModel = rebelService.getRebel(id);

            List<ItemModel> rebelItemModelList = rebelModel.getItems();

            for (int i = 0; i < itemTradeModel.getQuantity(); i++) {

                Optional<ItemModel> optionalItemModel = rebelItemModelList.stream().filter(item -> item.getName().equals(itemTradeModel.getItemName())).findFirst();

                if (optionalItemModel.isEmpty()) {
                    throw new NotFoundException("The rebel does not have the item or does not have the quantity informed: " + itemTradeModel.getItemName());
                }

                rebelItemModelList.remove(optionalItemModel.get());
            }

        }


    }

    public void itemModelExists(List<ItemTradeModel> firstRebelItems) throws NotFoundException {

        for (ItemTradeModel itemTradeModel : firstRebelItems) {
            if (itemRepository.findByName(itemTradeModel.getItemName()).isEmpty()) {
                throw new NotFoundException("Item " + itemTradeModel + " not found");
            }
        }

    }

    public ItemModel getItemByName(String name) throws NotFoundException {

        if (itemRepository.findByName(name).isEmpty()) {
            throw new NotFoundException("Item " + name + " not found");
        }

        return itemRepository.findByName(name).get(0);
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
