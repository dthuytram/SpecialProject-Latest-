package com.tramdt.dto;

import com.tramdt.dto.IDto.ValidatorFlight;
import com.tramdt.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlightValidator implements ConstraintValidator<ValidatorFlight, String> {

    @Autowired
    private IFlightService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (service.findByCodeFlight(value) > 0) {
            return false;
        }
        return true;
    }
}
