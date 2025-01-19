package com.example.Reporting.controller.validation;

import com.example.Reporting.controller.dto.DateRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, DateRange> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(DateRange value, ConstraintValidatorContext context) {
        LocalDate startDate = value.startDate();
        LocalDate endDate = value.endDate();
        if (isNull(value) || isNull(startDate) || isNull(endDate)) {
            return true;
        }
//        return !value.startDate().isAfter(value.endDate());
        return false;
    }
}
