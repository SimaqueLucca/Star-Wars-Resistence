package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.dto.ItemDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
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

    public void itemsExists(List<ItemDto> items) throws NotFoundException {

        for (ItemDto item : items) {
            if (itemRepository.findByName(item.getItemName()).isEmpty()) {
                throw new NotFoundException("Item " + item.getItemName() + " not found");
            }
            try {
                if (item.getQuantity() == 0) {
                    throw new NotFoundException("Item quantity cannot be 0 or null");
                }
            } catch (Exception exception) {
                throw new NotFoundException("Item quantity cannot be 0 or null");
            }
        }
    }

    public int checkListValue(List<ItemDto> itemTradeModelList) {
        int listValue = 0;

        for (ItemDto item : itemTradeModelList) {
            for (int i = 0; i < item.getQuantity(); i++) {
                listValue += itemRepository.getItemModelsByName(item.getItemName()).get(0).getValue();
            }
        }
        System.out.print(listValue);
        return listValue;
    }

    public void checkInventory(List<ItemDto> itemTradeModelList, String id) throws NotFoundException {

        for (ItemDto itemTradeModel : itemTradeModelList) {
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

    public void itemModelExists(List<ItemDto> firstRebelItems) throws NotFoundException {

        for (ItemDto itemTradeModel : firstRebelItems) {
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

    public List<ItemModel> listOFItemModel(List<ItemDto> itemDtoList) throws NotFoundException {
        List<ItemModel> itemModelList = new ArrayList<>();

        for (ItemDto item : itemDtoList) {

            if (item.getQuantity() > 30) {
                throw new NotFoundException("The quantity of items cannot be more than 30");
            }

            ItemModel itemModel = new ItemModel();
            itemModel.setName(item.getItemName());
            itemModel.setValue(itemValue(item.getItemName()));

            for (int i = 0; i < item.getQuantity(); i++) {
                itemModelList.add(itemModel);
            }
        }

        return itemModelList;
    }

}
