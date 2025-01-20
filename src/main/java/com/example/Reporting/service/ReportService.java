package com.example.Reporting.service;

import com.example.Reporting.client.TransactionClient;
//import com.example.Reporting.controller.dto.ReportDetails;
import com.example.Reporting.controller.dto.ReportDetails;
import com.example.Reporting.model.Transaction;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReportService {

    private final TransactionClient transactionClient;
    private final CsvConverter csvConverter;

    public ReportService(TransactionClient transactionClient, CsvConverter csvConverter) {
        this.transactionClient = transactionClient;
        this.csvConverter = csvConverter;
    }

    public byte[] prepareReport(ReportDetails reportDetails) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("ids", reportDetails.ids());
        LocalDate startDate = null;
        if (Objects.nonNull(startDate)) {
            variables.put("startDate", startDate);
        }
        LocalDate endDate = null;
        if (Objects.nonNull(endDate)) {
            variables.put("endDate", endDate);
        }

        List<Transaction> transactions = transactionClient.fetchTransactionsByPortfolioId(variables);
        String content = csvConverter.convertToCsv(transactions);

        return content.getBytes(StandardCharsets.UTF_8);
    }
}
