package com.report.generator.netw.service;

import com.report.generator.netw.model.InputRecord;
import com.report.generator.netw.model.ReferenceRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CsvFileReaderTest {

    @Autowired
    private CsvFileReader csvFileReader;

    @Test
    public void testReadInputFile() throws IOException {
        String inputFilePath = "src/test/resources/sample-input.csv";
        List<InputRecord> inputRecords = csvFileReader.readInputFile(inputFilePath);
        assertNotNull(inputRecords);
        assertFalse(inputRecords.isEmpty());
        assertEquals("value1", inputRecords.get(0).getField1());
    }

    @Test
    public void testReadReferenceFile() throws IOException {
        String referenceFilePath = "src/test/resources/sample-reference.csv";
        List<ReferenceRecord> referenceRecords = csvFileReader.readReferenceFile(referenceFilePath);
        assertNotNull(referenceRecords);
        assertFalse(referenceRecords.isEmpty());
        assertEquals("refValue1", referenceRecords.get(0).getRefkey1());
    }

}
