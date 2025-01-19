package com.example.Reporting.service;

import com.example.Reporting.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsvConverter {
    private static final String CSV_HEADER = "Portfolio's short name;Security name;Security ISIN code;Transaction's currency code;Amount;Unit price;trade amount;Type name;Transaction date;Settlement date\n";
    private static final String DELIMETER = ";";

    public String convertToCsv(List<Transaction> transactions) {
        StringBuilder content = new StringBuilder(CSV_HEADER);
        transactions.forEach(transaction -> content
                .append(transaction.parentPortfolio().id()).append(DELIMETER)
                .append(transaction.securityName()).append(DELIMETER)
                .append(transaction.securityCode()).append(DELIMETER)
                .append(transaction.currencyCode()).append(DELIMETER)
                .append(transaction.amount()).append(DELIMETER)
                .append(transaction.unitPrice()).append(DELIMETER)
                .append(transaction.tradeAmount()).append(DELIMETER)
                .append(transaction.typeName()).append(DELIMETER)
                .append(transaction.transactionDate()).append(DELIMETER)
                .append(transaction.settlementDate())
                .append("\n")
        );

        return content.toString();
    }
}
