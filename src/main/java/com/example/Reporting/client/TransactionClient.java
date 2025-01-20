package com.example.Reporting.client;

import com.example.Reporting.model.Transaction;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TransactionClient {
    private final HttpGraphQlClient graphQlClient;

    public TransactionClient(HttpGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    public List<Transaction> fetchTransactionsByPortfolioId(Map<String, Object> variables) {

        String document = """
                query MyQuery($ids: [Long], $startDate: String, $endDate: String) {
                   transactions(portfolioIds: $ids, startDate: $startDate, endDate: $endDate) {
                     parentPortfolio {
                       shortName
                     }
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

        return graphQlClient.document(document)
                .variables(variables)
                .retrieveSync("transactions")
                .toEntityList(Transaction.class);
    }
}
