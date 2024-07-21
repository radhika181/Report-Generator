package com.report.generator.netw.service;

import com.report.generator.netw.model.OutputRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CsvFileWriterTest {

    @Autowired
    private CsvFileWriter csvFileWriter;

    @Test
    public void testWriteOutputFile() throws IOException {
        String outputFilePath = "src/test/resources/sample-output.csv";
        OutputRecord outputRecord = new OutputRecord();
        outputRecord.setOutfield1("outValue1");
        outputRecord.setOutfield2("outValue2");
        outputRecord.setOutfield3("outValue3");
        outputRecord.setOutfield4(new BigDecimal("4"));
        outputRecord.setOutfield5(new BigDecimal("5"));

        csvFileWriter.writeOutputFile(outputFilePath, List.of(outputRecord));

        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());

        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertEquals(2, lines.size()); // header + 1 record
        assertEquals("outfield1,outfield2,outfield3,outfield4,outfield5", lines.get(0));
        assertEquals("outValue1,outValue2,outValue3,4,5", lines.get(1));
    }

}
