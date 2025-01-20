package com.example.Reporting.controller.dto;

import com.example.Reporting.controller.validation.ValidDateRange;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@ValidDateRange
public record ReportRequest(
        @NotNull
        @Size(min = 1, message = "There should be at list one id in parameters")
        @Parameter(
                description = "List of portfolio IDs to filter by (e.g., ids=1,2,3)",
                explode = Explode.TRUE,
                style = ParameterStyle.MATRIX
        )
        List<Integer> ids,

        @Parameter(
                description = "Start date for the transactions filter (format: yyyy-MM-dd)",
                example = "2020-01-01"
        )
        @RequestParam
        LocalDate startDate,

        @Parameter(
                description = "End date for the transactions filter (format: yyyy-MM-dd)",
                example = "2020-12-31"
        )
        @RequestParam
        LocalDate endDate
) {
        public Map<String, Object> mapToVariables() {
                return Map.of(
                        "ids", ids,
                        "startDate", startDate.toString(),
                        "endDate", endDate.toString()
                );
        }
}
