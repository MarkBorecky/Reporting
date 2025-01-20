package com.example.Reporting.controller;

import com.example.Reporting.controller.dto.ReportDetails;
import com.example.Reporting.service.ReportService;
import jakarta.validation.Valid;
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
    public ResponseEntity<byte[]> foo(@Valid ReportDetails reportDetails) {
        byte[] csvBytes = portfolioService.prepareReport(reportDetails);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachement", "transactions.csv");

        return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
    }
}
