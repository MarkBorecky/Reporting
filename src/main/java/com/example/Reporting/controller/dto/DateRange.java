package com.example.Reporting.controller.dto;

import com.example.Reporting.controller.validation.ValidDateRange;

import java.time.LocalDate;

@ValidDateRange
public record DateRange(
    LocalDate startDate,
    LocalDate endDate
) {
}
