package com.starwarsresistence.projetofinal.validation.NotTraitor;

import com.starwarsresistence.projetofinal.model.RebelModel;
import com.starwarsresistence.projetofinal.service.RebelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotTraitorValidator implements ConstraintValidator<NotTraitor, String> {

    @Autowired
    private RebelService rebelService;

    @Override
    public void initialize(NotTraitor constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        RebelModel rebelModel = rebelService.getRebel(id);
        return !rebelModel.getTraitor();
    }
}
