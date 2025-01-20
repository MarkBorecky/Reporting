package com.example.Reporting.model;

import java.util.StringJoiner;

import static java.lang.String.valueOf;
import static org.apache.commons.lang3.StringUtils.trim;

public record Transaction(
        String portfolioShortName,
        String securityCode,
        String securityName,
        String currencyCode,
        int amount,
        float unitPrice,
        int tradeAmount,
        String typeName,
        String transactionDate,
        String settlementDate
) implements CsvMappable {

    @Override
    public String mapToCsvRow(String delimiter) {
        return new StringJoiner(delimiter)
                .add(trim(portfolioShortName))
                .add(trim(securityCode))
                .add(trim(securityName))
                .add(trim(currencyCode))
                .add(valueOf(amount))
                .add(valueOf(unitPrice))
                .add(valueOf(typeName))
                .add(trim(typeName))
                .add(trim(transactionDate))
                .add(trim(settlementDate))
                .toString();
    }
}
