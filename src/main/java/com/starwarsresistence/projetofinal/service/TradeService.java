package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.dto.ItemDto;
import com.starwarsresistence.projetofinal.dto.TradeDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    private final RebelService rebelService;
    private final ItemService itemService;

    public TradeService(ItemService itemService, RebelService rebelService) {
        this.itemService = itemService;
        this.rebelService = rebelService;
    }


    public List<RebelModel> tradeItem(TradeDto tradeDto) throws NotFoundException {

        rebelService.rebelExists(tradeDto.getFirstRebelID());
        rebelService.rebelExists(tradeDto.getSecondRebelID());

        if (tradeDto.getFirstRebelID().equals(tradeDto.getSecondRebelID())) {
            throw new NotFoundException("The exchange cannot be made between the same rebel");
        }

        itemService.checkInventory(tradeDto.getFirstRebelItems(), tradeDto.getFirstRebelID());
        itemService.checkInventory(tradeDto.getSecondRebelItems(), tradeDto.getSecondRebelID());

        List<ItemDto> firstRebelItems = tradeDto.getFirstRebelItems();
        List<ItemDto> secondRebelItems = tradeDto.getSecondRebelItems();

        itemService.itemModelExists(tradeDto.getFirstRebelItems());
        itemService.itemModelExists(tradeDto.getSecondRebelItems());

        RebelModel firstRebelModel = rebelService.getRebel(tradeDto.getFirstRebelID());
        RebelModel secondRebelModel = rebelService.getRebel(tradeDto.getSecondRebelID());

        if (itemService.checkListValue(firstRebelItems) != itemService.checkListValue(secondRebelItems)) {
            throw new NotFoundException("The rebel's list of items must have the same value");
        }

        List<ItemModel> newFirstRebelItems = doTrade(firstRebelModel, firstRebelItems, itemModelList(secondRebelItems));
        List<ItemModel> newSecondRebelItems = doTrade(secondRebelModel, secondRebelItems, itemModelList(firstRebelItems));

        firstRebelModel.setItems(newFirstRebelItems);
        secondRebelModel.setItems(newSecondRebelItems);

        rebelService.save(firstRebelModel);
        rebelService.save(secondRebelModel);

        List<RebelModel> rebelModelList = new ArrayList<>();
        rebelModelList.add(firstRebelModel);
        rebelModelList.add(secondRebelModel);

        return rebelModelList;

    }

    public List<ItemModel> doTrade(RebelModel rebelModel, List<ItemDto> outItemList, List<ItemModel> inputItemList) {

        List<ItemModel> rebelItems = rebelModel.getItems();

        for (ItemDto itemDto : outItemList) {
            for (int i = 0; i < itemDto.getQuantity(); i++) {
                Optional<ItemModel> optionalItemModel = rebelItems.stream().filter(itemModel -> itemModel.getName().equals(itemDto.getItemName())).findFirst();
                optionalItemModel.ifPresent(rebelItems::remove);
            }
        }

        inputItemList.addAll(rebelItems);

        return inputItemList;
    }

    public List<ItemModel> itemModelList(List<ItemDto> itemTradeModelList) throws NotFoundException {

        List<ItemModel> itemModelList = new ArrayList<>();

        for (ItemDto itemTradeModel : itemTradeModelList) {
            for (int i = 0; i < itemTradeModel.getQuantity(); i++) {
                ItemModel itemModel = itemService.getItemByName(itemTradeModel.getItemName());
                itemModelList.add(itemModel);
            }
        }

        return itemModelList;
    }

}
