package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.dto.RebelDto;
import com.starwarsresistence.projetofinal.exception.NotFoundException;
import com.starwarsresistence.projetofinal.model.GenderModel;
import com.starwarsresistence.projetofinal.model.ItemModel;
import com.starwarsresistence.projetofinal.model.LocalizationModel;
import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.RebelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RebelService {
    @Autowired
    private RebelRepository rebelRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    LocalizationService localizationService;
    @Autowired
    GenderService genderService;

    public List<RebelModel> findAll() {
        return rebelRepository.findAll();
    }

    @Transactional
    public RebelModel save(RebelDto rebelDto) throws NotFoundException {
        RebelModel rebelModel = new RebelModel();

        itemService.itemsExists(rebelDto.getItems());
        List<ItemModel> itemModelList = itemService.listOFItemModel(rebelDto.getItems());
        rebelModel.setItems(itemModelList);

        localizationService.planetExists(rebelDto.getLocalization().getName());
        localizationService.coordinateIsNull(rebelDto.getLocalization().getLatitude());
        localizationService.coordinateIsNull(rebelDto.getLocalization().getLongitude());

        LocalizationModel localizationModel = new LocalizationModel();
        localizationModel.setLongitude(rebelDto.getLocalization().getLongitude());
        localizationModel.setLatitude(rebelDto.getLocalization().getLatitude());
        localizationModel.setName(rebelDto.getLocalization().getName());
        rebelModel.setLocalization(localizationModel);

        genderService.genderExists(rebelDto.getGender());
        GenderModel genderModel = new GenderModel();
        genderModel.setName(genderService.getGenderData(rebelDto.getGender()).getName());
        genderModel.setIdentifier(genderService.getGenderData(rebelDto.getGender()).getIdentifier());
        rebelModel.setGender(genderModel);

        rebelModel.setTraitor(false);
        rebelModel.setTraitorCount(0);
        BeanUtils.copyProperties(rebelDto, rebelModel);

        return rebelRepository.save(rebelModel);
    }

    @Transactional
    public void save(RebelModel rebelModel) {
        rebelRepository.save(rebelModel);
    }

    @Transactional
    public String saveReport(String id) {
        String message;

        RebelModel rebelModel = getRebel(id);

        if (rebelModel.getTraitor()) {
            return "The rebel is already considered a traitor!";
        }

        rebelModel.setTraitorCount(rebelModel.getTraitorCount() + 1);

        message = "Rebel reported as traitor";
        if (rebelModel.getTraitorCount() >= 3) {
            message = "The rebel is now a traitor";
            rebelModel.setTraitor(true);
        }

        rebelRepository.save(rebelModel);
        return message;
    }

    public void rebelExists(String id) throws NotFoundException {

        if (rebelRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Rebel with id " + id + " not found");
        }
    }

    public RebelModel getRebel(String id) {
        return rebelRepository.findById(id).get();
    }
}
