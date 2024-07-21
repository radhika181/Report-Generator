package com.report.generator.netw.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.report.generator.netw.model.InputRecord;
import com.report.generator.netw.model.ReferenceRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class CsvFileReader {


    public List<InputRecord> readInputFile(String filePath) throws IOException {
        log.info("Reading input file: {}", filePath);
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            CsvToBean<InputRecord> csvToBean = new CsvToBeanBuilder<InputRecord>(reader)
                    .withType(InputRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }

    public List<ReferenceRecord> readReferenceFile(String filePath) throws IOException {
        log.info("Reading reference file: {}", filePath);
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            CsvToBean<ReferenceRecord> csvToBean = new CsvToBeanBuilder<ReferenceRecord>(reader)
                    .withType(ReferenceRecord.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }

}
