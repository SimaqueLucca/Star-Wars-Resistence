package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.dto.TradeDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.ItemTradeModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    private final RebelService rebelService;
    private final ItemService itemService;

    public TradeService(ItemService itemService, RebelService rebelService) {
        this.itemService = itemService;
        this.rebelService = rebelService;
    }


    public List<RebelModel> tradeItem(TradeDto tradeDto) throws NotFoundException {

        itemService.checkInventory(tradeDto.getFirstRebelItems(), tradeDto.getFirstRebelID());
        itemService.checkInventory(tradeDto.getSecondRebelItems(), tradeDto.getSecondRebelID());

        List<ItemTradeModel> firstRebelItems = tradeDto.getFirstRebelItems();
        List<ItemTradeModel> secondRebelItems = tradeDto.getSecondRebelItems();

        itemService.itemModelExists(tradeDto.getFirstRebelItems());
        itemService.itemModelExists(tradeDto.getSecondRebelItems());

        RebelModel firstRebelModel = rebelService.getRebel(tradeDto.getFirstRebelID());
        RebelModel secondRebelModel = rebelService.getRebel(tradeDto.getSecondRebelID());

        if (itemService.checkListValue(firstRebelItems) != itemService.checkListValue(secondRebelItems)) {
            throw new NotFoundException("The rebel's list of items must have the same value");
        }

        firstRebelModel.setItems(itemModelList(secondRebelItems));
        secondRebelModel.setItems(itemModelList(firstRebelItems));

        rebelService.save(firstRebelModel);
        rebelService.save(secondRebelModel);

        List<RebelModel> rebelModelList = new ArrayList<>();
        rebelModelList.add(firstRebelModel);
        rebelModelList.add(secondRebelModel);

        return rebelModelList;

    }

    public List<ItemModel> itemModelList(List<ItemTradeModel> itemTradeModelList) throws NotFoundException {

        List<ItemModel> itemModelList = new ArrayList<>();

        for (ItemTradeModel itemTradeModel : itemTradeModelList) {
            for (int i = 0; i < itemTradeModel.getQuantity(); i++) {
                ItemModel itemModel = itemService.getItemByName(itemTradeModel.getItemName());
                itemModelList.add(itemModel);
            }
        }

        return itemModelList;
    }

}
