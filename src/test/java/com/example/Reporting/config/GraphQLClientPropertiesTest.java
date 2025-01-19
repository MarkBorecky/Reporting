package com.example.Reporting.config;

import com.example.Reporting.config.graphql.GraphQLClientProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraphQLClientPropertiesTest {

    @Autowired
    GraphQLClientProperties graphQLClientProperties;

    @Test
    void shouldLoadProperties() {
        Assertions.assertThat(graphQLClientProperties.url()).startsWith("https://");
    }
}