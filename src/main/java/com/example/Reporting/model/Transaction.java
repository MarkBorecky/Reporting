package com.example.Reporting.model;

public record Transaction(
        Portfolio parentPortfolio,
        String securityCode,
        String securityName,
        String currencyCode,
        int amount,
        float unitPrice,
        int tradeAmount,
        String typeName,
        String transactionDate,
        String settlementDate
) {
}
