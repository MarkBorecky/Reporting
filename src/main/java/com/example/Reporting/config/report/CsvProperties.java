package com.example.Reporting.config.report;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("report.csv")
public record CsvProperties(
        String delimiter,
        String header
) {
}
