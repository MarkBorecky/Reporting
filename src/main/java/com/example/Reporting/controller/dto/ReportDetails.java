package com.example.Reporting.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ReportDetails(
        @NotNull
        @Size(min = 1, message = "There should be at list one id in parameters")
        List<Integer> ids,
        @Valid
        DateRange dateRange
) {
}
