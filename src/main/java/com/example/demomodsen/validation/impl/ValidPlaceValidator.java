package com.example.demomodsen.validation.impl;

import com.example.demomodsen.database.repository.PlaceRepository;
import com.example.demomodsen.validation.ValidPlace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ValidPlaceValidator implements ConstraintValidator<ValidPlace, Integer> {

    private final PlaceRepository placeRepository;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return placeRepository.findById(value).isPresent();
    }
}
