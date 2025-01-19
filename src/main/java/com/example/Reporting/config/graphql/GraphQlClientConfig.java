package com.example.Reporting.config.graphql;

import com.example.Reporting.exception.NoAuthorizationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQlClientConfig {

    @Bean
    public HttpGraphQlClient graphQlClient(GraphQLClientProperties graphQLClientProperties) {

        WebClient client = WebClient.builder()
                .baseUrl(graphQLClientProperties.url())
                .filter(authorizationHeaderFilter())
                .build();

        return HttpGraphQlClient.builder(client)
                .build();
    }

    private ExchangeFilterFunction authorizationHeaderFilter() {
        return (request, next) -> {
            String jwtToken = getJwtToken();

            if (jwtToken != null) {
                request = ClientRequest.from(request)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                        .build();
            }

            return next.exchange(request);
        };
    }

    private String getJwtToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (String) authentication.getPrincipal();
        }
        throw new NoAuthorizationException("Security Context can't find authentication");
    }
}
