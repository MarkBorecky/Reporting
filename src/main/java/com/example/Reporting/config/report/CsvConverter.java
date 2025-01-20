package com.example.Reporting.config.report;

import com.example.Reporting.model.CsvMappable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvConverter {
    private final CsvProperties csvProperties;

    public CsvConverter(CsvProperties csvProperties) {
        this.csvProperties = csvProperties;
    }

    public String convertToCsv(List<? extends CsvMappable> data) {

        String delimiter = csvProperties.delimiter();

        String rows = data.stream()
                .map(value -> value.mapToCsvRow(delimiter))
                .collect(Collectors.joining(StringUtils.LF));

        return StringUtils.join(
                csvProperties.header(),
                StringUtils.LF,
                rows
        );
    }
}
