package com.example.Reporting.config.graphql;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("graphql.client")
public record GraphQLClientProperties(String url) {
}
