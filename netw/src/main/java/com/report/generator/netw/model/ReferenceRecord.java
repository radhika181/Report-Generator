package com.report.generator.netw.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReferenceRecord {

    @CsvBindByName
    private String refkey1;

    @CsvBindByName
    private String refdata1;

    @CsvBindByName
    private String refkey2;

    @CsvBindByName
    private String refdata2;

    @CsvBindByName
    private String refdata3;

    @CsvBindByName
    private BigDecimal refdata4;

}
