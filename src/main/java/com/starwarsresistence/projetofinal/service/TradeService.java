package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.dto.TradeDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private RebelService rebelService;
    @Autowired
    private ItemService itemService;

    public List<RebelModel> tradeItem(TradeDto tradeDto) throws MethodArgumentNotValidException, NotFoundException {

        itemService.itemsExists(tradeDto.getFirstRebelItems());
        itemService.itemsExists(tradeDto.getSecondRebelItems());

        itemService.checkInventory(tradeDto.getFirstRebelItems(), tradeDto.getFirstRebelID());
        itemService.checkInventory(tradeDto.getSecondRebelItems(), tradeDto.getSecondRebelID());

        List<ItemModel> firstRebelItems = itemService.listOFItemModel(tradeDto.getFirstRebelItems());
        List<ItemModel> secondRebelItems = itemService.listOFItemModel(tradeDto.getSecondRebelItems());

        RebelModel firstRebelModel = rebelService.getRebel(tradeDto.getFirstRebelID());
        RebelModel secondRebelModel = rebelService.getRebel(tradeDto.getSecondRebelID());

        if (itemService.checkListValue(firstRebelItems) != itemService.checkListValue(secondRebelItems)) {
            throw new IllegalArgumentException("The rebel's list of items must have the same value");
        }

        firstRebelModel.setItems(secondRebelItems);
        secondRebelModel.setItems(firstRebelItems);

        rebelService.save(firstRebelModel);
        rebelService.save(secondRebelModel);

        List<RebelModel> rebelModelList = new ArrayList<>();
        rebelModelList.add(firstRebelModel);
        rebelModelList.add(secondRebelModel);

        return rebelModelList;

    }

}
