package com.example.demomodsen.validation.impl;

import com.example.demomodsen.database.repository.OrganizerRepository;
import com.example.demomodsen.validation.ValidOrganizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ValidOrganizerValidator implements ConstraintValidator<ValidOrganizer, Integer> {

    private final OrganizerRepository organizerRepository;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return organizerRepository.findById(value).isPresent();
    }
}
