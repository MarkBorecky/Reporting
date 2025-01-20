package com.example.Reporting;

import com.example.Reporting.config.graphql.GraphQLClientProperties;
import com.example.Reporting.config.report.CsvProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		GraphQLClientProperties.class,
		CsvProperties.class
})
public class ReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingApplication.class, args);
	}

}
