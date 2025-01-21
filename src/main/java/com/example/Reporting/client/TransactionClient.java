package com.example.Reporting.client;

import com.example.Reporting.controller.dto.ReportRequest;
import com.example.Reporting.exception.DataFetchException;
import com.example.Reporting.model.Transaction;
import org.springframework.graphql.client.FieldAccessException;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionClient {
    private final HttpGraphQlClient graphQlClient;

    public TransactionClient(HttpGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    public List<Transaction> fetchTransactionsByPortfolioId(ReportRequest request) {

        String document = """
                query MyQuery($ids: [Long], $startDate: String, $endDate: String) {
                  transactions(portfolioIds: $ids, startDate: $startDate, endDate: $endDate) {
                     portfolioShortName
                     securityCode
                     securityName
                     currencyCode
                     amount
                     unitPrice
                     tradeAmount
                     typeName
                     transactionDate
                     settlementDate
                   }
                 }
                """;

        try {
            return graphQlClient
                    .document(document)
                    .variables(request.mapToVariables())
                    .retrieveSync("transactions")
                    .toEntityList(Transaction.class);
        } catch (FieldAccessException exception) {
            throw new DataFetchException("Problem with fetching data " + exception.getMessage(), exception);
        }
    }
}
