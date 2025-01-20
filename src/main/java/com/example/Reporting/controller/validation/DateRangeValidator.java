package com.example.Reporting.controller.validation;

import com.example.Reporting.controller.dto.ReportDetails;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, ReportDetails> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ReportDetails value, ConstraintValidatorContext context) {
        if (isNull(value) || isNull(value.startDate()) || isNull(value.endDate())) {
            return true;
        }
        return !value.startDate().isAfter(value.endDate());
    }
}
