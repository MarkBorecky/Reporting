package com.example.Reporting.service;

import com.example.Reporting.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.trim;

@Service
public class CsvConverter {
    private static final String CSV_HEADER = "Portfolio's short name;Security name;Security ISIN code;Transaction's currency code;Amount;Unit price;trade amount;Type name;Transaction date;Settlement date\n";
    private static final String DELIMETER = ";";

    public String convertToCsv(List<Transaction> transactions) {
        StringBuilder content = new StringBuilder(CSV_HEADER);
        transactions.forEach(transaction -> content
                .append(trim(transaction.parentPortfolio().shortName())).append(DELIMETER)
                .append(trim(transaction.securityName())).append(DELIMETER)
                .append(trim(transaction.securityCode())).append(DELIMETER)
                .append(trim(transaction.currencyCode())).append(DELIMETER)
                .append(transaction.amount()).append(DELIMETER)
                .append(transaction.unitPrice()).append(DELIMETER)
                .append(transaction.tradeAmount()).append(DELIMETER)
                .append(trim(transaction.typeName())).append(DELIMETER)
                .append(trim(transaction.transactionDate())).append(DELIMETER)
                .append(trim(transaction.settlementDate()))
                .append("\n")
        );

        return content.toString();
    }
}
