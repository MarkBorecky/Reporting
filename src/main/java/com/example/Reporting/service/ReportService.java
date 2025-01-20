package com.example.Reporting.service;

import com.example.Reporting.client.TransactionClient;
import com.example.Reporting.controller.dto.ReportRequest;
import com.example.Reporting.model.Transaction;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ReportService {

    private final TransactionClient transactionClient;
    private final CsvConverter csvConverter;

    public ReportService(TransactionClient transactionClient, CsvConverter csvConverter) {
        this.transactionClient = transactionClient;
        this.csvConverter = csvConverter;
    }

    public byte[] prepareReport(ReportRequest request) {
        List<Transaction> transactions = transactionClient.fetchTransactionsByPortfolioId(request);
        String content = csvConverter.convertToCsv(transactions);

        return content.getBytes(StandardCharsets.UTF_8);
    }
}
