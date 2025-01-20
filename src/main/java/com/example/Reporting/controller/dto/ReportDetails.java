package com.example.Reporting.controller.dto;

import com.example.Reporting.controller.validation.ValidDateRange;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@ValidDateRange
public record ReportDetails(
        @NotNull
        @Size(min = 1, message = "There should be at list one id in parameters")
        List<Integer> ids,
        LocalDate startDate,
        LocalDate endDate
) {
}
