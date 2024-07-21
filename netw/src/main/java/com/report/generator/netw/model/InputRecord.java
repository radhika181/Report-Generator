package com.report.generator.netw.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InputRecord {

    @CsvBindByName
    private String field1;

    @CsvBindByName
    private String field2;

    @CsvBindByName
    private String field3;

    @CsvBindByName
    private BigDecimal field4;

    @CsvBindByName
    private BigDecimal field5;

    @CsvBindByName
    private String refkey1;

    @CsvBindByName
    private String refkey2;

}
