package com.example.Reporting.controller;

import com.example.Reporting.controller.dto.ReportRequest;
import com.example.Reporting.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class ReportController {
    private final ReportService portfolioService;

    public ReportController(ReportService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping(
            value = "/reports",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @Operation(
            summary = "Generate transactions report",
            description = """
                This endpoint generates a CSV report of transactions based on the provided filters:
                - **ids**: List of portfolio IDs to filter by (e.g., `ids=1,2`).
                - **startDate**: Start date for the transactions filter.
                - **endDate**: End date for the transactions filter.
                The response is a downloadable CSV file.
            """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The report was generated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                    schema = @Schema(type = "string", format = "binary", description = "CSV file")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input parameters",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Unexpected server error",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    public ResponseEntity<byte[]> getReport(
            @Valid @ParameterObject ReportRequest request
    ) {
        byte[] csvBytes = portfolioService.prepareReport(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachement", "transactions.csv");

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/test")
    String hello() {
        return "Hello World";
    }

    @GetMapping("/secure")
    String secure() {
        return "Hello Secure World";
    }
}
