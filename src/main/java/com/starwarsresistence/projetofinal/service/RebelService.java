package com.starwarsresistence.projetofinal.service;

import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RebelService {

    @Autowired
    private RebelRepository rebelRepository;

    public List<RebelModel> findAll(){
        return rebelRepository.findAll();
    }

    @Transactional
    public RebelModel save(RebelModel rebelModel){
        return rebelRepository.save(rebelModel);
    }

}
